package com.otaku.fairyland.video.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.otaku.fairyland.R;
import com.otaku.fairyland.base.BaseActivity;

import org.lynxz.zzplayerlibrary.controller.IPlayerImpl;
import org.lynxz.zzplayerlibrary.util.OrientationUtil;
import org.lynxz.zzplayerlibrary.widget.VideoPlayer;

/**
 * Created by ${wangjishan} on 2016/11/25.
 *
 * @version V1.0
 * @Description:
 */

public class VideoActivity extends BaseActivity {


    public static String VIDEOURl = "VideoUrl";

    private VideoPlayer mVp;
    private String mVideoUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.videoactivity_layout);

        mVideoUrl = getIntent().getExtras().getString(VIDEOURl);

        initData();
        initView();
        initListener();

    }


    private IPlayerImpl playerImpl = new IPlayerImpl() {

        @Override
        public void onNetWorkError() {
            showToast(null);
        }

        @Override
        public void onBack() {
            // 全屏播放时,单击左上角返回箭头,先回到竖屏状态,再关闭
            // 这里功能最好跟onBackPressed()操作一致
//            int orientation = OrientationUtil.getOrientation(VideoActivity.this);
//            if (orientation == OrientationUtil.HORIZONTAL) {
//                OrientationUtil.forceOrientation(VideoActivity.this, OrientationUtil.VERTICAL);
//            } else {
//                finish();
//            }

            finish();
        }

        @Override
        public void onError() {
            showToast("播放器发生异常");
        }
    };

    private void initListener() {
        mVp.setPlayerController(playerImpl);
    }

    private void initData() {
        //        mVideoUrl = "android.resource://" + getPackageName() + "/" + R.raw.shuai_dan_ge;
//        mVideoUrl = "http://124.205.69.166/mp4files/50630000045C1BA0/45.43.9.2/media/videos/mp4/8422.mp4";
    }

    private void initView() {
        mVp = (VideoPlayer) findViewById(R.id.vp);
        mVp.setTitle("视频名称");
        mVp.loadAndStartVideo(this, mVideoUrl);
        //设置控制栏播放/暂停/全屏/退出全屏按钮图标
        mVp.setIconPlay(R.drawable.play);
        mVp.setIconPause(R.drawable.pause);
        mVp.setIconExpand(R.drawable.expand);
        mVp.setIconShrink(R.drawable.shrink);
        //隐藏/显示控制栏时间值信息
        // mVp.hideTimes();
        // mVp.showTimes();
        // 自定义加载框图标
        mVp.setIconLoading(R.drawable.loading_red);

        // 设置进度条样式
        mVp.setProgressThumbDrawable(R.drawable.progress_thumb);
        mVp.setProgressLayerDrawables(R.drawable.biz_video_progressbar);//自定义的layer-list
        // mVp.setProgressLayerDrawables(0, 0, R.drawable.shape_progress);//逐层设置,0的话表示保持默认

        // mVp.setControlFlag(VideoPlayer.FLAG_DISABLE_VOLUME_CHANGE);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mVp != null) {
            mVp.updateActivityOrientation();
        }
    }


    private void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = getResources().getString(R.string.zz_player_network_invalid);
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVp.onHostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVp.onHostPause();
    }

    @Override
    public void onBackPressed() {
        mVp.onHostDestroy();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVp.onHostDestroy();
    }


}
