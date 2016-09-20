package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android.navigationdrawerexample.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentESK.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentESK#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChooseSchedule extends Fragment {
    private static MainActivity main_fragment;

    private ImageView pdf_schedules;
    private ImageView search_schedules;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentESK.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChooseSchedule newInstance(MainActivity main) {
        FragmentChooseSchedule fragment = new FragmentChooseSchedule();
        main_fragment = main;
        return fragment;
    }

    public FragmentChooseSchedule() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_schedule, container, false);

        pdf_schedules = (ImageView) view.findViewById(R.id.pdf_schedules);
        search_schedules = (ImageView) view.findViewById(R.id.search_schedules);

        pdf_schedules.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                        break;

                    case MotionEvent.ACTION_UP:
                        ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                        ((MainActivity) getActivity()).selectItem(31, 1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                        break;

                    default:
                        ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                        break;
                }
                return true;
            }
        });
        search_schedules.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                        break;

                    case MotionEvent.ACTION_UP:
                        ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                        ((MainActivity) getActivity()).selectItem(32, 1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                        break;

                    default:
                        ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                        break;
                }
                return true;
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
