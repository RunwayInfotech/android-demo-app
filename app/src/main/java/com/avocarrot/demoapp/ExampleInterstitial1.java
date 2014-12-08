package com.avocarrot.demoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avocarrot.androidsdk.interstitial.AvocarrotInterstitial;
import com.avocarrot.androidsdk.interstitial.AvocarrotInterstitialListener;

public class ExampleInterstitial1 extends Fragment {

    ProgressBar loadingBar;
    TextView loadingLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interstitial, container, false);
        loadingBar = (ProgressBar) view.findViewById(R.id.progressBar);
        loadingLabel = (TextView) view.findViewById(R.id.progressBarLabel);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // --------------------------------------------------------
        // Avocarrot Integration: Interstitial

        final AvocarrotInterstitial myInterstitialAd = new AvocarrotInterstitial();
        myInterstitialAd.initWithKey("3dbab458941a2446e2b48ac866b42027f5cac288");
        myInterstitialAd.setSandbox(true);
        myInterstitialAd.setLogger(true, "ALL");
        myInterstitialAd.showAd(getActivity(), "activity_triggered_1");
        myInterstitialAd.setAdListener(new AvocarrotInterstitialListener() {
            @Override
            public void adDidLoad() {
                Log.d("Avocarrot", "adDidLoad");
                loadingBar.setVisibility(View.GONE);
                loadingLabel.setVisibility(View.GONE);
            }

            @Override
            public void adDidNotLoad(String reason) {
                loadingBar.setVisibility(View.GONE);
                loadingLabel.setText("Ad did fail to load");
                Log.d("Avocarrot", "adDidNotLoad: " + reason);
            }

            @Override
            public void adDidFailToLoad(Exception e) {
                Log.e("Avocarrot", "adDidFailToLoad: " + e);
                loadingBar.setVisibility(View.GONE);
                loadingLabel.setText("Ad did fail to load");
            }

            @Override
            public void adWillAppear() {
                Log.d("Avocarrot", "adWillAppear");
            }

            @Override
            public void adDidAppear() {
                Log.d("Avocarrot", "adDidAppear");
            }

            @Override
            public void adWillDisappear() {
                Log.d("Avocarrot", "adWillDisappear");
            }

            @Override
            public void adDidDisappear() {
                Log.d("Avocarrot", "adDidDisappear");
            }

            @Override
            public void userWillLeaveApp() {
                Log.d("Avocarrot", "userWillLeaveApp");
            }
        });
        // --------------------------------------------------------
    }

    public static Fragment getInstance() {
        return new ExampleInterstitial1();
    }

}
