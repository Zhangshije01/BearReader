package com.llx.bear.network.networkInterface;


import com.llx.bear.BearReaderApplication;
import com.llx.bear.R;
import com.llx.bear.commen.utils.LogUtil;
import com.llx.bear.model.resultModel.BaseResultModel;
import com.llx.bear.network.OnHttpResultListener;

import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import rx.Subscriber;

/**
 * on 2017/10/21-15:11
 * @author zhangshijie
 */

public class CallSubscriber<T> extends Subscriber<T> {

    /**
     *  回调
     */
    private OnHttpResultListener<T> mListener;

    /**
     * 创建订阅者实例的代码路径
     */
    private String pathInfo = "";

    public CallSubscriber(OnHttpResultListener<T> mListener) {
        this.mListener = mListener;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //for (StackTraceElement s : stackTrace) {
        int length = stackTrace.length;
        if (length > 6) {
            length = 6;
        }
        for (int i = 5; i < length; i++) {
            StackTraceElement s = stackTrace[i];
            pathInfo += ("at：" + s.getClassName() + "." + s.getMethodName() + " (" + s.getFileName() + ":" + s.getLineNumber() + ")\n");
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
        LogUtil.d("pathTag");
        LogUtil.d(e);
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage("网络请求异常记录：\n" + e.getClass().getSimpleName()+"\n"
                        + pathInfo + e.getMessage())
                .withLevel(Event.Level.FATAL)
                .withSentryInterface(new ExceptionInterface(e));
        try {
            Sentry.capture(eventBuilder);
        } catch (Exception e1) {
        }
        _onError(BearReaderApplication.getInstance().getResources().getString(R.string.app_name));
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
                if (t!=null) {
                    _onNext(t);
                    if (mListener != null) {
                        mListener.onRequestSuccess(t);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onRequestError(resultModel.getMessage());
                    }
                }
            } else {
                if (mListener != null) {
                    mListener.onRequestSuccess(t);
                }
            }
        } else {
            if (mListener != null) {
                mListener.onRequestError(BearReaderApplication.getInstance().getResources().getString(R.string.sorry_network_is_not_good));
            }
        }
    }

    public void _onStart() {
    }

    public void _onNext(T t) {
    }

    public void _onError(String msg) {
    }

    public void _onCompleted() {
    }
}

