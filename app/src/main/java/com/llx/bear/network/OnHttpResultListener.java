package com.llx.bear.network;

/**
 * Author:  fanlei
 * Time: 2017/9/9 18:06
 */

public interface OnHttpResultListener<T> {

    void onRequestStart();

    void onRequestSuccess(T t);

    void onRequestError(String error);

    void onRequestCompleted();

}
