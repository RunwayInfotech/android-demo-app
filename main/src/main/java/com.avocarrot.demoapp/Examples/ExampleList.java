package com.avocarrot.demoapp.main.Examples;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ExampleList extends LinearLayout {

    public ExampleList(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        View baseView = inflate(getContext(), R.layout.fragment_list, null);
        addView(baseView);

        class MyCustomAdapter extends BaseAdapter {
            private DemoContent content = new DemoContent(getContext());

            private LayoutInflater inflater;
            private CustomAdItem customAd = null;

            @Override public int getCount()               { return content.getSize(); }
            @Override public Object getItem(int position) { return null;              }
            @Override public long getItemId(int position) { return 0;                 }

            public MyCustomAdapter (Context context) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            public View getView(final int position, View convertView, ViewGroup parent) {

                View currentView = inflater.inflate(R.layout.fragment_list_row, null);

                TextView avatarName     = (TextView)  currentView.findViewById(R.id.list_avatar_name);
                TextView avatarLocation = (TextView)  currentView.findViewById(R.id.list_avatar_location);
                TextView avatarStatus   = (TextView)  currentView.findViewById(R.id.list_avatar_status);
                ImageView avatarIcon    = (ImageView) currentView.findViewById(R.id.list_avatar_image);

                avatarName.setText(content.getNameFor(position));
                avatarLocation.setText(content.getLocationFor(position));
                avatarStatus.setText(content.getPostFor(position));
                avatarIcon.setImageDrawable(content.getIconFor(position));

                return currentView;
            }
        }

        MyCustomAdapter myListAdapter = new MyCustomAdapter(getContext());

        // --------------------------------------------------------
        // Avocarrot integration: In-Stream

        AvocarrotInstream myInStreamAd1 = new AvocarrotInstream(myListAdapter);
        myInStreamAd1.setFrequency(2, 3);
        myInStreamAd1.setLayout(
                R.layout.avo_list_layout,
                R.id.avo_container,
                R.id.avo_native_headline,
                R.id.avo_native_image,
                R.id.avo_cta_button
        );
        myInStreamAd1.loadAdForPlacement((Activity) getContext(), "morpheus_list");
        myInStreamAd1.setAdListener(new AvocarrotInstreamListener() {
            @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
            @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
            @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
            @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
        });

        // --------------------------------------------------------

        ListView myList= (ListView) baseView.findViewById(R.id.listView);
        myList.setAdapter(myInStreamAd1);
    }
}