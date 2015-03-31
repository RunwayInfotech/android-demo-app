package com.avocarrot.demoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avocarrot.androidsdk.AdError;
import com.avocarrot.androidsdk.AvocarrotInterstitial;
import com.avocarrot.androidsdk.AvocarrotInterstitialListener;
import com.avocarrot.demoapp.main.R;

public class ExampleInterstitialSimple extends Fragment {

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

        AvocarrotInterstitial interstitial = new AvocarrotInterstitial(getActivity(), "3dbab458941a2446e2b48ac866b42027f5cac288", "824db3658bdd2b565f8ffad3c75308ee5bc50f18");
        interstitial.setSandbox(true);
        interstitial.setLogger(true, "ALL");

        interstitial.setListener(new AvocarrotInterstitialListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                loadingBar.setVisibility(View.GONE);
                loadingLabel.setVisibility(View.GONE);
            }

            @Override
            public void onAdError(AdError error) {
                super.onAdError(error);
                loadingBar.setVisibility(View.GONE);
                loadingLabel.setText("Ad did fail to load");
            }
        });
        interstitial.loadAndShowAd();
        // --------------------------------------------------------
    }

    public static Fragment getInstance() {
        return new ExampleInterstitialSimple();
    }

}
