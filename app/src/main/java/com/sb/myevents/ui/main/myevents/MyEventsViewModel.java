package com.sb.myevents.ui.main.myevents;

import androidx.lifecycle.ViewModel;

import com.sb.myevents.sys.components.DaggerRepositoryComponent;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui.main.myevents
 * My Events
 */
public class MyEventsViewModel extends ViewModel {

    public MyEventsViewModel() {
        DaggerRepositoryComponent.builder()
                .build().inject(this);
    }

}
