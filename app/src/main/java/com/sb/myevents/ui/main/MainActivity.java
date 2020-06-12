package com.sb.myevents.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    //region Dependencies Injection
    @Inject
    MainViewModel viewModel;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Dagger
        DaggerViewModelComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        //endregion
    }
}
