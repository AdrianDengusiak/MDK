package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentESK.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentESK#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentESK extends Fragment {
    private WebView description;
    private RelativeLayout more;
    private RelativeLayout loading_circle;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment FragmentESK.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentESK newInstance() {
        FragmentESK fragment = new FragmentESK();
        return fragment;
    }

    public FragmentESK() {
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
        View view = inflater.inflate(R.layout.fragment_esk, container, false);

        loading_circle = (RelativeLayout) view.findViewById(R.id.loadingPanel);

        description = (WebView) view.findViewById(R.id.description_text);

        String htmlText = "<html><head><style>body { margin-left: 0; padding-left: 0; margin-right: 0; padding-right: 0; text-align:justify; font-size:13px; color:white; } p { text-indent: 20px; }</style></head><body> %s </body></Html>";
        String text = "<p>Ideą Europejskiej Stolicy Kultury jest wzajemne poznanie, zbliżenie i dialog międzykulturowy Europejczyków. ESK stanowi ważny element poszukiwania nowej tożsamości zjednoczonej Europy. Miasta noszące tytuł ESK przez rok skupiają na sobie uwagę całej Europy. Mają niepowtarzalną szansę nie tylko wnieść wkład w rozwiązywanie kwestii ważnych dla naszego kontynentu, ale także przyspieszyć swój rozwój i zyskać skuteczną promocję.</p>" +
                "<p>Jednym z celów Unii Europejskiej jest pielęgnowanie bogactwa kulturowego Europy. Zadanie to realizuje między innymi program zainicjowany przez grecką minister kultury Melina Mercouri w 1985 roku pod nazwą Europejskie Miasta Kultury. Pierwszym miastem, które otrzymało ten status, były Ateny.</p>" +
                "<p>21 czerwca 2011 roku tytuł Europejskiej Stolicy Kultury na rok 2016 został przyznany miastom: Wrocław w Polsce i San Sebastian w Hiszpanii. Miasta, które zwyciężyły w konkursie, na rok staną się kulturalnymi centrami Europy. Ten czas wypełniony będzie festiwalami, koncertami, konferencjami i innymi przedsięwzięciami artystyczno-kulturalnymi, które skupią uwagę nie tylko mieszkańców miasta, regionu i kraju, ale również całego kontynentu.</p>";
        description.setBackgroundColor(Color.TRANSPARENT);
        description.setScrollbarFadingEnabled(true);
        description.loadDataWithBaseURL(null, String.format(htmlText, text), "text/html", "UTF-8", null);
        description.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                loading_circle.setVisibility(View.GONE);
            }
        });

        more = (RelativeLayout) view.findViewById(R.id.more_esk_information);

        more.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(LoadingView.isOnline(getActivity())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button_pressed));
                            break;

                        case MotionEvent.ACTION_UP:
                            ((RelativeLayout) v).setBackgroundDrawable(getResources().getDrawable(R.drawable.cultural_center_button));
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse("http://www.wroclaw2016.pl/"));
                            startActivity(intent);

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
