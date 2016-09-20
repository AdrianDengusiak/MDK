package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
 * {@link FragmentSearchSchedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSearchSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearchSchedule extends Fragment {
    private static MainActivity main_fragment;
    public static SharedPreferences sharedPref;

    private Spinner mdk_choose;
    private Spinner category_choose;
    private Spinner day_choose;

    private TextView mdk_text;
    private TextView category_text;
    private TextView day_text;

    private String choosen_mdk = "0";
    private String choosen_category = "%";
    private String choosen_day = "%";

    private RelativeLayout search;
    private RelativeLayout loading_circle;

    private OnFragmentInteractionListener mListener;

    public interface Get {
        @GET("/search.php")
        Call<ResponseBody> getStringJSON(@QueryMap Map<String, String> parameters);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentSearchSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearchSchedule newInstance(MainActivity main) {
        FragmentSearchSchedule fragment = new FragmentSearchSchedule();
        main_fragment = main;
        return fragment;
    }

    public FragmentSearchSchedule() {
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
        View view =  inflater.inflate(R.layout.fragment_search_schedule, container, false);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        mdk_choose = (Spinner) view.findViewById(R.id.mdk_choose);
        category_choose = (Spinner) view.findViewById(R.id.category_choose);
        day_choose = (Spinner) view.findViewById(R.id.day_choose);
        mdk_text = (TextView) view.findViewById(R.id.text_mdk);
        category_text = (TextView) view.findViewById(R.id.text_category);
        day_text = (TextView) view.findViewById(R.id.text_day);
        search = (RelativeLayout) view.findViewById(R.id.search);
        loading_circle = (RelativeLayout) view.findViewById(R.id.loadingPanel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(main_fragment,
                R.array.MDK, R.layout.spinner_layout);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mdk_choose.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(main_fragment,
                R.array.Category, R.layout.spinner_layout);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_choose.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(main_fragment,
                R.array.Day, R.layout.spinner_layout);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_choose.setAdapter(adapter);

        mdk_choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        choosen_mdk = MDK_ID.mdk_dowolny;
                        break;
                    case 1:
                        choosen_mdk = MDK_ID.mdk_fabryczna;
                        break;
                    case 2:
                        choosen_mdk = MDK_ID.mdk_kopernika;
                        break;
                    case 3:
                        choosen_mdk = MDK_ID.mdk_krzyki;
                        break;
                    case 4:
                        choosen_mdk = MDK_ID.mdk_srodmiescie;
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        category_choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        choosen_category = "%";
                        break;
                    case 1:
                        choosen_category = "Sport i Gry";
                        break;
                    case 2:
                        choosen_category = "Muzyka i Dźwięk";
                        break;
                    case 3:
                        choosen_category = "Taniec i Rekreacja";
                        break;
                    case 4:
                        choosen_category = "Malarstwo i Grafika";
                        break;
                    case 5:
                        choosen_category = "Rękodzieło i Plastyka";
                        break;
                    case 6:
                        choosen_category = "Film i Teatr";
                        break;
                    case 7:
                        choosen_category = "Nauka i Technika";
                        break;
                    case 8:
                        choosen_category = "Środowisko i Społeczeństwo";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        day_choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 0:
                        choosen_day = "%";
                        break;
                    case 1:
                        choosen_day = "Poniedziałek";
                        break;
                    case 2:
                        choosen_day = "Wtorek";
                        break;
                    case 3:
                        choosen_day = "Środa";
                        break;
                    case 4:
                        choosen_day = "Czwartek";
                        break;
                    case 5:
                        choosen_day = "Piątek";
                        break;
                    case 6:
                        choosen_day = "Sobota";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(LoadingView.isOnline(main_fragment)) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                            break;

                        case MotionEvent.ACTION_UP:
                            ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));

                            loading_circle.setVisibility(View.VISIBLE);
                            mdk_choose.setVisibility(View.GONE);
                            category_choose.setVisibility(View.GONE);
                            day_choose.setVisibility(View.GONE);
                            mdk_text.setVisibility(View.GONE);
                            category_text.setVisibility(View.GONE);
                            day_text.setVisibility(View.GONE);
                            search.setVisibility(View.GONE);

                            createList(choosen_mdk, choosen_category, choosen_day);

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("mdk_position", mdk_choose.getSelectedItemPosition());
                            editor.putInt("category_position", category_choose.getSelectedItemPosition());
                            editor.putInt("day_position", day_choose.getSelectedItemPosition());
                            editor.commit();

                            break;

                        case MotionEvent.ACTION_MOVE:
                            ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                            break;

                        default:
                            ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                            break;
                    }
                }
                else {
                    Toast.makeText(main_fragment, "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        SharedPreferences sharedPref = main_fragment.getPreferences(Context.MODE_PRIVATE);
        mdk_choose.setSelection(sharedPref.getInt("mdk_position", 0));
        category_choose.setSelection(sharedPref.getInt("category_position", 0));
        day_choose.setSelection(sharedPref.getInt("day_position", 0));

        loading_circle.setVisibility(View.GONE);

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void createList(String choosen_mdk, String choosen_category, String choosen_day) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://73.j.pl")
                .build();
        Get service = retrofit.create(Get.class);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("mdk_id", choosen_mdk);
        parameters.put("category", choosen_category);
        parameters.put("day", choosen_day);

        Call<ResponseBody> result = service.getStringJSON(parameters);

        result.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getSchedule(response.body().string());
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

    private void getSchedule(String responseString) {
        try {
            JSONObject jsonRootObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("Zajecie");

            List<ScheduleRow> scheduleList = new ArrayList<ScheduleRow>();

            for(int i = 0; i < jsonArray.length(); i++) {
                scheduleList.add(new ScheduleRow(jsonArray.getJSONObject(i).getString("Nazwa"), jsonArray.getJSONObject(i).getString("DzienTygodnia"), jsonArray.getJSONObject(i).getString("GodzinaRozpoczecia"), jsonArray.getJSONObject(i).getString("GodzinaZakonczenia"), jsonArray.getJSONObject(i).getString("Sala"), jsonArray.getJSONObject(i).getString("NazwaKursu"), jsonArray.getJSONObject(i).getString("Imie") + " " + jsonArray.getJSONObject(i).getString("Nazwisko"), jsonArray.getJSONObject(i).getString("Grupa")));
            }

            if(scheduleList.size() == 0) {
                loading_circle.setVisibility(View.GONE);
                mdk_choose.setVisibility(View.VISIBLE);
                category_choose.setVisibility(View.VISIBLE);
                day_choose.setVisibility(View.VISIBLE);
                mdk_text.setVisibility(View.VISIBLE);
                category_text.setVisibility(View.VISIBLE);
                day_text.setVisibility(View.VISIBLE);
                search.setVisibility(View.VISIBLE);

                Toast.makeText(getActivity(), "Nie odnaleziono zajęć spełniających wybrane kryteria, spróbój ponownie.", Toast.LENGTH_SHORT).show();
            }
            else {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).selectItem(321, 1, scheduleList, null);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
