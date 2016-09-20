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
public class FragmentGallery extends Fragment implements AdapterView.OnItemSelectedListener {
    private static MainActivity main_fragment;
    public static SharedPreferences sharedPref;

    private Spinner mdk_choose;
    private ListView gallery_list;
    private RelativeLayout loading_circle;

    private OnFragmentInteractionListener mListener;

    public interface Get {
        @GET("/galleries.php")
        Call<ResponseBody> getStringJSON(@QueryMap Map<String, String> parameters);
    }

    public interface GetImages {
        @GET("/photos.php")
        Call<ResponseBody> getStringJSON(@QueryMap Map<String, String> parameters);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGallery newInstance(MainActivity main) {
        FragmentGallery fragment = new FragmentGallery();
        main_fragment = main;
        return fragment;
    }

    public FragmentGallery() {
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
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        mdk_choose = (Spinner) view.findViewById(R.id.mdk_choose);
        gallery_list = (ListView) view.findViewById(R.id.gallery_list);
        loading_circle = (RelativeLayout) view.findViewById(R.id.loadingPanel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.MDK, R.layout.spinner_layout);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mdk_choose.setAdapter(adapter);

        mdk_choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loading_circle.setVisibility(View.VISIBLE);
                gallery_list.setVisibility(View.GONE);

                switch (position) {
                    case 0:
                        createList(MDK_ID.mdk_dowolny);
                        break;
                    case 1:
                        createList(MDK_ID.mdk_fabryczna);
                        break;
                    case 2:
                        createList(MDK_ID.mdk_kopernika);
                        break;
                    case 3:
                        createList(MDK_ID.mdk_krzyki);
                        break;
                    case 4:
                        createList(MDK_ID.mdk_srodmiescie);
                        break;
                }

                gallery_list.invalidateViews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        createList(MDK_ID.mdk_dowolny);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        mdk_choose.setSelection(sharedPref.getInt("mdk_position", 0));

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
        parameters.put("type", "Galeria");
        parameters.put("mdk_id", mdk_id);

        Call<ResponseBody> result = service.getStringJSON(parameters);

        result.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getGalleries(response.body().string());
                    loading_circle.setVisibility(View.GONE);
                    gallery_list.setVisibility(View.VISIBLE);
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

    private void getGalleries(String responseString) {
        try {
            JSONObject jsonRootObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("Galeria");

            List<GalleryRow> galleryList = new ArrayList<GalleryRow>();

            for (int i = 0; i < jsonArray.length(); i++) {
                galleryList.add(new GalleryRow(jsonArray.getJSONObject(i).getString("IdGalerii"),
                        MDK_ID.getMDKName(jsonArray.getJSONObject(i).getString("MlodziezowyDomKulturyIdMDK")),
                        jsonArray.getJSONObject(i).getString("NazwaGalerii"),
                        jsonArray.getJSONObject(i).getString("DataUtworzenia")));
            }

            if (getActivity() != null) {
                GalleryListAdapter customAdapter = new GalleryListAdapter(getActivity(), R.layout.gallery_list_row, galleryList);
                gallery_list.setAdapter(customAdapter);

                gallery_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (LoadingView.isOnline(getActivity())) {
                            GalleryRow gallery_item = (GalleryRow) gallery_list.getItemAtPosition(position);

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://73.j.pl")
                                    .build();
                            GetImages service = retrofit.create(GetImages.class);

                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("type", "Zdjecie");
                            parameters.put("gallery_id", gallery_item.getGalleryId());

                            Call<ResponseBody> result = service.getStringJSON(parameters);

                            result.enqueue(new Callback<ResponseBody>() {

                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    try {
                                        getPhotos(response.body().string());
                                    } catch (IOException e) {
                                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("mdk_position", mdk_choose.getSelectedItemPosition());
                            editor.putString("title", gallery_item.getGalleryName());
                            editor.commit();
                        }
                        else
                            Toast.makeText(getActivity(), "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private void getPhotos(String responseString) {
        try {
            JSONObject jsonRootObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("Zdjecie");

            ArrayList<String> photo_list_data = new ArrayList<String>();

            for(int i = 0; i < jsonArray.length(); i++) {
                photo_list_data.add(jsonArray.getJSONObject(i).getString("Zdjecie"));
            }

            Intent intent = new Intent(getActivity(), GalleryActivity.class);
            intent.putStringArrayListExtra(GalleryActivity.EXTRA_NAME, photo_list_data);

            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
