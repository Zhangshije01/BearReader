package com.llx.bear.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.llx.bear.R;
import com.llx.bear.ui.base.BaseActivity;
import com.llx.bear.ui.widget.SwipeableLayout;

/**
 * @author Administrator
 */
public class BookCityActivity extends BaseActivity implements SwipeableLayout.OnLayoutCloseListener {
    private SwipeableLayout swipeableLayout;
    public static void start(Context context){
        context.startActivity(new Intent(context,BookCityActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_city);
//        setStatusBarDarkMode();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        swipeableLayout = findViewById(R.id.swipeable_layout);
        swipeableLayout.setOnLayoutCloseListener(this);
    }

    @Override
    public void OnLayoutClosed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.dialog_push_out, R.anim.dialog_push_out);
    }
}
