package com.avocarrot.demoapp.main.Examples;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.avocarrot.androidsdk.AvocarrotInstreamListener;
import com.avocarrot.androidsdk.custom.CustomAdItem;
import com.avocarrot.androidsdk.instream.AvocarrotInstream;
import com.avocarrot.demoapp.main.Helpers.DemoContent;
import com.avocarrot.demoapp.main.R;

import java.util.Arrays;

public class ExampleFeed extends LinearLayout {

    public ExampleFeed(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        View baseView = inflate(getContext(), R.layout.fragment_feed, null);
        addView(baseView);

        // Custom adapter for GridView
        class MyCustomAdapter extends BaseAdapter {

            private DemoContent content = new DemoContent(getContext());
            private boolean[]animatedCells ;

            private LayoutInflater inflater;
            private CustomAdItem customAd = null;

            @Override
            public int getCount() {
                return content.getSize();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            public MyCustomAdapter(Context context) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                animatedCells = new boolean[this.getCount()];
                Arrays.fill(animatedCells, Boolean.FALSE);
            }
            public void handleAnimation(View currentView, int position){
                try{
                    if ((position > animatedCells.length) || (animatedCells[position]==true))
                        return;

                    animatedCells[position]=true;
                    AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setDuration(800);
                    fadeIn.setFillAfter(true);
                    currentView.startAnimation(fadeIn);

                } catch (Exception e){
                    e.printStackTrace();
                    Log.d("ExampleFeed", "Problem with animating cells: "+ e.toString());
                }
            }
            public View getView(final int position, View convertView, ViewGroup parent) {

                View currentView = inflater.inflate(R.layout.fragment_feed_row, null);

                TextView feedName = (TextView) currentView.findViewById(R.id.feed_name);
                TextView feedDate = (TextView) currentView.findViewById(R.id.feed_date);
                TextView feedLikeCount = (TextView) currentView.findViewById(R.id.feed_like_count);
                ImageView feedImage = (ImageView) currentView.findViewById(R.id.feed_image);
                ImageView feedIcon = (ImageView) currentView.findViewById(R.id.feed_icon);

                feedName.setText(content.getNameFor(position));
                feedDate.setText(content.getDateFor(position));
                feedLikeCount.setText(content.getNumberFor(position));
                feedIcon.setImageDrawable(content.getIconFor(position));
                feedImage.setImageDrawable(content.getImageFor(position));

                handleAnimation(currentView,position);

                return currentView;
            }
        }

        final MyCustomAdapter myCustomAdapter= new MyCustomAdapter(getContext());
        ListView myFeed = (ListView) baseView.findViewById(R.id.listView);
        myFeed.animate().alpha(1F).setDuration(2000);

        // --------------------------------------------------------
        // Avocarrot integration: In-Stream

        AvocarrotInstream myFeedAdapter = new AvocarrotInstream(myCustomAdapter);
        myFeedAdapter.setAdListener(new AvocarrotInstreamListener() {
            @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
            @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
            @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
            @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
        });
        myFeedAdapter.setFrequency(1, 4);
        myFeedAdapter.setLayout(
                R.layout.avo_feed_layout,
                R.id.avo_container,
                R.id.avo_native_headline,
                R.id.avo_native_description,
                R.id.avo_native_image,
                R.id.avo_native_creative,
                R.id.avo_cta_button
        );

        myFeedAdapter.loadAdForPlacement((Activity) getContext(), "instream_1");
        myFeed.setAdapter(myFeedAdapter);

        // --------------------------------------------------------

    }
}

