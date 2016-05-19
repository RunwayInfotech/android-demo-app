package com.avocarrot.avocarrotdemoapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avocarrot.androidsdk.AvocarrotInstreamRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.models.DemoListItem;
import com.avocarrot.avocarrotdemoapp.models.DemoTileItem;
import com.avocarrot.avocarrotdemoapp.utils.AppUtils;
import com.avocarrot.avocarrotdemoapp.utils.DemoItemCollection;
import com.avocarrot.avocarrotdemoapp.viewholders.ListViewHolder;
import com.avocarrot.avocarrotdemoapp.viewholders.TileViewHolder;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TileExampleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    public TileExampleFragment() {
        // Required empty public constructor
    }

    //Initialize click listener for RecyclerView
    private  TileViewHolder.DemoTileItemListener demoTileItemListener=new TileViewHolder.DemoTileItemListener() {
        @Override
        public void onDemoTileItemClicked(DemoTileItem demoTileItem) {
            Toast.makeText(getContext(),"Demo Tile Item clicked",Toast.LENGTH_SHORT).show();
        }
    };


    public static TileExampleFragment newInstance() {

        Bundle args = new Bundle();

        TileExampleFragment fragment = new TileExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tile_example, container, false);
        ButterKnife.bind(this,view);
        swipeContainer.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        //Init Avocarrot Adapter
        initAvocarrotAdapter();


        return view;
    }

    private void initAvocarrotAdapter(){
        //Initialize user adapter
        EasyRecyclerAdapter adapter=  new EasyRecyclerAdapter<DemoTileItem>(
                getContext(),
                TileViewHolder.class,
                DemoItemCollection.getDemoTileItems(),
                demoTileItemListener);

        //Initialize Avocarrot Adapter
        AvocarrotInstreamRecyclerView myTileAdapter = new AvocarrotInstreamRecyclerView(adapter, getActivity(), AppUtils.getApiId(), AppUtils.getTilePlacementId());
        //Set sandbox mode on (this need to be removed for live ads)
        myTileAdapter.setSandbox(true);
        //Enable logging
        myTileAdapter.setLogger(true, "ALL");
        //Set first ad position and step for the next
        myTileAdapter.setFrequency(1,4);
        //Bind our custom layout elements to Avocarrot adapter
        myTileAdapter.setLayout(
                R.layout.avo_tile_layout,
                R.id.avo_container,
                R.id.avo_native_headline,
                -1,
                -1,
                R.id.avo_native_image,
                R.id.avo_cta_button,
                R.id.videoView,
                R.id.adChoices
        );
        //Apply avocarrot adapter to recyclerview
        recyclerView.setAdapter(myTileAdapter);
    }
    @Override
    public void onRefresh() {
        swipeContainer.setRefreshing(false);
        initAvocarrotAdapter();
    }
}
