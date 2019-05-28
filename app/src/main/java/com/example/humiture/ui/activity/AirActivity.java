package com.example.humiture.ui.activity;

import android.view.View;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.view.Gallery.BannerLayout;
import com.example.humiture.ui.view.adapter.AirAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class AirActivity extends BaseActivity {

    @BindView(R2.id.air_banner)
    BannerLayout mLayout;

    private AirAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_air;
    }

    @Override
    protected void initView() {
        super.initView();
        mAdapter = new AirAdapter(this);
        mLayout.setAdapter(mAdapter);
    }

    @OnClick({R2.id.air_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.air_back:
                finish();
                break;
        }
    }
}
