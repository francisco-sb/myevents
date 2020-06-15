package com.sb.myevents.ui.main.login;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.sb.myevents.R;
import com.sb.myevents.domain.repositories.UserRepository;
import com.sb.myevents.sys.components.DaggerRepositoryComponent;
import com.sb.myevents.sys.util.ReactiveEvent;
import com.sb.myevents.sys.util.ResourceProvider;
import com.sb.myevents.ui.MainApp;

import javax.inject.Inject;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.login
 * My Events
 */
public class LoginViewModel extends ViewModel {

    @Inject
    UserRepository repository;

    private ResourceProvider resourceProvider;

    ReactiveEvent<String> onSignInFailed;
    ReactiveEvent<Boolean> onSignInSuccessful;
    ReactiveEvent<String> onMessage;

    public LoginViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);

        resourceProvider = MainApp.utilComponent.getResourceProvider();

        onSignInFailed = new ReactiveEvent<>();
        onSignInSuccessful = new ReactiveEvent<>();
        onMessage = new ReactiveEvent<>();
    }

    void signIn(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty())
            repository.signInUser(email, password, this::onSignIn);
        else
            onMessage.postValue(resourceProvider.getString(R.string.empty_string_message));
    }

    //region:: REFERENCE METHODS
    private void onSignIn(Object o) {
        if (o instanceof FirebaseUser)
            onSignInSuccessful.callOnThread();
        else
            onSignInFailed.postValue(resourceProvider.getString(R.string.auth_failed_message));
    }
    //endregion
}
