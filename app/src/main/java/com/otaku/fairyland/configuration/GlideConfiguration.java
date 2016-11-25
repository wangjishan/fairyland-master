package com.otaku.fairyland.configuration;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by ${wangjishan} on 2015/11/30.
 *
 * @version V1.0
 * @Description:
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
//      builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        int cacheSize100MegaBytes = 104857600;
//        String downloadDirectoryPath = Environment.getDownloadCacheDirectory().getPath();
//        builder.setDiskCache(
//                new DiskLruCacheFactory(downloadDirectoryPath, "glideCache", cacheSize100MegaBytes)
//        );

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
        glide.get(context).setMemoryCategory(MemoryCategory.LOW);
    }


}
