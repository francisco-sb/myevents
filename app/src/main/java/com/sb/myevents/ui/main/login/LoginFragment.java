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
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;
import com.sb.myevents.ui.main.MainActivity;

import java.util.Objects;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    //region Dependencies Injection
    @Inject
    LoginViewModel viewModel;
    //endregion

    //region Views
    private TextInputLayout passwordLayout;

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
        View rootView =  inflater.inflate(R.layout.login_fragment, container, false);

        MaterialTextView registerButton = rootView.findViewById(R.id.register_textView);
        MaterialButton signInButton = rootView.findViewById(R.id.sign_in_button);

        passwordLayout = rootView.findViewById(R.id.password_layout);
        TextInputEditText emailEditText = rootView.findViewById(R.id.email_editText);
        TextInputEditText passwordEditText = rootView.findViewById(R.id.password_editText);

        observeStreams();

        signInButton.setOnClickListener(v -> {
            if (passwordLayout.isErrorEnabled())
                passwordLayout.setErrorEnabled(false);

            viewModel.signIn(Objects.requireNonNull(emailEditText.getText()).toString(), Objects.requireNonNull(passwordEditText.getText()).toString());
        });

        registerButton.setOnClickListener(v -> {
            assert getActivity() != null;
            ((MainActivity) getActivity()).navigateTo(MainActivity.NEW_USER); //NOSONAR
        });

        return rootView;
    }

    //region:: PRIVATE METHODS
    private void observeStreams() {
        viewModel.onSignInFailed.observe(this, this::signInFailed);
        viewModel.onSignInSuccessful.observe(this, this::signInSuccessful);
        viewModel.onMessage.observe(this, this::emptyField);
    }
    //endregion

    //region:: REFERENCE METHODS
    private void signInFailed(Object o) {
        Toast.makeText(getContext(), (CharSequence) o, Toast.LENGTH_SHORT).show();
    }

    private void signInSuccessful(@SuppressWarnings("unused") Object o) {
        assert  getActivity() != null;
        ((MainActivity) getActivity()).navigateTo(MainActivity.MY_EVENTS); //NOSONAR
    }

    private void emptyField(Object o) {
        passwordLayout.setErrorEnabled(true);
        passwordLayout.setError((String) o);
    }
    //endregion

}
