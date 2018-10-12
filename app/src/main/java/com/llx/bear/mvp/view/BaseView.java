package com.llx.bear.mvp.view;

/**
 * Created by 蜗牛 on 2018-08-05.
 */

public interface BaseView<T> {
    void attachPresenter(T presenter);
    void showDialog();
    void dismissDialog();
    void loadError();
}
