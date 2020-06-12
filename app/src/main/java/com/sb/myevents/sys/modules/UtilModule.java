package com.sb.myevents.sys.modules;

import android.content.Context;

import com.sb.myevents.sys.util.AppExecutors;
import com.sb.myevents.sys.util.ResourceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.modules
 * My Events
 */
@Module(includes = ContextModule.class)
public class UtilModule {

    @SuppressWarnings("WeakerAccess")
    @Provides
    @Singleton
    public AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public ResourceProvider provideResourceProvider(Context context) {
        return new ResourceProvider(context);
    }

}
