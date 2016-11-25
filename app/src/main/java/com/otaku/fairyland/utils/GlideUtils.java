package com.otaku.fairyland.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.otaku.fairyland.MyApplication;
import com.otaku.fairyland.R;
import com.otaku.fairyland.configuration.GlideCircleTransform;

import java.io.IOException;

/**
 * Created by ${wangjishan} on 2015/11/30.
 *
 * @version V1.0
 * @Description:
 */
public class GlideUtils {


    private static GlideUtils instance;

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 加载网络的图片
     *
     * @param picurl
     * @param imageview
     */
    public void displayImage(Context context, String picurl, ImageView imageview) {

        Glide.with(context).load(picurl)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_pic)
//                .thumbnail(0.1f)
                .dontAnimate()
                .dontTransform()
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .error(R.drawable.default_pic)
                .crossFade()
                .fitCenter()
                .into(imageview);
    }


//    /**
//     * 加载网络的图片 适用于加载CircleImageView
//     *
//     * @param picurl
//     * @param imageview
//     */
//    public void displayCircleImage(Context context, String picurl, CircleImageView imageview) {
//        if (StringUtils.isNullOrEmpty(picurl)) {
//            return;
//        }
//        String zoom;
//        if (imageview.getWidth() != 0 && imageview.getHeight() != 0)
//            zoom = StringUtils.getZoom(picurl, imageview.getWidth(), imageview.getHeight());
//        else
//            zoom = picurl;
//        Glide.with(context).load(zoom)
////                .placeholder(R.drawable.default_pic)
////                .thumbnail(0.1f)
////                .dontAnimate()
////                .dontTransform()
////                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .error(R.drawable.default_picerror)
//                .crossFade()
//                .fitCenter()
//                .into(imageview);
//
////        Glide.with(mContext)
////                .load(zoom)
////                .crossFade()
////                .into(imageview);
//    }


    /**
     * 加载网络的图片
     *
     * @param picurl
     * @param imageview
     */
    public void displayImage(Context context, Uri picurl, ImageView imageview) {
        Glide.with(context).load(picurl)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .dontAnimate()
                .dontTransform()
                .placeholder(R.drawable.default_pic)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .error(R.drawable.default_pic)
                .crossFade()
                .fitCenter()
                .into(imageview);
    }

    /**
     * 加载本地图片
     *
     * @param picurl
     * @param imageview
     */
    public void displayLocalImage(Context context, Uri picurl, ImageView imageview) {
        Glide.with(context).load(picurl).thumbnail(0.1f)
                .dontAnimate()
                .dontTransform()
                .placeholder(R.drawable.default_pic)
                .error(R.drawable.default_pic)
                .fitCenter()
                .into(imageview);
    }

    /**
     * 加载本地图片
     *
     * @param picurl
     * @param imageview
     */
    public void displayLocalImage(Context context, Uri picurl, ImageView imageview, int width, int height) {
        Glide.with(context).load(picurl).thumbnail(0.1f)
                .override(width, height)
                .dontAnimate()
                .dontTransform()
                .placeholder(R.drawable.default_pic)
                .error(R.drawable.default_pic)
                .fitCenter()
                .into(imageview);
    }


    /**
     * 带有回调的加载，可通过url获得相应的Bitmap
     *
     * @param picurl
     */

    public void displayImage(Context context, Uri picurl, int width, int height, SimpleTarget<Bitmap> simpletarget) {
        Glide.with(context).load(picurl).asBitmap().thumbnail(0.5f)
                .override(width, height)
                .dontAnimate()
                .fitCenter()
                .dontTransform()
                .into(simpletarget);
    }


    /**
     * 带有回调的加载，可通过url获得相应的Bitmap
     *
     * @param picurl
     */

    public void displayImage(Context context, String picurl, int width, int height, SimpleTarget<Bitmap> simpletarget) {
        Glide.with(context).load(picurl).asBitmap().thumbnail(0.5f)
                .override(width, height)
                .dontAnimate()
                .dontTransform()
                .fitCenter()
                .into(simpletarget);
    }


    /**
     * 清除view中的glide的缓存
     *
     * @param view
     */
    public void clearCache(View view) {
        if (view != null) {
            Glide.clear(view);
        }
    }

    /**
     * 暂停图片的加载
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 唤醒图片的加载
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }


    /**
     * 获取glide的缓存路径
     *
     * @return
     */
    public static String CachePath() {
        try {
            return Glide.getPhotoCacheDir(MyApplication.getSelf().getContext()).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 显示圆形的图片
     *
     * @param picurl
     * @param imageview
     */
    public void displayCircleImage(Context context, String picurl, ImageView imageview) {

//        DrawableTypeRequest<String> requestBuilder = requestmanager.load(picurl);
//        requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL)
//                .thumbnail(0.1f)
//                .dontAnimate()
//                .dontTransform()
//                .transform(new GlideCircleTransform(context))
//                .crossFade()
//                .fitCenter()
//                .into(imageview);
        Glide.with(context).load(picurl).centerCrop().dontAnimate()
                .transform(new GlideCircleTransform(context)).into(imageview);
    }


}
