package com.otaku.fairyland.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.otaku.fairyland.R;
import com.otaku.fairyland.base.BaseRecylerViewAdapter;
import com.otaku.fairyland.base.BaseViewHolder;
import com.otaku.fairyland.main.Recommends;
import com.otaku.fairyland.utils.GlideUtils;
import com.otaku.fairyland.video.ui.VideoActivity;

import java.util.List;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public class MainActivityAdapter extends BaseRecylerViewAdapter<Recommends> {

    private GlideUtils glideUtils;
    private Context context;


    public MainActivityAdapter(Context mContext, RecyclerView recyclerView, List<Recommends> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
        this.glideUtils = GlideUtils.getInstance();
        this.context = mContext;
    }


    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, Recommends recommends) {


        if (holder instanceof BaseViewHolder) {
            ImageView iv_pic = ((BaseViewHolder) holder).getView(R.id.iv_pic);
            glideUtils.displayImage(context, recommends.getDefaultpic(), iv_pic);
//            ((BaseViewHolder) holder).setText(R.id.tv_title, context.getString(R.string.recommend_size, recommends.getTitle(), StringUtils.getListStr(recommends.getImages()).size()));
            ((BaseViewHolder) holder).setText(R.id.tv_tagName, recommends.getCategory());
//            ((BaseViewHolder) holder).setText(R.id.tv_name, recommends.getTimeVal());
            CardView cardView = ((BaseViewHolder) holder).getView(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, VideoActivity.class);
                    context.startActivity(intent);
                }
            });

        }


    }


}
