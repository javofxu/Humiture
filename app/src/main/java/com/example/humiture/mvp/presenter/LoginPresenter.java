package com.example.humiture.mvp.presenter;


import android.content.Context;
import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.LoginData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.greenDao.DaoSession;
import com.example.humiture.mvp.contract.LoginContract;
import com.example.humiture.mvp.model.LoginModel;
import com.example.humiture.utils.helper.GreenDaoHelp;
import com.example.humiture.utils.helper.SpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.example.humiture.utils.helper.SpUtils.TIME_DAY;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private Context context;
    private LoginContract.Model model= new LoginModel();
    private DaoSession daoSession;
    private List<KuFangSetData> kuFangSetDataList;
    private KuFangSetData kuFangSetData;


    public LoginPresenter(Context context) {
        this.context = context;
    }

    /**
     * 登录
     * @param username      用户名
     * @param password      密码
     */
    @Override
    public void getLogin(String username, String password) {
        Observable<LoginData> observable = model.getLogin(username,password);
        observable.subscribe(new Consumer<LoginData>() {
            @Override
            public void accept(LoginData loginData) throws Exception {
                if (loginData.getStatus() == 0) {
                    //将账户密码保存到本地
                    //设置的缓存时间为一天
                    SpUtils.getInstance(context).setString("username", username, TIME_DAY);
                    SpUtils.getInstance(context).setString("password", password, TIME_DAY);
                    SpUtils.getInstance(context).setInt("user_id", loginData.getData().getUser_id(), TIME_DAY);
                    SpUtils.getInstance(context).setString("name", loginData.getData().getUsername(), TIME_DAY);
                    mView.loginSuccess();
                } else {
                    //错误
                    mView.loginFail(loginData.getMsg());
                }
            }
        }, throwable -> Log.d(TAG, "loginPresenter:获取数据失败"));
    }

    /**
     * 获取库房列表
     * 将库房列表保存到本地数据库
     */
    @Override
    public void getWareHouse() {
        Observable<List<Warehouse.Data>> observable = model.getWarehouse();
        observable.subscribe(data -> {
            Log.d(TAG, "getWareHouse: LoginPresenter" + data.size());
            if (data.size() > 0){
                //将库房列表保存到本地数据库
                daoSession = GreenDaoHelp.getInstance(context).getDaoSession();
                for (int i = 0; i < data.size(); i++) {
                    //判断是否有一致的数据，然后保存到本地
                    kuFangSetDataList = GreenDaoHelp.getInstance(context).isExit(data.get(i).getName());
                    Log.i(TAG, "getWareHouse: LoginPresenter" + kuFangSetDataList.size() + data.get(i).getName() + "---" + data.get(i).getStoreId());
                    if (kuFangSetDataList.size() <= 0) {
                        kuFangSetData = new KuFangSetData();
                        kuFangSetData.setStoreId(data.get(i).getStoreId());
                        kuFangSetData.setName(data.get(i).getName());
                        daoSession.insert(kuFangSetData);
                    }
                }
            }

        }, throwable -> mView.netWorkError());
    }

}
