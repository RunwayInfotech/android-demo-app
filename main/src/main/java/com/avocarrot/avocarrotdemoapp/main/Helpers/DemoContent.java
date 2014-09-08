package com.avocarrot.avocarrotdemoapp.main.Helpers;

import com.avocarrot.avocarrotdemoapp.main.R;

import java.util.ArrayList;
import java.util.List;

//  Class to return a demo feed for Avocarrot integrations

public class DemoContent {

    private List<DemoAvatarDetails> data = new ArrayList<DemoAvatarDetails>();

    public DemoContent() {
        data.add(new DemoAvatarDetails("Cindy Weiss"    , "1 hour ago" , "Los Angeles", "Check out my new, updated website!", "2",
                R.drawable.feed_avatar_1, R.drawable.feed_post_1, R.drawable.feed_image_square_1));
        data.add(new DemoAvatarDetails("Michael Lonon"  , "1 hour ago" , "San Diego"  , "My new book is out today!! #excited ","4",
                R.drawable.feed_avatar_2, R.drawable.feed_post_2, R.drawable.feed_image_square_2));
        data.add(new DemoAvatarDetails("Linda Christensen" , "2 hours ago", "Berlin"     , "Photo:  http://tmblr.co/ZPpLJKu121pLzlN","1",
                R.drawable.feed_avatar_3, R.drawable.feed_post_3, R.drawable.feed_image_square_3));
        data.add(new DemoAvatarDetails("Nancy Williams" , "3 hours ago", "London"     , "We be clubbing #Lodon #clubbinganimal","0",
                R.drawable.feed_avatar_4, R.drawable.feed_post_4, R.drawable.feed_image_square_4));
        data.add(new DemoAvatarDetails("Jesse Sheehan"  , "Yesterday"  , "Seattle"    , "Getting ready for work!","12",
                R.drawable.feed_avatar_5, R.drawable.feed_post_5, R.drawable.feed_image_square_5));
        data.add(new DemoAvatarDetails("Raymond Hoerner", "Yesterday"  , "Paris"      , "To buy or not to buy?","0",
                R.drawable.feed_avatar_6, R.drawable.feed_post_6, R.drawable.feed_image_square_6));
        data.add(new DemoAvatarDetails("Thomas Mena", "2 days ago"  , "Rome"      , "Didn't know I had to ask.. ","1",
                R.drawable.feed_avatar_7, R.drawable.feed_post_7, R.drawable.feed_image_square_7));
        data.add(new DemoAvatarDetails("James Tran", "2 days ago"  , "Athens"      , "Feeling excited! :D ","3",
                R.drawable.feed_avatar_8, R.drawable.feed_post_8, R.drawable.feed_image_square_8));
    }

    // Getters
    public String getNameFor(int position)     { return data.get(position).name;        }
    public String getLocationFor(int position) { return data.get(position).location;    }
    public String getDateFor(int position)     { return data.get(position).date;        }
    public String getPostFor(int position)     { return data.get(position).post;        }
    public String getNumberFor(int position)   { return data.get(position).number;      }

    public int getSize()                       { return data.size();                    }
    public int getSquareImageFor(int position) { return data.get(position).squareImage; }
    public int getImageFor(int position)       { return data.get(position).image;       }
    public int getIconFor(int position)        { return data.get(position).icon;        }

    class DemoAvatarDetails {

        public String name,
                      date,
                      location,
                      post,
                      number;

        public int    image,
                      icon,
                      squareImage;

        DemoAvatarDetails(String name, String date, String location, String post, String number, int icon, int image, int squareImage){
            this.name = name;
            this.squareImage = squareImage;
            this.location = location;
            this.number = number;
            this.post = post;
            this.date = date;
            this.image = image;
            this.icon = icon;
        }
    }
}




