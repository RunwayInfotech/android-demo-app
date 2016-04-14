package com.avocarrot.avocarrotdemoapp.utils;

/**
 * Created by theokir on 07/04/16.
 */
public class AppUtils {
   public enum AdType {
        IMAGE_ADS,VIDEO_ADS
    }

    private static AdType currentAdType=AdType.VIDEO_ADS;

    public static AdType getCurrentAdType() {
        return currentAdType;
    }

    public static void setCurrentAdType(AdType currentAdType) {
        AppUtils.currentAdType = currentAdType;
    }

    public static boolean isAdtypeImageMode(){
        return currentAdType.equals(AdType.IMAGE_ADS);
    }

    public static boolean isAdtypeVideoMode(){
        return currentAdType.equals(AdType.VIDEO_ADS);
    }
    public static void setAdTypeImageMode(){
        currentAdType=AdType.IMAGE_ADS;
    }

    public static void setAdTypeVideoMode(){
        currentAdType=AdType.VIDEO_ADS;
    }

    public static String getApiId(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"857256d9f042de5889d7858e8cf1be99b921e2d1":"0c41420f71e22a70b3e702b915b3c709b5da7a53";
    }

    public static String getFeedPlacementId(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"a822089b25988b25459963f9dfb9bd77d2396513":"bd507357a5c5baa3c4bed49c68c0e5191ada307a";
    }

    public static String getListPlacementId(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"4d2c2e7dcd3ef53ed045bda03f11f7a9c283ee03":"8394dcb7af30322df62db6358d4f24c1be03266d";
    }

    public static String getTilePlacementId(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"da796f182bb48420027f6646533eb1cb2e9718db":"8c0767ff811cf5b6a15bad6bd06de5046cc32d76";
    }

    public static String getGamePlacementId(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"a6de9386eb1100db027da7424a787a72885a353b":"23e159d5817745f69744dcd5d75f7d2da43d5179";
    }

    public static String getNaturalPausePlacementId1(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"a8a4ee7f1221f552ec70e23e5a4824ea833753a7":"2bd987efd783e5cafd6a2c84bae57e06bafe78fc";
    }

    public static String getNaturalPausePlacementId2(){
        return currentAdType.equals(AdType.IMAGE_ADS)?"2d2943b036c3197334b4b72964f8ea82443d8e19":"2d65295107450f38b3a7206869b1c24e2b078127";
    }



}
