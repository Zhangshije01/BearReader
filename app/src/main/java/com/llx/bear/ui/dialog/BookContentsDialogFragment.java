package com.llx.bear.ui.dialog;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.llx.bear.BR;
import com.llx.bear.R;
import com.llx.bear.databinding.FragmentDialogBookContentsBinding;
import com.llx.bear.model.resultBean.BookMixAToc;
import com.llx.bear.ui.adapter.base.MyMvvmAdapter;
import com.llx.bear.ui.base.BaseDialogFragment;
import com.llx.suandroidbase.commen.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangshijie
 * Time: 2018/11/2 16:31
 *          阅读页面打开目录
 */

public class BookContentsDialogFragment extends BaseDialogFragment {
    private FragmentDialogBookContentsBinding mBinding;
    private MyMvvmAdapter<BookMixAToc.mixToc.Chapters> mAdapter;
    private List<BookMixAToc.mixToc.Chapters> mChaptersLists = new ArrayList<>();
    private MyMvvmAdapter.OnItemClickListener<BookMixAToc.mixToc.Chapters> mListener;
    public BookContentsDialogFragment() {
    }

    public BookContentsDialogFragment(List<BookMixAToc.mixToc.Chapters> mChaptersLists) {
        this.mChaptersLists = mChaptersLists;
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

        mAdapter = new MyMvvmAdapter<BookMixAToc.mixToc.Chapters>(mActivity,mChaptersLists,R.layout.item_book_reader_contents, BR.Chapters){
            @Override
            public void onInjectView(RecyclerHolder holder, final BookMixAToc.mixToc.Chapters data, final int position) {
                super.onInjectView(holder, data, position);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mListener!=null){
                            mListener.onItemClick(data,position);
                            dismissAllowingStateLoss();
                        }
                    }
                });
            }
        };
        mBinding.recyclerBookReaderContents.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.recyclerBookReaderContents.setAdapter(mAdapter);

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

    public void setItemClickListener(MyMvvmAdapter.OnItemClickListener<BookMixAToc.mixToc.Chapters> mListener) {
        this.mListener = mListener;
    }
}
