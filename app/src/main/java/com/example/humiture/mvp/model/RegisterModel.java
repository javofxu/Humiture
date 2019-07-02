package com.example.humiture.mvp.model;

import com.example.humiture.data.Common;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.RegisterContract;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:
 */
public class RegisterModel implements RegisterContract.Model {

    ApiService api = RetrofitClient.create(ApiService.class);

    //注册
    @Override
    public Observable<Common> getRegister(String username, String password, String repassword) {
        return api.getRegister(username,password,repassword).map(common -> common).compose(RetrofitClient.schedulersTransformer);
    }
}
