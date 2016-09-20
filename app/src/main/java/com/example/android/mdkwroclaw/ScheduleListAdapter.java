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
 * Created by AD on 2016-04-15.
 */
public class ScheduleListAdapter extends ArrayAdapter<ScheduleRow> {

    public ScheduleListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ScheduleListAdapter(Context context, int resource, List<ScheduleRow> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.schedule_list_layout, null);
        }

        ScheduleRow p = getItem(position);

        if (p != null) {
            TextView which = (TextView) v.findViewById(R.id.which);
            TextView when = (TextView) v.findViewById(R.id.when);
            TextView what = (TextView) v.findViewById(R.id.what);
            TextView who = (TextView) v.findViewById(R.id.who);


            if (which != null) {
                which.setText(p.getMDKName());
            }

            if (when != null) {
                when.setText(p.getDay() + ", " + p.getStartHour() + "-" + p.getEndHour() + ", Sala: " + p.getPlace());
            }

            if (what != null) {
                what.setText(p.getCourse());
            }

            if (who != null) {
                who.setText(p.getLecturer() + ", Grupa: " + p.getGroup());
            }
        }

        return v;
    }
}
