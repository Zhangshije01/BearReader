package com.llx.bear.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.llx.bear.R;
import com.llx.bear.databinding.ActivityBookReaderBinding;
import com.llx.bear.model.resultModel.BookDetailResultModel;
import com.llx.bear.ui.base.BaseActivity;

/**
 * @author zhangshijie
 *         阅读页
 */
public class BookReaderActivity extends BaseActivity {
    private ActivityBookReaderBinding mBinding;
    private static final String INTENT_BEAN = "bookDetailResultModel";
    private BookDetailResultModel bookDetailResultModel;
    public static void startActivity(Context context, BookDetailResultModel bookDetailResultModel) {
        context.startActivity(new Intent(context, BookReaderActivity.class)
                .putExtra(INTENT_BEAN, bookDetailResultModel));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_reader);

    }

    @Override
    public void configView() {
        bookDetailResultModel = (BookDetailResultModel) getIntent().getSerializableExtra(INTENT_BEAN);
    }
}
