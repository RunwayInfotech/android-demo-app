package com.avocarrot.avocarrotdemoapp.main;

import android.app.Activity;
import android.graphics.Typeface;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avocarrot.androidsdk.Avocarrot;
import com.avocarrot.androidsdk.AvocarrotInstreamAdListener;
import com.avocarrot.androidsdk.AvocarrotInterstitialAdListener;
import com.avocarrot.androidsdk.instream.InstreamAdapter;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static Typeface FONT_REGULAR, FONT_LIGHT, FONT_BOLD;

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

//        Avocarrot.initWithKey(getApplicationContext(), "3dbab458941a2446e2b48ac866b42027f5cac288");

        Avocarrot.initWithKey(getApplicationContext(), "018ab1d44c34f1f186674b64d605e2a00ed18ce3");
        Avocarrot.setSandbox(true);

        // Load fonts
        FONT_REGULAR = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        FONT_BOLD = Typeface.createFromAsset(getAssets(), "fonts/OpenSansBold.ttf");
        FONT_LIGHT = Typeface.createFromAsset(getAssets(), "fonts/OpenSansLight.ttf");

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
            case 1:  mTitle = getString(R.string.title_section1);  break;
            case 2:  mTitle = getString(R.string.title_section2);  break;
            case 3:  mTitle = getString(R.string.title_section3);  break;
            case 4:  mTitle = getString(R.string.title_section4);  break;
            case 5:  mTitle = getString(R.string.title_section5);  break;
            case 6:  mTitle = getString(R.string.title_section6);  break;
            case 7:  mTitle = getString(R.string.title_section7);  break;
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                //---------------------------------------------------------------------------------------
                case 1:
                    View welcomeFragment = inflater.inflate(R.layout.fragment_welcome, container, false);

                    // Set Custom Font
                    TextView txtTitle = (TextView)welcomeFragment.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_REGULAR);

                    TextView txtTextView2 = (TextView)welcomeFragment.findViewById(R.id.textView2);
                    txtTextView2.setTypeface(FONT_LIGHT);

                    TextView txtTextView1= (TextView)welcomeFragment.findViewById(R.id.textView1);
                    txtTextView1.setTypeface(FONT_LIGHT);

                    // Animate arrow image
                    final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                    final AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

                    final ImageView arrowImage = (ImageView) welcomeFragment.findViewById(R.id.arrow);
                    arrowImage.startAnimation(fadeIn);

                    fadeIn.setStartOffset(500);
                    fadeIn.setDuration(300);

                    fadeOut.setStartOffset(4000);
                    fadeOut.setDuration(300);

                    fadeIn.setAnimationListener(new Animation.AnimationListener(){
                        @Override public void onAnimationEnd(Animation arg0)    { arrowImage.startAnimation(fadeOut); }
                        @Override public void onAnimationRepeat(Animation arg0) {}
                        @Override public void onAnimationStart(Animation arg0)  {}
                    });
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationEnd(Animation arg0) { arrowImage.startAnimation(fadeIn); }
                        @Override public void onAnimationRepeat(Animation arg0) {}
                        @Override public void onAnimationStart(Animation arg0) {}
                    });

                    return welcomeFragment;
                //---------------------------------------------------------------------------------------
                case 2:
                    // Interstitial Ads #1:

                    View activityFragment1 = inflater.inflate(R.layout.fragment_activity_1, container, false);

                    // Set custom font
                    txtTitle= (TextView)activityFragment1.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    Button showFirstAdBtn = (Button) activityFragment1.findViewById(R.id.show_activity_1);
                    showFirstAdBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Avocarrot.getInstance().showAd((Activity)v.getContext(), "activity_triggered_1");
                        }
                    });

                    // Avocarrot.getInstance().loadAdForPlacement("activity_triggered_1");
                    Avocarrot.getInstance().loadAdForPlacement("activity-triggered");

                    Avocarrot.getInstance().setAdListener(new AvocarrotInterstitialAdListener() {
                        @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
                        @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
                        @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
                        @Override public void adWillAppear()               { Log.d("Avocarrot", "adWillAppear");            }
                        @Override public void adDidAppear()                { Log.d("Avocarrot", "adDidAppear");             }
                        @Override public void adWillDisappear()            { Log.d("Avocarrot", "adWillDisappear");         }
                        @Override public void adDidDisappear()             { Log.d("Avocarrot", "adDidDisappear");          }
                        @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
                    });

                    return activityFragment1;
                //---------------------------------------------------------------------------------------
                case 3:
                    // Interstitial Ads #2:

                    View activityFragment2 = inflater.inflate(R.layout.fragment_activity_2, container, false);

                    // Set custom font
                    txtTitle= (TextView)activityFragment2.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    Button showSecondAdBtn = (Button) activityFragment2.findViewById(R.id.show_activity_2);
                    showSecondAdBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Avocarrot.getInstance().showAd((Activity)v.getContext(), "activity_triggered_2");
                        }
                    });

                    // Avocarrot.getInstance().loadAdForPlacement("activity_triggered_2");
                    Avocarrot.getInstance().loadAdForPlacement("activity-triggered");

                    Avocarrot.getInstance().setAdListener(new AvocarrotInterstitialAdListener() {
                        @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
                        @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
                        @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
                        @Override public void adWillAppear()               { Log.d("Avocarrot", "adWillAppear");            }
                        @Override public void adDidAppear()                { Log.d("Avocarrot", "adDidAppear");             }
                        @Override public void adWillDisappear()            { Log.d("Avocarrot", "adWillDisappear");         }
                        @Override public void adDidDisappear()             { Log.d("Avocarrot", "adDidDisappear");          }
                        @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
                    });

                    return activityFragment2;
                //---------------------------------------------------------------------------------------
                case 4:
                    // In-Stream Ads #1:

                    View instreamFragment1 = inflater.inflate(R.layout.fragment_instream_1, container, false);

                    // Set custom font
                    txtTitle= (TextView)instreamFragment1.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    String[] vegetables = new String[] { "Arugula", "Artichoke", "Brussels Sprout", "Broccoli", "Cabbage", "Cress", "Kale", "Lentils", "Lettuce", "Parsley", "Pea", "Spinach", "Watercress" };
                    ArrayAdapter<String> vegetableAdapter = new ArrayAdapter<String>(
                            inflater.getContext(),
                            R.layout.fragment_instream_row,
                            R.id.title,
                            vegetables);

                    InstreamAdapter.getAdapter().initialize(vegetableAdapter);
                    InstreamAdapter.setLayout(
                            R.layout.avo_instream_layout_1,
                            R.id.avo_container,
                            R.id.avo_native_headline,
                            R.id.avo_native_image,
                            R.id.avo_cta_button
                    );
                    InstreamAdapter.setFrequency(2,4);

                    // InstreamAdapter.getAdapter().loadAds((Activity)container.getContext(), "instream_1");
                    InstreamAdapter.getAdapter().loadAds((Activity)container.getContext(), "instream");

                    ListView vegetableList = (ListView) instreamFragment1.findViewById(R.id.listView);
                    vegetableList.setAdapter(InstreamAdapter.getAdapter());
                    vegetableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(view.getContext(), ((TextView) view.findViewById(R.id.title)).getText(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    Avocarrot.getInstance().setAdListener(new AvocarrotInstreamAdListener() {
                        @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
                        @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
                        @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
                        @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
                    });

                    return instreamFragment1;
                //---------------------------------------------------------------------------------------
                case 5:
                    // In-Stream Ads #2:

                    View instreamFragment2 = inflater.inflate(R.layout.fragment_instream_2, container, false);

                    // Set custom font
                    txtTitle= (TextView)instreamFragment2.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    String[] fruits = new String[] { "Apple", "Apricot", "Banana", "Blueberry", "Coconut",
                            "Grapefruit", "Guava", "Lychee", "Nectarine", "Orange", "Passionfruit", "Raspberry", "Strawberry" };
                    ArrayAdapter<String> fruitAdapter = new ArrayAdapter<String>(
                            inflater.getContext(),
                            R.layout.fragment_instream_row,
                            R.id.title,
                            fruits);

                    InstreamAdapter.getAdapter().initialize(fruitAdapter);
                    InstreamAdapter.setLayout(
                            R.layout.avo_instream_layout_2,
                            R.id.avo_container,
                            R.id.avo_native_headline,
                            R.id.avo_native_image,
                            R.id.avo_cta_button
                    );
                    InstreamAdapter.setFrequency(2,4);

                    // InstreamAdapter.getAdapter().loadAds((Activity)container.getContext(), "instream_2");
                    InstreamAdapter.getAdapter().loadAds((Activity)container.getContext(), "instream");

                    ListView fruitList = (ListView) instreamFragment2.findViewById(R.id.listView);
                    fruitList.setAdapter(InstreamAdapter.getAdapter());
                    fruitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(view.getContext(), ((TextView) view.findViewById(R.id.title)).getText(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    Avocarrot.getInstance().setAdListener(new AvocarrotInstreamAdListener() {
                        @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
                        @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
                        @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
                        @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
                    });

                    return instreamFragment2;
                //---------------------------------------------------------------------------------------
                case 6:
                    // Custom Ads #1:
                    View customFragment1 = inflater.inflate(R.layout.fragment_custom_1, container, false);

                    // Set custom font
                    txtTitle= (TextView)customFragment1.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    return customFragment1;

                //---------------------------------------------------------------------------------------
                case 7:
                    // Custom Ads #2:
                    View customFragment2 = inflater.inflate(R.layout.fragment_custom_2, container, false);

                    // Set custom font
                    txtTitle= (TextView)customFragment2.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    return customFragment2;

                //---------------------------------------------------------------------------------------
                default:
                    View defaultFragment = inflater.inflate(R.layout.fragment_welcome, container, false);
                    return defaultFragment;
                //---------------------------------------------------------------------------------------

            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}








