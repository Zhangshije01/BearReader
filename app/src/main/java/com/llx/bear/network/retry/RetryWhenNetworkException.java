package com.llx.bear.network.retry;

import com.llx.bear.commen.Constant;
import com.llx.bear.commen.utils.SPUtils;
import com.llx.bear.model.resultModel.AuthTokenResultModel;
import com.llx.bear.network.RetrofitServiceGenerator;
import com.llx.bear.network.api.BearTokenApiService;
import com.llx.suandroidbase.commen.LogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.CompositeException;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Create by fanlei 09/11/2017
 * 超时重试机制
 */
public class RetryWhenNetworkException  implements Func1<Observable<? extends Throwable>, Observable<?>> {
    private int count = 1;    //  重试次数
    private long delay = 2000;//  重试时间间隔

    public RetryWhenNetworkException() {

    }

    public RetryWhenNetworkException(int count) {
        this.count = count;
    }

    public RetryWhenNetworkException(int count, long delay) {
        this.count = count;
        this.delay = delay;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable
                .zipWith(Observable.range(1, count + 1), new Func2<Throwable, Integer, Wrapper>() {
                    @Override
                    public Wrapper call(Throwable throwable, Integer integer) {
                        return new Wrapper(throwable, integer);
                    }
                }).flatMap(new Func1<Wrapper, Observable<?>>() {
                    @Override
                    public Observable<?> call(Wrapper wrapper) {
                        if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof TimeoutException
                                || wrapper.throwable instanceof CompositeException)
                                && wrapper.index < count + 1) {
                            return Observable.timer(delay + (wrapper.index - 1) * delay, TimeUnit.MILLISECONDS);
                        } else if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof TimeoutException
                                || wrapper.throwable instanceof CompositeException)
                                && wrapper.index == count + 1) {
                            // 请求 token
                            LogUtil.d("重试次数达到上限，开始请求token");
                            RetrofitServiceGenerator.createTokenService(BearTokenApiService.class)
                                    .http_getAssetsToken(Constant.NET_WORK_TOKEN_PARAMS.TOKEN_PAMAS_MAP)
                                    .subscribeOn(Schedulers.io())
                                    .unsubscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<AuthTokenResultModel>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            LogUtil.d(e.getMessage());
                                        }

                                        @Override
                                        public void onNext(AuthTokenResultModel authTokenResultModel) {
                                            if (authTokenResultModel != null) {
                                                SPUtils.put(Constant.CONFIG_KEY.AUTH_TOKEN_KEY, authTokenResultModel.getAccess_token());// 存起来token
                                            }
                                        }
                                    });
                        }
                        return Observable.error(wrapper.throwable);
                    }
                });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}
