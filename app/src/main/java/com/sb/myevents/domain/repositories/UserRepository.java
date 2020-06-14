package com.sb.myevents.domain.repositories;

import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseAuth;
import com.sb.myevents.data.entities.User;
import com.sb.myevents.sys.util.AppExecutors;
import com.sb.myevents.ui.MainApp;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.domain.repositories
 * My Events
 */
public class UserRepository {

    private AppExecutors executors;

    private FirebaseAuth auth;

    public UserRepository() {
        executors = MainApp.utilComponent.getAppExecutors();
        auth = FirebaseAuth.getInstance();
    }

    public void isUserSigned(Observer<Object> observer) {
        executors.networkIO().execute(() -> observer.onChanged(auth.getCurrentUser()));
    }

    public void logout(Observer<Boolean> observer) {
        executors.networkIO().execute(() -> {
            auth.signOut();
            observer.onChanged(true);
        });
    }

    public void createUser(User user, Observer<Object> observer) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(executors.networkIO(), task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                observer.onChanged(auth.getCurrentUser());
            } else {
                // If sign in fails, display a message to the user.
                observer.onChanged(null);
            }
        });
    }

    public void signInUser(String email, String password, Observer<Object> observer) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(executors.networkIO(), task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                observer.onChanged(auth.getCurrentUser());
            } else {
                // If sign in fails, display a message to the user.
                observer.onChanged(null);
            }
        });
    }
}
