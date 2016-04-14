package com.avocarrot.avocarrotdemoapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by theokir on 06/04/16.
 */
public class Toolbox {
    public static Drawable loadDataFromAsset(String file, Context mContext) {
        try {
            InputStream ims = mContext.getAssets().open(file);
            Drawable d = Drawable.createFromStream(ims, null);
            return d;
        } catch (IOException ex) {
            return null;
        }
    }
}
