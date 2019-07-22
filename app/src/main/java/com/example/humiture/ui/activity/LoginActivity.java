package com.example.humiture.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.custom.CustomEditText;
import com.example.custom.OnHasTextListener;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.LoginContract;
import com.example.humiture.mvp.presenter.LoginPresenter;
import com.example.humiture.utils.helper.SpUtils;
import com.github.mikephil.charting.charts.BubbleChart;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.humiture.utils.helper.SpUtils.TIME_DAY;

/**
 * 登录页的功能实现
 *
 * {
 *     "status": 0,
 *     "msg": "登录成功",
 *     "data": {
 *         "user_id": 1,
 *         "username": "admin"
 *     }
 * }
 *
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R2.id.edt_login_name)
    CustomEditText name;
    @BindView(R2.id.edt_login_pwd)
    CustomEditText password;
    @BindView(R2.id.btn_login)
    Button btn_login;

    private String lg_username = null;
    private String lg_password = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.btn_login,R2.id.tv_register,R2.id.login_Direct})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                lg_username = name.getText().toString();
                lg_password = password.getText().toString();
                if(TextUtils.isEmpty(lg_username) || TextUtils.isEmpty(lg_password)){
                    showToast("账户或密码为空");
                }else{
                    showProgressDialog();
                    mPresent.getLogin(lg_username,lg_password);
                }
                break;
            case R.id.tv_register:
                skipAnotherActivity(RegisterActivity.class);
                break;
            case R.id.login_Direct:
                loginDirect();
                break;
        }
    }

    /**
     * 直接登录
     */
    private void loginDirect(){
        String name = SpUtils.getInstance(this).getString("username","");
        String password = SpUtils.getInstance(this).getString("password","");
        int user_id = SpUtils.getInstance(this).getInt("user_id",0);
        String username = SpUtils.getInstance(this).getString("name","");
        mPresent.getLogin(name,password);
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {
        dismissProgressDialog();
        //获取库房
        mPresent.getWareHouse();
        skipAnotherActivity(MainActivity.class);
    }

    /**
     * 登录失败
     */
    @Override
    public void loginFail(String msg) {
        dismissProgressDialog();
        showToast(msg);
    }

    @Override
    public void netWorkError() {
        showToast(getResources().getString(R.string.network_error));
    }

}
