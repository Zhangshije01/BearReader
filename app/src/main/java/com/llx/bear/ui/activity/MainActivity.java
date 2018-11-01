package com.llx.bear.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.llx.bear.BearReaderApplication;
import com.llx.bear.R;
import com.llx.bear.databinding.ActivityMainBinding;
import com.llx.bear.ui.adapter.MainActivityViewPagerAdapter;
import com.llx.bear.ui.base.BaseActivity;
import com.llx.bear.ui.fragment.BookFragment;
import com.llx.bear.ui.fragment.FindFragment;
import com.llx.bear.ui.fragment.IdeaFragment;
import com.llx.bear.ui.fragment.MeFragment;
import com.llx.suandroidbase.commen.StatusBarUtils;
import com.llx.suandroidbase.commen.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshijie
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;

    private long lastExitTime = 0;
    private long exitWaitTime = 1500;
    /**当前页面*/
    private int currentPage = 0;
    private FindFragment findFragment;
    private BookFragment bookFragment;
    private IdeaFragment ideaFragment;
    private MeFragment meFragment;

    private List<Fragment> mFragmentList;
    private MainActivityViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.StatusBarLightMode(this);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initData();
        initView();
    }

    public void initData() {
        findFragment = new FindFragment();
        bookFragment = new BookFragment();
        ideaFragment = new IdeaFragment();
        meFragment = new MeFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(findFragment);
        mFragmentList.add(bookFragment);
        mFragmentList.add(ideaFragment);
        mFragmentList.add(meFragment);
    }

    public void initView(){
        adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(),mFragmentList);
        mBinding.vpMain.setAdapter(adapter);
        mBinding.vpMain.setOffscreenPageLimit(4);
        mBinding.vpMain.setCurrentItem(currentPage, false);
        mBinding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBinding.nbTablayoutMain.setupWithViewPager(mBinding.vpMain);
        mBinding.nbTablayoutMain.setTabView();// 设置view
        mBinding.nbTablayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (System.currentTimeMillis() - lastExitTime > exitWaitTime) {
            ToastUtils.showToast("请再按一次");
            lastExitTime = System.currentTimeMillis();
        } else {
            BearReaderApplication.getInstance().exitProgrom();
        }
        return true;
    }


}
