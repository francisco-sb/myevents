package com.sb.myevents.ui.main.myevents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.sb.myevents.R;
import com.sb.myevents.data.entities.Event;

import java.util.List;

/**
 * Created by Sb on 15/06/2020
 * com.sb.myevents.ui.main.myevents
 * My Events
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;

    EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bind(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList != null ? eventList.size() : 0;
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView name;
        MaterialTextView date;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_item_textView);
            date = itemView.findViewById(R.id.date_item_textView);
        }

        void bind(Event event) {
            name.setText(event.getName());
            date.setText(event.getDate());
        }
    }

}
