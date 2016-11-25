package com.otaku.fairyland.base;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.otaku.fairyland.R;


/**
 * Created by ${wangjishan} on 2015/11/30.
 *
 * @version V1.0
 * @Description:
 */
public class BaseFragment_layout extends RelativeLayout {


    private View baseinflate;
    private View layoutresource;
    private View head_view;
    private Toolbar toolbar;
    private TextView tv_title;
    private ImageView iv_Circle;
    private ProgressBar progressBar;
    private LinearLayout ll_Prompt;
    private TextView tv_Prompt;

    public BaseFragment_layout(Context context) {
        super(context);
    }

    public BaseFragment_layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFragment_layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public BaseFragment_layout(Context context, View view) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        /**
         * 添加头部
         * */
        head_view = layoutInflater.inflate(R.layout.head_layout, null);
        toolbar = (Toolbar) head_view.findViewById(R.id.toolbar);
        tv_title = (TextView) head_view.findViewById(R.id.tv_title);
        iv_Circle = (ImageView) head_view.findViewById(R.id.iv_Circle);
        toolbar.setTitle("");
        LayoutParams headparams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        headparams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        addView(head_view, headparams);


        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.BELOW, R.id.toolbar);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        this.layoutresource = view;
        addView(view, params);

        baseinflate = layoutInflater.inflate(R.layout.basefragment_layout, null);
        progressBar = (ProgressBar) baseinflate.findViewById(R.id.progressBar);
        ll_Prompt = (LinearLayout) baseinflate.findViewById(R.id.ll_Prompt);
        tv_Prompt = (TextView) baseinflate.findViewById(R.id.tv_Prompt);
        addView(baseinflate, params);

    }

    public void closeTitle() {
        this.removeView(head_view);
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        if (title != null)
            tv_title.setText(title);
        else
            tv_title.setVisibility(View.GONE);
//        toolbar.setTitle(title);
    }

    /**
     * @param isshow
     */
    public void setLeftBack(boolean isshow) {
        if (isshow) {
//            rl_back.setVisibility(View.VISIBLE);
        } else {
//            rl_back.setVisibility(View.GONE);
        }
    }

    /**
     * @param url 设置头部右侧的图片显示
     */
    public void setRightImage(String url, OnClickListener lietener) {
        iv_Circle.setVisibility(VISIBLE);
        if (lietener != null) {
            iv_Circle.setOnClickListener(lietener);
        }
    }

    /**
     * 开始加载
     */
    public void loadStart() {
        baseinflate.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        ll_Prompt.setVisibility(View.GONE);
        layoutresource.setVisibility(View.GONE);
    }

    /**
     * 加载成功
     */
    public void loadSuccess() {
        baseinflate.setVisibility(View.GONE);
        layoutresource.setVisibility(View.VISIBLE);
    }

    /**
     * 加载失败
     */
    public void loadFailure() {

        layoutresource.setVisibility(View.GONE);
        baseinflate.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        ll_Prompt.setVisibility(View.VISIBLE);
        tv_Prompt.setText(R.string.baseAgain);

    }

    /**
     * 加载失败 点击重试
     */
    public void loadFailure(OnClickListener lisenter) {

        layoutresource.setVisibility(View.GONE);
        baseinflate.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        ll_Prompt.setVisibility(View.VISIBLE);
        tv_Prompt.setText(R.string.baseAgain);
        baseinflate.setEnabled(true);
        baseinflate.setClickable(true);
        baseinflate.setOnClickListener(lisenter);
    }

    /**
     * 暂无数据
     */
    public void loadNoneData() {
        baseinflate.setEnabled(false);
        baseinflate.setClickable(false);
        layoutresource.setVisibility(View.GONE);
        baseinflate.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        ll_Prompt.setVisibility(View.VISIBLE);
        tv_Prompt.setText(R.string.noneData);

    }

    /**
     * 是否加载成功
     *
     * @return
     */
    public boolean isLoadSuccess() {
        if (layoutresource.getVisibility() == View.GONE)
            return false;
        else
            return true;
    }


}
