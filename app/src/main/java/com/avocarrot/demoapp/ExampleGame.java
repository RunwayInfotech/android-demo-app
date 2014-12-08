package com.avocarrot.demoapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
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

import com.avocarrot.androidsdk.custom.AvocarrotCustom;
import com.avocarrot.androidsdk.custom.AvocarrotCustomListener;
import com.avocarrot.androidsdk.custom.CustomAdItem;

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
        AvocarrotCustom myCustomAd = new AvocarrotCustom();
        myCustomAd.initWithKey("3dbab458941a2446e2b48ac866b42027f5cac288");
        myCustomAd.setSandbox(true);
        myCustomAd.setLogger(true, "ALL");
        myCustomAd.loadAdForPlacement(getActivity(), "custom");
        myCustomAd.setAdListener(new AvocarrotCustomListener() {
            @Override
            public void adDidLoad(final CustomAdItem ad) {

                Log.d("Avocarrot", "Ad did load");

                ImageView adImage = (ImageView) game_notification_board.findViewById(R.id.avo_native_creative);
                TextView adDescription = (TextView) game_notification_board.findViewById(R.id.avo_native_description);
                TextView headline = (TextView) game_notification_board.findViewById(R.id.avo_native_headline);
                Button adButton = (Button) game_notification_board.findViewById(R.id.avo_cta_button);
                Button closeButton = (Button) game_notification_board.findViewById(R.id.avo_close_button);

                adDescription.setText(ad.getSubHeadline());
                headline.setText(ad.getHeadline());
                adImage.setImageBitmap(ad.getImage());
                adButton.setText(ad.getCTAText());

                // Bind View
                ad.bindView(game_notification_board);

                // Show ad on click
                adButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ad.handleClick();
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

            @Override
            public void adDidNotLoad(String reason) {
                Log.d("Avocarrot", "adDidNotLoad: " + reason);
            }

            @Override
            public void adDidFailToLoad(Exception e) {
                Log.e("Avocarrot", "adDidFailToLoad", e);
            }

            @Override
            public void onAdImpression(String message) {
                Log.d("Avocarrot", "onAdImpression: " + message);
            }

            @Override
            public void onAdClick(String message) {
                Log.d("Avocarrot", "onAdClick: " + message);
            }

            @Override
            public void userWillLeaveApp() {
                Log.d("Avocarrot", "userWillLeaveApp");
            }

        });

    }

    // Animate objects
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void animateObjects(View cloudImage1, View cloudImage2, View monster, Context mContext) {

        ObjectAnimator cloudAnim = ObjectAnimator.ofFloat(cloudImage1, "x", 690f);
        cloudAnim.setDuration(3000);
        cloudAnim.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnim.setRepeatMode(ValueAnimator.REVERSE);
        cloudAnim.start();

        ObjectAnimator cloudAnim2 = ObjectAnimator.ofFloat(cloudImage2, "x", 800f);
        cloudAnim2.setDuration(8000);
        cloudAnim2.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnim2.setRepeatMode(ValueAnimator.REVERSE);
        cloudAnim2.start();

        monster.setRotation(-20f);
        ObjectAnimator ballAnimator = ObjectAnimator.ofFloat(monster, "rotation", 20f);
        ballAnimator.setDuration(2000);
        ballAnimator.setRepeatCount(ValueAnimator.INFINITE);
        ballAnimator.setRepeatMode(ValueAnimator.REVERSE);
        ballAnimator.start();

        monster.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.game_monster_zoom_in));
    }

    public static Fragment getInstance() {
        return new ExampleGame();
    }

}