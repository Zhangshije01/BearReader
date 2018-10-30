package com.llx.bear.mvp.presenter;

import com.llx.bear.model.resultBean.BookMixAToc;
import com.llx.bear.model.resultBean.ChapterRead;
import com.llx.bear.mvp.contract.BookReaderContract;
import com.llx.bear.network.BearReaderApi;

import rx.Subscriber;

/**
 * @author: zhangshijie
 * Time: 2018/10/30 11:10
 */

public class BookReaderPresenterImpl extends BasePresenterImpl implements BookReaderContract.Presenter {
    private BookReaderContract.BookReaderView mView;

    public BookReaderPresenterImpl(BookReaderContract.BookReaderView mView) {
        this.mView = mView;
    }

    @Override
    public void getChapterRead(String url, final int chapter) {
        BearReaderApi.getChapterRead(url)
                .subscribe(new Subscriber<ChapterRead>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ChapterRead chapterRead) {
                        mView.showChapterRead(chapterRead.chapter, chapter);
                    }
                });
    }

    @Override
    public void getBookMixAToc(String bookId) {
        BearReaderApi.getBookMixAToc(bookId, "chapters").subscribe(new Subscriber<BookMixAToc>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BookMixAToc bookMixAToc) {
                mView.showBookChapterList(bookMixAToc.mixToc.chapters);
            }
        });
    }
}
