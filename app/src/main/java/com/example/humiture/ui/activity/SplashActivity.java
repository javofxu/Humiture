package com.example.humiture.ui.activity;

import android.os.UserManager;

import com.example.base.BaseActivity;
import com.example.base.rx.RxTimerUtil;
import com.example.humiture.R;
import com.example.humiture.utils.helper.SpUtils;


/**
 * Created by 许格.
 * Date on 2019/5/14.
 * 飞溅页面
 */
public class SplashActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        super.initData();
        isLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxTimerUtil.cancel();
    }

    /**
     * 判断是否登录过
     */
    private void isLogin(){
        String name = SpUtils.getInstance(this).getString("username","");
        String password = SpUtils.getInstance(this).getString("password","");
        if(name.length()<=0 || password.length() <= 0){
            showToast("请登录");
            RxTimerUtil.timer(1000,()->{skipAnotherActivity(LoginActivity.class);finish();});
        }else{
            RxTimerUtil.timer(1000,()->{skipAnotherActivity(MainActivity.class);finish();});
        }
    }

}
