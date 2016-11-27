package com.otaku.fairyland.main.presenter;

import com.otaku.fairyland.main.AdvertiseInfo;
import com.otaku.fairyland.main.RecommendInfo;
import com.otaku.fairyland.main.model.MainActivityModelImpl;
import com.otaku.fairyland.main.view.MainActivityView;

import java.util.HashMap;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public class MainActivityPresenterImpl implements MainActivityPresenter, MainActivityModelImpl.OnResultListener {


    private MainActivityView mainActivityView;
    private MainActivityModelImpl mainActivityModelImpl;

    public MainActivityPresenterImpl(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        this.mainActivityModelImpl = new MainActivityModelImpl();
    }

    @Override
    public void onAdvertiseListSuccess(AdvertiseInfo info) {
        mainActivityView.ResultAdvertiseListSuccess(info);
    }

    @Override
    public void onAdvertiseListFailure(String failureStr) {
        mainActivityView.ResultAdvertiseListFailure(failureStr);
    }

    @Override
    public void onRecommendListStart() {
        mainActivityView.showProgress();
    }

    @Override
    public void onRecommendListSuccess(RecommendInfo info) {
        mainActivityView.ResultRecommendListSuccess(info);
    }

    @Override
    public void onRecommendListFailure(String failureStr) {
        mainActivityView.ResultRecommendListFailure(failureStr);
    }

    @Override
    public void RequestAdvertiseList(HashMap<String, String> map) {
        mainActivityModelImpl.AdvertiseListRequest(map,this);
    }

    @Override
    public void RequestRecommendList(HashMap<String, String> map) {
        mainActivityModelImpl.RecommendListRequest(map, this);
    }
}
