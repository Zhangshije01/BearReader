package com.llx.bear.mvp.contract;

import com.llx.bear.model.resultBean.BookMixAToc;
import com.llx.bear.model.resultBean.ChapterRead;
import com.llx.bear.mvp.presenter.BasePresenter;
import com.llx.bear.mvp.view.BaseView;

import java.util.List;

/**
 * @author: zhangshijie
 * Time: 2018/10/30 10:54
 */

public interface BookReaderContract {
    interface Presenter extends BasePresenter{
        void getChapterRead(String url,int chapter);
        void getBookMixAToc(String bookId);
    }
    interface BookReaderView extends BaseView<Presenter>{
        void showChapterRead(ChapterRead.Chapter data, int chapter);
        void showBookChapterList(List<BookMixAToc.mixToc.Chapters> chaptersList);
    }
}
