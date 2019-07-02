package com.example.humiture.mvp.contract;

import com.baidu.speech.EventManager;
import com.example.base.BaseView;

/**
 * Created by 许格.
 * Date on 2019/5/30.
 * dec:
 */
public interface VoiceContract {

    interface mView extends BaseView{

        void showNum(int num);

        void showVoice(String tip);

        void showError(String error);
    }

    interface present{
        /**
         * 获取随机数
         */
        void getRandom();

        /**
         * 停止
         */
        void stopTimer();

        /**
         * 播放提示音
         */
        void prompt();
        /**
         * 开始录语音
         * @param manager
         */
        void start(EventManager manager);

        /**
         * 停止录语音
         * @param manager
         */
        void stop(EventManager manager);

        /**
         * 临时识别结果
         * @param
         */
        void asrPartial(String param);

        /**
         * 识别结束
         * @param param
         */
        void asrFinish(String param);

    }
}
