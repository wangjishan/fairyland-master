package com.otaku.fairyland.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.otaku.fairyland.utils.StringUtils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by ${wangjishan} on 2016/6/30.
 *
 * @version V1.0
 * @Description:网络请求时传入的参数信息
 */
public class NetUtils {

    /**
     * 图片的上传的接口
     */
    public static String UPLOADPICURL = "http://img.n8guanjia.com/Ashx/Upload.ashx";
    /**
     *
     */
//    public static String url = "http://febscrest.ppku.cc/GetHandler.ashx?";
//    http://wangjishan9.duapp.com/api.php?page=1&pageSize=20&output=json
    public static String url = "http://wangjishan9.duapp.com/api.php?";
    /**
     *
     */
//    public static String posturl = "http://restc.n8guanjia.com/PostHandler.ashx?";
    private static String MerchantPassword = "6F2D157B25E916C064CF07BB5B98059D";


    public static java.util.HashMap<String, String> getMapParamer(String methodname, java.util.HashMap<String, String> parameter) {
        java.util.Date now = new java.util.Date();
        java.util.HashMap<String, String> map = new java.util.HashMap<String, String>();

//        map.put("app_key", MerchantCode);
//        map.put("method", methodname);
        map.put("parameter", (parameter == null || parameter.size() <= 0) ? "{}" : StringUtils.getJsonStr(parameter));
//        map.put("timestamp", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));
//        map.put("version", "v1.1");
        map.put("output", "json");
        map.put("sign", SignRequest(map, MerchantPassword));
//        map.put("imei", MyApplication.getInstance().getImei());
        return map;
    }


    public static String SignRequest(Map<String, String> parameters, String secret) {
        Map<String, String> sortedParams = parameters;
        StringBuilder query = new StringBuilder();
        Set<String> keySet = sortedParams.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (key != "") {
                query.append(key).append(sortedParams.get(key));
            }
        }
        query.append(secret);
        return Md5(query.toString());
    }

    public static String Md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().toUpperCase(Locale.getDefault());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    /**
     * 判断是否接入网络
     *
     * @param mContext
     * @return
     */
    public static boolean netIsAvailable(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else {
                if (info.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }


}
