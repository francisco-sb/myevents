package com.sb.myevents.ui.main.myevents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;
import com.sb.myevents.ui.main.MainActivity;

import java.util.Objects;

import javax.inject.Inject;

public class MyEventsFragment extends Fragment {

    //region Dependencies Injection
    @Inject
    MyEventsViewModel viewModel;
    //endregion

    //region Views
    private RecyclerView recyclerView;
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
        View rootView = inflater.inflate(R.layout.my_events_fragment, container, false);
        setHasOptionsMenu(true);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).show();

        recyclerView = rootView.findViewById(R.id.events_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        observeStreams();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sign_out) {
            viewModel.signOut();
            return true;
        } else if (item.getItemId() == R.id.add_event) {
            assert getActivity() != null;
            ((MainActivity) getActivity()).navigateTo(MainActivity.EVENT); //NOSONAR
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    //region:: PRIVATE METHODS
    private void observeStreams() {
        viewModel.onSignOutAction.observe(this, this::logout);
    }
    //endregion

    //region:: REFERENCE METHODS
    private void logout(Object o) {
        Toast.makeText(getContext(), (String) o, Toast.LENGTH_SHORT).show();

        assert getActivity() != null;
        ((MainActivity) getActivity()).navigateTo(MainActivity.LOGIN); //NOSONAR
    }
    //endregion
}
