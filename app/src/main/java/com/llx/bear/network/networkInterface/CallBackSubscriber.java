package com.llx.bear.network.networkInterface;


import com.llx.bear.BearReaderApplication;
import com.llx.bear.R;
import com.llx.bear.model.resultModel.BaseResultModel;
import com.llx.bear.network.OnHttpResultListener;

import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import rx.Subscriber;

/**
 * Author:  zhangshijie
 * Time: 2017/9/12 15:22
 * <p>
 * 重写回调，放置公共操作
 */

public abstract class CallBackSubscriber<T> extends Subscriber<T> {

    private OnHttpResultListener<T> mListener;// 回调
    //创建订阅者实例的代码路径
    private String pathInfo = "";

    public CallBackSubscriber(OnHttpResultListener<T> mListener) {
        this.mListener = mListener;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //for (StackTraceElement s : stackTrace) {
        int length = stackTrace.length;
        if (length > 5) {
            length = 5;
        }
        for (int i = 0; i < length; i++) {
            StackTraceElement s = stackTrace[i];
            pathInfo += ("at：" + s.getClassName() + "." + s.getMethodName() + " (" + s.getFileName() + ":" + s.getLineNumber() + ")\n");
            /*info += ("类名：" + s.getClassName() + "  ,  java文件名：" + s.getFileName() + ",  当前方法名字：" + s.getMethodName() + ""
                    + " , 当前代码是第几行：" + s.getLineNumber() + ", ");*/
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        _onStart();
        if (mListener != null) {
            mListener.onRequestStart();
        }
    }

    @Override
    public void onCompleted() {
        _onCompleted();
        if (mListener != null) {
            mListener.onRequestCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage("网络请求异常记录：\n"
                        + pathInfo + e.getMessage())
                .withLevel(Event.Level.FATAL)
                .withSentryInterface(new ExceptionInterface(e));
        try {
            Sentry.capture(eventBuilder);
        } catch (Exception e1) {
            //logger.error("Error sending uncaught exception to Sentry.", e);
        }
        _onError(BearReaderApplication.getInstance().getResources().getString(R.string.sorry_network_is_not_good));
        if (mListener != null) {
            //String carshInfo = LogUtil.getCrashInfo(e);
            //mListener.onRequestError(carshInfo);
            mListener.onRequestError(BearReaderApplication.getInstance().getResources().getString(R.string.sorry_network_is_not_good));
        }
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            if (t instanceof BaseResultModel) {
                BaseResultModel resultModel = (BaseResultModel) t;
                // -501 三方未绑定邮箱、-502用户输入邮箱为网站正常注册邮箱，-504 facebook,-505 paypal登录 -506 google 登录邮箱校验
                if (resultModel.isSuccess() || resultModel.getCode() == -501 || resultModel.getCode() == -502 || resultModel.getCode() == -504 || resultModel.getCode() == -505 || resultModel.getCode() == -506) {
                    _onNext(t);
                    if (mListener != null) {
                        mListener.onRequestSuccess(t);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onRequestError(resultModel.getMessage());
                    }
                }
            }
        } else {
            if (mListener != null) {
                mListener.onRequestError(BearReaderApplication.getInstance().getResources().getString(R.string.sorry_network_is_not_good));
            }
        }
    }

    public abstract void _onStart();

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);

    public abstract void _onCompleted();
}
