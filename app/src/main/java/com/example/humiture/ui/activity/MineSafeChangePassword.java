package com.example.humiture.ui.activity;

import android.text.TextUtils;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.custom.CustomEditText;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.MessageData;
import com.example.humiture.mvp.contract.MineInfoContract;
import com.example.humiture.mvp.presenter.MineInfoPresenter;
import com.example.humiture.utils.helper.SpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/6/12
 *Author:冰冰凉
 *dec:密码重置
 */
public class MineSafeChangePassword extends BaseActivity<MineInfoPresenter> implements MineInfoContract.View {

    @BindView(R2.id.edt_register_pwd)
    CustomEditText password;
    @BindView(R2.id.edt_register_pwd_again)
    CustomEditText rePassword;

    private String s_password = null;
    private String s_repassword = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_changepwd;
    }

    @Override
    protected void initPresent()  {
        super.initPresent();
        mPresent = new MineInfoPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.register_back,R2.id.btn_change})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.btn_change:
                changeGO();
                break;
        }
    }

    /**
     * 修改密码
     */
    private void changeGO(){
        s_password = password.getText().toString();
        s_repassword = rePassword.getText().toString();
        if(TextUtils.isEmpty(s_password)){
            showToast("密码不能为空");
        }else if(TextUtils.isEmpty(s_repassword)){
            showToast("确认密码不能为空");
        }else if(!s_password.equals(s_repassword)){
            showToast("两次密码不一致");
        }else {
            int userId = SpUtils.getInstance(this).getInt("user_id",0);
            showProgressDialog();
            mPresent.getChangePwd(userId,s_password);
        }
    }

    @Override
    public void onSuccess(String msg) {
        dismissProgressDialog();
        showToast(msg);
    }

    @Override
    public void onFail(String msg) {
        dismissProgressDialog();
        showToast(msg);
    }

    @Override
    public void onListSuccess(MessageData data) {

    }

    @Override
    public void noDetails() {

    }

    @Override
    public void netWorkError() {

    }

}
