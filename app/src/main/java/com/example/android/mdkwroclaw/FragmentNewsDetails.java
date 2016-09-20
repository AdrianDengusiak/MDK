package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentNewsDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentNewsDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNewsDetails extends Fragment {
    private static MainActivity main_fragment;
    private static NewsRow news_row;

    private TextView date;
    private TextView title;
    private WebView text;
    private RelativeLayout loading_circle;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentNewsDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNewsDetails newInstance(MainActivity main, NewsRow news) {
        FragmentNewsDetails fragment = new FragmentNewsDetails();
        main_fragment = main;
        news_row = news;
        return fragment;
    }

    public FragmentNewsDetails() {
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
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);

        date = (TextView) view.findViewById(R.id.date);
        title = (TextView) view.findViewById(R.id.title);
        text = (WebView) view.findViewById(R.id.description_text);
        loading_circle = (RelativeLayout) view.findViewById(R.id.loadingPanel);

        date.setText(news_row.getDate());
        title.setText(news_row.getTitle());

        String htmlText = "<html><head><style>body { margin-left: 0; padding-left: 0; margin-right: 0; padding-right: 0; text-align:justify; font-size:14px; color:white; } p { text-indent: 20px; }</style></head><body> %s </body></Html>";
        String text_data = "<p>" + news_row.getDescription() + "</p>";
        text.setBackgroundColor(Color.TRANSPARENT);
        text.setScrollbarFadingEnabled(true);
        text.loadDataWithBaseURL(null, String.format(htmlText, text_data), "text/html", "UTF-8", null);
        text.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                loading_circle.setVisibility(View.GONE);
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
