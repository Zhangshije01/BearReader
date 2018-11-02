package com.llx.bear.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.llx.bear.ui.dialog.LoadingDialog;

/**
 * @author: zhangshijie
 * Time: 2018/10/9 17:27
 */

public abstract class BaseFragment extends Fragment{
    public BaseActivity mActivity;
    private FragmentManager mFragmentManager;
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;
    private LoadingDialog loadingDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mFragmentManager = getChildFragmentManager();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        configView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisible = true;
            lazy();
        }else{
            isVisible = false;
        }
    }

    /**
     * 懒加载的方法
     */
    private void lazy() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        //该方法 只会被调用一次
        lazyLoadData();
        isFirst = false;
    }

    /**
     * 懒加载的方法
     */
    protected void lazyLoadData() {}

    public abstract void configView();

    public LoadingDialog getLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.getInstance(mActivity);
            loadingDialog.setCancelable(true);
        }
        return loadingDialog;
    }

    public void showLoadingDialog() {
        getLoadingDialog().show();
    }

    public void dissmissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public void goneViews(View... views){
        for (View view : views){
            if(view.getVisibility() == View.VISIBLE){
                view.setVisibility(View.GONE);
            }
        }
    }

    public void visibleViews(View... views){
        for (View view : views){
            if(view.getVisibility() != View.VISIBLE){
                view.setVisibility(View.VISIBLE);
            }
        }
    }

}
