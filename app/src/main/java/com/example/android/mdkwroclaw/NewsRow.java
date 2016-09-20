package com.example.android.mdkwroclaw;

/**
 * Created by AD on 2016-04-24.
 */
public class NewsRow {
    private String date;
    private String title;
    private String short_description;
    private String description;

    public NewsRow(String date, String title, String short_description, String description) {
        this.date = date;
        this.title = title;
        this.short_description = short_description;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return short_description;
    }

    public String getDescription() {
        return description;
    }
}
