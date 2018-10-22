package com.llx.bear.network.api;

import com.llx.bear.model.resultModel.BookDetailResultModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
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
    @GET("/book/{bookId}")
    Observable<BookDetailResultModel> getBookDetail(@Path("bookId") String bookId);

}
