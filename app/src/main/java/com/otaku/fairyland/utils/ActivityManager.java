package com.otaku.fairyland.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by ${wangjishan} on 2015/12/3.
 *
 * @version V1.0
 * @Description: activity管理类
 */
public class ActivityManager {

    private static final String TAG = "str";
    private static Stack<Activity> activityStack;

    private static ActivityManager instance;

    /**
     * <单例方法>
     * <功能详细描述>
     *
     * @return 该对象的实例
     * @see [类、类#方法、类#成员]
     */
    public static ActivityManager getActivityManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * <获取当前栈顶Activity>
     * <功能详细描述>
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        }
        Activity activity = activityStack.lastElement();

        UtilsLog.e(TAG, "get current activity:" + activity.getClass().getSimpleName());
        return activity;
    }

    /**
     * <将Activity入栈>
     * <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        UtilsLog.i(TAG, "push stack activity:" + activity.getClass().getSimpleName());
        activityStack.add(activity);
    }

    /**
     * <退出栈顶Activity>
     * <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            UtilsLog.i(TAG, "remove current activity:" + activity.getClass().getSimpleName());
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * <退出栈中所有Activity,当前的activity除外>
     * <功能详细描述>
     *
     * @param cls
     * @see [类、类#方法、类#成员]
     */
    public void popAllActivityExceptMain(Class cls) {
//        while (true) {
//            Activity activity = currentActivity();
//            if (activity == null) {
//                break;
//            }
//            if (activity.getClass().equals(cls)) {
//                break;
//            }
//            popActivity(activity);
//        }

//        for (Activity activity : activityStack) {
//
//            if(!activity.getClass().equals(cls)){
//                activityStack.remove(activity);
//            }
//        }

    }

    /**
     * 弹出栈中所有的activity
     */

    public void exitAllActivity() {

        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

}
