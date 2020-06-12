package com.sb.myevents.domain.repositories;

import com.sb.myevents.sys.util.AppExecutors;
import com.sb.myevents.ui.MainApp;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.domain.repositories
 * My Events
 */
public class UserRepository {

    private AppExecutors executors;

    public UserRepository() {
        executors = MainApp.utilComponent.getAppExecutors();
    }
}
