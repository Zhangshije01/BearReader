package com.llx.bear.network.utils;

import com.llx.bear.commen.Constant;
import com.llx.bear.commen.utils.SPUtils;
import com.llx.bear.model.resultModel.AuthTokenResultModel;
import com.llx.bear.network.RetrofitServiceGenerator;
import com.llx.bear.network.api.BearTokenApiService;
import com.llx.bear.network.retry.RetryWhenNetworkException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author:  fanlei
 * Time: 2017/9/12 15:50
 * <p>
 * 创建请求的对象,将一些公共操作放到一起
 */

public class RxHttpUtils {

    /**
     * 创建网络请求(每次请求都先请求token 的创建方法)
     * @param observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createHttpRequestWithToken(final Observable<T> observable) {
        return RetrofitServiceGenerator.createTokenService(BearTokenApiService.class)
                .http_getAssetsToken(Constant.NET_WORK_TOKEN_PARAMS.TOKEN_PAMAS_MAP)
                .flatMap(new Func1<AuthTokenResultModel, Observable<T>>() {
                    @Override
                    public Observable<T> call(AuthTokenResultModel authTokenResultModel) {
                        if (authTokenResultModel != null) {
                            SPUtils.put(Constant.CONFIG_KEY.AUTH_TOKEN_KEY, authTokenResultModel.getAccess_token());
                            return observable;
                        }
                        return null;
                    }
                })
                .retryWhen(new RetryWhenNetworkException()) // 重试
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 创建网络请求（不带token）
     * @param observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createHttpRequest(final Observable<T> observable) {
        return observable
                // 重试
                .retryWhen(new RetryWhenNetworkException())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *  创建合并网络请求 (merge)
     * @param observables
     * @param <T>
     * @return
     */
    public static <T> Observable<T> mergeHttpRequest(final Observable<? extends T>... observables) {
        return Observable.merge(observables)
                // 重试
                .retryWhen(new RetryWhenNetworkException())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
