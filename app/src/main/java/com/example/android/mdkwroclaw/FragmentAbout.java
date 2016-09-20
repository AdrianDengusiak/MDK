package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAbout.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAbout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAbout extends Fragment {
    private TextView author;
    private TextView promotor;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentAbout.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAbout newInstance() {
        FragmentAbout fragment = new FragmentAbout();
        return fragment;
    }

    public FragmentAbout() {
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
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        Toast.makeText(getActivity(), "Kliknij adres e-mail, aby wysłać wiadomość", Toast.LENGTH_SHORT).show();

        author = (TextView) view.findViewById(R.id.author);
        promotor = (TextView) view.findViewById(R.id.promotor);

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText = "mailto:adrian.dengusiak@gmail.com" + "?subject=" + Uri.encode("MDK Wrocław - aplikacja mobilna") + "&body=" + Uri.encode("");
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                startActivity(Intent.createChooser(sendIntent, "Wybierz aplikację"));
            }
        });

        promotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText = "mailto:k.wasko@mikrozet.pl" + "?subject=" + Uri.encode("MDK Wrocław - aplikacja mobilna") + "&body=" + Uri.encode("");
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                startActivity(Intent.createChooser(sendIntent, "Wybierz aplikację"));
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
