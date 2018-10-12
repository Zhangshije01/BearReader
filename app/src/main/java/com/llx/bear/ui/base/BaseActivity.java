package com.llx.bear.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.llx.bear.commen.utils.StatusBarUtil;

/**
 * @author: zhangshijie
 * Time: 2018/10/9 11:42
 */

public class BaseActivity extends FragmentActivity{
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mActivity = this;

        //屏幕状态栏
        setScreenView();
    }
    public void setScreenView(){
    }
}
