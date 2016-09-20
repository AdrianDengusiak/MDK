package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
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
public class FragmentTeachers extends Fragment implements AdapterView.OnItemSelectedListener {
    private static MainActivity main_fragment;
    public static SharedPreferences sharedPref;
    private static int cultural_center_id;

    private TextView title_mdk;
    private ListView teacher_list;
    private RelativeLayout loading_circle;

    private OnFragmentInteractionListener mListener;

    public interface Get {
        @GET("/teachers.php")
        Call<ResponseBody> getStringJSON(@QueryMap Map<String, String> parameters);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTeachers newInstance(int mdk_id, MainActivity main) {
        FragmentTeachers fragment = new FragmentTeachers();
        main_fragment = main;
        cultural_center_id = mdk_id;
        return fragment;
    }

    public FragmentTeachers() {
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
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("mdk_position", cultural_center_id);
        editor.commit();

        title_mdk = (TextView) view.findViewById(R.id.title_mdk);
        teacher_list = (ListView) view.findViewById(R.id.teacher_list);
        loading_circle = (RelativeLayout) view.findViewById(R.id.loadingPanel);

        title_mdk.setText(MDK_ID.getMDKName(Integer.toString(cultural_center_id)));

        loading_circle.setVisibility(View.VISIBLE);

        createList(Integer.toString(cultural_center_id));

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

    private void createList(String mdk_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://73.j.pl")
                .build();
        Get service = retrofit.create(Get.class);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", "Etat");
        parameters.put("mdk_id", mdk_id);

        Call<ResponseBody> result = service.getStringJSON(parameters);

        result.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getTeachers(response.body().string());
                    loading_circle.setVisibility(View.GONE);
                    teacher_list.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void getTeachers(String responseString) {
        try {
            JSONObject jsonRootObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("Etat");

            ArrayList<TeacherRow> teacher_list_data = new ArrayList<TeacherRow>();

            for(int i = 0; i < jsonArray.length(); i++) {
                teacher_list_data.add(new TeacherRow(jsonArray.getJSONObject(i).getString("Imie"), jsonArray.getJSONObject(i).getString("Nazwisko")));
            }

            TeacherListAdapter customAdapter = new TeacherListAdapter(getActivity(), R.layout.teacher_list_row, teacher_list_data);

            teacher_list.setAdapter(customAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
