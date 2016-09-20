package com.example.android.mdkwroclaw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

/**
 * Created by AD on 2015-10-13.
 */
public class LoadingView extends Activity {
    private RelativeLayout loading_view;
    private TextView loading_text;
    private ImageView loading_click_logo;
    private Animation blink_item;
    public static int view_height;
    public static int view_width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_view);

        loading_view = (RelativeLayout) findViewById(R.id.loading_view);
        loading_text = (TextView) findViewById(R.id.loading_text);
        loading_click_logo = (ImageView) findViewById(R.id.loading_click_logo);

        blink_item = AnimationUtils.loadAnimation(this, R.anim.blink_item);
        loading_click_logo.startAnimation(blink_item);

        blink_item = AnimationUtils.loadAnimation(this, R.anim.blink_text);
        loading_text.startAnimation(blink_item);

        ViewTreeObserver viewTreeObserver = loading_view.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view_height = loading_view.getHeight();
                view_width = loading_view.getWidth();
            }
        });

        loading_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent m) {
                if (m.getAction() == MotionEvent.ACTION_DOWN) {
                    //stripe.setBackgroundResource(R.drawable.stripe_empty);
                    // loading_text.setBackgroundResource(R.drawable.stripe_light_focused);
                    // loading_text.clearAnimation();
                }
                return false;
            }
        });

        loading_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(LoadingView.this, MainActivity.class));
                finish();
            }
        });
        if(!isOnline(this)) {
            new AlertDialog.Builder(this)
                    .setTitle("Brak połączenia z Internetem")
                    .setMessage("Niektóre funkcje aplikacji mogą być niedostępne. Połącz się z siecią, aby uzyskać dostęp do wszystkich funkcjonalności.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
