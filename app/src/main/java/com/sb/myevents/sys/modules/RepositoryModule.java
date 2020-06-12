package com.sb.myevents.sys.modules;

import com.sb.myevents.domain.repositories.EventRepository;
import com.sb.myevents.domain.repositories.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.modules
 * My Events
 */
@Module
public class RepositoryModule {

    @SuppressWarnings("WeakerAccess")
    @Provides
    @Singleton
    public UserRepository provideUserRepository() {
        return new UserRepository();
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    @Singleton
    public EventRepository provideEventRepository() {
        return new EventRepository();
    }

}
