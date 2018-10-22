package com.llx.bear.mvp.presenter;


import com.llx.bear.model.resultModel.BookDetailResultModel;
import com.llx.bear.mvp.contract.BookFragmentContract;
import com.llx.bear.network.utils.RxHttpUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @author: zhangshijie
 * Time: 2018/10/22 16:37
 */

public class BookFragmentPresenterImpl extends BasePresenterImpl implements BookFragmentContract.Presenter {
    private BookFragmentContract.BookFragmentView mView;

    public BookFragmentPresenterImpl(BookFragmentContract.BookFragmentView mView) {
        this.mView = mView;
    }

    @Override
    public void loadBookRackData() {
        RxHttpUtils.createHttpRequest(RxHttpUtils.getApiService().getBookDetail("526e8e3e7cfc087140004df7"))
                .subscribe(new Subscriber<BookDetailResultModel>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loadError();
                    }

                    @Override
                    public void onNext(BookDetailResultModel bookDetailResultModel) {
                        mView.dismissDialog();
                        List<BookDetailResultModel> mLists = new ArrayList<BookDetailResultModel>();
                        for (int i = 0; i < 10; i++) {
                            mLists.add(bookDetailResultModel);
                        }
                        mView.showBookRackData(mLists);
                    }
                });
    }
}
