package com.example.android.mdkwroclaw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

import java.util.List;

/**
 * Created by AD on 2016-04-28.
 */
public class TeacherListAdapter extends ArrayAdapter<TeacherRow> {

    public TeacherListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TeacherListAdapter(Context context, int resource, List<TeacherRow> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.teacher_list_row, null);
        }

        TeacherRow p = getItem(position);

        if (p != null) {
            TextView teacher_name = (TextView) v.findViewById(R.id.teacher_name);

            if (teacher_name != null) {
                teacher_name.setText(p.getName() + " " + p.getSurname());
            }
        }

        return v;
    }
}
