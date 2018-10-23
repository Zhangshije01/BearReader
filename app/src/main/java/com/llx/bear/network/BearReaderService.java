package com.llx.bear.network;

import com.llx.bear.model.resultModel.BookDetailResultModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author zhangshijie
 * @date 2017/7/15
 * <p>
 * 注意：所有接口必须全部带有注释
 */

public interface BearReaderService {

    /**
     * 获取书籍详情
     */
    @GET("/book/{bookId}")
    Observable<BookDetailResultModel> getBookDetail(@Path("bookId") String bookId);

}
