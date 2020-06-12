package com.sb.myevents.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;

import java.util.Objects;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    //region Dependencies Injection
    @Inject
    MainViewModel viewModel;
    //endregion

    //region Navigation
    public static final String NEW_USER = "new_user";
    public static final String MY_EVENTS = "my_events";
    public static final String EVENT = "event";
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Dagger
        DaggerViewModelComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        //endregion

        setupNavigation();
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
    }

    public void navigateTo(String tag) {
        if (NEW_USER.equals(tag))
            navController.navigate(R.id.userFragment);

        if (MY_EVENTS.equals(tag))
            navController.navigate(R.id.myEventsFragment);
    }
}
