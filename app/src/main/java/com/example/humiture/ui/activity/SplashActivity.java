package com.example.humiture.ui.activity;

import com.example.base.BaseActivity;
import com.example.base.rx.RxTimerUtil;
import com.example.humiture.R;


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
        RxTimerUtil.timer(1000,()->{skipAnotherActivity(MainActivity.class);finish();});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxTimerUtil.cancel();
    }
}
