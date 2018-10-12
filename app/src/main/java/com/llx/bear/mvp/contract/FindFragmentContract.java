package com.llx.bear.mvp.contract;

import com.llx.bear.mvp.presenter.BasePresenter;
import com.llx.bear.mvp.view.BaseView;
import com.llx.bear.model.resultBean.FindBean;

import java.util.List;

/**
 * @author: zhangshijie
 * Time: 2018/10/11 14:24
 */

public interface FindFragmentContract {
    interface Presenter extends BasePresenter{
        void loadFindRecommendData();
    }

    /**
     *
     */
    interface FindFragmeneView extends BaseView<Presenter>{
        /**
         *
         * @param mFindBeanList
         */
        void showLoadData(List<FindBean> mFindBeanList);
    }
}
