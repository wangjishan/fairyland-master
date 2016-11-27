package com.otaku.fairyland.main.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.otaku.fairyland.R;
import com.otaku.fairyland.base.BaseActivity;
import com.otaku.fairyland.main.AdvertiseInfo;
import com.otaku.fairyland.main.RecommendInfo;
import com.otaku.fairyland.main.Recommends;
import com.otaku.fairyland.main.adapter.HeaderPagerAdapter;
import com.otaku.fairyland.main.adapter.MainActivityAdapter;
import com.otaku.fairyland.main.presenter.MainActivityPresenterImpl;
import com.otaku.fairyland.main.view.MainActivityView;
import com.otaku.fairyland.net.NetUtils;
import com.otaku.fairyland.utils.UtilsLog;
import com.otaku.fairyland.widget.AutoScrollViewPage;
import com.otaku.fairyland.widget.CirclePageIndicator;
import com.otaku.fairyland.widget.MySwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends BaseActivity implements MainActivityView {


    private RecyclerView lv_main;
    private MySwipeRefreshLayout swipeRefreshLayout;
    private AutoScrollViewPage viewPager;
    private CirclePageIndicator indicator;

    private MainActivityAdapter mainActivityAdapter;
    private MainActivityPresenterImpl mainfragmentpresenterimpl;
    private List<Recommends> RecommendList = new ArrayList<Recommends>();
    private List<Recommends> photoPaths = new ArrayList<Recommends>();
    private HeaderPagerAdapter headerPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main);
        setHeadTitle("首页");


        initializeView();
    }


    private void initializeView() {

        mainfragmentpresenterimpl = new MainActivityPresenterImpl(this);
        swipeRefreshLayout = (MySwipeRefreshLayout) this.findViewById(R.id.swipeRefreshLayout);
        lv_main = (RecyclerView) this.findViewById(R.id.Main_RecyclerView);

        View header = LayoutInflater.from(this).inflate(R.layout.header_layout, null);
        viewPager = (AutoScrollViewPage) header.findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) header.findViewById(R.id.indicator);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lv_main.setLayoutManager(layoutManager);

        mainActivityAdapter = new MainActivityAdapter(this, lv_main, RecommendList, R.layout.mainactivityadapter_layout);
        headerPagerAdapter = new HeaderPagerAdapter(this, photoPaths);
        mainActivityAdapter.addHeadView(header);

        initializeData();
    }


    /**/
    private void initializeData() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        lv_main.setAdapter(mainActivityAdapter);
        viewPager.setAdapter(headerPagerAdapter);
        indicator.setViewPager(viewPager);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

//                if (!isDataLoaded) {
//                    swipeRefreshLayout.setRefreshing(true);
//                    NetForAdvertise();
                NetForAdvertise();
                NetForRecommendList();
//                    NetForRecommendList(BaseRecylerViewAdapter.other);
//                }
            }
        });
    }


    /*首页的列表*/
    private void NetForAdvertise() {

        HashMap<String, String> map = new HashMap<String, String>();
        mainfragmentpresenterimpl.RequestAdvertiseList(NetUtils.getMapParamer("advertise", map));
    }

    /*首页的列表*/
    private void NetForRecommendList() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("page", "1");
        map.put("pageSize", "20");
        mainfragmentpresenterimpl.RequestRecommendList(NetUtils.getMapParamer("recommends", map));

    }


    @Override
    public void ResultAdvertiseListSuccess(AdvertiseInfo info) {
        photoPaths.clear();
        photoPaths.addAll(info.getData());
        headerPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void ResultAdvertiseListFailure(String failureStr) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void ResultRecommendListSuccess(RecommendInfo info) {
        UtilsLog.i("str", "====" + info);
        loadSuccess();
        mainActivityAdapter.updateData(info.getData());
        RecommendList.clear();
        RecommendList.addAll(info.getData());


    }

    @Override
    public void ResultRecommendListFailure(String failureStr) {

    }


}
