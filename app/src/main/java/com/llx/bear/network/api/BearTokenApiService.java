package com.llx.bear.network.api;


import com.llx.bear.model.resultModel.AuthTokenResultModel;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Time: 2017/9/8 16:32
 * 请求 token 的地址
 * @author zhangshijie
 */

public interface BearTokenApiService {
    /**
     * 获取token
     *
     * @return 返回一个json数组
     */
    @FormUrlEncoded
    @POST("OAuth/Token")
    Observable<AuthTokenResultModel> http_getAssetsToken(@FieldMap Map<String, String> map);

}
