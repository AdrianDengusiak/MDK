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
public class GalleryListAdapter extends ArrayAdapter<GalleryRow> {

    public GalleryListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public GalleryListAdapter(Context context, int resource, List<GalleryRow> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.gallery_list_row, null);
        }

        GalleryRow p = getItem(position);

        if (p != null) {
            TextView gallery_name = (TextView) v.findViewById(R.id.gallery_name);
            TextView mdk_name = (TextView) v.findViewById(R.id.mdk_name);
            TextView gallery_date = (TextView) v.findViewById(R.id.gallery_date);

            if (gallery_name != null) {
                gallery_name.setText(p.getGalleryName());
            }

            if (mdk_name != null) {
                mdk_name.setText(p.getMDKName());
            }

            if (gallery_date != null) {
                gallery_date.setText(p.getDate());
            }
        }

        return v;
    }
}
