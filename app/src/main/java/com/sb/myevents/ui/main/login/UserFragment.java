package com.sb.myevents.ui.main.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;
import com.sb.myevents.ui.main.MainActivity;

import java.util.Objects;

import javax.inject.Inject;

public class UserFragment extends Fragment {

    //region Dependencies Injection
    @Inject
    UserViewModel viewModel;
    //endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region Dagger
        DaggerViewModelComponent.builder()
                .contextModule(new ContextModule(this))
                .build().inject(this);
        //endregion
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_fragment, container, false);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).show();

        TextInputEditText nameEditText = rootView.findViewById(R.id.name_editText);
        TextInputEditText lastnameEditText = rootView.findViewById(R.id.lastname_editText);
        TextInputEditText phoneEditText = rootView.findViewById(R.id.phone_editText);
        TextInputEditText emailEditText = rootView.findViewById(R.id.email_editText);
        TextInputEditText passwordEditText = rootView.findViewById(R.id.password_editText);

        MaterialButton registerButton = rootView.findViewById(R.id.register_button);

        observeStreams();

        registerButton.setOnClickListener(view ->
                viewModel.createUser(
                        Objects.requireNonNull(nameEditText.getText()).toString(),
                        Objects.requireNonNull(lastnameEditText.getText()).toString(),
                        Objects.requireNonNull(phoneEditText.getText()).toString(),
                        Objects.requireNonNull(emailEditText.getText()).toString(),
                        Objects.requireNonNull(passwordEditText.getText()).toString()
                )
        );

        return rootView;
    }

    //region:: PRIVATE METHODS
    private void observeStreams() {
        viewModel.onSignUpFailed.observe(this, this::signUpFailed);
        viewModel.onSignUpSuccessful.observe(this, this::signUpSuccessful);
    }
    //endregion

    //region:: REFERENCE METHODS
    private void signUpFailed(Object o) {
        Toast.makeText(getContext(), (String) o, Toast.LENGTH_SHORT).show();
    }

    private void signUpSuccessful(Object o) {
        Toast.makeText(getContext(), (String) o, Toast.LENGTH_SHORT).show();

        assert getActivity() != null;
        ((MainActivity) getActivity()).navigateTo(MainActivity.MY_EVENTS); //NOSONAR

    }
    //endregion

}
