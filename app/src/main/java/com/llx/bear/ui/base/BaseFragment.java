package com.llx.bear.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * @author: zhangshijie
 * Time: 2018/10/9 17:27
 */

public class BaseFragment extends Fragment{
    public BaseActivity mActivity;
    private FragmentManager mFragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mFragmentManager = getChildFragmentManager();
    }
}
