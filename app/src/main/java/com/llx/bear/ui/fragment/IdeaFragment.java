package com.llx.bear.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.bear.R;
import com.llx.bear.databinding.FragmentIdeaBinding;
import com.llx.bear.ui.base.BaseFragment;

/**
 * 想法
 * @author zhangshijie
 */
public class IdeaFragment extends BaseFragment {

    private FragmentIdeaBinding binding;
    public IdeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_idea,container,false);
        return binding.getRoot();
    }

    @Override
    public void configView() {
        //test
    }

    @Override
    protected void lazyLoadData() {
        super.lazyLoadData();
    }
}
