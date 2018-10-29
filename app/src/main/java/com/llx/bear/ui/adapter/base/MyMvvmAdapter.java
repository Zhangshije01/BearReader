package com.llx.bear.ui.adapter.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 史航
 * on 2017/9/11-17:22
 */

public class MyMvvmAdapter<T> extends BaseMvvmAdapter<T>{

    int mLayoutId;
    int mVarId;
    private OnItemClickListener<T> mItemClickListenr;

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
    public void onInjectView(RecyclerHolder holder, final T data, final int position) {
        holder.getDataBinding().setVariable(mVarId,data);
        holder.getDataBinding().executePendingBindings();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListenr!=null){
                    mItemClickListenr.onItemClick(data,position);
                }
            }
        });
    }

    public void setItemClickListenr(OnItemClickListener<T> mItemClickListenr) {
        this.mItemClickListenr = mItemClickListenr;
    }

    public interface OnItemClickListener<T>{
        void onItemClick(T data,int position);
        void onItemLongClick();
    }

}
