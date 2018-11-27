package com.llx.bear.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.bear.R;
import com.llx.bear.databinding.FragmentFindBinding;
import com.llx.bear.mvp.contract.FindFragmentContract;
import com.llx.bear.mvp.presenter.FindFragmentPresenterImpl;
import com.llx.bear.ui.activity.BookCityActivity;
import com.llx.bear.ui.adapter.FindGralleyAdapter;
import com.llx.bear.ui.base.BaseFragment;
import com.llx.bear.model.resultBean.FindBean;
import com.llx.bear.ui.widget.BearGralleyView;

import java.util.List;

/**
 * 发现
 *
 * @author 张士杰
 */
public class FindFragment extends BaseFragment implements FindFragmentContract.FindFragmeneView, View.OnClickListener {
    private FragmentFindBinding mBinding;
    private FindFragmentContract.Presenter mPresenter;
    private FindGralleyAdapter adapter;

    public FindFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new FindFragmentPresenterImpl(this);
        mPresenter.loadFindRecommendData();
        initEvent();
    }

    @Override
    public void configView() {

    }

    @Override
    public void attachPresenter(FindFragmentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void loadError() {

    }

    @Override
    public void showLoadData(List<FindBean> mFindBeanList) {
        adapter = new FindGralleyAdapter(mFindBeanList);
        mBinding.findFragmentGralleryView.setAdapter(adapter);
    }

    public void initEvent() {
        mBinding.findFragmentGralleryView.setOnPageChangeListener(new BearGralleyView.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
        });
        mBinding.tvFindBookCity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_find_book_city:
                BookCityActivity.start(mActivity);
                mActivity.overridePendingTransition(R.anim.dialog_push_in, android.R.anim.fade_out);
                break;
            default:
                break;
        }
    }
}
