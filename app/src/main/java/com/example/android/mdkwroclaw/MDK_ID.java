package com.example.android.mdkwroclaw;

/**
 * Created by AD on 2016-04-19.
 */
public class MDK_ID {
    static String mdk_dowolny = "0";
    static String mdk_fabryczna = "1";
    static String mdk_kopernika = "2";
    static String mdk_krzyki = "3";
    static String mdk_srodmiescie = "4";

    public static String getMDKName(String mdk_id) {
        switch(mdk_id) {
            case "1":
                return "MDK Fabryczna";
            case "2":
                return "MDK im. Kopernika";
            case "3":
                return "MDK Krzyki";
            case "4":
                return "MDK Śródmieście";
        }

        return null;
    }
}
