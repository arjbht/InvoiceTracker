package com.ab.invoicetracker.utils;

import android.os.Environment;

/**
 * Created by Arjun Bhatt on 6/3/2021.
 */
public class CheckForSdCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
