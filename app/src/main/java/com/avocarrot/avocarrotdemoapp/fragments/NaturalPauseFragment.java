package com.avocarrot.avocarrotdemoapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.avocarrot.androidsdk.AvocarrotInterstitial;
import com.avocarrot.androidsdk.AvocarrotInterstitialListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.utils.AppUtils;

/**
 * Created by theokir on 06/04/16.
 */
public class NaturalPauseFragment extends Fragment {
    @Bind(R.id.showPreloadAd)
    Button showPreloadAd;

    @Bind(R.id.loadAd)
    Button loadAd;


    private AvocarrotInterstitial preloadedInterstitial;

    public NaturalPauseFragment() {
        // Required empty public constructor
    }

    public static NaturalPauseFragment newInstance() {

        Bundle args = new Bundle();

        NaturalPauseFragment fragment = new NaturalPauseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_natural_pause, container, false);
        ButterKnife.bind(this, view);

        setButtonState(true);
        return view;
    }
    //Preload ad
    @OnClick(R.id.loadAd)
    public void loadAd(){
        //Initialize Avocarrot Adapter
        preloadedInterstitial = new AvocarrotInterstitial(getActivity(), AppUtils.getApiId(), AppUtils.getNaturalPausePlacementId1());
        //Set sandbox mode on (this need to be removed for live ads)
        preloadedInterstitial.setSandbox(true);
        //Enable logging
        preloadedInterstitial.setLogger(true, "ALL");
        //Set AvocarrotInterstitialListener to track when the ad is loaded
        preloadedInterstitial.setListener(new AvocarrotInterstitialListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setButtonState(false);
            }
        });
        //Load ad
        preloadedInterstitial.loadAd();
    }

    //Load and show ad
    @OnClick(R.id.showAd)
    public void showAd(){
        //Initialize Avocarrot Adapter
        AvocarrotInterstitial interstitial = new AvocarrotInterstitial(getActivity(), AppUtils.getApiId(), AppUtils.getNaturalPausePlacementId2());
        //Set sandbox mode on (this need to be removed for live ads)
        interstitial.setSandbox(true);
        //Enable logging
        interstitial.setLogger(true, "ALL");
        //Load and show ad
        interstitial.loadAndShowAd();

    }

    //Instantly show the preloaded ad
    @OnClick(R.id.showPreloadAd)
    public void showPreloadAd(){
        //Show the preloaded ad
        preloadedInterstitial.showAd();
        setButtonState(true);
    }

    private void setButtonState(boolean loadState){
        showPreloadAd.setEnabled(!loadState);
        loadAd.setEnabled(loadState);


    }
}