package com.llx.bear.network.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by luchunhao on 2018/3/6.
 */

public class NetworkStatusChangedReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkStatusChangedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
//            Activity activity = BearReaderApplication.getInstance().currentActivity;
//            if (null == activity) {
//                return;
//            }
//
//            if (NetWorkUtils.isNetworkAvailable()) {
//                LogUtil.d(TAG, "网络可用了");
//                ((BaseActivity)activity).displayTopView(false);
//            } else {
//                LogUtil.d(TAG, "网络不可用");
//                ((BaseActivity)activity).displayTopView(true);
//            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
