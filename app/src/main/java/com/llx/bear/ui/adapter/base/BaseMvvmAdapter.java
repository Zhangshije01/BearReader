package com.llx.bear.ui.adapter.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.suandroidbase.commen.LogUtils;

import java.util.List;

/**
 * Created by shihang on 2016/11/16.
 */
public abstract class BaseMvvmAdapter<T> extends RecyclerView.Adapter {
    //item类型
    protected final int ITEM_TYPE_HEADER = 1001;//头视图
    protected final int ITEM_TYPE_FOOTER = 1002;//脚视图
    protected final int ITEM_TYPE_LOADING = 1003;//加载中视图
    protected final int ITEM_TYPE_NONE = 1004;//无数据视图
    protected final int ITEM_TYPE_ERROR = 1005;//加载失败视图
    protected final int ITEM_TYPE_LOAD_MORE = 1006;//加载更多数据

    protected final int LOADING = 101;//加载中
    protected final int EMPTY = 102;//无数据
    protected final int ERROR = 103;//加载失败

    public int NODATA_STATUS = LOADING;//内容 ,根据这个属性来决定在没有数据时需要加载哪种视图,默认状态为正在加载


    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mList;   // 数据集
    protected int totalCount; // adapter最大数量,控制滑到最底部是隐藏加载框
    private int totalProductListCount;

    private View headerView;
    private View footerView;
    private View loadMoreView;


