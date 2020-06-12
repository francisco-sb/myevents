package com.sb.myevents.ui;

import android.app.Application;

import com.sb.myevents.sys.components.DaggerUtilComponent;
import com.sb.myevents.sys.components.UtilComponent;
import com.sb.myevents.sys.modules.ContextModule;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.ui
 * My Events
 */
public class MainApp extends Application {

    public static UtilComponent utilComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        utilComponent = DaggerUtilComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

}
