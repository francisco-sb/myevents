package com.sb.myevents.ui.main.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;
import com.sb.myevents.ui.main.MainActivity;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    //region Dependencies Injection
    @Inject
    LoginViewModel viewModel;
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
        View rootView =  inflater.inflate(R.layout.login_fragment, container, false);

        MaterialTextView registerButton = rootView.findViewById(R.id.register_textView);
        MaterialButton signInButton = rootView.findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(v -> {
            assert  getActivity() != null;
            ((MainActivity) getActivity()).navigateTo(MainActivity.MY_EVENTS);
        });

        registerButton.setOnClickListener(v -> {
            assert getActivity() != null;
            ((MainActivity) getActivity()).navigateTo(MainActivity.NEW_USER);
        });

        return rootView;
    }

}
