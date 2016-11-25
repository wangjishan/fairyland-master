package com.otaku.fairyland.main.model;

import java.util.HashMap;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public interface MainActivityModel {


    /*首页广告列表*/
    void AdvertiseListRequest(HashMap<String, String> map, MainActivityModelImpl.OnResultListener listener);


    /*首页的案例列表*/
    void RecommendListRequest(HashMap<String, String> map, MainActivityModelImpl.OnResultListener listener);


}
