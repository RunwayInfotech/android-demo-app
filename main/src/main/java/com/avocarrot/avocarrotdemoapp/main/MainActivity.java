package com.avocarrot.avocarrotdemoapp.main;

import android.app.Activity;
import android.content.Context;
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
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.avocarrot.androidsdk.Avocarrot;
import com.avocarrot.androidsdk.AvocarrotCustomListener;
import com.avocarrot.androidsdk.AvocarrotInstreamListener;
import com.avocarrot.androidsdk.AvocarrotInterstitialListener;
import com.avocarrot.androidsdk.custom.AvocarrotCustom;
import com.avocarrot.androidsdk.custom.CustomAdItem;
import com.avocarrot.androidsdk.instream.AvocarrotInstream;
import com.avocarrot.androidsdk.interstitial.AvocarrotInterstitial;


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
    public static Typeface FONT_REGULAR, FONT_LIGHT, FONT_BOLD, FONT_ITALIC;

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

        //---------------------------------------------------------------------------------------
        // Initialize Avocarrot SDK
        //---------------------------------------------------------------------------------------
        Avocarrot.initWithKey(getApplicationContext(), "3dbab458941a2446e2b48ac866b42027f5cac288");
        Avocarrot.setSandbox(true);

        // Load fonts
        FONT_REGULAR = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        FONT_BOLD    = Typeface.createFromAsset(getAssets(), "fonts/OpenSansBold.ttf"   );
        FONT_LIGHT   = Typeface.createFromAsset(getAssets(), "fonts/OpenSansLight.ttf"  );
        FONT_ITALIC  = Typeface.createFromAsset(getAssets(), "fonts/OpenSansItalic.ttf" );
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
            case 1:  mTitle = getString(R.string.title_section1);                break;
            case 2:  mTitle = getString(R.string.title_section2);  break;
            case 3:  mTitle = getString(R.string.title_section3);  break;
            case 4:  mTitle = getString(R.string.title_section4);           break;
            case 5:  mTitle = getString(R.string.title_section5);           break;
            case 6:  mTitle = getString(R.string.title_section6);              break;
            case 7:  mTitle = getString(R.string.title_section7);              break;
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
                // Welcome :
                //---------------------------------------------------------------------------------------

                case 1:
                    View welcomeFragment = inflater.inflate(R.layout.fragment_welcome, container, false);

                    // Set Custom Fonts
                    TextView txtTitle = (TextView)welcomeFragment.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_BOLD);

                    TextView txtTextView1= (TextView)welcomeFragment.findViewById(R.id.textView1);
                    txtTextView1.setTypeface(FONT_REGULAR);

                    TextView txtTextView2 = (TextView)welcomeFragment.findViewById(R.id.textView2);
                    txtTextView2.setTypeface(FONT_LIGHT);

                    // Set up animation for icon_arrow image
                    final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                    final AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

                    final ImageView arrowImage = (ImageView) welcomeFragment.findViewById(R.id.arrow);
                    arrowImage.startAnimation(fadeIn);

                    fadeIn.setStartOffset(500);
                    fadeIn.setDuration(300);

                    fadeOut.setStartOffset(4000);
                    fadeOut.setDuration(300);

                    fadeIn.setAnimationListener(new Animation.AnimationListener()  {
                        @Override public void onAnimationEnd(Animation arg0)       { arrowImage.startAnimation(fadeOut); }
                        @Override public void onAnimationRepeat(Animation arg0)    {}
                        @Override public void onAnimationStart(Animation arg0)     {}
                    });
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationEnd(Animation arg0)       { arrowImage.startAnimation(fadeIn); }
                        @Override public void onAnimationRepeat(Animation arg0)    {}
                        @Override public void onAnimationStart(Animation arg0)     {}
                    });

                    return welcomeFragment;

                //---------------------------------------------------------------------------------------
                // Interstitial Ads #1:
                //---------------------------------------------------------------------------------------

                case 2:

                    View activityFragment1 = inflater.inflate(R.layout.fragment_activity_1, container, false);

                    // Set custom font
                    txtTitle= (TextView)activityFragment1.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    TextView txtDescription= (TextView)activityFragment1.findViewById(R.id.description);
                    txtDescription.setTypeface(FONT_REGULAR);

                    Button showFirstAdBtn = (Button) activityFragment1.findViewById(R.id.show_activity_1);
                    showFirstAdBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AvocarrotInterstitial.getInstance().showAd((Activity) v.getContext(), "activity_triggered_1");
                        }
                    });

                    AvocarrotInterstitial.getInstance().loadAdForPlacement("activity_triggered_1");

                    Avocarrot.getInstance().setAdListener(new AvocarrotInterstitialListener() {
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
                // Interstitial Ads #2:
                //---------------------------------------------------------------------------------------

                case 3:

                    View activityFragment2 = inflater.inflate(R.layout.fragment_activity_2, container, false);

                    // Set custom font
                    txtTitle= (TextView)activityFragment2.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    txtDescription= (TextView)activityFragment2.findViewById(R.id.description);
                    txtDescription.setTypeface(FONT_REGULAR);

                    Button showSecondAdBtn = (Button) activityFragment2.findViewById(R.id.show_activity_2);
                    showSecondAdBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AvocarrotInterstitial.getInstance().showAd((Activity)v.getContext(), "activity_triggered_2");
                        }
                    });

                    AvocarrotInterstitial.getInstance().loadAdForPlacement("activity_triggered_2");

                    Avocarrot.getInstance().setAdListener(new AvocarrotInterstitialListener() {
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
                // In-Stream Ads #1:
                //---------------------------------------------------------------------------------------

                case 4:

                    View instreamFragment1 = inflater.inflate(R.layout.fragment_instream_1, container, false);

                    // Set custom font
                    txtTitle= (TextView)instreamFragment1.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    String[] vegetables = new String[] { "Arugula", "Artichoke", "Brussels Sprout", "Broccoli", "Cabbage", "Cress", "Kale", "Lentils", "Lettuce", "Parsley", "Pea", "Spinach", "Watercress" };
                    ArrayAdapter<String> vegetableAdapter = new ArrayAdapter<String>(
                            inflater.getContext(),
                            R.layout.fragment_instream_1_row,
                            R.id.title,
                            vegetables);

                    AvocarrotInstream.getInstance().initialize(vegetableAdapter);
                    AvocarrotInstream.setFrequency(2, 4);
                    AvocarrotInstream.setLayout(
                            R.layout.avo_instream_layout_1,
                            R.id.avo_container,
                            R.id.avo_native_headline,
                            R.id.avo_native_image,
                            R.id.avo_cta_button
                    );
                    AvocarrotInstream.getInstance().loadAdForPlacement((Activity)container.getContext(), "instream_1");

                    ListView vegetableList = (ListView) instreamFragment1.findViewById(R.id.listView);
                    vegetableList.setAdapter(AvocarrotInstream.getInstance());
                    vegetableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(view.getContext(), ((TextView) view.findViewById(R.id.title)).getText(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    Avocarrot.getInstance().setAdListener(new AvocarrotInstreamListener() {
                        @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
                        @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
                        @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
                        @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
                    });

                    return instreamFragment1;

                //---------------------------------------------------------------------------------------
                // In-Stream Ads #2:
                //---------------------------------------------------------------------------------------

                case 5:

                    View instreamFragment2 = inflater.inflate(R.layout.fragment_instream_2, container, false);

                    // Set custom font
                    txtTitle= (TextView)instreamFragment2.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    String[] fruits = new String[] { "Apple", "Apricot", "Banana", "Blueberry", "Coconut", "Grapefruit", "Guava", "Lychee", "Nectarine", "Orange", "Passionfruit", "Raspberry", "Strawberry" };
                    ArrayAdapter<String> fruitAdapter = new ArrayAdapter<String>(
                            inflater.getContext(),
                            R.layout.fragment_instream_2_row,
                            R.id.title,
                            fruits);

                    AvocarrotInstream.getInstance().initialize(fruitAdapter);
                    AvocarrotInstream.setFrequency(1, 3);
                    AvocarrotInstream.setLayout(
                            R.layout.avo_instream_layout_2,
                            R.id.avo_container,
                            R.id.avo_native_headline,
                            R.id.avo_native_image,
                            R.id.avo_cta_button
                    );

                    AvocarrotInstream.getInstance().loadAdForPlacement((Activity) container.getContext(), "instream_2");

                    ListView fruitList = (ListView) instreamFragment2.findViewById(R.id.listView);
                    fruitList.setAdapter(AvocarrotInstream.getInstance());
                    fruitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(view.getContext(), ((TextView) view.findViewById(R.id.title)).getText(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    Avocarrot.getInstance().setAdListener(new AvocarrotInstreamListener() {
                        @Override public void adDidLoad()                  { Log.d("Avocarrot", "adDidLoad");               }
                        @Override public void adDidNotLoad(String reason)  { Log.d("Avocarrot", "adDidNotLoad: " + reason); }
                        @Override public void adDidFailToLoad(Exception e) { Log.e("Avocarrot", "adDidFailToLoad: " + e);   }
                        @Override public void userWillLeaveApp()           { Log.d("Avocarrot", "userWillLeaveApp");        }
                    });

                    return instreamFragment2;

                //---------------------------------------------------------------------------------------
                // Custom Ads #1:
                //---------------------------------------------------------------------------------------

                case 6:

                    View customFragment1 = inflater.inflate(R.layout.fragment_custom_1, container, false);

                    // Set custom font
                    txtTitle= (TextView)customFragment1.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    txtDescription= (TextView)customFragment1.findViewById(R.id.description);
                    txtDescription.setTypeface(FONT_ITALIC);

                    // Set up a custom animation
                    final AlphaAnimation custom1FadeIn = new AlphaAnimation(0,1);
                    custom1FadeIn.setDuration(1000);

                    // Custom adapter for GridView
                    class MyGridAdapter extends BaseAdapter {

                        private final String[] data = {"Gingerbread","Honeycomb","Ice Cream Sandwich","<AD POSITION>","Jelly Bean","KitKat"};

                        private LayoutInflater inflater;
                        private CustomAdItem customAd = null;

                        @Override public int getCount() { return data.length; }
                        @Override public Object getItem(int position) { return null; }
                        @Override public long getItemId(int position) { return 0;}

                        public MyGridAdapter (Context context) {
                            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        }

                        public void registerAd(CustomAdItem ad){
                            customAd = ad;
                            notifyDataSetChanged();
                        }

                        public View getView(final int position, View convertView, ViewGroup parent) {

                            View gridView =inflater.inflate(R.layout.fragment_custom_1_row, null);

                            TextView item_label_number= (TextView) gridView.findViewById(R.id.grid_item_label_no);
                            TextView item_label_title= (TextView) gridView.findViewById(R.id.grid_item_label_title);
                            ImageView item_label_image = (ImageView) gridView.findViewById(R.id.grid_item_image);
                            Button item_label_button = (Button) gridView.findViewById(R.id.grid_item_button);

                            // Show a native Ad on position 3
                            if (position == 3) {

                                if (customAd==null) {
                                    gridView.setVisibility(View.GONE);
                                    return gridView;
                                }

                                // Fill in details
                                if (customAd.getCTAText()!= null)
                                    item_label_button.setText(customAd.getCTAText());

                                if (customAd.getHeadline()!= null)
                                    item_label_title.setText(customAd.getHeadline());

                                if (customAd.getIcon()!= null)
                                    item_label_image.setImageBitmap(customAd.getIcon());
                                else if (customAd.getImage()!= null)
                                    item_label_image.setImageBitmap(customAd.getImage());

                                // Bind view
                                customAd.bindView(gridView);

                                item_label_number.setText("Sponsored Ad");

                                //Set click listener
                                item_label_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        customAd.handleClick();
                                    }
                                });

                                // Animate fade in of view
                                gridView.startAnimation(custom1FadeIn);
                            }
                            else {
                                item_label_number.setText("#"+ position);
                                item_label_title.setText(data[position]);
                                item_label_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(v.getContext(), data[position], Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            return gridView;
                        }

                    }

                    // Load grid view
                    final GridView gridView = (GridView) customFragment1.findViewById(R.id.gridViewBlock);
                    final MyGridAdapter gridViewAdapter = new MyGridAdapter(inflater.getContext());
                    gridView.setAdapter(gridViewAdapter);

                    // Load custom Ad
                    AvocarrotCustom.getInstance().loadAdForPlacement((Activity) container.getContext(), "custom");

                    Avocarrot.getInstance().setAdListener(new AvocarrotCustomListener() {
                        @Override public void adDidLoad(CustomAdItem ad)     {
                            Log.d("Avocarrot", "adDidLoad");
                            gridViewAdapter.registerAd(ad);
                        }
                        @Override public void adDidNotLoad(String reason)    { Log.d("Avocarrot", "adDidNotLoad: " + reason);    }
                        @Override public void adDidFailToLoad(Exception e)   { Log.e("Avocarrot", "adDidFailToLoad: " + e);      }
                        @Override public void userWillLeaveApp()             { Log.d("Avocarrot", "userWillLeaveApp");           }
                        @Override public void onAdImpression(String message) { Log.d("Avocarrot", "onAdImpression: " + message); }
                        @Override public void onAdClick(String message)      { Log.d("Avocarrot", "onAdClick: " + message);      }

                    });
                    return customFragment1;

                //---------------------------------------------------------------------------------------
                // Custom Ads #2:
                //---------------------------------------------------------------------------------------

                case 7:

                    final View customFragment2 = inflater.inflate(R.layout.fragment_custom_2, container, false);

                    // Load fields from XML
                    final TextView adHeadline = (TextView)customFragment2.findViewById(R.id.custom_ad_headline);
                    final TextView adButton= (TextView)customFragment2.findViewById(R.id.custom_ad_cta);
                    final ImageView adImage = (ImageView)customFragment2.findViewById(R.id.custom_ad_image);
                    final RelativeLayout adWrapper = (RelativeLayout)customFragment2.findViewById(R.id.custom_ad_wrapper);

                    // Set custom font
                    txtTitle = (TextView)customFragment2.findViewById(R.id.title);
                    txtTitle.setTypeface(FONT_LIGHT);

                    txtDescription= (TextView)customFragment2.findViewById(R.id.description);
                    txtDescription.setTypeface(FONT_ITALIC);

                    adButton.setTypeface(FONT_LIGHT);
                    adHeadline.setTypeface(FONT_BOLD);

                    // Load custom Ad
                    AvocarrotCustom.getInstance().loadAdForPlacement((Activity) container.getContext(), "custom");

                    // Hide ad fields until ad is ready to be loaded
                    adWrapper.setVisibility(View.GONE);
                    adButton.setVisibility(View.GONE);

                    Avocarrot.getInstance().setAdListener(new AvocarrotCustomListener() {
                        @Override public void adDidLoad(final CustomAdItem ad)     {

                            // Fill in details
                            if (ad.getImage()!= null)
                                adImage.setImageBitmap(ad.getImage());

                            if (ad.getHeadline()!= null)
                                adHeadline.setText(ad.getHeadline());

                            if (ad.getCTAText()!= null)
                                adButton.setText(ad.getCTAText());

                            // Bind view
                            ad.bindView(customFragment2);

                            // Set click listener
                            adButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ad.handleClick();
                                }
                            });

                            adWrapper.setVisibility(View.VISIBLE);
                            adButton.setVisibility(View.VISIBLE);

                            Log.d("Avocarrot", "adDidLoad");
                        }
                        @Override public void adDidNotLoad(String reason)    { Log.d("Avocarrot", "adDidNotLoad: " + reason);    }
                        @Override public void adDidFailToLoad(Exception e)   { Log.e("Avocarrot", "adDidFailToLoad: " + e);      }
                        @Override public void userWillLeaveApp()             { Log.d("Avocarrot", "userWillLeaveApp");           }
                        @Override public void onAdImpression(String message) { Log.d("Avocarrot", "onAdImpression: " + message); }
                        @Override public void onAdClick(String message)      { Log.d("Avocarrot", "onAdClick: " + message);      }

                    });

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








