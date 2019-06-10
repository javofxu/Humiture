package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.baidu.speech.EventManager;
import com.baidu.speech.asr.SpeechConstant;
import com.example.base.rx.RxPresenter;
import com.example.base.rx.RxTimerUtil;
import com.example.humiture.R;
import com.example.humiture.data.AsrFinish.AsrFinishJsonData;
import com.example.humiture.data.AsrPartial.AsrPartialJsonData;
import com.example.humiture.mvp.contract.VoiceContract;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by 许格.
 * Date on 2019/5/30.
 * dec:
 */
public class VoicePresent extends RxPresenter<VoiceContract.mView> implements VoiceContract.present {

    private String msg;
    private Context mContext;
    private MediaPlayer player = null;

    public VoicePresent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getRandom() {
        RxTimerUtil.interval(1000, () -> {
            Random random = new Random();
            int num = random.nextInt(99)%(99) + 1;
            mView.showNum(num);
        });
    }

    @Override
    public void stopTimer() {
        RxTimerUtil.cancel();
        if (player!=null) player.release();
    }

    @Override
    public void prompt() {
        player = MediaPlayer.create(mContext, R.raw.siri);
        player.setVolume(0.5f,0.5f);
        player.start();
    }

    @Override
    public void start(EventManager manager) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        String event;
        event = SpeechConstant.ASR_START;
        params.put(SpeechConstant.PID, 1536); // 默认1536
        params.put(SpeechConstant.DECODER, 0); // 纯在线(默认)
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN); // 语音活动检测
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 1000); // 不开启长语音。开启VAD尾点检测，即静音判断的毫秒数。建议设置800ms-3000ms
        params.put(SpeechConstant.ACCEPT_AUDIO_DATA, false);// 是否需要语音音频数据回调
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);// 是否需要语音音量数据回调

        String json; //可以替换成自己的json
        json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json
        manager.send(event, json,null,0, 0);
    }

    @Override
    public void stop(EventManager manager) {
        manager.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }

    @Override
    public void asrPartial(String param) {
        Gson gson = new Gson();
        AsrPartialJsonData jsonData = gson.fromJson(param, AsrPartialJsonData.class);
        String resultType = jsonData.getResult_type();
        if(resultType != null && resultType.equals("final_result")){
            msg = jsonData.getBest_result();
        }
    }

    @Override
    public void asrFinish(String param) {
        Gson gson = new Gson();
        AsrFinishJsonData jsonData = gson.fromJson(param, AsrFinishJsonData.class);
        String desc = jsonData.getDesc();
        if(desc !=null && desc.equals("Speech Recognize success.")){
            mView.showVoice(msg);
        }else{
            String errorCode = "\n错误码:" + jsonData.getError();
            String errorSubCode = "\n错误子码:"+ jsonData.getSub_error();
            String errorResult = errorCode + errorSubCode;
            mView.showError(errorResult);
        }
    }
}
