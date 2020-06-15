package com.sb.myevents.domain.repositories;

import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sb.myevents.data.entities.Event;
import com.sb.myevents.sys.util.AppExecutors;
import com.sb.myevents.ui.MainApp;

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
    private static final String EVENTS = "events";

    public EventRepository() {
        executors = MainApp.utilComponent.getAppExecutors();

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    public void createEvent(Event event) {

    }

    public void getEvents(Observer<List<Event>> observer) {
        executors.networkIO().execute(() -> {
            FirebaseUser user = auth.getCurrentUser();
            String userId = "";

            if (user != null) {
                userId = user.getUid();
            }

        });
    }
}
