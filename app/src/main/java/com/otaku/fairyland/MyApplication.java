package com.otaku.fairyland;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ${wangjishan} on 2016/6/13.
 *
 * @version V1.0
 * @Description:
 */
public class MyApplication extends Application {

    private static MyApplication mApp;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = (MyApplication) getApplicationContext();
        context = getApplicationContext();
        initializeData();
    }

    /**
     * 进行某些操作的初始化
     */
    private void initializeData() {
        /*okhttp的初始化的操作*/
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
         /*百度定位的初始化*/
//        locationService = new LocationService(getApplicationContext());
    }


    /**
     * 获取上下文
     *
     * @return
     */
    public Context getContext() {
        return context;
    }


    /**
     * 获取自身的对象
     *
     * @return
     */
    public static MyApplication getSelf() {
        return mApp;
    }


    /**
     * 获取imei值
     *
     * @return
     */
    public String getImei() {
        String imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        return imei;
    }

}
