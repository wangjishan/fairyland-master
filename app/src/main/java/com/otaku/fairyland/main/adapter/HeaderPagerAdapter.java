package com.otaku.fairyland.main.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.otaku.fairyland.R;
import com.otaku.fairyland.main.Recommends;
import com.otaku.fairyland.utils.GlideUtils;
import com.otaku.fairyland.utils.IntentUtils;
import com.otaku.fairyland.video.ui.VideoActivity;

import java.util.List;

/**
 * Created by ${wangjishan} on 2016/7/25.
 *
 * @version V1.0
 * @Description:
 */
public class HeaderPagerAdapter extends PagerAdapter {


    private List<Recommends> mStories;
    private int mChildCount;
    private LayoutInflater inflater;
    private GlideUtils glideUtils;
    private Activity mContext;
    private Bundle bundle;


    public HeaderPagerAdapter(Activity context, List<Recommends> stories) {
        this.mStories = stories;
        this.inflater = LayoutInflater.from(context);
        this.glideUtils = GlideUtils.getInstance();
        this.mContext = context;
        this.bundle = new Bundle();
    }

    @Override
    public int getCount() {
        return mStories == null ? 0 : mStories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.headerpageradapter_layout, null);
        ImageView iv_header = (ImageView) view.findViewById(R.id.iv_header);

        final Recommends advInfos = mStories.get(position);
        if (advInfos != null) {
            glideUtils.displayImage(mContext, advInfos.getDefaultpic(), iv_header);

            iv_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle.clear();
                    bundle.putString(VideoActivity.VIDEOURl, advInfos.getVideoaddress());
                    IntentUtils.ToActivity(mContext, VideoActivity.class,bundle);
                }
            });
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mChildCount = getCount();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}
