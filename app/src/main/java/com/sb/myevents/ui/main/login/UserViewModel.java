package com.sb.myevents.ui.main.login;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.sys.components.DaggerRepositoryComponent;

public class UserViewModel extends ViewModel {

    public UserViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }

}
