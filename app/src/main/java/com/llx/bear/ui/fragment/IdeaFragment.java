package com.llx.bear.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llx.bear.R;
import com.llx.bear.ui.base.BaseFragment;

/**
 * 想法
 * @author zhangshijie
 */
public class IdeaFragment extends BaseFragment {


    public IdeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idea, container, false);
    }

    @Override
    public void configView() {

    }
}
