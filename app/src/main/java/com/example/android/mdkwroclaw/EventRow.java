package com.example.android.mdkwroclaw;

/**
 * Created by AD on 2016-04-15.
 */
public class EventRow {
    private String event_name;
    private String event_place;
    private String event_date;

    EventRow(String event_name, String event_place, String event_date) {
        this.event_name = event_name;
        this.event_place = event_place;
        this.event_date = event_date;
    }

    public String getEventName() {
        return event_name;
    }

    public String getEventPlace() {
        return event_place;
    }

    public String getEventDate() {
        return event_date;
    }
}
