package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.android.navigationdrawerexample.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentEvents.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentEvents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSchedule extends Fragment implements AdapterView.OnItemSelectedListener {
    private static MainActivity main_fragment;
    private static List<ScheduleRow> schedule_list_data;

    private ListView schedule_list;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSchedule newInstance(MainActivity main, List<ScheduleRow> scheduleList) {
        FragmentSchedule fragment = new FragmentSchedule();
        main_fragment = main;
        schedule_list_data = scheduleList;
        return fragment;
    }

    public FragmentSchedule() {
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
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        schedule_list = (ListView) view.findViewById(R.id.schedule_list);

        ScheduleListAdapter customAdapter = new ScheduleListAdapter(getActivity(), R.layout.fragment_schedule_row, schedule_list_data);

        schedule_list.setAdapter(customAdapter);

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
