package com.llx.bear.ui.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.llx.bear.commen.utils.DensityUtils;

/**
 * @author zhangshijie
 *
 */
public class BearGralleyView extends LinearLayout implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private OnPageChangeListener mListener;
    public interface OnPageChangeListener {
        void onPageSelected(int position);
    }

    public void setOnPageChangeListener(OnPageChangeListener mListener) {
        this.mListener = mListener;
    }

    public BearGralleyView(@NonNull Context context) {
        super(context);
        init();
    }

    public BearGralleyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BearGralleyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BearGralleyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        setClipChildren(false);
        initView();
        initEvent();
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(this);
    }

    private void initView() {
        // 创建imageView
        viewPager = new ViewPager(getContext());
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOverScrollMode(OVER_SCROLL_NEVER);
        viewPager.setPageMargin(DensityUtils.dp2px(getContext(), 15));
        LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,1.0f);
        params.setMargins(DensityUtils.dp2px(getContext(), 45), 0, DensityUtils.dp2px(getContext(), 45), 0);
        addView(viewPager,params);

    }

    /**
     * 设置适配器
     * @param adapter
     */
    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null) {
            viewPager.setAdapter(adapter);
            // 默认高亮显示第一个
            viewPager.post(new Runnable() {
                @Override
                public void run() {
                    viewPager.getChildAt(0).setSelected(true);
                }
            });
        }
    }

    public void setCurrentItem(int position){
        setCurrentItem(position,false);
    }
    public void setCurrentItem(int position,boolean smoothScroll){
        viewPager.setCurrentItem(position,smoothScroll);
    }

    /**
     * 重写onTouch事件，将viewGroup的事件传递给viewpager，防止边缘不能滑动的问题
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return viewPager.dispatchTouchEvent(event);
    }

    /**
     * viewPager滑动的监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mListener != null) {
            mListener.onPageSelected(position);
        }
        // 设置选中状态
        for (int i = 0; i < viewPager.getChildCount(); i++) {
            View view = viewPager.getChildAt(i);
            if (i == position) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
