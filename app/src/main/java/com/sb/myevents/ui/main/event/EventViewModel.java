package com.sb.myevents.ui.main.event;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.domain.repositories.EventRepository;
import com.sb.myevents.sys.components.DaggerRepositoryComponent;

import javax.inject.Inject;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.event
 * My Events
 */
public class EventViewModel extends ViewModel {

    @Inject
    EventRepository repository;

    public EventViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }
}
