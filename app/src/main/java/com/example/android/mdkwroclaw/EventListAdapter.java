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
public class EventListAdapter extends ArrayAdapter<EventRow> {

        public EventListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public EventListAdapter(Context context, int resource, List<EventRow> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.event_list_row, null);
            }

            EventRow p = getItem(position);

            if (p != null) {
                TextView event_name = (TextView) v.findViewById(R.id.event_name);
                TextView event_place = (TextView) v.findViewById(R.id.event_place);
                TextView event_date = (TextView) v.findViewById(R.id.event_date);

                if (event_name != null) {
                    event_name.setText(p.getEventName());
                }

                    if (event_place != null) {
                    event_place.setText(p.getEventPlace());
                }

                if (event_date != null) {
                    event_date.setText(p.getEventDate());
                }
            }

            return v;
        }
}
