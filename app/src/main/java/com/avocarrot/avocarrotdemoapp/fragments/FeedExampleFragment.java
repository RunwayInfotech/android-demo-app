package com.avocarrot.avocarrotdemoapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avocarrot.androidsdk.AvocarrotInstreamRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.models.DemoFeedItem;
import com.avocarrot.avocarrotdemoapp.utils.AppUtils;
import com.avocarrot.avocarrotdemoapp.utils.DemoItemCollection;
import com.avocarrot.avocarrotdemoapp.viewholders.FeedViewHolder;

import java.util.ArrayList;
import java.util.List;

import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedExampleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    private List<DemoFeedItem> feeds;

    //Initialize click listener for RecyclerView
    private FeedViewHolder.DemoFeedItemListener demoFeedItemListener=new FeedViewHolder.DemoFeedItemListener() {
        @Override
        public void onDemoFeedItemClicked(DemoFeedItem demoFeedItem) {
            Toast.makeText(getContext(),"Demo Feed Item clicked",Toast.LENGTH_SHORT).show();
        }
    };

    public FeedExampleFragment() {
        // Required empty public constructor
    }

    public static FeedExampleFragment newInstance() {

        Bundle args = new Bundle();

        FeedExampleFragment fragment = new FeedExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feed_example, container, false);
        ButterKnife.bind(this,view);
        swipeContainer.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Init Avocarrot Adapter
        initAvocarrotAdapter();
        return view;
    }


    private void initAvocarrotAdapter(){
        feeds=new ArrayList<>(DemoItemCollection.getDemoFeedItems());
        //Initialize user adapter
        EasyRecyclerAdapter adapter=  new EasyRecyclerAdapter<DemoFeedItem>(
                getContext(),
                FeedViewHolder.class,
                feeds
        );

        //Initialize Avocarrot Adapter
        AvocarrotInstreamRecyclerView  myFeedAdapter = new AvocarrotInstreamRecyclerView(adapter, getActivity(), AppUtils.getApiId(), AppUtils.getFeedPlacementId());
        //Set sandbox mode on (this need to be removed for live ads)
        myFeedAdapter.setSandbox(true);
        //Enable logging
        myFeedAdapter.setLogger(true, "ALL");
        //Set first ad position and step for the next
        myFeedAdapter.setFrequency(1, 4);

        //Apply avocarrot adapter to recyclerview
        recyclerView.setAdapter(myFeedAdapter);
    }



    @Override
    public void onRefresh() {
        swipeContainer.setRefreshing(false);
        initAvocarrotAdapter();
    }
}
