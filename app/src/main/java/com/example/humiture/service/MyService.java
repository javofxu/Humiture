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

public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    private static MyWakeup myWakeup;
    private Handler handler;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setMyWakeup();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myWakeup.release();
    }
}
