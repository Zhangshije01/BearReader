package com.llx.bear.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.bear.R;
import com.llx.bear.databinding.ItemFindGralleryBinding;
import com.llx.bear.ui.model.resultBean.FindBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2017/9/25 10:30
 * Vip中心的适配器
 *
 * @author zhangshijie
 */

public class FindGralleyAdapter extends PagerAdapter {

    private List<FindBean> mFindBeanList = new ArrayList<>();


    public FindGralleyAdapter(List<FindBean> findBeanList) {
        this.mFindBeanList = findBeanList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemFindGralleryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.item_find_grallery, container, false);
        binding.setFindBean(mFindBeanList.get(position % mFindBeanList.size()));
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mFindBeanList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
