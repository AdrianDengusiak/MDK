package com.example.android.mdkwroclaw;

/**
 * Created by AD on 2016-04-28.
 */
public class TeacherRow {
    private String name;
    private String surname;

    public TeacherRow(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
