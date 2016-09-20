package com.example.android.mdkwroclaw;

/**
 * Created by AD on 2016-04-22.
 */
public class ScheduleRow {
    private String mdk_name;
    private String day;
    private String start_hour;
    private String end_hour;
    private String place;
    private String course;
    private String lecturer;
    private String group;

    ScheduleRow(String mdk_name, String day, String start_hour, String end_hour, String place, String course, String lecturer, String group) {
        this.mdk_name = mdk_name;
        this.day = day;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.place = place;
        this.course = course;
        this.lecturer = lecturer;
        this.group = group;
    }

    public String getMDKName() {
        return mdk_name;
    }

    public String getDay() {
        return day;
    }

    public String getStartHour() {
        return start_hour;
    }

    public String getEndHour() {
        return end_hour;
    }

    public String getPlace() {
        return place;
    }

    public String getCourse() {
        return course;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getGroup() {
        return group;
    }
}
