package com.otaku.fairyland.main;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27.
 */

public class AdvertiseInfo implements Serializable {


    private String code;
    private String message;
    private String totalCount;
    private List<Recommends> data;

    public AdvertiseInfo() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<Recommends> getData() {
        return data;
    }

    public void setData(List<Recommends> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RecommendInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", data=" + data +
                '}';
    }


}
