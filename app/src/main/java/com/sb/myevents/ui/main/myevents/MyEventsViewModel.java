package com.sb.myevents.ui.main.myevents;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sb.myevents.R;
import com.sb.myevents.domain.repositories.EventRepository;
import com.sb.myevents.domain.repositories.UserRepository;
import com.sb.myevents.sys.components.DaggerRepositoryComponent;
import com.sb.myevents.sys.util.ReactiveEvent;
import com.sb.myevents.sys.util.ResourceProvider;
import com.sb.myevents.ui.MainApp;

import javax.inject.Inject;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.myevents
 * My Events
 */
public class MyEventsViewModel extends ViewModel {

    @Inject
    UserRepository userRepository;

    @Inject
    EventRepository eventRepository;

    private ResourceProvider resourceProvider;

    ReactiveEvent<String> onSignOutAction;
    MutableLiveData<Object> onEvents;

    public MyEventsViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);

        resourceProvider = MainApp.utilComponent.getResourceProvider();

        onSignOutAction = new ReactiveEvent<>();
        onEvents = new MutableLiveData<>();
    }

    void signOut() {
        userRepository.logout(this::onSignOut);
    }

    void getEvents() {
        eventRepository.getEvents(this::onGetEvents);
    }

    //region:: REFERENCE METHODS
    private void onSignOut(Boolean result) {
        if (Boolean.TRUE.equals(result))
            onSignOutAction.postValue(resourceProvider.getString(R.string.session_finalized_message));
    }

    private void onGetEvents(Object events) {
        if (events != null)
            onEvents.postValue(events);
    }
    //endregion
}
