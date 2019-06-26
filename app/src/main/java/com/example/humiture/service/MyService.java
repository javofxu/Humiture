package com.example.humiture.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.base.rx.RxTimerUtil;
import com.example.humiture.R;
import com.example.humiture.data.Alarm;
import com.example.humiture.data.MessageData;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.utils.NetWork;
import com.example.humiture.utils.helper.SpUtils;
import com.google.gson.Gson;

import androidx.annotation.Nullable;

/**
 *Time:2019/6/20
 *Author:冰冰凉
 *dec:  报警信息推送
 */
public class MyService extends Service {

    private static final String TAG = "MyService";

    private NotificationManager notificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //定时轮询
//        RxTimerUtil.interval(1000);

        /*AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int hour = 60 * 60 * 1000;
        long triggerAtTime = System.currentTimeMillis() + hour;
        Intent i = new Intent(this,MyReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);

            //获取的信息
            showNotification(MyService.this);*/

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //通知
    private void showNotification(Context context){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent mIntent = new Intent(context,MineInfoActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(context.getApplicationContext())
                .setContentIntent(pendingIntent)
                .setTicker("报警信息")
                .setSmallIcon(R.mipmap.app)
                .setContentTitle("报警信息")
                .setAutoCancel(true)
                .setContentText("[库房报警]");

        Notification notification = builder.build();
        notificationManager.notify(0, notification);
    }

}
