package com.sb.myevents.domain.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sb.myevents.data.entities.Event;
import com.sb.myevents.sys.util.AppExecutors;
import com.sb.myevents.ui.MainApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.domain.repositories
 * My Events
 */
public class EventRepository {

    private AppExecutors executors;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    public EventRepository() {
        executors = MainApp.utilComponent.getAppExecutors();

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path = "users/" + getUid() + "/events";
        databaseReference = database.getReference().child(path);
    }

    private String getUid() {
        FirebaseUser user = auth.getCurrentUser();
        String uid = "";

        if (user != null) {
            uid = user.getUid();
        }

        return uid;
    }

    public void createEvent(Event event, Observer<Boolean> observer) {
        executors.networkIO().execute(() -> {
            String key = databaseReference.push().getKey();

            assert key != null;
            databaseReference.child(key).setValue(event);
            observer.onChanged(true);
        });
    }

    public void getEvents(Observer<Object> observer) {
        executors.networkIO().execute(() -> databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Event> events = new ArrayList<>();
                for (DataSnapshot item: dataSnapshot.getChildren()) {
                    Event event = item.getValue(Event.class);

                    if (event != null) {
                        event.setId(item.getKey());
                        events.add(item.getValue(Event.class));
                    }
                }

                observer.onChanged(events);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                observer.onChanged(databaseError.getMessage());
            }
        }));
    }
}
