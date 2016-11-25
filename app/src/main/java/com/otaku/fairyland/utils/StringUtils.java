package com.otaku.fairyland.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${wangjishan} on 2015/12/1.
 *
 * @version V1.0
 * @Description:
 */
public class StringUtils {

    /**
     * 判断是否为空
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        if (text == null || "".equals(text.trim()) || text.trim().length() == 0
                || "null".equals(text.trim())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 电话号码验证
     *
     * @param phoneNumber 手机号码
     * @return
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
        Matcher m = pattern.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * @param context 获取版本
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 将字符串进行MD5编码
     *
     * @param key
     * @return
     */

    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    /**
     * 将字节数组编码
     *
     * @param bytes
     * @return
     */

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 获取下载文件的格式
     *
     * @param imageurl
     * @return
     */

    public static String getImageFormat(String imageurl) {
        String format = "png";
        if (!isNullOrEmpty(imageurl) && imageurl.contains(".")) {
            format = imageurl.substring(imageurl.lastIndexOf("."), imageurl.length());
        }
        return format;
    }


    /**
     * 将Map集合转换成Json格式
     *
     * @param map
     * @return
     */
    public static String getJsonStr(HashMap<String, String> map) {
        String jsonresult = "";
        try {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
//				if (!isNullOrEmpty(entry.getValue())) {
                jsonObject.put(entry.getKey(), entry.getValue());
//				}
            }
            jsonresult = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonresult;
    }

    /**
     * 图片地址的截取
     *
     * @param url
     * @return
     */
    public static ArrayList<String> getListStr(String url) {
        ArrayList<String> strings = new ArrayList<>();
        String str = "";
        if (url.contains("Error IN File Upload")) {
            str = url.replace("Error IN File Upload", " ");
        } else if (url.contains("error in file upload")) {
            str = url.replace("error in file upload", " ");
        } else {
            str = url;
        }
        String sTr[] = null;
        if (str.contains(",")) {
            sTr = str.split(",");
        } else if (str.contains("|")) {
            sTr = str.split("\\|");
        } else {
            sTr = new String[]{str};
        }

        for (String s : sTr) {
            if (!isNullOrEmpty(s) && s.contains("http")) {
                strings.add(s);
            }
        }

        return strings;
    }


    /**
     * 去除字符串中的空格、回车、换行符、制表符 \n \t \s \r
     *
     * @param str
     * @return
     */

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
