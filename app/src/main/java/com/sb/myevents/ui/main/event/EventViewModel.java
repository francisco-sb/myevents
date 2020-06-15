package com.sb.myevents.ui.main.event;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.R;
import com.sb.myevents.data.entities.Event;
import com.sb.myevents.domain.repositories.EventRepository;
import com.sb.myevents.sys.components.DaggerRepositoryComponent;
import com.sb.myevents.sys.util.ReactiveEvent;
import com.sb.myevents.sys.util.ResourceProvider;
import com.sb.myevents.ui.MainApp;

import javax.inject.Inject;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.event
 * My Events
 */
public class EventViewModel extends ViewModel {

    @Inject
    EventRepository repository;

    private ResourceProvider resourceProvider;

    ReactiveEvent<String> onEventCreateSuccessful;
    ReactiveEvent<String> onEventCreateFailed;

    public EventViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);

        resourceProvider = MainApp.utilComponent.getResourceProvider();

        onEventCreateSuccessful = new ReactiveEvent<>();
        onEventCreateFailed = new ReactiveEvent<>();
    }

    void createEvent(String name, String place, String date, String time) {
        if (!name.isEmpty() && !place.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
            Event event = new Event(name, place, date, time);

            repository.createEvent(event, this::onEventCreate);
        } else {
            onEventCreateFailed.postValue(resourceProvider.getString(R.string.fields_required_message));
        }
    }

    //region:: REFERENCE METHODS
    private void onEventCreate(Boolean result) {
        if (Boolean.TRUE.equals(result))
            onEventCreateSuccessful.postValue(resourceProvider.getString(R.string.event_created_message));
    }
    //endregion
}
