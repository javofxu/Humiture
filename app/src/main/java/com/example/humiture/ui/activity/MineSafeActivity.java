package com.example.humiture.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class MineSafeActivity extends BaseActivity {

    @BindView(R2.id.mine_safe_name)
    TextView tv_name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_safe;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.mine_safe_back,R2.id.mine_safe_ll_name,R2.id.mine_safe_ll_password})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_safe_ll_name:
                break;
            case R.id.mine_safe_ll_password:
                break;
            case R.id.mine_safe_back:
                finish();
                break;
        }
    }

}
