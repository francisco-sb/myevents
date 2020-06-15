package com.sb.myevents.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    //region Dependencies Injection
    @Inject
    MainViewModel viewModel;
    //endregion

    //region Navigation
    public static final String LOGIN = "login";
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

    //region:: PRIVATE METHODS
    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
    }
    //endregion

    //region:: PUBLIC METHODS
    public void navigateTo(String tag) {
        if (NEW_USER.equals(tag)) {
            navController.navigate(R.id.userFragment);
        } else if (MY_EVENTS.equals(tag)) {
            navController.navigate(R.id.myEventsFragment);
        } else if ((EVENT.equals(tag))) {
            navController.navigate(R.id.eventFragment);
        } else if (LOGIN.equals(tag)) {
            navController.navigate(R.id.loginFragment);
        }
    }
    //endregion
}
