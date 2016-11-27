package com.otaku.fairyland.main.view;

import com.otaku.fairyland.main.AdvertiseInfo;
import com.otaku.fairyland.main.RecommendInfo;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public interface MainActivityView {


    /*首页案例请求成功*/
    void ResultAdvertiseListSuccess(AdvertiseInfo info);

    /*首页案例请求失败*/
    void ResultAdvertiseListFailure(String failureStr);


    /*刚进入时显示加载图标*/
    void showProgress();

    /*首页案例请求成功*/
    void ResultRecommendListSuccess(RecommendInfo info);

    /*首页案例请求失败*/
    void ResultRecommendListFailure(String failureStr);


}