    public BaseMvvmAdapter(Context ctx, List<T> list) {
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);
        mList = list;
    }

    /**
     * 根据ViewType创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_HEADER) {//头视图
            return new RecyclerHolder(headerView);
        } else if (viewType == ITEM_TYPE_FOOTER) {//脚视图
            return new RecyclerHolder(footerView);
        } else if (viewType == ITEM_TYPE_NONE) {//空视图
            View view = getEmptyView(parent);
            return new RecyclerHolder(view);
        } else if (viewType == ITEM_TYPE_ERROR) {//错误视图
            View view = getErrorView(parent);
            return new RecyclerHolder(view);
        } else if (viewType == ITEM_TYPE_LOADING) {//加载中视图
            View view = getLoadingView(parent);
            return new RecyclerHolder(view);
        } else if (viewType == ITEM_TYPE_LOAD_MORE) {
            return new RecyclerHolder(loadMoreView);
        }

        return createContentHolder(parent, viewType);
    }


    // 此方法用来保证第一个item占据 第一行的所有cell(该方法与调用的顺序有关，初始化时需要先setLayoutManager，再setAdapter)
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return notNormalItem(position) ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 是否是非正常item项，如果是则在gridLayoutManager的情况也也只展示一块
     *
     * @param position
     * @return 返回true 则该item占通条位置，否则走正常逻辑（仅限于在GridLayoutManager情况下）
     */
    protected boolean notNormalItem(int position) {
        return getItemViewType(position) == ITEM_TYPE_HEADER ||
                getItemViewType(position) == ITEM_TYPE_ERROR ||
                getItemViewType(position) == ITEM_TYPE_FOOTER ||
                getItemViewType(position) == ITEM_TYPE_LOADING ||
                getItemViewType(position) == ITEM_TYPE_NONE ||
                getItemViewType(position) == ITEM_TYPE_LOAD_MORE;
    }

    /**
     * 根据 position 为holder绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mList == null || mList.size() == 0) {
            if (getItemViewType(position) == ITEM_TYPE_LOAD_MORE) {
                holder.itemView.setVisibility(View.GONE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);
            }
            return;//无数据时不绑定，以免出错
        }

        if (isFootView(position) || isHeaderView(position)) {
            return;//是头布局或者脚布局也不绑定
        }


        //LogUtils.d("lch---", "position===" + position + "\ttotalCount===" + totalCount);
        if (getItemViewType(position) == ITEM_TYPE_LOAD_MORE) {
            if (position + 1 >= totalCount || totalCount == 0) {
                holder.itemView.setVisibility(View.GONE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);
            }
            return;
        }

        if (headerView != null) {
            position--;
        }

        try {
            onInjectView((RecyclerHolder) holder, mList.get(position), position);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 整个recyclerView的item数量
     *
     * @return
     */
    @Override
    public final int getItemCount() {
        return (headerView == null ? 0 : 1) + (footerView == null ? 0 : 1) + getContentItemCount() + (loadMoreView == null ? 0 : 1);
    }


    /*************************以上是必须要重写的方法******************************/


    public abstract RecyclerHolder createContentHolder(ViewGroup parent, int viewType);


    /**
     * 正文内容item数量
     *
     * @return
     */
    public int getContentItemCount() {
        int count = mList == null ? 0 : mList.size();
        if (count == 0) {
            count = 1;
        }
        return count;
    }

    /****************************必须要重写方法的拓展*************************************/

    /**
     * 根据position判断当前item类型
     * 以后再继承的adapter尽量不要调用这个方法，因为不需要处理header和footer的逻辑，推荐用getContentViewType代替
     */

    @Deprecated
    @Override
    public final int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return ITEM_TYPE_HEADER;
        }

        if (isFootView(position)) {
            return ITEM_TYPE_FOOTER;
        }

        if (isLoadMoreView(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }

        if (mList == null || mList.size() == 0) {
            //没有数据时的视图
            switch (NODATA_STATUS) {
                case LOADING:
                    return ITEM_TYPE_LOADING;
                case EMPTY:
                    return ITEM_TYPE_NONE;
                case ERROR:
                    return ITEM_TYPE_ERROR;
                default:
                    return ITEM_TYPE_NONE;
            }
        }

        if (headerView != null) {
            //如果有headerView，则数据项要顺延
            if (position > 0) {
                position--;
            }
        }

        if (position >= mList.size()) {
            LogUtils.d("mytag", "stop");
        }

        return getContentViewType(position, mList.get(position));
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (notNormalItem(position)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }

    /**
     * 判断当前是否是最后一页数据
     * @return
     */
    public boolean isLastPageData() {
        if (mList.size() + (headerView == null ? 0 : 1) + (footerView == null ? 0 : 1) + (loadMoreView == null ? 0 : 1) == totalCount) {
            return true;
        }
        return false;
    }

    public void setTotalCount(int totalCount) {
        this.totalProductListCount = totalCount;
        this.totalCount = totalCount + (headerView == null ? 0 : 1) + (footerView == null ? 0 : 1) + (loadMoreView == null ? 0 : 1);
    }

    public int getTotalCount() {
        return totalProductListCount;
    }

    public abstract int getContentViewType(int position, T data);

    public abstract void onInjectView(RecyclerHolder holder, T data, int position);


    public void setHeaderView(View view) {
        headerView = view;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setLoadMoreView(View view) {
        this.loadMoreView = view;
    }

    private boolean isLoadMoreView(int position) {
        //getItemCount() == position + 1 && (position + 1 != totalCount) && totalCount != 0
        return loadMoreView != null && position == getContentItemCount() + (headerView == null ? 0 : 1) + (footerView == null ? 0 : 1) && totalCount != 0;
    }

    public void setFooterView(View view) {
        footerView = view;
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return headerView != null && position == 0;
    }

    //判断当前item是否是FooterView
    public boolean isFootView(int position) {
        return footerView != null && position == getContentItemCount() + (headerView == null ? 0 : 1);
    }

    protected View getLoadingView(ViewGroup parent) {
        if (getLoadindId() != -1) {
            View view = mInflater.inflate(getLoadindId(), parent, false);
            return view;
        }
        return new View(mContext);
    }

    protected int getLoadindId() {
        return -1;
    }


    protected View getErrorView(ViewGroup parent) {
        if (getErrorId() != -1) {
            View view = mInflater.inflate(getErrorId(), parent, false);
            return view;
        }
        return new View(mContext);
    }

    protected int getErrorId() {
        return -1;
    }

    protected View getEmptyView(ViewGroup parent) {
        if (getEmptyId() != -1) {
            return mInflater.inflate(getEmptyId(), parent, false);
        }
        return new View(mContext);
    }

    protected int getEmptyId() {
        return -1;
    }

    public void loadSuccess() {
        NODATA_STATUS = EMPTY;
        notifyDataSetChanged();
    }

    public void loadEmpty() {
        NODATA_STATUS = EMPTY;
        notifyDataSetChanged();
    }

    public void loadError() {
        NODATA_STATUS = ERROR;
        notifyDataSetChanged();
    }

    public void loadStart() {
        NODATA_STATUS = LOADING;
        notifyDataSetChanged();
    }


    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        ViewDataBinding dataBinding;

        public RecyclerHolder(View itemView) {
            super(itemView);
        }

        public RecyclerHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }

        public <T extends ViewDataBinding> T getDataBinding() {
            return (T) dataBinding;
        }

        public <T extends View> T get(int viewId) {
            return (T) itemView.findViewById(viewId);
        }


        /**
         * 通过ViewId设置点击监听
         *
         * @param viewId
         * @param l
         */
        public void setOnClickListener(int viewId, View.OnClickListener l) {//通过ViewId设置点击监听
            get(viewId).setOnClickListener(l);
        }

        /**
         * 整体设置点击监听
         *
         * @param l
         */
        public void setOnClickListener(View.OnClickListener l) {//通过ViewId设置点击监听
            itemView.setOnClickListener(l);
        }

        /**
         * 整体设置点击监听
         *
         * @param l
         */
        public void setOnLongClickListener(View.OnLongClickListener l) {//通过ViewId设置点击监听
            itemView.setOnLongClickListener(l);
        }

    }


}
