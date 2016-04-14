package com.avocarrot.avocarrotdemoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avocarrot.androidsdk.AvocarrotCustom;
import com.avocarrot.androidsdk.AvocarrotCustomListener;
import com.avocarrot.androidsdk.CustomModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.utils.AppUtils;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by theokir on 06/04/16.
 */
public class GameExampleFragment extends Fragment {
    @Bind(R.id.game_target)
    ImageView target;
    @Bind(R.id.cloud1)
    ImageView cloudImage1;
    @Bind(R.id.cloud2)
    ImageView cloudImage2;


    @Bind(R.id.game_notification_board)
    RelativeLayout game_notification_board;

    public GameExampleFragment() {
        // Required empty public constructor
    }

    public static GameExampleFragment newInstance() {

        Bundle args = new Bundle();

        GameExampleFragment fragment = new GameExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_game_example, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        game_notification_board.setVisibility(GONE);
        animateObjects(cloudImage1, cloudImage2, target, getActivity());
        configureAndLoadAd();
    }

    @OnTouch(R.id.game_target)
    public boolean onTargetTouch(View v, MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Show Ad
            game_notification_board.setVisibility(VISIBLE);

        }
        return true;
    }

    private void configureAndLoadAd(){
        //Initialize Avocarrot Adapter
        final AvocarrotCustom myCustomAd = new AvocarrotCustom(getActivity(), AppUtils.getApiId(), AppUtils.getGamePlacementId() );
        //Set sandbox mode on (this need to be removed for live ads)
        myCustomAd.setSandbox(true);
        //Enable logging
        myCustomAd.setLogger(true, "ALL");
        //Set AvocarrotInterstitialListener to track when the ad is loaded
        myCustomAd.setListener(new AvocarrotCustomListener() {
            @Override
            public void onAdLoaded(List<CustomModel> ads) {
                super.onAdLoaded(ads);

                if ((ads == null) || (ads.size() < 1))
                    return;

                Log.d("Avocarrot", "Ad did load");

                final CustomModel ad = ads.get(0);


                TextView adDescription = (TextView) game_notification_board.findViewById(R.id.avo_native_description);
                TextView headline = (TextView) game_notification_board.findViewById(R.id.avo_native_headline);
                Button adButton = (Button) game_notification_board.findViewById(R.id.avo_cta_button);
                Button closeButton = (Button) game_notification_board.findViewById(R.id.avo_close_button);
                ImageView adImage = (ImageView) game_notification_board.findViewById(R.id.avo_native_creative);
                adDescription.setText(ad.getDescription());
                headline.setText(ad.getTitle());

                //Use videoView from Avocarrot SDK (com.avocarrot.androidsdk.VideoView)
                com.avocarrot.androidsdk.VideoView videoView = (com.avocarrot.androidsdk.VideoView)game_notification_board.findViewById(R.id.avo_video_view);

                //Downloads and loads main image and video in your ui components
                myCustomAd.loadMedia(ad, adImage, videoView);


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
                        game_notification_board.setVisibility(View.GONE);
                    }
                });

            }
        });

        myCustomAd.loadAd();
    }

    public void animateObjects(View cloudImage1, View cloudImage2, View ball, Context mContext) {

        cloudImage1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.cloud_1));
        cloudImage2.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.cloud_2));

        ball.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.game_monster_zoom_in));
    }
}
