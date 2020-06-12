package com.sb.myevents.ui.main;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.sys.components.DaggerRepositoryComponent;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main
 * My Events
 */
public class MainViewModel extends ViewModel {

    public MainViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }
}
