package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.example.humiture.data.Common;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.LoginData;
import com.example.humiture.data.Warehouse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:
 */
public interface LoginContract {
    interface Model {
        //登录
        Observable<LoginData> getLogin(@Field("username") String username, @Field("password") String password);

        /**
         * 获取库房列表
         * @return
         */
        Observable<List<Warehouse.Data>> getWarehouse();
    }

    interface View extends BaseView {
        void loginSuccess();        //登录成功逻辑
        void loginFail(String msg);           //登录失败逻辑
        void netWorkError();
    }

    interface Presenter {
        //执行登录
        void getLogin(String username,String password);

        /**
         * 获取库房和库房ID集合
         */
        void getWareHouse();
    }
}
