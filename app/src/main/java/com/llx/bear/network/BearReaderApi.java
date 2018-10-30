package com.llx.bear.network;

import com.llx.bear.BuildConfig;
import com.llx.bear.model.resultBean.BookMixAToc;
import com.llx.bear.model.resultBean.ChapterRead;
import com.llx.bear.model.resultModel.BookDetailResultModel;
import com.llx.suandroidbase.network.RetrofitServiceGenerator;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建请求的对象,将一些公共操作放到一起
 *
 * @author zhangshijie
 */

public class BearReaderApi {

    private static final String HOST_API_ONLINE = "https://keeper.lifely.us/";
    private static final String HOST_API_SANDBOX = "http://api.zhuishushenqi.com";

    public static String getBaseApiUri() {
        if (BuildConfig.DEBUG) {
            return HOST_API_SANDBOX;
        }
        return HOST_API_ONLINE;
    }

    public static BearReaderService getApiService() {
        return RetrofitServiceGenerator.createService(getBaseApiUri(), BearReaderService.class);
    }

    /**
     * 创建网络请求
     */
    public static <T> Observable<T> createHttpRequest(final Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 创建合并网络请求 (merge)
     */
    public static <T> Observable<T> mergeHttpRequest(final Observable<? extends T>... observables) {
        return Observable.merge(observables)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *  获取书籍详情
     */
    public static Observable<BookDetailResultModel> getBookDetail(String bookId){
        return createHttpRequest(getApiService().getBookDetail(bookId));
    }

    /**
     *  获取章节内容
     */
    public static Observable<ChapterRead> getChapterRead(String url){
        return createHttpRequest(getApiService().getChapterRead(url));
    }

    public static Observable<BookMixAToc> getBookMixAToc(String bookId,String views){
        return createHttpRequest(getApiService().getBookMixAToc(bookId,views));
    }



}
