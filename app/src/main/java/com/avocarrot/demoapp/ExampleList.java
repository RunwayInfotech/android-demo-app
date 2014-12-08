package com.avocarrot.demoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocarrot.androidsdk.custom.CustomAdItem;
import com.avocarrot.androidsdk.instream.AvocarrotInstream;
import com.avocarrot.androidsdk.instream.AvocarrotInstreamListener;
import com.avocarrot.demoapp.helpers.DemoContent;

public class ExampleList extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyCustomAdapter myListAdapter = new MyCustomAdapter(getActivity());

        // --------------------------------------------------------
        // Avocarrot integration: In-Stream

        AvocarrotInstream myInStreamAd1 = new AvocarrotInstream(myListAdapter);
        myInStreamAd1.initWithKey("3dbab458941a2446e2b48ac866b42027f5cac288");
        myInStreamAd1.setSandbox(true);
        myInStreamAd1.setLogger(true, "ALL");
        myInStreamAd1.setFrequency(2, 3);
        myInStreamAd1.setLayout(
                R.layout.avo_list_layout,
                R.id.avo_container,
                R.id.avo_native_headline,
                R.id.avo_native_image,
                R.id.avo_cta_button
        );
        myInStreamAd1.loadAdForPlacement(getActivity(), "morpheus_list");
        myInStreamAd1.setAdListener(new AvocarrotInstreamListener() {
            @Override
            public void adDidLoad() {
                Log.d("Avocarrot", "adDidLoad");
            }

            @Override
            public void adDidNotLoad(String reason) {
                Log.d("Avocarrot", "adDidNotLoad: " + reason);
            }

            @Override
            public void adDidFailToLoad(Exception e) {
                Log.e("Avocarrot", "adDidFailToLoad: " + e);
            }

            @Override
            public void userWillLeaveApp() {
                Log.d("Avocarrot", "userWillLeaveApp");
            }
        });

        // --------------------------------------------------------

        setListAdapter(myInStreamAd1);
    }


    class MyCustomAdapter extends BaseAdapter {
        private DemoContent content;

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
            content = new DemoContent(context);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.fragment_list_row, parent, false);

                holder.avatarName = (TextView) convertView.findViewById(R.id.list_avatar_name);
                holder.avatarLocation = (TextView) convertView.findViewById(R.id.list_avatar_location);
                holder.avatarStatus = (TextView) convertView.findViewById(R.id.list_avatar_status);
                holder.avatarIcon = (ImageView) convertView.findViewById(R.id.list_avatar_image);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.avatarName.setText(content.getNameFor(position));
            holder.avatarLocation.setText(content.getLocationFor(position));
            holder.avatarStatus.setText(content.getPostFor(position));
            holder.avatarIcon.setImageDrawable(content.getIconFor(position));

            return convertView;
        }

        public class ViewHolder {
            TextView avatarName, avatarLocation, avatarStatus;
            ImageView avatarIcon;
        }

    }

    public static Fragment getInstance() {
        return new ExampleList();
    }

}