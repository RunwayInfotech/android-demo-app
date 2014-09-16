package com.avocarrot.avocarrotdemoapp.main.Helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//  Class to return a demo feed for Avocarrot integrations

public class DemoContent {

    private List<DemoAvatarDetails> data = new ArrayList<DemoAvatarDetails>();

    public DemoContent(Context myContext) {
        data.add(new DemoAvatarDetails(myContext, "Cindy Weiss"    , "1 hour ago" , "Los Angeles", "Check out my new, updated website!", "2",
                "feed_avatar_1.png", "feed_post_1.png", "feed_image_square_1.png"));
        data.add(new DemoAvatarDetails(myContext, "Michael Lonon"  , "1 hour ago" , "San Diego"  , "My new book is out today!! #excited ","4",
                "feed_avatar_2.png", "feed_post_2.png", "feed_image_square_2.png"));
        data.add(new DemoAvatarDetails(myContext,"Linda Christensen" , "2 hours ago", "Berlin"     , "Photo:  http://tmblr.co/ZPpLJKu121pLzlN","1",
                "feed_avatar_3.png", "feed_post_3.png", "feed_image_square_3.png"));
        data.add(new DemoAvatarDetails(myContext,"Nancy Williams" , "3 hours ago", "London"     , "We be clubbing #Lodon #clubbinganimal","0",
                "feed_avatar_4.png", "feed_post_4.png", "feed_image_square_4.png"));
        data.add(new DemoAvatarDetails(myContext,"Jesse Sheehan"  , "Yesterday"  , "Seattle"    , "Getting ready for work!","12",
                "feed_avatar_5.png", "feed_post_5.png", "feed_image_square_5.png"));
        data.add(new DemoAvatarDetails(myContext,"Raymond Hoerner", "Yesterday"  , "Paris"      , "To buy or not to buy?","0",
                "feed_avatar_6.png", "feed_post_6.png", "feed_image_square_6.png"));
        data.add(new DemoAvatarDetails(myContext,"Christalle Comeau", "2 days ago"  , "Rome"      , "Didn't know I had to ask.. ","1",
                "feed_avatar_7.png", "feed_post_7.png", "feed_image_square_7.png"));
        data.add(new DemoAvatarDetails(myContext,"James Tran", "2 days ago"  , "Athens"      , "Feeling excited! :D ","3",
                "feed_avatar_8.png", "feed_post_8.png", "feed_image_square_8.png"));
    }

    // Getters
    public String getNameFor(int position)     { return data.get(position).name;        }
    public String getLocationFor(int position) { return data.get(position).location;    }
    public String getDateFor(int position)     { return data.get(position).date;        }
    public String getPostFor(int position)     { return data.get(position).post;        }
    public String getNumberFor(int position)   { return data.get(position).number;      }

    public int getSize()                          { return data.size();                    }
    public Drawable getSquareImageFor(int position) { return data.get(position).squareImage; }
    public Drawable getImageFor(int position)       { return data.get(position).image;       }
    public Drawable getIconFor(int position)        { return data.get(position).icon;        }

    class DemoAvatarDetails {

        public String name,
                      date,
                      location,
                      post,
                      number;

        public Drawable image,
                        icon,
                        squareImage;

        DemoAvatarDetails( Context mContext, String name, String date, String location, String post, String number, String icon, String image, String squareImage){
            this.name = name;
            this.location = location;
            this.number = number;
            this.post = post;
            this.date = date;
            this.squareImage = loadDataFromAsset(squareImage, mContext);
            this.image = loadDataFromAsset(image, mContext);
            this.icon = loadDataFromAsset(icon, mContext);

        }
    }

    public static Drawable loadDataFromAsset(String file, Context mContext) {
        try {
            InputStream ims = mContext.getAssets().open(file);
            Drawable d = Drawable.createFromStream(ims, null);
              return d;
        }
        catch(IOException ex) {
            return null;
        }
    }
}




