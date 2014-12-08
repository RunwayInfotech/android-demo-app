package com.avocarrot.demoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.avocarrot.demoapp.helpers.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /** Fragment managing the behaviors, interactions and presentation of the navigation drawer. */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /** Used to store the last screen title. For use in {@link #restoreActionBar()}. */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        /* load welcome by calling an invalid id */
        onNavigationDrawerItemSelected(-1);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.container, getFragmentByPosition(position))
            .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:  mTitle = getString(R.string.menu_option_1); break;
            case 2:  mTitle = getString(R.string.menu_option_2); break;
            case 3:  mTitle = getString(R.string.menu_option_3); break;
            case 4:  mTitle = getString(R.string.menu_option_4); break;
            case 5:  mTitle = getString(R.string.menu_option_5); break;
            case 6:  mTitle = getString(R.string.menu_option_6); break;
        }

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }


    Fragment getFragmentByPosition(int position) {
        switch (position) {
            case 0: return ExampleFeed.getInstance();
            case 1: return ExampleList.getInstance();
            case 2: return ExampleTile.getInstance();
            case 3: return ExampleGame.getInstance();
            case 4: return ExampleInterstitial1.getInstance();
            case 5: return ExampleInterstitial2.getInstance();

            default: return Welcome.getInstance();
        }
    }

    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//
//        }
//
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////
////           /*
////            * Avocarrot singleton is initialized inside the "onCreate" method
////            *
////            */
////
////            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
////
////                case 2:
////                    // Feed Fragment
////                    return new ExampleFeed(container.getContext());
////
////                case 3:
////                    // List Fragment
////                    return new ExampleList(container.getContext());
////
////                case 4:
////                    // Tile Fragment
////                    return new ExampleTile(container.getContext());
////
////                case 5:
////                    // Fame your own Fragment
////                    return new ExampleGame(container.getContext());
////
////                case 6:
////                    // Interstitial Fragment
////                    return new ExampleInterstitial1(container.getContext());
////
////                case 7:
////                    // Interstitial Fragment
////                    return new ExampleInterstitial2(container.getContext());
////
////                default:
////                    // Default - Welcome Screen
////                    return inflater.inflate(R.layout.fragment_welcome, container, false);
////            }
////        }
////        @Override
////        public void onAttach(Activity activity) {
////            super.onAttach(activity);
////            ((MainActivity) activity).onSectionAttached(
////                    getArguments().getInt(ARG_SECTION_NUMBER));
////        }
//    }
}