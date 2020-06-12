package com.sb.myevents.sys.components;

import com.sb.myevents.sys.modules.RepositoryModule;
import com.sb.myevents.ui.main.MainViewModel;
import com.sb.myevents.ui.main.event.EventViewModel;
import com.sb.myevents.ui.main.login.UserViewModel;
import com.sb.myevents.ui.main.myevents.MyEventsViewModel;
import com.sb.myevents.ui.main.login.LoginViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.components
 * My Events
 */
@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {

    void inject(MainViewModel viewModel);

    void inject(LoginViewModel viewModel);

    void inject(UserViewModel viewModel);

    void inject(MyEventsViewModel viewModel);

    void inject(EventViewModel viewModel);

}
