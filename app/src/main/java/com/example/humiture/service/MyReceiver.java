package com.example.humiture.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *Time:2019/6/20
 *Author:冰冰凉
 *dec:  开机启动服务
 */
public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //设置开机自动启动服务
        Intent service = new Intent(context,MyService.class);
        context.startService(service);
        Log.i(TAG, "开机自动启动服务");
    }
}
