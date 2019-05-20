package com.example.humiture.ui.activity;

import android.view.View;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;

import butterknife.OnClick;

public class NewsActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @OnClick({R2.id.news_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.news_back:
                finish();
                break;
        }
    }
}
