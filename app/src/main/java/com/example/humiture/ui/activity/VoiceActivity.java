package com.example.humiture.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.VoiceContract;
import com.example.humiture.mvp.presenter.VoicePresent;
import com.example.humiture.service.MyService;
import com.example.humiture.ui.view.WaveView;
import com.example.humiture.ui.view.listener.UiMessageListener;
import com.example.humiture.utils.CommonUtil;
import com.example.humiture.utils.speak.InitConfig;
import com.example.humiture.utils.speak.MySyntherizer;
import com.example.humiture.utils.speak.NonBlockSyntherizer;
import com.example.humiture.utils.speak.SpeakUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class VoiceActivity extends BaseActivity<VoicePresent> implements VoiceContract.mView {

    @BindView(R2.id.voice_wave)
    WaveView mWaveView;
    @BindView(R2.id.voice_tip)
    TextView mTip;
    @BindView(R2.id.voice_speech)
    ImageView mSpeech;

    private EventManager mManager;
    protected MySyntherizer mSynthesizer;
    private Handler mainHandler;

    protected String appId = "16371361";

    protected String appKey = "qjfi4hwZbCp9vI4syLy7r9Li";

    protected String secretKey = "462zNUM2kxT56R7SoxtnooaoSo6ZQLBi";

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    protected TtsMode ttsMode = TtsMode.MIX;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_voice;
    }

    @Override
    protected void processBeforeInitView() {
        super.processBeforeInitView();
        CommonUtil.addActivity(this,VoiceActivity.class);
        mainHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "handleMessage: "+msg);
            }
        };
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new VoicePresent(this);
    }

    @Override
    protected void initView() {
        super.initView();
        initialTts();
        mWaveView.startAnim();
        mManager = EventManagerFactory.create(this,"asr");
    }

    @Override
    public void initData() {
        super.initData();
        mPresent.getRandom();
    }

    @Override
    protected void initListener() {
        super.initListener();
        EventListener listener = (s, s1, bytes, i, i1) -> {
            if(s == SpeechConstant.CALLBACK_EVENT_ASR_READY){
                // 引擎准备就绪，可以开始说话
                Log.i("TAG","start:"+s1);
            } else if(s == SpeechConstant.CALLBACK_EVENT_ASR_BEGIN){
                // 检测到用户的已经开始说话
            } else if(s == SpeechConstant.CALLBACK_EVENT_ASR_END){
                // 检测到用户的已经停止说话
                Log.i("TAG","start:"+s1);
            } else if (s == SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL){
                // 临时识别结果, 长语音模式需要从此消息中取出结果
                mPresent.asrPartial(s1);
            } else if (s == SpeechConstant.CALLBACK_EVENT_ASR_FINISH){
                // 识别结束， 最终识别结果或可能的错误
                mPresent.prompt();
                mPresent.asrFinish(s1);
            }
        };
        mManager.registerListener(listener);
    }

    @OnClick({R.id.voice_speech})
    void onClick(View view){
        switch (view.getId()){
            case R.id.voice_speech:
                mSpeech.setVisibility(View.INVISIBLE);
                mWaveView.setVisibility(View.VISIBLE);
                mPresent.start(mManager);
                break;
        }
    }

    @Override
    public void showNum(int num) {
        mWaveView.setVolume((float)num);
    }

    @Override
    public void showVoice(String tip) {
        mTip.setText(tip);
        mWaveView.setVisibility(View.INVISIBLE);
        mSpeech.setVisibility(View.VISIBLE);
        mPresent.stop(mManager);
        mSynthesizer.speak(tip);
    }

    @Override
    public void showError(String error) {
        mTip.setText("我好像不明白");
        mWaveView.setVisibility(View.INVISIBLE);
        mSpeech.setVisibility(View.VISIBLE);
        mPresent.stop(mManager);
        mSynthesizer.speak("我好像不明白");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.stopTimer();
        mSynthesizer.release();
        mManager.send(SpeechConstant.ASR_CANCEL,"{}",null,0,0);
        MyService.wakeUpStart();
        CommonUtil.removeActivity(this);
    }

    protected void initialTts() {
        LoggerProxy.printable(true); // 日志打印在logcat中
        // 设置初始化参数
        SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);

        Map<String, String> params = SpeakUtils.getParams();

        // appId appKey secretKey 网站上您申请的应用获取。注意使用离线合成功能的话，需要应用中填写您app的包名。包名在build.gradle中获取。
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);

        mSynthesizer = new NonBlockSyntherizer(this, initConfig, mainHandler); // 此处可以改为MySyntherizer 了解调用过程
    }


}
