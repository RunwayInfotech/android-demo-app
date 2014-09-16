package com.avocarrot.avocarrotdemoapp.main.Examples;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avocarrot.androidsdk.AvocarrotCustomListener;
import com.avocarrot.androidsdk.custom.AvocarrotCustom;
import com.avocarrot.androidsdk.custom.CustomAdItem;
import com.avocarrot.avocarrotdemoapp.main.Helpers.DemoContent;
import com.avocarrot.avocarrotdemoapp.main.R;

import java.util.Arrays;

public class ExampleTile extends LinearLayout {

    private boolean cellWasAnimated = false;
    public ExampleTile(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        View baseView = inflate(getContext(), R.layout.fragment_tile, null);
        addView(baseView);

        // Custom adapter for GridView
        class MyGridAdapter extends BaseAdapter {

            private int adSlotPosition = 3;
            private boolean[] animatedCells;
            private DemoContent content = new DemoContent(getContext());

            private LayoutInflater inflater;
            private CustomAdItem customAd = null;

            @Override public int getCount()               { return content.getSize(); }
            @Override public Object getItem(int position) { return null; }
            @Override public long getItemId(int position) { return 0;}

            public MyGridAdapter (Context context) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                animatedCells = new boolean[this.getCount()];
                Arrays.fill(animatedCells, Boolean.FALSE);
            }

            public void registerAd(CustomAdItem ad){
                customAd = ad;
                notifyDataSetChanged();
            }

            public void handleAnimation(View currentView, int position) {

                try {
                    if ((position > animatedCells.length) || (animatedCells[position] == true))
                        return;
                    animatedCells[position] = true;

                    currentView.setVisibility(View.VISIBLE);

                    AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setDuration(200);
                    fadeIn.setStartOffset(position * 100);
                    fadeIn.setFillAfter(true);
                    currentView.startAnimation(fadeIn);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("ExampleTile", "Problem with animating cells: "+ e.toString());
                }
            }
            public View getView(final int position, View convertView, ViewGroup parent) {

                View gridView = inflater.inflate(R.layout.fragment_tile_row, null);

                TextView tileText     = (TextView) gridView.findViewById(R.id.tile_title);
                TextView tileNumber   = (TextView) gridView.findViewById(R.id.tile_number);
                TextView tileLocation = (TextView) gridView.findViewById(R.id.tile_location);
                ImageView tileImage   = (ImageView) gridView.findViewById(R.id.tile_image);

                // Normalize position
                int currentPosition = (position > adSlotPosition)?position:position +1;

                // Show a native Ad on position 3
                if (position == adSlotPosition) {
                    gridView  = inflater.inflate(R.layout.avo_tile_layout, null);

                    TextView nativeAdHeadline= (TextView) gridView.findViewById(R.id.avo_native_headline);
                    ImageView nativeAdImage= (ImageView) gridView.findViewById(R.id.avo_native_image);
                    Button nativeButton= (Button) gridView.findViewById(R.id.avo_cta_button);

                    if (customAd!=null) {

                        // Fill in details
                        if (customAd.getCTAText() != null)
                            nativeButton.setText(customAd.getCTAText());

                        if (customAd.getHeadline() != null)
                            nativeAdHeadline.setText(customAd.getHeadline());

                        if (customAd.getImage() != null)
                            nativeAdImage.setImageBitmap(customAd.getImage());

                        // Bind view
                        customAd.bindView(gridView);

                        //Set click listener
                        nativeButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) { customAd.handleClick();}
                        });

                        // Show elements
                        if (!cellWasAnimated) {
                            cellWasAnimated = true;
                            gridView.setAlpha(0f);
                            gridView.animate().alpha(1f).setDuration(150).setInterpolator(new AccelerateInterpolator());
                        }
                    } else {
                        // Hide Elements
                        gridView.setAlpha(0f);
                    }

                } else {
                    // Else return normal cell
                    tileText.setText(content.getPostFor(currentPosition));
                    tileNumber.setText(content.getNumberFor(currentPosition));
                    tileLocation.setText(content.getLocationFor(currentPosition));
                    tileImage.setImageDrawable(content.getSquareImageFor(currentPosition));
                }
                handleAnimation(gridView, currentPosition);
                return gridView;
            }
        }

        // Create Grid View

        final GridView gridView = (GridView) baseView.findViewById(R.id.gridViewBlock);
        final MyGridAdapter gridViewAdapter = new MyGridAdapter(getContext());
        gridView.setAdapter(gridViewAdapter);

        // --------------------------------------------------------
        // Avocarrot Integration: Custom

        AvocarrotCustom myTileAd = new AvocarrotCustom();
        myTileAd.loadAdForPlacement((Activity) getContext(), "custom");
        myTileAd.setAdListener(new AvocarrotCustomListener() {
            @Override public void adDidLoad(CustomAdItem ad)     {
                Log.d("Avocarrot", "adDidLoad");
                gridViewAdapter.registerAd(ad);
                gridView.invalidateViews();
            }
            @Override public void adDidNotLoad(String reason)    { Log.d("Avocarrot", "adDidNotLoad: " + reason);    }
            @Override public void adDidFailToLoad(Exception e)   { Log.e("Avocarrot", "adDidFailToLoad: " + e);      }
            @Override public void userWillLeaveApp()             { Log.d("Avocarrot", "userWillLeaveApp");           }
            @Override public void onAdImpression(String message) { Log.d("Avocarrot", "onAdImpression: " + message); }
            @Override public void onAdClick(String message)      { Log.d("Avocarrot", "onAdClick: " + message);      }

        });
        // --------------------------------------------------------

    }
}
