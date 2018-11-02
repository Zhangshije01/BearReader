package com.llx.bear.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * @author: zhangshijie
 * Time: 2018/11/2 16:29
 */

public class BaseDialogFragment extends DialogFragment{
    public BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        Class<?> clazz = DialogFragment.class;
        Field mDismissed = null;
        Field mShownByMe = null;
        try {
            mDismissed = clazz.getDeclaredField("mDismissed");
            mShownByMe = clazz.getDeclaredField("mShownByMe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mDismissed.setAccessible(true);
        mShownByMe.setAccessible(true);

        try {
            mDismissed.setBoolean(this, false);
            mShownByMe.setBoolean(this, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //mDismissed = false;
        //mShownByMe = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
