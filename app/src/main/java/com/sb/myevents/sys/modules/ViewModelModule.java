package com.sb.myevents.sys.modules;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.sb.myevents.ui.main.MainViewModel;
import com.sb.myevents.ui.main.event.EventViewModel;
import com.sb.myevents.ui.main.myevents.MyEventsViewModel;
import com.sb.myevents.ui.main.login.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.modules
 * My Events
 */
@Module(includes = ContextModule.class)
public class ViewModelModule {

    @SuppressWarnings("WeakerAccess")
    @Provides
    public MainViewModel provideMainViewModel(FragmentActivity fragmentActivity) {
        return ViewModelProviders.of(fragmentActivity).get(MainViewModel.class);
    }

    @Provides
    public LoginViewModel provideLoginViewModel(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(LoginViewModel.class);
    }

    @Provides
    public MyEventsViewModel provideMyEventsViewModel(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(MyEventsViewModel.class);
    }

    @Provides
    public EventViewModel provideEventViewModel(Fragment fragment) {
        return ViewModelProviders.of(fragment).get(EventViewModel.class);
    }

}
