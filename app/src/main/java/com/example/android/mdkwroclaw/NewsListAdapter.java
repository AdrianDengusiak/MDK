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
public class NewsListAdapter extends ArrayAdapter<NewsRow> {

    public NewsListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public NewsListAdapter(Context context, int resource, List<NewsRow> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.news_list_row, null);
        }

        NewsRow p = getItem(position);

        if (p != null) {
            TextView news_date = (TextView) v.findViewById(R.id.news_date);
            TextView news_title = (TextView) v.findViewById(R.id.news_title);
            TextView news_desc = (TextView) v.findViewById(R.id.news_desc);

            if (news_date != null) {
                news_date.setText(p.getDate());
            }

            if (news_title != null) {
                news_title.setText(p.getTitle());
            }

            if (news_desc != null) {
                news_desc.setText(p.getShortDescription());
            }
        }

        return v;
    }
}
