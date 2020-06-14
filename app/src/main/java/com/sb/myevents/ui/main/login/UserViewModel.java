package com.sb.myevents.ui.main.login;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.sb.myevents.R;
import com.sb.myevents.data.entities.User;
import com.sb.myevents.domain.repositories.UserRepository;
import com.sb.myevents.sys.components.DaggerRepositoryComponent;
import com.sb.myevents.sys.util.ReactiveEvent;
import com.sb.myevents.sys.util.ResourceProvider;
import com.sb.myevents.ui.MainApp;

import javax.inject.Inject;

public class UserViewModel extends ViewModel {

    @Inject
    UserRepository repository;

    private ResourceProvider resourceProvider;

    ReactiveEvent<String> onSignUpFailed;
    ReactiveEvent<String> onSignUpSuccessful;

    public UserViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);

        resourceProvider = MainApp.utilComponent.getResourceProvider();

        onSignUpFailed = new ReactiveEvent<>();
        onSignUpSuccessful = new ReactiveEvent<>();
    }

    void createUser(String name, String lastname, String phone, String email, String pass) {
        if (!name.isEmpty() && !lastname.isEmpty()  && !phone.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
            User user = new User(name, lastname, phone, email, pass);

            repository.createUser(user, this::onSignUp);
            repository.saveUser(user);
        } else {
            onSignUpFailed.postValue(resourceProvider.getString(R.string.fields_required_message));
        }
    }

    //region:: REFERENCE METHODS
    private void onSignUp(Object o) {
        if (o instanceof FirebaseUser)
            onSignUpSuccessful.postValue(resourceProvider.getString(R.string.user_created_message));
        else
            onSignUpFailed.postValue(resourceProvider.getString(R.string.user_not_created_message));
    }
    //endregion

}
