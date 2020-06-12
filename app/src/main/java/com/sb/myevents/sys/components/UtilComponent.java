package com.sb.myevents.sys.components;

import android.content.Context;

import com.sb.myevents.sys.modules.UtilModule;
import com.sb.myevents.sys.util.AppExecutors;
import com.sb.myevents.sys.util.ResourceProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.components
 * My Events
 */
@Singleton
@Component(modules = UtilModule.class)
public interface UtilComponent {

    AppExecutors getAppExecutors();

    ResourceProvider getResourceProvider();

    Context getContext();

}
