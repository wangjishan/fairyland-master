package com.otaku.fairyland.main.model;

import com.otaku.fairyland.configuration.ConstantValues;
import com.otaku.fairyland.main.RecommendInfo;
import com.otaku.fairyland.net.NetUtils;
import com.otaku.fairyland.utils.JsonUtils;
import com.otaku.fairyland.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public class MainActivityModelImpl implements MainActivityModel {


    @Override
    public void AdvertiseListRequest(HashMap<String, String> map, OnResultListener listener) {

    }

    @Override
    public void RecommendListRequest(HashMap<String, String> map, OnResultListener listener) {

        OkHttpUtils.get()
                .url(NetUtils.url)
                .params(map)
                .build()
                .execute(new RecommendListRequestCallback(listener));

    }

    private class RecommendListRequestCallback extends StringCallback {

        private OnResultListener mListener;

        private RecommendListRequestCallback(OnResultListener listener) {
            this.mListener = listener;
        }

        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            if (!StringUtils.isNullOrEmpty(e.getMessage())) {
                mListener.onRecommendListFailure(e.getMessage());
            }
        }

        @Override
        public void onResponse(String response, int id) {
            try {
                RecommendInfo bean = JsonUtils.getJson(response, RecommendInfo.class);
                if (bean != null && bean.getCode().equals(ConstantValues.REQUEST_SUCCESS)) {
                    mListener.onRecommendListSuccess(bean);
                } else if (bean != null) {
                    mListener.onRecommendListFailure(bean.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                mListener.onRecommendListFailure("列表获取失败");
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }


    public interface OnResultListener {

        void onAdvertiseListSuccess();

        void onAdvertiseListFailure(String failureStr);

        void onRecommendListStart();

        void onRecommendListSuccess(RecommendInfo info);

        void onRecommendListFailure(String failureStr);

    }


}
