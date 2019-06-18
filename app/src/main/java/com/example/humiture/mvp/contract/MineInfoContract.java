package com.example.humiture.mvp.contract;

import android.support.design.widget.TabLayout;

import com.example.base.BaseView;
import com.example.humiture.data.Common;
import com.example.humiture.data.MessageData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 *Time:2019/6/12
 *Author:冰冰凉
 *dec:
 */
public interface MineInfoContract {
    interface Model {
        //修改密码
        Observable<Common> getChangePwd(@Field("userId") int userId, @Field("password") String password);

        //报警信息列表
        Observable<MessageData> getAlarmMessage(@Field("strtime") String strtime);
    }

    interface View extends BaseView {
        //成功
        void onSuccess(String msg);
        //失败
        void onFail(String msg);

        void onListSuccess(MessageData data);

        /**
         * 无数据
         */
        void noDetails();

        /**
         * 网络异常
         */
        void netWorkError();

    }

    interface Presenter {

        //为TabLayout设置宽度
        void reflex(final TabLayout tabLayout);

        //修改密码
        void getChangePwd(int userId,String password);

        //报警消息列表
        void getAlarmMessage(String strtime);

    }
}
