package com.avocarrot.avocarrotdemoapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.avocarrot.avocarrotdemoapp.models.DemoFeedItem;
import com.avocarrot.avocarrotdemoapp.models.DemoListItem;
import com.avocarrot.avocarrotdemoapp.models.DemoTileItem;

/**
 * Created by theokir on 06/04/16.
 */
public class DemoItemCollection {
    public static List<DemoTileItem> getDemoTileItems() {
        List<DemoTileItem> items = new ArrayList<>();
        items.add(new DemoTileItem("Check out my new, updated website!", "2", "Los Angeles", "feed_image_square_1.png"));
        items.add(new DemoTileItem("My new book is out today!! #excited", "3", "San Diego", "feed_image_square_2.png"));
        items.add(new DemoTileItem("Photo:  http://tmblr.co/ZPpLJKu121pLzlN", "1", "Berlin", "feed_image_square_3.png"));
        items.add(new DemoTileItem("We be clubbing #Lodon #clubbinganimal", "7", "London", "feed_image_square_4.png"));
        items.add(new DemoTileItem("Getting ready for work!", "2", "Seattle", "feed_image_square_5.png"));
        items.add(new DemoTileItem("To buy or not to buy?", "10", "Paris", "feed_image_square_6.png"));
        items.add(new DemoTileItem("Didn't know I had to ask.. ", "2", "Rome", "feed_image_square_7.png"));
        items.add(new DemoTileItem("Feeling excited! :D", "12", "Athens", "feed_image_square_8.png"));


        return items;
    }


    public static List<DemoListItem> getDemoListItems() {
        List<DemoListItem> items = new ArrayList<>();
        items.add(new DemoListItem("Airplane Food In Economy Vs. First Class On 20 Airlines", "17", "32","list_item1.jpg"));
        items.add(new DemoListItem("21 Genius iPhone Gadgets You Never Knew You Needed", "25", "7","list_item2.jpg"));
        items.add(new DemoListItem("DNA-Editing Company Goes Public While Nasty Patent Fight Roars On By Stephanie M. Lee", "8", "40","list_item3.jpg"));
        items.add(new DemoListItem("Search for the Fountain of Youth while on holiday!", "3", "5","list_item4.jpg"));
        items.add(new DemoListItem("Avoid these 10 mistakes while explorin’ the arctic waves", "2", "4", "list_item5.jpg"));


        return items;
    }

    public static List<DemoFeedItem> getDemoFeedItems() {
        List<DemoFeedItem> items = new ArrayList<>();

        items.add(new DemoFeedItem("feed_avatar_1.png","Penny Bell","24 Movies Your Probably Missed This Year, But Should Totally See", "feed_post_1.jpg","2 hours ago"));
        items.add(new DemoFeedItem("feed_avatar_2.png","Evan Jones","Tiger Populations are Up For The First Time In 100 Years", "feed_post_2.jpg","3 hours ago"));

        return items;
    }
}
