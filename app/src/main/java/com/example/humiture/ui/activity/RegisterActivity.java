package com.example.humiture.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.custom.CustomEditText;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.RegisterContract;
import com.example.humiture.mvp.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec: 注册
 * {
 *     "status": 0,
 *     "msg": "注册成功",
 *     "data": "{}"
 * }
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R2.id.edt_register_name)
    CustomEditText edt_register_name;
    @BindView(R2.id.edt_register_pwd)
    CustomEditText edt_register_pwd;
    @BindView(R2.id.edt_register_pwd_again)
    CustomEditText edt_register_pwd_again;

    private String username = null;
    private String password = null;
    private String repassword = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new RegisterPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.register_back,R2.id.btn_register})
    void onClick(View view){
        switch (view.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.btn_register:
                registerGo();
                break;
        }
    }

    /**
     * 注册
     */
    private void registerGo(){
        username = edt_register_name.getText().toString();
        password = edt_register_pwd.getText().toString();
        repassword = edt_register_pwd_again.getText().toString();
        if(TextUtils.isEmpty(username)){
            showToast("账户不能为空");
        }else if(TextUtils.isEmpty(password)){
            showToast("密码不能为空");
        } else if(TextUtils.isEmpty(repassword)){
            showToast("确认密码不能为空");
        } else{
            showProgressDialog();
            mPresent.getRegister(username,password,repassword);
        }
    }

    @Override
    public void registerSuccess(String msg) {
        dismissProgressDialog();
        showToast(msg);
        skipAnotherActivity(LoginActivity.class);
    }

    @Override
    public void registerFail(String msg) {
        dismissProgressDialog();
        showToast(msg);
    }
}
