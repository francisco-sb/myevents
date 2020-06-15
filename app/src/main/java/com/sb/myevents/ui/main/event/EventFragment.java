package com.sb.myevents.ui.main.event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.sb.myevents.R;
import com.sb.myevents.sys.components.DaggerViewModelComponent;
import com.sb.myevents.sys.modules.ContextModule;
import com.sb.myevents.ui.main.MainActivity;

import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;

public class EventFragment extends Fragment {

    //region Dependencies Injection
    @Inject
    EventViewModel viewModel;
    //endregion

    //region Views
    private MaterialTextView dateTextView, timeTextView;
    //endregion

    //region Calendar - Time
    private static final String ZERO = "0";
    private static final String SLASH = "/";
    private static final String COLON = ":";

    private final Calendar c = Calendar.getInstance();

    private final int month = c.get(Calendar.MONTH);
    private final int day = c.get(Calendar.DAY_OF_MONTH);
    private final int year = c.get(Calendar.YEAR);

    private final int hour = c.get(Calendar.HOUR_OF_DAY);
    private final int minute = c.get(Calendar.MINUTE);
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
        View rootView = inflater.inflate(R.layout.event_fragment, container, false);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).show();

        TextInputEditText nameEditText = rootView.findViewById(R.id.event_name_editText);
        TextInputEditText placeEditText = rootView.findViewById(R.id.place_editText);
        dateTextView = rootView.findViewById(R.id.date_textView);
        timeTextView = rootView.findViewById(R.id.time_textView);

        observeStreams();

        dateTextView.setOnClickListener(view -> openDatePicker());
        timeTextView.setOnClickListener(view -> openTimePicker());

        rootView.findViewById(R.id.save_button).setOnClickListener(view ->
                viewModel.createEvent(
                        Objects.requireNonNull(nameEditText.getText()).toString(),
                        Objects.requireNonNull(placeEditText.getText()).toString(),
                        Objects.requireNonNull(dateTextView.getText()).toString(),
                        Objects.requireNonNull(timeTextView.getText()).toString()
                )
        );
        return rootView;
    }

    //region:: PRIVATE METHODS
    private void observeStreams() {
        viewModel.onEventCreateFailed.observe(this, this::eventCreateFailed);
        viewModel.onEventCreateSuccessful.observe(this, this::eventCreateSuccessful);
    }

    private void openDatePicker(){
        assert getContext() != null;
        DatePickerDialog getDate = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            final int currentMonth = month + 1;
            String formatDay = (dayOfMonth < 10)? ZERO + dayOfMonth : String.valueOf(dayOfMonth);
            String formatMonth = (currentMonth < 10)? ZERO + currentMonth : String.valueOf(currentMonth);

            String date = formatDay + SLASH + formatMonth + SLASH + year;
            dateTextView.setText(date);
        }, year, month, day);
        getDate.show();

    }

    private void openTimePicker(){
        TimePickerDialog getTime = new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
            String formatHour =  (hourOfDay < 10)? ZERO + hourOfDay : String.valueOf(hourOfDay);
            String formatMinute = (minute < 10)? ZERO + minute : String.valueOf(minute);
            String amPm;

            if(hourOfDay < 12) {
                amPm = "a.m.";
            } else {
                amPm = "p.m.";
            }
            String time = formatHour + COLON + formatMinute + " " + amPm;
            timeTextView.setText(time);
        }, hour, minute, false);

        getTime.show();
    }

    //endregion

    //region:: REFERENCE METHODS
    private void eventCreateSuccessful(Object o) {
        Toast.makeText(getContext(), (String) o, Toast.LENGTH_SHORT).show();

        assert getActivity() != null;
        ((MainActivity) getActivity()).navigateTo(MainActivity.MY_EVENTS); //NOSONAR
    }

    private void eventCreateFailed(Object o) {
        Toast.makeText(getContext(), (String) o, Toast.LENGTH_SHORT).show();
    }
    //endregion

}
