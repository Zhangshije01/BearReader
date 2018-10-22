package com.llx.suandroidbase.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.llx.suandroidbase.R;

/**
 * @author: zhangshijie
 * Time: 2018/10/22 16:12
 */

public class GlideUtil {

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public static void loadImage(ImageView imageView, String url) {
        loadImage(imageView,url, R.color.F5F5F5);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     * @param resId
     */
    public static void loadImage(ImageView imageView, String url, int resId) {
        if (imageView.getContext() != null) {
            try {
                Glide.with(imageView.getContext())
                        .load(url)
                        .placeholder(resId)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
