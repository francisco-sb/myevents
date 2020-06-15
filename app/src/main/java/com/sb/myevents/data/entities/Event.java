package com.sb.myevents.data.entities;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.data.entities
 * My Events
 */
public class Event {

    private String id;
    private String name;
    private String place;
    private String date;
    private String time;

    public Event() {
        // Default constructor
    }

    public Event(String name, String place, String date, String time) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
