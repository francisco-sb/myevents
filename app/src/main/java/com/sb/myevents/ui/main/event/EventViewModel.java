package com.sb.myevents.ui.main.event;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.sys.components.DaggerRepositoryComponent;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.event
 * My Events
 */
public class EventViewModel extends ViewModel {

    public EventViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }
}
