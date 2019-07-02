package com.example.humiture.mvp.model;

import com.example.humiture.data.Common;
import com.example.humiture.data.LoginData;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.LoginContract;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:
 */
public class LoginModel implements LoginContract.Model {

    ApiService api = RetrofitClient.create(ApiService.class);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Observable<LoginData> getLogin(String username, String password) {
        return api.getLogin(username,password).map(loginData -> loginData).compose(RetrofitClient.schedulersTransformer);
    }
}
