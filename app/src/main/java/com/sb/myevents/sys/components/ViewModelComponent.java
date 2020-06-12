package com.sb.myevents.sys.components;

import com.sb.myevents.sys.modules.ViewModelModule;
import com.sb.myevents.ui.main.MainActivity;
import com.sb.myevents.ui.main.event.EventFragment;
import com.sb.myevents.ui.main.myevents.MyEventsFragment;
import com.sb.myevents.ui.main.login.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.components
 * My Events
 */
@Singleton
@Component(modules = ViewModelModule.class)
public interface ViewModelComponent {

    void inject(MainActivity view);

    void inject(LoginFragment view);

    void inject(MyEventsFragment view);

    void inject(EventFragment view);

}
