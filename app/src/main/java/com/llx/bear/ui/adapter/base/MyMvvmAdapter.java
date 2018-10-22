package com.llx.bear.ui.adapter.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 史航
 * on 2017/9/11-17:22
 */

public class MyMvvmAdapter<T> extends BaseMvvmAdapter<T>{

    int mLayoutId;
    int mVarId;

    public MyMvvmAdapter(Context ctx, List<T> list, int mLayoutId, int mVarId) {
        super(ctx, list);
        this.mLayoutId = mLayoutId;
        this.mVarId = mVarId;
    }

    @Override
    public RecyclerHolder createContentHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), mLayoutId, parent, false);
        return new RecyclerHolder(binding);
    }

    @Override
    public int getContentViewType(int position, T data) {
        return 0;
    }

    @Override
    public void onInjectView(RecyclerHolder holder, T data, int position) {
        holder.getDataBinding().setVariable(mVarId,data);
        holder.getDataBinding().executePendingBindings();
    }

}
