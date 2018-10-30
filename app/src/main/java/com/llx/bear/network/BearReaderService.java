package com.llx.bear.network;

import com.llx.bear.model.resultBean.BookMixAToc;
import com.llx.bear.model.resultBean.ChapterRead;
import com.llx.bear.model.resultModel.BookDetailResultModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    /**
     *  获取章节内容
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead> getChapterRead(@Path("url") String url);

    /**
     *  获取章节目录数据
     * @param bookId
     * @param view
     * @return
     */
    @GET("/mix-atoc/{bookId}")
    Observable<BookMixAToc> getBookMixAToc(@Path("bookId") String bookId, @Query("view") String view);
}
