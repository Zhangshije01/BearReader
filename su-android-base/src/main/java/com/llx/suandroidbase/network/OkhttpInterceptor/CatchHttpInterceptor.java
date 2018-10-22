package com.llx.suandroidbase.network.OkhttpInterceptor;


import com.llx.suandroidbase.SuApplication;
import com.llx.suandroidbase.commen.NetWorkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>
 * 缓存拦截器
 * <p>
 * @author zhangshijie
 */

public class CatchHttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        // 缓存控制器
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(60 * 30, TimeUnit.SECONDS);
        cacheBuilder.maxStale(60 * 60, TimeUnit.SECONDS);
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();
        if (!NetWorkUtils.isNetworkConnected(SuApplication.getAppContext())) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetWorkUtils.isNetworkConnected(SuApplication.getAppContext())) {
            // read from cache（半小时）
            int maxAge = 60 * 30;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60; // 缓存时间( 1 小时 )
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
