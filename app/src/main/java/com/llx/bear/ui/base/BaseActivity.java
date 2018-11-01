package com.llx.bear.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.llx.bear.ui.dialog.LoadingDialog;


/**
 * @author: zhangshijie
 * Time: 2018/10/9 11:42
 */

public abstract class BaseActivity extends FragmentActivity {
    public Activity mActivity;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mActivity = this;
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

}
