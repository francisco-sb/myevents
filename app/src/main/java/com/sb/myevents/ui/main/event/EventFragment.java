package com.sb.myevents.ui.main.event;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;

import javax.inject.Inject;

public class EventFragment extends Fragment {

    //region Dependencies Injection
    @Inject
    EventViewModel viewModel;
    //endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region Dagger
        DaggerViewModelComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        //endregion
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_fragment, container, false);
    }

}
