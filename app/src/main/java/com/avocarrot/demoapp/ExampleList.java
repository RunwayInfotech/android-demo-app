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

import com.avocarrot.androidsdk.AvocarrotInstream;
import com.avocarrot.demoapp.helpers.DemoContent;
import com.avocarrot.demoapp.main.R;

public class ExampleList extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyCustomAdapter myListAdapter = new MyCustomAdapter(getActivity());

        // --------------------------------------------------------
        // Avocarrot integration: In-Stream

        AvocarrotInstream myInStreamAd1 = new AvocarrotInstream(myListAdapter, getActivity(), "3dbab458941a2446e2b48ac866b42027f5cac288", "0aa0d2193aca5716b25bfaee403a91f588953a07");
        myInStreamAd1.setSandbox(true);
        myInStreamAd1.setLogger(true, "ALL");
        myInStreamAd1.setFrequency(2, 3);

        // --------------------------------------------------------

        setListAdapter(myInStreamAd1);
    }


    class MyCustomAdapter extends BaseAdapter {
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