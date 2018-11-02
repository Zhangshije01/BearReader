package com.llx.bear.ui.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import com.llx.bear.ui.dialog.LoadingDialog;


/**
 * @author: zhangshijie
 * Time: 2018/10/9 11:42
 */

public abstract class BaseActivity extends FragmentActivity {
    public Activity mActivity;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        transparent19and20();
    }


    public void transparent19and20(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public LoadingDialog getLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.getInstance(this);
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

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

}
