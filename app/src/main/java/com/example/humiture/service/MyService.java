package com.example.humiture.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.baidu.speech.asr.SpeechConstant;
import com.example.humiture.ui.activity.VoiceActivity;
import com.example.humiture.utils.CommonUtil;
import com.example.humiture.utils.wakeUp.IWakeupListener;
import com.example.humiture.utils.wakeUp.MyLogger;
import com.example.humiture.utils.wakeUp.MyWakeup;
import com.example.humiture.utils.wakeUp.RecogWakeupListener;
import java.util.HashMap;
import java.util.Map;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo
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

    private static final String TAG = MyService.class.getSimpleName();
    private static MyWakeup myWakeup;
    private Handler handler;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    @Override
    public void onCreate() {
        super.onCreate();
        setMyWakeup();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        wakeUpStart();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 设置语音唤醒功能
     */
    private void setMyWakeup(){
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
            }

        };
        MyLogger.setHandler(handler);
        IWakeupListener listener = new RecogWakeupListener(handler);
        myWakeup = new MyWakeup(MyService.this, listener);
    }

    /**
     * 接收到唤醒词并唤醒成功
     * @param msg
     */
    private void handleMsg(Message msg){
        if (msg.obj != null) {
            Log.d(TAG, "handleMsg: "+msg.obj.toString());
            String name = msg.obj.toString();
            if (name.indexOf("唤醒成功")!=-1){
                Handler handler=new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    if (!CommonUtil.isActivityExist(VoiceActivity.class)){
                        Intent intent = new Intent(MyService.this, VoiceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                        startActivity(intent);
                        stop();
                    }
                });
            }
        }
    }

    /**
     * 开启唤醒
     */
    public static void wakeUpStart() {
        Map<String, Object> params = new HashMap<>();
        params.put(SpeechConstant.WP_WORDS_FILE, "assets://WakeUp.bin");
        myWakeup.start(params);
    }

    /**
     * 停止唤醒
     */
    private void stop(){
        myWakeup.stop();
=======

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
        myWakeup.release();
    }
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
