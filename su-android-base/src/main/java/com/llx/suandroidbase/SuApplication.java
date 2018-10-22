package com.llx.suandroidbase;

import android.app.Application;
import android.content.Context;

/**
 * @author: zhangshijie
 * Time: 2018/10/22 13:26
 */

public class SuApplication {
    private static Context sAppContext;
    private static Application sApplication;
    private static boolean sIsDebug;

    public static void init(Application application, boolean isDebug) {
        sAppContext = application;
        sAppContext = application.getApplicationContext();
        sIsDebug = isDebug;
    }
    public static Application getApplication() {
        return sApplication;
    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
