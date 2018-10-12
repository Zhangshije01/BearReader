package com.llx.bear.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.llx.bear.R;

/**
 * @author: zhangshijie
 * Time: 2018/10/9 15:31
 */

public class BearFontTextView extends AppCompatTextView {

    /**自定义的字体*/
    private String TypefaceName;
    /**下划线*/
    private boolean isShowUnderLine;
    /**中间横线*/
    private boolean isShowMidLine;
    public BearFontTextView(Context context) {
        super(context);
    }

    public BearFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);

        if(!TextUtils.isEmpty(TypefaceName)){
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + TypefaceName);
            this.setTypeface(typeface);
        }
        if(isShowUnderLine){
            //标记下划线
            this.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            //防锯齿
            this.getPaint().setAntiAlias(true);
        }

        if(isShowMidLine){
            //中间划线
            this.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //防锯齿
            this.getPaint().setAntiAlias(true);
        }
    }

    public BearFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    public void initAttr(AttributeSet attributeSet){
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.BearFontTextView);
        isShowUnderLine = a.getBoolean(R.styleable.BearFontTextView_underline,false);
        isShowMidLine = a.getBoolean(R.styleable.BearFontTextView_midline,false);
        int fontType = a.getInt(R.styleable.BearFontTextView_bearFont,0);
        switch (fontType){
            case 1:
                TypefaceName = "GillSans.ttf";
                break;
            case 2:
                TypefaceName = "GillSans-Bold.ttf";
                break;
            case 3:
                TypefaceName = "GillSans-Bold-Italic.ttf";
                break;
            case 4:
                TypefaceName = "GillSans-Italic.ttf";
                break;
            case 5:
                TypefaceName = "GillSans-MT.ttf";
                break;
            case 6:
                TypefaceName = "GillSans-MT-Bold.ttf";
                break;
            case 7:
                TypefaceName = "GillSans-MT-Bold-Italic.ttf";
                break;
            case 8:
                TypefaceName = "GillSans-MT-Italic.ttf";
                break;
            case 9:
                TypefaceName = "GillsansSemiBold.ttf";
                break;
            case 10:
                TypefaceName = "DidotBold.ttf";
                break;
            default:
                TypefaceName = "GillSans.ttf";
                break;
        }

        a.recycle();
    }
}
