package com.otaku.fairyland.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.otaku.fairyland.R;
import com.otaku.fairyland.utils.ActivityManager;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/6/13.
 */
public class BaseActivity extends AppCompatActivity {

    public BaseFragment_layout baseLayout;

    /**
     * 头部组件
     */
    public Toolbar toolbar = null;

    /**
     * 头部右侧的头像
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getActivityManager().pushActivity(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ViewServer.get(this).removeWindow(this);
    }


    /**
     * 友盟session的统计
     */
    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    /**
     * 友盟session的统计
     */
    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    /**
     * 设置布局
     *
     * @param layoutResId 布局id
     */
    protected void setView(int layoutResId) {
        baseLayout = new BaseFragment_layout(this, LayoutInflater.from(this).inflate(layoutResId, null));
        toolbar = (Toolbar) baseLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(baseLayout);
        ButterKnife.bind(this);
        initializeLisenter();
    }


    protected void setView(int layoutResId, Toolbar.OnMenuItemClickListener listener) {
        baseLayout = new BaseFragment_layout(this, LayoutInflater.from(this).inflate(layoutResId, null));
        toolbar = (Toolbar) baseLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(baseLayout);
        ButterKnife.bind(this);
        initializeLisenter();
    }

    /**
     *
     */
    private void initializeLisenter() {
    }

    /**
     * 关闭顶部title
     */
    protected void closeTitle() {

        baseLayout.closeTitle();
    }

    /**
     * 显示与隐藏左边的返回箭头
     *
     * @param
     */
    protected void setLeftBack() {
//        baseLayout.setLeftBack(isshow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Handle Back Navigation :D
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.onBackPressed();
            }
        });
    }

    /**
     * 设置title
     */
    protected void setHeadTitle(String title) {
        baseLayout.setTitle(title);
    }

    /**
     * 设置右侧的头部的图片显示
     */
    protected void setRightImage(String url, View.OnClickListener lietener) {
        baseLayout.setRightImage(url, lietener);
    }

    /**
     * @param listener 头部menu的显示
     */
    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        if (toolbar != null) {
            toolbar.setOnMenuItemClickListener(listener);
        }
    }

    /**
     * 加载开始
     */
    protected void loadStart() {
        baseLayout.loadStart();
    }

    /**
     * 加载失败
     */
    protected void loadFailure() {
        baseLayout.loadFailure();
    }

    /**
     * 加载失败,点击重试
     */
    protected void loadFailure(View.OnClickListener lisenter) {
        baseLayout.loadFailure(lisenter);
    }

    /**
     * 加载成功
     */
    protected void loadSuccess() {
        baseLayout.loadSuccess();
    }

    /**
     * 无数据
     */
    protected void loadNoData() {
        baseLayout.loadNoneData();
    }

    /**
     * 是否加载成功
     *
     * @return
     */
    public boolean isLoadSuccess() {
        return baseLayout.isLoadSuccess();
    }
}
