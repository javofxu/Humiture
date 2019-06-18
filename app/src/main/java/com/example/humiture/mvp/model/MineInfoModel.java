package com.example.humiture.mvp.model;

import android.util.Log;

import com.example.humiture.data.Common;
import com.example.humiture.data.MessageData;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.MineInfoContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:
 */
public class MineInfoModel implements MineInfoContract.Model {

    private static final String TAG = "MineInfoModel";
    ApiService api = RetrofitClient.create(ApiService.class);

    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    @Override
    public Observable<Common> getChangePwd(int userId, String password) {
        return api.getChangePwd(userId,password).map(common -> common).compose(RetrofitClient.schedulersTransformer);
    }

    /**
     * 我的消息  报警消息列表
     * @param strtime
     * @return
     */
    @Override
    public Observable<MessageData> getAlarmMessage(String strtime) {
        return api.getAlarmMessage(strtime).map(messageData -> {
            Log.i(TAG, "apply: " + messageData.getStatus());
            return messageData;
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
