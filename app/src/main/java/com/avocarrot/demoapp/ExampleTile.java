package com.avocarrot.demoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocarrot.androidsdk.instream.AvocarrotInstream;
import com.avocarrot.demoapp.helpers.DemoContent;

import java.util.Arrays;

public class ExampleTile extends Fragment {

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tile, container, false);
        gridView = (GridView) view.findViewById(R.id.gridViewBlock);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyGridAdapter adapter = new MyGridAdapter(getActivity());

        // --------------------------------------------------------
        // Avocarrot Integration: Instream
        AvocarrotInstream tileAdapter = new AvocarrotInstream(adapter);
        tileAdapter.initWithKey("3dbab458941a2446e2b48ac866b42027f5cac288");
        tileAdapter.setSandbox(true);
        tileAdapter.setLogger(true, "ALL");
        tileAdapter.setFrequency(3, 3);
        tileAdapter.setLayout(
            R.layout.avo_tile_layout,
            R.id.avo_container,
            R.id.avo_native_headline,
            R.id.avo_native_description,
            0,
            R.id.avo_native_image,
            R.id.avo_cta_button
        );
        tileAdapter.loadAdForPlacement(getActivity(), "custom");

        gridView.setAdapter(tileAdapter);
        // --------------------------------------------------------

    }

    // Custom adapter for GridView
    class MyGridAdapter extends BaseAdapter {

        private boolean[] animatedCells;
        private DemoContent content;
        private LayoutInflater inflater;

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

        public MyGridAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            content = new DemoContent(context);
            animatedCells = new boolean[content.getSize()];
            Arrays.fill(animatedCells, Boolean.FALSE);
        }

        public void handleAnimation(View currentView, int position) {

            try {
                if ((position > animatedCells.length) || animatedCells[position])
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
                Log.d("ExampleTile", "Problem with animating cells: " + e.toString());
            }
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_tile_row, parent, false);
                holder = new ViewHolder();
                holder.tileText = (TextView) convertView.findViewById(R.id.tile_title);
                holder.tileNumber = (TextView) convertView.findViewById(R.id.tile_number);
                holder.tileLocation = (TextView) convertView.findViewById(R.id.tile_location);
                holder.tileImage = (ImageView) convertView.findViewById(R.id.tile_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tileText.setText(content.getPostFor(position));
            holder.tileNumber.setText(content.getNumberFor(position));
            holder.tileLocation.setText(content.getLocationFor(position));
            holder.tileImage.setImageDrawable(content.getSquareImageFor(position));

            handleAnimation(convertView, position);

            return convertView;
        }

        class ViewHolder {
            TextView tileText, tileNumber, tileLocation;
            ImageView tileImage;
        }
    }

    public static Fragment getInstance() {
        return new ExampleTile();
    }

}
