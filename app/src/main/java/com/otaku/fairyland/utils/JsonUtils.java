package com.otaku.fairyland.utils;

import com.google.gson.Gson;

/**
 * Created by ${wangjishan} on 2016/6/14.
 *
 * @version V1.0
 * @Description:
 */
public class JsonUtils {

    private static Gson gson;

    /**
     * 返回一个Javabean
     *
     * @param str 返回的内容
     * @param cls 实体名
     * @return Javabean
     * @throws Exception
     */
    public static <T> T getJson(String str, Class<T> cls) {
        try {
            if (gson == null) {
                gson = new Gson();
            }
            return gson.fromJson(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
