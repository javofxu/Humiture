package com.example.humiture.ui.activity;

import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;

/**
 * 登录页的功能实现
 */
public class LoginActivity extends BaseActivity {

    private TextView tv_register = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(v -> skipAnotherActivity(RegisterActivity.class));
    }

    @Override
    public void initData() {
        super.initData();
    }
}
