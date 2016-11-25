package org.lynxz.zzplayerlibrary.util;

import android.util.Log;

import org.lynxz.zzplayerlibrary.BuildConfig;


/**
 * Created by zxz on 2016/4/28.
 */
public class DebugLog {
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
