package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDownloadPDFSchedules.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDownloadPDFSchedules#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDownloadPDFSchedules extends Fragment {
    private static MainActivity main_fragment;

    private ImageView mdk_fabryczna;
    private ImageView mdk_kopernika;
    private ImageView mdk_krzyki;
    private ImageView mdk_srodmiescie;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentDownloadPDFSchedules.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDownloadPDFSchedules newInstance(MainActivity main) {
        FragmentDownloadPDFSchedules fragment = new FragmentDownloadPDFSchedules();
        main_fragment = main;
        return fragment;
    }

    public FragmentDownloadPDFSchedules() {
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
        View view = inflater.inflate(R.layout.fragment_download_pdfschedules, container, false);

        mdk_fabryczna = (ImageView) view.findViewById(R.id.mdk_fabryczna);
        mdk_kopernika= (ImageView) view.findViewById(R.id.mdk_kopernika);
        mdk_krzyki = (ImageView) view.findViewById(R.id.mdk_krzyki);
        mdk_srodmiescie = (ImageView) view.findViewById(R.id.mdk_srodmiescie);

        mdk_fabryczna.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(LoadingView.isOnline(getActivity())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        case MotionEvent.ACTION_UP:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            Toast.makeText(getActivity(), "Rozpoczęto pobieranie planu...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdkfabryczna.pdf")));
                            break;

                        case MotionEvent.ACTION_MOVE:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        default:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            break;
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        mdk_kopernika.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(LoadingView.isOnline(getActivity())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        case MotionEvent.ACTION_UP:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            Toast.makeText(getActivity(), "Rozpoczęto pobieranie planu...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdkkopernika.pdf")));
                            break;

                        case MotionEvent.ACTION_MOVE:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        default:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            break;
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        mdk_krzyki.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (LoadingView.isOnline(getActivity())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        case MotionEvent.ACTION_UP:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            Toast.makeText(getActivity(), "Rozpoczęto pobieranie planu...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdkkrzyki.pdf")));
                            break;

                        case MotionEvent.ACTION_MOVE:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        default:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            break;
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        mdk_srodmiescie.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(LoadingView.isOnline(getActivity())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        case MotionEvent.ACTION_UP:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            Toast.makeText(getActivity(), "Rozpoczęto pobieranie planu...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.73.j.pl/mdksrodmiescie.pdf")));
                            break;

                        case MotionEvent.ACTION_MOVE:
                            ((ImageView) v).setColorFilter(Color.argb(150, 0, 100, 0));
                            break;

                        default:
                            ((ImageView) v).setColorFilter(Color.argb(0, 0, 0, 0));
                            break;
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Brak połączenia z Internetem!", Toast.LENGTH_SHORT).show();
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
