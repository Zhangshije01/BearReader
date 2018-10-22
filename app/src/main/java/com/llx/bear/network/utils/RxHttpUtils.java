package com.llx.bear.network.utils;

import com.llx.bear.BuildConfig;
import com.llx.bear.network.api.BearDataApiService;
import com.llx.suandroidbase.network.RetrofitServiceGenerator;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建请求的对象,将一些公共操作放到一起
 *
 * @author zhangshijie
 */

public class RxHttpUtils {

    private static final String HOST_API_ONLINE = "https://keeper.lifely.us/";
    private static final String HOST_API_SANDBOX = "http://54.201.208.127:8893/";

    public static String getBaseApiUri() {
        if (BuildConfig.DEBUG) {
            return HOST_API_SANDBOX;
        }
        return HOST_API_ONLINE;
    }

    public static BearDataApiService getApiService() {
        return RetrofitServiceGenerator.createService(getBaseApiUri(), BearDataApiService.class);
    }

    /**
     * 创建网络请求
     *
     * @param observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createHttpRequest(final Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 创建合并网络请求 (merge)
     *
     * @param observables
     * @param <T>
     * @return
     */
    public static <T> Observable<T> mergeHttpRequest(final Observable<? extends T>... observables) {
        return Observable.merge(observables)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
