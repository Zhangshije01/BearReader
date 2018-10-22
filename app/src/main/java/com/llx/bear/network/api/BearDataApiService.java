package com.llx.bear.network.api;

import com.llx.bear.model.resultModel.BaseResultModel;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 *
 * @author zhangshijie
 * @date 2017/7/15
 * <p>
 * 注意：所有接口必须全部带有注释
 */

public interface BearDataApiService {

    /**
     * 从服务器获取优惠券数据
     */
    @POST("api/UserCenter/GetMyInviteInfo")
    Observable<BaseResultModel> GetMyInviteInfo(@Body RequestBody requestBody);

}
