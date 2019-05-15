package com.example.humiture.ui.activity;

import android.view.View;

import com.example.base.BaseActivity;
import com.example.custom.CustomEditText;
import com.example.humiture.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.edt_register_name)
    CustomEditText edt_register_name;
    @BindView(R.id.edt_register_pwd)
    CustomEditText edt_register_pwd;
    @BindView(R.id.edt_register_pwd_again)
    CustomEditText edt_register_pwd_again;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.register_back,R.id.btn_register})
    void onClick(View view){
        switch (view.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.btn_register:
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }
}
