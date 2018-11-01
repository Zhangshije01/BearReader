package com.llx.bear.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.bear.R;

/**
 * @author: zhangshijie
 * Time: 2018/11/1 13:41
 * loading界面
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context,0);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static LoadingDialog getInstance(Activity activity){
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_loading_dialog,null);
        LoadingDialog dialog = new LoadingDialog(activity,R.style.loading_dialog);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return dialog;
    }
}
