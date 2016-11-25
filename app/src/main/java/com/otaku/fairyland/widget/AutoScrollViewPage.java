package com.otaku.fairyland.widget;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${wangjishan} on 2016/7/25.
 *
 * @version V1.0
 * @Description:
 */
public class AutoScrollViewPage extends ViewPager {

    private static final String TAG = AutoScrollViewPage.class.getSimpleName();
    private static final int WHAT_SCROLL = 0;
    private long mDelayTime = 3000;

    private float mDownX;
    private float mDownY;

    private boolean isAutoScroll;
    public boolean isStopByTouch;

    public AutoScrollViewPage(Context context) {
        this(context, null);
    }

    public AutoScrollViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getX() - mDownX) > Math.abs(ev.getY() - mDownY)) {

                    //不允许拦截事件，自己处理
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            stopAutoScroll();
            isStopByTouch = true;
        } else if (ev.getAction() == MotionEvent.ACTION_UP && isStopByTouch) {
            startAutoScroll();
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setDelayTime(int delayTime) {
        this.mDelayTime = delayTime;
    }


    public void startAutoScroll() {
        isAutoScroll = true;
        sendScrollMessage(mDelayTime);
    }

    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeMessages(WHAT_SCROLL);
    }

    public boolean isAutoScrolling() {
        return isAutoScroll;
    }

    private void sendScrollMessage(long delayTimeInMills) {
        if (isAutoScroll) {
            handler.removeMessages(WHAT_SCROLL);
            handler.sendEmptyMessageDelayed(WHAT_SCROLL, delayTimeInMills);
        }
    }

    private void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        int count;
        if (adapter == null || (count = adapter.getCount()) < 1) {
            stopAutoScroll();
            return;
        }
        if (currentItem < count) {
            currentItem++;
        }
        if (currentItem == count) {
            currentItem = 0;
        }
        setCurrentItem(currentItem);
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_SCROLL) {
                scrollOnce();
                sendScrollMessage(mDelayTime);
            }
        }
    };


}
