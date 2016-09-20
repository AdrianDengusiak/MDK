package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
public class FragmentCulturalCenterInformation extends Fragment {
    private static MainActivity main_fragment;
    private static int cultural_center_id;

    private TextView bar_title;
    private ImageView logo;
    private RelativeLayout loading_circle;
    private ImageView arrow;

    private TextView full_name;
    private TextView street;
    private TextView city;
    private TextView phone_number;
    private TextView website;
    private TextView boss;
    private TextView transport_bus;
    private TextView transport_tram;

    private TextView address_label;
    private TextView web_label;
    private TextView boss_label;
    private TextView transport_label;
    private TextView other_label;

    private RelativeLayout schedule;
    private RelativeLayout teachers;
    private RelativeLayout localization;

    private OnFragmentInteractionListener mListener;

    public interface Get {
        @GET("/centers.php")
        Call<ResponseBody> getStringJSON(@QueryMap Map<String, String> parameters);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCulturalCenterInformation newInstance(int mdk_id, MainActivity main) {
        FragmentCulturalCenterInformation fragment = new FragmentCulturalCenterInformation();
        main_fragment = main;
        cultural_center_id = mdk_id;
        return fragment;
    }

    public FragmentCulturalCenterInformation() {
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
        View view = inflater.inflate(R.layout.fragment_cultural_center_information, container, false);

        bar_title = (TextView) view.findViewById(R.id.title_house_information);
        logo = (ImageView) view.findViewById(R.id.logo);
        loading_circle = (RelativeLayout) view.findViewById(R.id.loadingPanel);
        arrow = (ImageView) view.findViewById(R.id.arrow);

        full_name = (TextView) view.findViewById(R.id.full_name);
        street = (TextView) view.findViewById(R.id.street);
        city = (TextView) view.findViewById(R.id.city);
        phone_number = (TextView) view.findViewById(R.id.phone_number);
        website = (TextView) view.findViewById(R.id.website);
        boss = (TextView) view.findViewById(R.id.boss);
        transport_bus = (TextView) view.findViewById(R.id.transport_bus);
        transport_tram = (TextView) view.findViewById(R.id.transport_tram);

        address_label = (TextView) view.findViewById(R.id.address_label);
        web_label = (TextView) view.findViewById(R.id.web_label);
        boss_label = (TextView) view.findViewById(R.id.boss_label);
        transport_label = (TextView) view.findViewById(R.id.transport_label);
        other_label = (TextView) view.findViewById(R.id.other_label);

        schedule = (RelativeLayout) view.findViewById(R.id.schedule);
        teachers = (RelativeLayout) view.findViewById(R.id.teachers);
        localization = (RelativeLayout) view.findViewById(R.id.localization);

        loading_circle.setVisibility(View.VISIBLE);
        logo.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);

        full_name.setVisibility(View.GONE);
        street.setVisibility(View.GONE);
        city.setVisibility(View.GONE);
        phone_number.setVisibility(View.GONE);
        website.setVisibility(View.GONE);
        boss.setVisibility(View.GONE);
        transport_bus.setVisibility(View.GONE);
        transport_tram.setVisibility(View.GONE);

        address_label.setVisibility(View.GONE);
        web_label.setVisibility(View.GONE);
        boss_label.setVisibility(View.GONE);
        transport_label.setVisibility(View.GONE);
        other_label.setVisibility(View.GONE);

        schedule.setVisibility(View.GONE);
        teachers.setVisibility(View.GONE);
        localization.setVisibility(View.GONE);

        switch(cultural_center_id) {
            case 1:
                bar_title.setText("MDK Fabryczna");
                logo.setBackgroundResource(R.drawable.mdk_fabryczna_logo);
                break;
            case 2:
                bar_title.setText("MDK im. Mikołaja Kopernika");
                logo.setBackgroundResource(R.drawable.mdk_kopernika_logo);
                break;
            case 3:
                bar_title.setText("MDK Krzyki");
                logo.setBackgroundResource(R.drawable.mdk_krzyki_logo);
                break;
            case 4:
                bar_title.setText("MDK Śródmieście");
                logo.setBackgroundResource(R.drawable.mdk_srodmiescie_logo);
                break;
        }

        createMDKItem(Integer.toString(cultural_center_id));

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

