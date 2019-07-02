package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.example.humiture.data.Common;
import io.reactivex.Observable;
import retrofit2.http.Field;


public interface RegisterContract {
    interface Model {
        //注册接口
        Observable<Common> getRegister(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);
    }

    interface View extends BaseView {
        //注册成功
        void registerSuccess(String msg);
        //注册失败
        void registerFail(String msg);
    }

    interface Presenter {
        //注册
        void getRegister(String username,String password,String repassword);

    }
}
