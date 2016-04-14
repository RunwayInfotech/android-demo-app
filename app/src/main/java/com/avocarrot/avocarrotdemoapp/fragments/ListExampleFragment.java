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
import com.avocarrot.avocarrotdemoapp.models.DemoListItem;
import com.avocarrot.avocarrotdemoapp.utils.AppUtils;
import com.avocarrot.avocarrotdemoapp.utils.DemoItemCollection;
import com.avocarrot.avocarrotdemoapp.utils.RecyclerViewDivider;
import com.avocarrot.avocarrotdemoapp.viewholders.ListViewHolder;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListExampleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;


    //Initialize click listener for RecyclerView
    private  ListViewHolder.DemoListItemListener demoListItemListener=new ListViewHolder.DemoListItemListener() {
        @Override
        public void onDemoListItemClicked(DemoListItem demoListItem) {
            Toast.makeText(getContext(),"Demo List Item clicked",Toast.LENGTH_SHORT).show();
        }
    };

    public ListExampleFragment() {
        // Required empty public constructor
    }

    public static ListExampleFragment newInstance() {

        Bundle args = new Bundle();

        ListExampleFragment fragment = new ListExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list_example, container, false);
        ButterKnife.bind(this,view);

        swipeContainer.setOnRefreshListener(this);

        recyclerView.addItemDecoration(new RecyclerViewDivider(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Init Avocarrot Adapter
        initAvocarrotAdapter();

        return view;
    }

    private void initAvocarrotAdapter(){

        //Initialize user adapter
        EasyRecyclerAdapter adapter=  new EasyRecyclerAdapter<DemoListItem>(
                getContext(),
                ListViewHolder.class,
                DemoItemCollection.getDemoListItems(),
                demoListItemListener);

        //Initialize Avocarrot Adapter
        AvocarrotInstreamRecyclerView myListAdapter = new AvocarrotInstreamRecyclerView(adapter, getActivity(), AppUtils.getApiId(), AppUtils.getListPlacementId());
        //Set sandbox mode on (this need to be removed for live ads)
        myListAdapter.setSandbox(true);
        //Enable logging
        myListAdapter.setLogger(true, "ALL");
        //Set first ad position and step for the next
        myListAdapter.setFrequency(2, 5);

        //Apply avocarrot adapter to recyclerview
        recyclerView.setAdapter(myListAdapter);


    }

    @Override
    public void onRefresh() {
        swipeContainer.setRefreshing(false);
        initAvocarrotAdapter();
    }


}