    private void createMDKItem(String mdk_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://73.j.pl")
                .build();
        Get service = retrofit.create(Get.class);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", "MlodziezowyDomKultury");
        parameters.put("mdk_id", mdk_id);

        Call<ResponseBody> result = service.getStringJSON(parameters);

        result.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string();

                    JSONObject jsonRootObject = new JSONObject(res);
                    final JSONArray jsonArray = jsonRootObject.optJSONArray("MlodziezowyDomKultury");

                    MDKRow mdk = new MDKRow(jsonArray.getJSONObject(0).getString("Nazwa"),
                            jsonArray.getJSONObject(0).getString("Ulica") + " " + jsonArray.getJSONObject(0).getString("NrDomu"),
                            jsonArray.getJSONObject(0).getString("KodPocztowy") + ", " + jsonArray.getJSONObject(0).getString("Miasto"),
                            "Tel. " + jsonArray.getJSONObject(0).getString("Telefon"),
                            jsonArray.getJSONObject(0).getString("Witryna"),
                            jsonArray.getJSONObject(0).getString("Dyrektor"),
                            "Autobusy: " + jsonArray.getJSONObject(0).getString("Autobusy"),
                            "Tramwaje: " + jsonArray.getJSONObject(0).getString("Tramwaje"));

                    full_name.setText(mdk.getMDKFullName());
                    street.setText(mdk.getMDKStreet());
                    city.setText(mdk.getMDKCity());
                    phone_number.setText(mdk.getMDKPhoneNumber());
                    website.setText(mdk.getMDKWebsite());
                    boss.setText(mdk.getMDKBoss());
                    transport_bus.setText(mdk.getMDKTransportBus());
                    transport_tram.setText(mdk.getMDKTransportTram());

                    loading_circle.setVisibility(View.GONE);
                    logo.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);

                    full_name.setVisibility(View.VISIBLE);
                    street.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    phone_number.setVisibility(View.VISIBLE);
                    website.setVisibility(View.VISIBLE);
                    boss.setVisibility(View.VISIBLE);
                    transport_bus.setVisibility(View.VISIBLE);
                    transport_tram.setVisibility(View.VISIBLE);

                    address_label.setVisibility(View.VISIBLE);
                    web_label.setVisibility(View.VISIBLE);
                    boss_label.setVisibility(View.VISIBLE);
                    transport_label.setVisibility(View.VISIBLE);
                    other_label.setVisibility(View.VISIBLE);

                    schedule.setVisibility(View.VISIBLE);
                    teachers.setVisibility(View.VISIBLE);
                    localization.setVisibility(View.VISIBLE);

                    phone_number.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                Intent i = new Intent(Intent.ACTION_DIAL);
                                i.setData(Uri.parse("tel:" + jsonArray.getJSONObject(0).getString("Telefon")));
                                getActivity().startActivity(i);
                            } catch (JSONException e) {
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    /*website.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                String url = "http://" + jsonArray.getJSONObject(0).getString("Witryna");
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                getActivity().startActivity(i);
                            } catch (JSONException e) {
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/

                    schedule.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                                    break;

                                case MotionEvent.ACTION_UP:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                                    Toast.makeText(getActivity(), "Rozpoczęto pobieranie planu...", Toast.LENGTH_SHORT).show();

                                    switch (cultural_center_id) {
                                        case 1:
                                            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdkfabryczna.pdf")));
                                            break;
                                        case 2:
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdkkopernika.pdf")));
                                            break;
                                        case 3:
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdkkrzyki.pdf")));
                                            break;
                                        case 4:
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdksrodmiescie.pdf")));
                                            break;
                                    }
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                                    break;

                                default:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                                    break;
                            }
                            return true;
                        }
                    });

                    teachers.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                                    break;

                                case MotionEvent.ACTION_UP:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                                    if (getActivity() != null) {
                                        ((MainActivity) getActivity()).selectItem(111, cultural_center_id, null, null);
                                    }
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                                    break;

                                default:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                                    break;
                            }
                            return true;
                        }
                    });

                    localization.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                                    break;

                                case MotionEvent.ACTION_UP:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                                    Uri gmmIntentUri = Uri.parse("geo:0,0");

                                    switch (cultural_center_id) {
                                        case 1:
                                            gmmIntentUri = Uri.parse("geo:51.1159833,16.9484764?q=MDK+Fabryczna");
                                            break;
                                        case 2:
                                            gmmIntentUri = Uri.parse("geo:51.1004551,17.0341166?q=Szkolne+Schronisko+Młodzieżowe+Kołłątaja");
                                            break;
                                        case 3:
                                            gmmIntentUri = Uri.parse("geo:51.0809529,17.006669?q=Młodzieżowy+Dom+Kultury+Wrocław-Krzyki");
                                            break;
                                        case 4:
                                            gmmIntentUri = Uri.parse("geo:51.1172404,17.0308845?q=Młodzieżowy+Dom+Kultury+Dubois");
                                            break;
                                    }

                                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                    mapIntent.setPackage("com.google.android.apps.maps");

                                    if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null)
                                        startActivity(mapIntent);
                                    else
                                        Toast.makeText(getActivity(), "Nie wykryto aplikacji Google Maps", Toast.LENGTH_LONG).show();
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                                    break;

                                default:
                                    ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                                    break;
                            }
                            return true;
                        }
                    });

                } catch (IOException | JSONException e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
