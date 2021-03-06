package com.llx.bear.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.bear.BR;
import com.llx.bear.R;
import com.llx.bear.databinding.FragmentBookBinding;
import com.llx.bear.model.resultModel.BookDetailResultModel;
import com.llx.bear.mvp.contract.BookFragmentContract;
import com.llx.bear.mvp.presenter.BookFragmentPresenterImpl;
import com.llx.bear.ui.activity.BookReaderActivity;
import com.llx.bear.ui.adapter.base.MyMvvmAdapter;
import com.llx.bear.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshijie
 *         书架
 */
public class BookFragment extends BaseFragment implements BookFragmentContract.BookFragmentView,MyMvvmAdapter.OnItemClickListener{
    private FragmentBookBinding mBinding;
    private BookFragmentContract.Presenter mPresenter;
    private MyMvvmAdapter adapter;
    private List<BookDetailResultModel> mBookLists = new ArrayList<>();
    public BookFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new BookFragmentPresenterImpl(this);
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
        //获取书架书籍
        mPresenter.loadBookRackData();
    }

    @Override
    public void configView() {
        adapter = new MyMvvmAdapter(mActivity,mBookLists,R.layout.item_book_rack_layout, BR.BookDetailResultModel);
        mBinding.recyclerBookFragment.setLayoutManager(new GridLayoutManager(mActivity,3));
        mBinding.recyclerBookFragment.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerBookFragment.setAdapter(adapter);

        adapter.setItemClickListenr(this);
    }

    @Override
    public void attachPresenter(BookFragmentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showDialog() {
        showLoadingDialog();
    }

    @Override
    public void dismissDialog() {
        dissmissLoadingDialog();
    }

    @Override
    public void loadError() {

    }


    @Override
    public void showBookRackData(List<BookDetailResultModel> mLists) {
        mBookLists.addAll(mLists);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Object data, int position) {
        BookDetailResultModel bookDetailResultModel = (BookDetailResultModel) data;
        BookReaderActivity.startActivity(mActivity,bookDetailResultModel);
    }

    @Override
    public void onItemLongClick() {

    }
}
