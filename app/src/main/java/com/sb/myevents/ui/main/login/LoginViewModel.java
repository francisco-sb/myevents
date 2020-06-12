package com.sb.myevents.ui.main.login;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.sys.components.DaggerRepositoryComponent;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.login
 * My Events
 */
public class LoginViewModel extends ViewModel {

    public LoginViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }

}
