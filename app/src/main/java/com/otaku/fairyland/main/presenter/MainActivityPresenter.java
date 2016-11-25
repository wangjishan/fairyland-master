package com.otaku.fairyland.main.presenter;

import java.util.HashMap;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public interface MainActivityPresenter {


    /*首页广告列表*/
    void RequestAdvertiseList(HashMap<String, String> map);


    /*首页的案例列表*/
    void RequestRecommendList(HashMap<String, String> map);


}
