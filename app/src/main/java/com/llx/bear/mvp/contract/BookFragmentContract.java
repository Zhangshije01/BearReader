package com.llx.bear.mvp.contract;

import com.llx.bear.model.resultModel.BookDetailResultModel;
import com.llx.bear.mvp.presenter.BasePresenter;
import com.llx.bear.mvp.view.BaseView;

import java.util.List;

/**
 * @author: zhangshijie
 * Time: 2018/10/22 16:40
 */

public interface BookFragmentContract {
    interface Presenter extends BasePresenter{
        /**
         *  获取书架
         */
        void loadBookRackData();
    }
    interface BookFragmentView extends BaseView<Presenter>{
        /**
         *  得到书架中所有书籍
         * @param mBookLists 书籍集合
         */
        void showBookRackData(List<BookDetailResultModel> mBookLists);
    }
}
