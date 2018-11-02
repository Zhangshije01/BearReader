package com.llx.bear.ui.dialog;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.llx.bear.R;
import com.llx.bear.databinding.FragmentDialogBookContentsBinding;
import com.llx.bear.ui.base.BaseDialogFragment;
import com.llx.suandroidbase.commen.ScreenUtils;

/**
 * @author: zhangshijie
 * Time: 2018/11/2 16:31
 *          阅读页面打开目录
 */

public class BookContentsDialogFragment extends BaseDialogFragment {
    private FragmentDialogBookContentsBinding mBinding;

    public BookContentsDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_book_contents,container,false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLocation();
    }

    public void setLocation(){
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp;
        if(window!=null){
            wlp = window.getAttributes();
            wlp.width = ScreenUtils.getScreenWidth()*2/3;
            wlp.height = ScreenUtils.getScreenHeight();
            wlp.gravity = Gravity.LEFT;
            window.setAttributes(wlp);
        }
    }
}
