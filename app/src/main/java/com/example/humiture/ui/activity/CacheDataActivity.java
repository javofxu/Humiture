package com.example.humiture.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.utils.CacheDataManager;

import butterknife.BindView;
import butterknife.OnClick;

public class CacheDataActivity extends BaseActivity {

    @BindView(R2.id.tv_cache_show)
    TextView tv_cache_show;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cache_clear;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        try{
            tv_cache_show.setText(CacheDataManager.getTotalCacheSize(this));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.btn_cache_clear,R2.id.cache_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_cache_clear:
                //清除缓存
                CacheDataManager.clearCache(CacheDataActivity.this,handler);
                break;
            case R.id.cache_back:
                finish();
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(CacheDataActivity.this, "清理完成", Toast.LENGTH_SHORT).show();
                    try {
                        tv_cache_show.setText(CacheDataManager.getTotalCacheSize(CacheDataActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };

}
