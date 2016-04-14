package com.avocarrot.avocarrotdemoapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avocarrot.avocarrotdemoapp.fragments.FeedExampleFragment;
import com.avocarrot.avocarrotdemoapp.fragments.GameExampleFragment;
import com.avocarrot.avocarrotdemoapp.fragments.HomeFragment;
import com.avocarrot.avocarrotdemoapp.fragments.ListExampleFragment;
import com.avocarrot.avocarrotdemoapp.fragments.NaturalPauseFragment;
import com.avocarrot.avocarrotdemoapp.fragments.TileExampleFragment;
import com.avocarrot.avocarrotdemoapp.utils.AppUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private View navigationHeaderView;
    private TextView barTitle;
    private LinearLayout imageLayout;
    private RelativeLayout buttonLayout;
    private TabLayout adTypesDrawer;
    private TabLayout adTypes;
    private int lastFragmentId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        barTitle=(TextView) findViewById(R.id.barTitle);
        buttonLayout=(RelativeLayout) findViewById(R.id.buttonLayout);
        imageLayout=(LinearLayout) findViewById(R.id.imageLayout);
        navigationView.setNavigationItemSelectedListener(this);
        navigationHeaderView=navigationView.getHeaderView(0);
        adTypesDrawer=(TabLayout)navigationHeaderView.findViewById(R.id.adTypesDrawer);
        adTypes=(TabLayout)findViewById(R.id.adTypes);


        initCustomTabs();
        initCustomNavigationDrawerIcon(drawer,toggle);
        setupToolbar(null);
        initEvents();
        replaceContainerFragment(-1);
    }

    private void initEvents(){

        RelativeLayout headerTopLayout=(RelativeLayout)navigationHeaderView.findViewById(R.id.topLayout);
        headerTopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrlToBrowser("http://www.avocarrot.com/contact/");
            }
        });
    }

    private void initCustomTabs(){
        TabLayout.Tab videoTab=adTypesDrawer.newTab().setCustomView(R.layout.custom_tab_video);
        TabLayout.Tab imageTab=adTypesDrawer.newTab().setCustomView(R.layout.custom_tab_image);
        adTypesDrawer.addTab(videoTab);
        adTypesDrawer.addTab(imageTab);
        videoTab.select();

    }

    private void initCustomNavigationDrawerIcon( final DrawerLayout drawer, ActionBarDrawerToggle toggle){
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_dehaze_black_24dp, getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        TabLayout.OnTabSelectedListener tabSelectedListener= new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchMode(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
        adTypesDrawer.setOnTabSelectedListener(tabSelectedListener);
        adTypes.setOnTabSelectedListener(tabSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feed) {
            setupToolbar(getString(R.string.option_feed));
            replaceContainerFragment(id);
        } else if (id == R.id.nav_list) {
            setupToolbar(getString(R.string.option_list));
            replaceContainerFragment(id);
        } else if (id == R.id.nav_tile) {
            setupToolbar(getString(R.string.option_tile));
            replaceContainerFragment(id);
        } else if (id == R.id.nav_games) {
            setupToolbar(getString(R.string.option_games));
             replaceContainerFragment(id);
        } else if (id == R.id.nav_natural_pause) {
            setupToolbar(getString(R.string.option_natural_pause));
            replaceContainerFragment(id);
        } else if (id == R.id.nav_unity) {
            openUrlToBrowser("https://github.com/Avocarrot/unity-demo-app");
        } else if (id == R.id.nav_adapters) {
            openUrlToBrowser("https://github.com/Avocarrot/android-adapters");
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getFragmentForId(int id){
        if (id == R.id.nav_list) {
            return ListExampleFragment.newInstance();
        } else if (id == R.id.nav_tile) {
            return TileExampleFragment.newInstance();
        } else if (id == R.id.nav_feed) {
            return FeedExampleFragment.newInstance();
        }else if (id == R.id.nav_games) {
            return GameExampleFragment.newInstance();
        } else if (id == R.id.nav_natural_pause) {
            return NaturalPauseFragment.newInstance();
        }else{
            return HomeFragment.newInstance();
        }
    }

    private void setupToolbar(String title){
        //Home Fragment
        if(title==null){
            imageLayout.setVisibility(View.VISIBLE);
            barTitle.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.GONE);
        }else{
            barTitle.setText(title);
            barTitle.setVisibility(View.VISIBLE);
            imageLayout.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);


        }
    }

    private void replaceContainerFragment(int id){
        lastFragmentId=id;
        Fragment fragment= getFragmentForId(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }

    private void openUrlToBrowser(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void switchMode(int tabPosition){
        adTypesDrawer.getTabAt(tabPosition).select();
        adTypes.getTabAt(tabPosition).select();

        AppUtils.AdType currentMode= AppUtils.getCurrentAdType();
        boolean videoMode=tabPosition==0;
        AppUtils.AdType requestedMode=  videoMode? AppUtils.AdType.VIDEO_ADS: AppUtils.AdType.IMAGE_ADS;

        boolean needsRecreate=!currentMode.equals(requestedMode) ;
        AppUtils.setCurrentAdType(requestedMode);

        //Load feed if we change Adtype while on home screen
        int fragmentToLoad;

        if(lastFragmentId==-1){
            fragmentToLoad=R.id.nav_feed;
            setupToolbar(getString(R.string.option_feed));
            navigationView.setCheckedItem(fragmentToLoad);
        }else{
            fragmentToLoad=lastFragmentId;
        }

        if(needsRecreate){
            String toastInfoMsg=videoMode? "Switched to video ads":"Switched to display ads";
            replaceContainerFragment(fragmentToLoad);
            Toast.makeText(this,toastInfoMsg,Toast.LENGTH_SHORT).show();
        }



    }

}
