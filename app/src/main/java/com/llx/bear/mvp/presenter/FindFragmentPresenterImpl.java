package com.llx.bear.mvp.presenter;

import com.llx.bear.model.resultBean.FindBean;
import com.llx.bear.mvp.contract.FindFragmentContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangshijie
 * Time: 2018/10/11 14:27
 */

public class FindFragmentPresenterImpl extends BasePresenterImpl implements FindFragmentContract.Presenter {
    private FindFragmentContract.FindFragmeneView mView;

    public FindFragmentPresenterImpl(FindFragmentContract.FindFragmeneView mView) {
        this.mView = mView;
    }

    @Override
    public void loadFindRecommendData() {
        List<FindBean> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FindBean findBean = new FindBean();
            findBean.setTitle("诗和远方 第" + i + "卷");
            findBean.setDescription("bear reader" + i + "号");
            findBean.setUser("zsj" + i);
            mList.add(findBean);
        }

        mView.showLoadData(mList);

//        RxHttpUtils.createHttpRequest(RxHttpUtils.getApiService().GetMyInviteInfo())
//                .subscribe(new CallSubscriber<BaseResultModel>(new OnHttpResultListener<BaseResultModel>() {
//                    @Override
//                    public void onRequestStart() {
//
//                    }
//
//                    @Override
//                    public void onRequestSuccess(BaseResultModel baseResultModel) {
//
//                    }
//
//                    @Override
//                    public void onRequestError(String error) {
//
//                    }
//
//                    @Override
//                    public void onRequestCompleted() {
//
//                    }
//                }));
    }
}
