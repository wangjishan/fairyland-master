package com.otaku.fairyland.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/11/27.
 */

public class IntentUtils {


    /**
     * 带参数的传参
     *
     * @param activity
     * @param mClass
     * @param bundle
     */
    public static final void ToActivity(Activity activity, Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(activity, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtras(bundle);
        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.push_left_in,
//                R.anim.push_left_out);
    }


}
