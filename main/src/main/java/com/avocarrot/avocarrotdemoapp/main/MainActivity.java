package com.avocarrot.avocarrotdemoapp.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avocarrot.androidsdk.Avocarrot;
import com.avocarrot.androidsdk.AvocarrotAdListener;
import com.avocarrot.androidsdk.instream.InstreamAdapter;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        AvocarrotAdListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        Avocarrot.initWithKey(getApplicationContext(), "3dbab458941a2446e2b48ac866b42027f5cac288");
        Avocarrot.setSandbox(true);
        Avocarrot.getInstance().setAdListener(this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    View welcomeFragment = inflater.inflate(R.layout.fragment_welcome, container, false);
                    return welcomeFragment;
                case 2:
                    View activityFragment1 = inflater.inflate(R.layout.fragment_activity_1, container, false);
                    Button showFirstAdBtn = (Button) activityFragment1.findViewById(R.id.show_activity_1);
                    showFirstAdBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Avocarrot.getInstance().showAd((Activity)v.getContext(), "activity_triggered_1");
                        }
                    });
                    Avocarrot.getInstance().loadAdForPlacement("activity_triggered_1");
                    return activityFragment1;
                case 3:
                    View activityFragment2 = inflater.inflate(R.layout.fragment_activity_2, container, false);
                    Button showSecondAdBtn = (Button) activityFragment2.findViewById(R.id.show_activity_2);
                    showSecondAdBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Avocarrot.getInstance().showAd((Activity)v.getContext(), "activity_triggered_2");
                        }
                    });
                    Avocarrot.getInstance().loadAdForPlacement("activity_triggered_2");
                    return activityFragment2;
                case 4:
                    View instreamFragment1 = inflater.inflate(R.layout.fragment_instream_1, container, false);
                    String[] vegetables = new String[] { "Arugula", "Artichoke", "Brussels Sprout", "Broccoli", "Cabbage", "Cress", "Kale", "Lentils", "Lettuce",
                            "Parsley", "Pea", "Spinach", "Watercress" };
                    ArrayAdapter<String> vegetableAdapter = new ArrayAdapter<String>(
                            inflater.getContext(),
                            android.R.layout.simple_list_item_1,
                            vegetables);
                    ListView vegetableList = (ListView) instreamFragment1.findViewById(R.id.listView);

                    InstreamAdapter.getAdapter().initialize(vegetableAdapter);
                    InstreamAdapter.setLayout(
                            R.layout.avo_instream_layout_1,
                            R.id.avo_container,
                            R.id.avo_native_headline,
                            R.id.avo_native_image,
                            R.id.avo_cta_button
                    );
                    InstreamAdapter.setFrequency(2,4);
                    vegetableList.setAdapter(InstreamAdapter.getAdapter());
                    InstreamAdapter.getAdapter().loadAds((Activity)container.getContext(), "instream_1");

                    vegetableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(view.getContext(), ((TextView) view).getText(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    return instreamFragment1;
                case 5:
                    View instreamFragment2 = inflater.inflate(R.layout.fragment_instream_2, container, false);
                    String[] fruits = new String[] { "Apple", "Apricot", "Banana", "Blueberry", "Coconut",
                            "Grapefruit", "Guava", "Lychee", "Nectarine", "Orange", "Passionfruit", "Raspberry", "Strawberry" };
                    ArrayAdapter<String> fruitAdapter = new ArrayAdapter<String>(
                            inflater.getContext(),
                            android.R.layout.simple_list_item_1,
                            fruits);
                    ListView fruitList = (ListView) instreamFragment2.findViewById(R.id.listView);

                    InstreamAdapter.getAdapter().initialize(fruitAdapter);
                    InstreamAdapter.setLayout(
                            R.layout.avo_instream_layout_2,
                            R.id.avo_container,
                            R.id.avo_native_headline,
                            R.id.avo_native_image,
                            R.id.avo_cta_button
                    );
                    InstreamAdapter.setFrequency(2,4);
                    fruitList.setAdapter(InstreamAdapter.getAdapter());
                    InstreamAdapter.getAdapter().loadAds((Activity)container.getContext(), "instream_2");

                    fruitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(view.getContext(), ((TextView) view).getText(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    return instreamFragment2;
                default:
                    View defaultFragment = inflater.inflate(R.layout.fragment_welcome, container, false);
                    return defaultFragment;
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    @Override
    public void adDidLoad() {
        Log.d("Avocarrot", "adDidLoad");
    }

    @Override
    public void adDidNotLoad(String s) {
        Log.d("Avocarrot", "adDidNotLoad: " + s);
    }

    @Override
    public void adDidFailToLoad(Exception e) {
        Log.e("Avocarrot", "adDidFailToLoad", e);
    }

    @Override
    public void userWillLeaveApp() {
        Log.d("Avocarrot", "userWillLeaveApp");
    }
}
