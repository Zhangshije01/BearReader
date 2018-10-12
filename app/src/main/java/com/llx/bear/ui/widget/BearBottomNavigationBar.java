package com.llx.bear.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.llx.bear.R;


/**
 * Created by Administrator on 2017/7/19.
 * <p>
 * tlz 底部导航栏
 */

public class BearBottomNavigationBar extends TabLayout {

    private int res_img_array_id;// 数组资源id
    private int[] resIds;// drawable资源id数组

    public BearBottomNavigationBar(Context context) {
        super(context);
        init();
    }

    public BearBottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public BearBottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TlzBottomNavigationBar);
        res_img_array_id = a.getResourceId(R.styleable.TlzBottomNavigationBar_resource_array_id, -1);
        a.recycle();
    }

    private void init() {
        if (res_img_array_id != -1) {
            TypedArray ar = getContext().getResources().obtainTypedArray(res_img_array_id);
            int len = ar.length();
            resIds = new int[len];
            for (int i = 0; i < len; i++) {
                resIds[i] = ar.getResourceId(i, 0);
            }
            ar.recycle();
        }
    }

    // 设置TabView
    public void setTabView() {
        for (int i = 0; i < resIds.length; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_main_tab_view, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.iv_tlz_tab_icon);
            imageView.setImageResource(resIds[i]);

            TextView textView = (TextView) view.findViewById(R.id.tv_tlz_tab_msg);
            textView.setVisibility(GONE);
            textView.setTag(0);

            TabLayout.Tab tab = this.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(view);
            }

        }
    }

    // 刷新msg
    @SuppressLint("SetTextI18n")
    public void notifyDataSetChanged(int position, int num) {

        if (position < 0 || position >= this.getTabCount()) {
            throw new RuntimeException("数组越界，请检查数据是否准确!" + "position = " + position);
        }

        TabLayout.Tab tab = this.getTabAt(position);
        if (tab != null && tab.getCustomView() != null) {
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_tlz_tab_msg);
            if (num <= 0) {
                textView.setText(String.valueOf(0));
                textView.setVisibility(INVISIBLE);
            } else if (num > 0 && num <= 99) {
                textView.setText(String.valueOf(num));
                textView.setVisibility(VISIBLE);
            } else {
                String str = "99+";
                textView.setText(str);
                textView.setVisibility(VISIBLE);
            }
            // 设置Tag  用于取值
            textView.setTag(num < 0 ? 0 : num);
        }

    }

}
