package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.Common;
import com.example.humiture.mvp.contract.RegisterContract;
import com.example.humiture.mvp.model.RegisterModel;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:
 */
public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private static final String TAG = "RegisterPresenter";
    private Context context;
    private RegisterContract.Model model = new RegisterModel();

    public RegisterPresenter(Context context) {
        this.context = context;
    }

    /**
     * 注册接口
     * @param username
     * @param password
     * @param repassword
     */
    @Override
    public void getRegister(String username, String password, String repassword) {
        Observable<Common> observable = model.getRegister(username,password,repassword);
        observable.subscribe(common -> {
            if (common.getStatus() == 0) {
                //成功
                mView.registerSuccess(common.getMsg());
            } else {
                //失败
                mView.registerFail(common.getMsg());
            }
        }, throwable -> Log.d(TAG, "RegisterPresenter:获取数据失败"));
    }
}
