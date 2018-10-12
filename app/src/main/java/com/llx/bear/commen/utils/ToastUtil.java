package com.llx.bear.commen.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.llx.bear.BearReaderApplication;
import com.llx.bear.R;
import com.llx.bear.ui.widget.BearFontTextView;

/**
 * @author: zhangshijie
 * Time: 2018/10/9 15:19
 */

public class ToastUtil {

    public static void showSuccToast(String msg){
        View view = LayoutInflater.from(BearReaderApplication.getInstance()).inflate(R.layout.toast_show_success_layout,null);
        if(!TextUtils.isEmpty(msg)){
            BearFontTextView tv_show = view.findViewById(R.id.tv_toast_show);
            tv_show.setText(msg);
        }

        Toast toast = new Toast(BearReaderApplication.getInstance());
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
