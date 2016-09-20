package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.android.navigationdrawerexample.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMain extends Fragment {
    private static MainActivity main_fragment;

    private RelativeLayout cultural_center;
    private RelativeLayout news;
    private RelativeLayout schedule;
    private RelativeLayout event;
    private RelativeLayout multimedia;
    private RelativeLayout about;
    private RelativeLayout esk2015;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentMain.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMain newInstance(MainActivity main) {
        FragmentMain fragment = new FragmentMain();
        main_fragment = main;
        return fragment;
    }

    public FragmentMain() {
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        cultural_center = (RelativeLayout) view.findViewById(R.id.menu_item_house);
        news = (RelativeLayout) view.findViewById(R.id.menu_item_news);
        schedule = (RelativeLayout) view.findViewById(R.id.menu_item_schedule);
        event = (RelativeLayout) view.findViewById(R.id.menu_item_event);
        multimedia = (RelativeLayout) view.findViewById(R.id.menu_item_multimedia);
        about = (RelativeLayout) view.findViewById(R.id.menu_item_about);
        esk2015 = (RelativeLayout) view.findViewById(R.id.menu_item_esk);

        cultural_center.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(1, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        break;
                }
                return true;
            }
        });
        news.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(2, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        break;
                }
                return true;
            }
        });
        schedule.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(3, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        break;
                }
                return true;
            }
        });
        event.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(4, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        break;
                }
                return true;
            }
        });
        multimedia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(5, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        break;
                }
                return true;
            }
        });
        about.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(6, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        break;
                }
                return true;
            }
        });
        esk2015.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
                        ((MainActivity) getActivity()).selectItem(7, -1, null, null);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundResource(R.drawable.menu_button_shape_pressed);
                        break;

                    default:
                        v.setBackgroundResource(R.drawable.menu_button_shape);
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
