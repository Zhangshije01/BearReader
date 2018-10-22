package com.llx.bear;

import android.app.Activity;
import android.os.Bundle;

import com.llx.bear.ui.base.BaseApplication;
import com.llx.suandroidbase.SuApplication;

import java.util.LinkedList;

/**
 * @author: zhangshijie
 * Time: 2018/10/9 15:06
 */

public class BearReaderApplication extends BaseApplication{
    /**添加栈内activity*/
    private LinkedList<Activity> activityLinkedList = new LinkedList<>();
    /**单例*/
    private static BearReaderApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SuApplication.init(this, BuildConfig.DEBUG);

    }

    public static BearReaderApplication getInstance(){
        return application;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityLinkedList.add(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityLinkedList.remove(activity);
    }

    public void exitProgrom(){
        for (Activity activity:activityLinkedList){
            activity.finish();
        }
    }
}
