package com.llx.bear.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.llx.bear.R;
import com.llx.bear.commen.manager.CacheManager;
import com.llx.bear.commen.manager.SettingManager;
import com.llx.bear.commen.manager.ThemeManager;
import com.llx.bear.databinding.ActivityBookReaderBinding;
import com.llx.bear.model.resultBean.BookMixAToc;
import com.llx.bear.model.resultBean.ChapterRead;
import com.llx.bear.model.resultModel.BookDetailResultModel;
import com.llx.bear.mvp.contract.BookReaderContract;
import com.llx.bear.mvp.presenter.BookReaderPresenterImpl;
import com.llx.bear.ui.base.BaseActivity;
import com.llx.bear.ui.dialog.BookContentsDialogFragment;
import com.llx.bear.ui.widget.readview.BaseReadView;
import com.llx.bear.ui.widget.readview.NoAimWidget;
import com.llx.bear.ui.widget.readview.OnReadStateChangeListener;
import com.llx.suandroidbase.commen.LogUtils;
import com.llx.suandroidbase.commen.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshijie
 *         阅读页
 */
public class BookReaderActivity extends BaseActivity implements BookReaderContract.BookReaderView, View.OnClickListener {
    private ActivityBookReaderBinding mBinding;
    private BookReaderContract.Presenter mPresenter;
    private BookDetailResultModel bookDetailResultModel;
    private List<BookMixAToc.mixToc.Chapters> mChapterList = new ArrayList<>();
    private String bookId;
    private BaseReadView mPageWidget;
    private int curTheme;
    private boolean startRead = false;
    private int currentChapter = 1;
    private static final String INTENT_BEAN = "bookDetailResultModel";
    private View decorView;

    public static void startActivity(Context context, BookDetailResultModel bookDetailResultModel) {
        context.startActivity(new Intent(context, BookReaderActivity.class)
                .putExtra(INTENT_BEAN, bookDetailResultModel));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_reader);
        mPresenter = new BookReaderPresenterImpl(this);
        configView();
        initExtra();
        initAASet();
        initWidget();
        initEvent();
        mPresenter.getBookMixAToc(bookId);
    }

    public void configView() {
        hideStatusBar();
        decorView = getWindow().getDecorView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBinding.llReaderTop.getLayoutParams();
        params.topMargin = ScreenUtils.getStatusBarHeight(this) - 2;
        mBinding.llReaderTop.setLayoutParams(params);
    }

    public void initExtra() {
        bookDetailResultModel = (BookDetailResultModel) getIntent().getSerializableExtra(INTENT_BEAN);
        bookId = bookDetailResultModel._id;
    }

    public void initAASet() {
        curTheme = SettingManager.getInstance().getReadTheme();
        ThemeManager.setReaderTheme(curTheme, mBinding.rlBookReaderContainer);

    }

    public void initWidget() {
        mPageWidget = new NoAimWidget(this, bookId, mChapterList, new ReadListener());
//        mPageWidget = new PageWidget(this, bookId, mChapterList, new ReadListener());
//        mPageWidget = new OverlappedWidget(this, bookId, mChapterList, new ReadListener());
        mBinding.flBookReader.removeAllViews();
        mBinding.flBookReader.addView(mPageWidget);
    }

    public void initEvent() {
        mBinding.ivBookReaderContents.setOnClickListener(this);
    }


    @Override
    public void showBookChapterList(List<BookMixAToc.mixToc.Chapters> chaptersList) {
        mChapterList.clear();
        mChapterList.addAll(chaptersList);
        readCurrentChapter();
    }

    /**
     * 获取当前章节。章节文件存在则直接阅读，不存在则请求
     */
    public void readCurrentChapter() {
        if (CacheManager.getInstance().getChapterFile(bookId, currentChapter) != null) {
            showChapterRead(null, currentChapter);
        } else {
            mPresenter.getChapterRead(mChapterList.get(currentChapter - 1).link, currentChapter);
        }
    }

    @Override
    public synchronized void showChapterRead(ChapterRead.Chapter data, int chapter) {
        if (data != null) {
            CacheManager.getInstance().saveChapterFile(bookId, chapter, data);
        }

        if (!startRead) {
            startRead = true;
            currentChapter = chapter;
            if (!mPageWidget.isPrepared) {
                mPageWidget.init(curTheme);
            } else {
                mPageWidget.jumpToChapter(currentChapter);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_book_reader_contents:
                BookContentsDialogFragment fragment = new BookContentsDialogFragment();
                fragment.show(getSupportFragmentManager(),"");
                break;
            default:
                break;
        }
    }


    private class ReadListener implements OnReadStateChangeListener {
        @Override
        public void onChapterChanged(int chapter) {
            LogUtils.i("onChapterChanged:" + chapter);
            currentChapter = chapter;

//            mTocListAdapter.setCurrentChapter(currentChapter);

            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3 && i <= mChapterList.size(); i++) {
                if (i > 0 && i != chapter
                        && CacheManager.getInstance().getChapterFile(bookId, i) == null) {
                    mPresenter.getChapterRead(mChapterList.get(i - 1).link, i);
                }
            }
        }

        @Override
        public void onPageChanged(int chapter, int page) {
            LogUtils.i("onPageChanged:" + chapter + "-" + page);
        }

        @Override
        public void onLoadChapterFailure(int chapter) {
            LogUtils.i("onLoadChapterFailure:" + chapter);
            startRead = false;
            if (CacheManager.getInstance().getChapterFile(bookId, chapter) == null) {
                mPresenter.getChapterRead(mChapterList.get(chapter - 1).link, chapter);
            }
        }

        @Override
        public void onCenterClick() {
            LogUtils.i("onCenterClick");
            toggleReadBar();

        }

        @Override
        public void onFlip() {
        }
    }

    /**
     * 处理任务栏的展示和隐藏
     */
    public void toggleReadBar() {
        if (isVisible(mBinding.llReaderTop)) {
            hidReadBar();
        } else {
            showReadBar();
        }
    }

    public synchronized void hidReadBar() {
        goneViews(mBinding.llReaderTop, mBinding.llReaderBottom);
        hideStatusBar();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);

    }

    public synchronized void showReadBar() {
        visibleViews(mBinding.llReaderTop, mBinding.llReaderBottom);
        showStatusBar();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    }

    @Override
    public void attachPresenter(BookReaderContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showDialog() {
        showLoadingDialog();
    }

    @Override
    public void dismissDialog() {
        dissmissLoadingDialog();
    }

    @Override
    public void loadError() {

    }

}
