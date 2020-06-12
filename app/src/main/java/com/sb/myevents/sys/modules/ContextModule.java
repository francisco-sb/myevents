package com.sb.myevents.sys.modules;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.modules
 * My Events
 */
@Module
public class ContextModule {

    private FragmentActivity fragmentActivity;
    private Fragment fragment;
    private Context context;

    public ContextModule(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public ContextModule(Fragment fragment) {
        this.fragment = fragment;
    }

    public ContextModule(Context context) {
        this.context = context;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public FragmentActivity provideFragmentActivity() {
        return this.fragmentActivity;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public Fragment provideFragment() {
        return this.fragment;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    public Context provideContext() {
        return this.context;
    }

}
