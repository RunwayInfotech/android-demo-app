package com.avocarrot.demoapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avocarrot.androidsdk.AvocarrotCustom;
import com.avocarrot.androidsdk.AvocarrotCustomListener;
import com.avocarrot.androidsdk.CustomModel;
import com.avocarrot.demoapp.main.R;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ExampleGame extends Fragment {

    private long screenWidth, screenHeight;

    ImageView target, cloudImage1, cloudImage2;
    RelativeLayout game_screen, game_notification_board;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        target = (ImageView) view.findViewById(R.id.game_target);
        cloudImage1 = (ImageView) view.findViewById(R.id.cloud1);
        cloudImage2 = (ImageView) view.findViewById(R.id.cloud2);
        game_screen = (RelativeLayout) view.findViewById(R.id.game_screen);
        game_notification_board = (RelativeLayout) view.findViewById(R.id.game_notification_board);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        game_notification_board.setVisibility(GONE);
        animateObjects(cloudImage1, cloudImage2, target, getActivity());

        // Get screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        // GET SHOTS for target
        target.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    target.animate().translationY(800f).setDuration(700).setInterpolator(new AccelerateInterpolator()).setStartDelay(500);

                    // Show Ad
                    game_notification_board.setVisibility(VISIBLE);
                    AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setDuration(700);
                    fadeIn.setStartOffset(2000);
                    fadeIn.setFillAfter(true);
                    game_notification_board.startAnimation(fadeIn);
                }
                return true;
            }
        });


        // Avocarrot Integration
        final AvocarrotCustom myCustomAd = new AvocarrotCustom(getActivity(), "3dbab458941a2446e2b48ac866b42027f5cac288", "custom" );
        myCustomAd.setSandbox(true);
        myCustomAd.setLogger(true, "ALL");
        myCustomAd.setListener(new AvocarrotCustomListener() {
            @Override
            public void onAdLoaded(List<CustomModel> ads) {
                super.onAdLoaded(ads);

                if ((ads==null) || (ads.size()<1))
                    return;

                Log.d("Avocarrot", "Ad did load");

                final CustomModel ad = ads.get(0);

                ImageView adImage = (ImageView) game_notification_board.findViewById(R.id.avo_native_creative);
                TextView adDescription = (TextView) game_notification_board.findViewById(R.id.avo_native_description);
                TextView headline = (TextView) game_notification_board.findViewById(R.id.avo_native_headline);
                Button adButton = (Button) game_notification_board.findViewById(R.id.avo_cta_button);
                Button closeButton = (Button) game_notification_board.findViewById(R.id.avo_close_button);

                adDescription.setText(ad.getDescription());
                headline.setText(ad.getTitle());
                myCustomAd.loadImage(ad, adImage);
                adButton.setText(ad.getCTAText());

                // Bind View
                myCustomAd.bindView(ad, game_notification_board);

                // Show ad on click
                game_notification_board.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myCustomAd.handleClick(ad);
                    }
                });

                // Hide notice board on exit
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        game_notification_board.animate().alpha(0).setDuration(100).setInterpolator(new AccelerateInterpolator());
                    }
                });

            }
        });

        myCustomAd.loadAd();


    }

    // Animate objects
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void animateObjects(View cloudImage1, View cloudImage2, View ball, Context mContext) {

        cloudImage1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.cloud_1));
        cloudImage2.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.cloud_2));

        ball.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.game_monster_zoom_in));
    }

    public static Fragment getInstance() {
        return new ExampleGame();
    }

}