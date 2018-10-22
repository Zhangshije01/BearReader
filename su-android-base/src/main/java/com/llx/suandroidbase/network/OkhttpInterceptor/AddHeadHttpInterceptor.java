package com.llx.suandroidbase.network.OkhttpInterceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time: 2017/9/9 10:56
 * <p>
 * 添加请求头的拦截器
 * @author zhagnshijie
 */

public class AddHeadHttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        /**
         *  token
         */
//        String auth_token = (String) SPUtils.get(Constant.CONFIG_KEY.AUTH_TOKEN_KEY, " ");

        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
//                .addHeader("Authorization", auth_token == null ? "" : "Bearer " + auth_token)
                .addHeader("Accept", "*/*")
                .method(originalRequest.method(), originalRequest.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
