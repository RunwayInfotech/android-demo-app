package com.avocarrot.avocarrotdemoapp.main.Examples;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avocarrot.androidsdk.AvocarrotCustomListener;
import com.avocarrot.androidsdk.custom.AvocarrotCustom;
import com.avocarrot.androidsdk.custom.CustomAdItem;
import com.avocarrot.avocarrotdemoapp.main.R;

public class ExampleGame extends LinearLayout {

    private static long screenWidth,
                        screenHeight;

    public ExampleGame(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        View baseView = inflate(getContext(), R.layout.fragment_game, null);

        final ImageView target      = (ImageView) baseView.findViewById(R.id.game_target);
        final ImageView cloudImage1 = (ImageView) baseView.findViewById(R.id.cloud1);
        final ImageView cloudImage2 = (ImageView) baseView.findViewById(R.id.cloud2);

        final RelativeLayout game_screen= (RelativeLayout)baseView.findViewById(R.id.game_screen);
        final RelativeLayout game_notification_board = (RelativeLayout)baseView.findViewById(R.id.game_notification_board);
        game_notification_board.setVisibility(GONE);

        animateObjects(cloudImage1, cloudImage2, target, getContext());

        // Get screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        // GET SHOTS for target
        target.setOnTouchListener(new OnTouchListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN){

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
        final AvocarrotCustom myCustomAd = new AvocarrotCustom();
        myCustomAd.loadAdForPlacement((Activity) getContext(), "custom");
        myCustomAd.setAdListener(new AvocarrotCustomListener() {
            @Override
            public void adDidLoad( final CustomAdItem ad) {

                Log.d("Avocarrot", "Ad did load");

                ImageView adImage =      (ImageView) game_notification_board.findViewById(R.id.avo_native_creative);
                TextView adDescription = (TextView ) game_notification_board.findViewById(R.id.avo_native_description);
                TextView headline =      (TextView ) game_notification_board.findViewById(R.id.avo_native_headline);
                Button adButton =        (Button)    game_notification_board.findViewById(R.id.avo_cta_button);
                Button closeButton =     (Button)    game_notification_board.findViewById(R.id.avo_close_button);

                adDescription.setText(ad.getSubHeadline());
                headline.setText(ad.getHeadline());
                adImage.setImageBitmap(ad.getImage());
                adButton.setText(ad.getCTAText());

                // Bind View
                ad.bindView(game_notification_board);

                // Show ad on click
                adButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    ad.handleClick();
                    }
                });

                // Hide notice board on exit
                closeButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    game_notification_board.animate().alpha(0).setDuration(100).setInterpolator(new AccelerateInterpolator());
                    }
                });
            }

            @Override public void adDidNotLoad(String reason)    { Log.d("Avocarrot", "adDidNotLoad: " + reason);    }
            @Override public void adDidFailToLoad(Exception e)   { Log.e("Avocarrot", "adDidFailToLoad", e);         }
            @Override public void onAdImpression(String message) { Log.d("Avocarrot", "onAdImpression: " + message); }
            @Override public void onAdClick(String message)      { Log.d("Avocarrot", "onAdClick: " + message);      }
            @Override public void userWillLeaveApp()             { Log.d("Avocarrot", "userWillLeaveApp");           }

        });
        addView(baseView);
    }

    // Animate objects
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void animateObjects(View cloudImage1,View cloudImage2, View monster, Context mContext){

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
}