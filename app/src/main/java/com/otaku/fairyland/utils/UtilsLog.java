package com.otaku.fairyland.utils;

/**
 * Created by ${wangjishan} on 2015/11/26.
 *
 * @version V1.0
 * @Description: 打印类
 */
public class UtilsLog {

    private static boolean isLog = true;

    public static void d(String key, String value) {
        if (isLog) {
            android.util.Log.d(key, value);
        }
    }

    public static void i(String key, String value) {
        if (isLog) {
            android.util.Log.i(key, value);
        }
    }

    public static void e(String key, String value) {
        if (isLog) {
            android.util.Log.e(key, value);
            // add(key,value);
        }
    }

    public static void log(String tag, String info) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        int i = 1;
        if (isLog) {
            StackTraceElement s = ste[i];
            android.util.Log.e(tag, String.format("======[%s][%s][%s]=====%s", s.getClassName(), s.getLineNumber(), s.getMethodName(), info));
        }
    }

    public static void w(String key, String value) {
        if (isLog) {
            android.util.Log.w(key, value);
        }
    }

    public static void w(String key, Throwable tr) {
        if (isLog) {
            android.util.Log.w(key, tr);
        }
    }

    public static void w(String key, String value, Throwable tr) {
        if (isLog) {
            android.util.Log.w(key, value, tr);
        }
    }

}
