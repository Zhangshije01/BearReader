package com.llx.bear.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.llx.bear.R;


/**
 * Created by Administrator on 2017/7/19.
 */

public class BearCancelScrollViewPager extends ViewPager {

    private boolean isPagingEnabled = false;

    public BearCancelScrollViewPager(Context context) {
        super(context);
    }

    public BearCancelScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);

    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BearCancelScrollViewPager);
        isPagingEnabled = a.getBoolean(R.styleable.BearCancelScrollViewPager_cancel_scroll, true);
        a.recycle();
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return !this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }


    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
