package com.avocarrot.avocarrotdemoapp.main.Examples;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avocarrot.androidsdk.AvocarrotInterstitialListener;
import com.avocarrot.androidsdk.interstitial.AvocarrotInterstitial;
import com.avocarrot.avocarrotdemoapp.main.R;

public class ExampleInterstitial2 extends LinearLayout {

    public ExampleInterstitial2(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        View baseView = inflate(getContext(), R.layout.fragment_interstitial, null);
        addView(baseView);

        final ProgressBar loadingBar1 = (ProgressBar) baseView.findViewById(R.id.progressBar);
        final TextView loadingLabel1  = (TextView)    baseView.findViewById(R.id.progressBarLabel);

        // --------------------------------------------------------
        // Avocarrot Integration: Interstitial

        final AvocarrotInterstitial myInterstitialAd = new AvocarrotInterstitial();

        myInterstitialAd.showAd((Activity) getContext(), "activity_triggered_2");
        myInterstitialAd.setAdListener(new AvocarrotInterstitialListener() {
            @Override public void adDidLoad() {
                Log.d("Avocarrot", "adDidLoad");
                loadingBar1.setVisibility(View.GONE);
                loadingLabel1.setVisibility(View.GONE);
            }
            @Override public void adDidNotLoad(String reason)  {
                loadingBar1.setVisibility(View.GONE);
                loadingLabel1.setText("Ad did fail to load");
                Log.d("Avocarrot", "adDidNotLoad: " + reason);
            }
            @Override public void adDidFailToLoad(Exception e) {
                Log.e("Avocarrot", "adDidFailToLoad: " + e);
                loadingBar1.setVisibility(View.GONE);
                loadingLabel1.setText("Ad did fail to load");
            }
            @Override public void adWillAppear()       { Log.d("Avocarrot", "adWillAppear");            }
            @Override public void adDidAppear()        { Log.d("Avocarrot", "adDidAppear");             }
            @Override public void adWillDisappear()    { Log.d("Avocarrot", "adWillDisappear");         }
            @Override public void adDidDisappear()     { Log.d("Avocarrot", "adDidDisappear");          }
            @Override public void userWillLeaveApp()   { Log.d("Avocarrot", "userWillLeaveApp");        }
        });
    }
    // --------------------------------------------------------

}
