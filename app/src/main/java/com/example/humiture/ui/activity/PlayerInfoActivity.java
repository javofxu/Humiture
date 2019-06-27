package com.example.humiture.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.view.adapter.PlayerInfoAdapter;
import com.example.humiture.ui.view.adapter.StatAlarmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/6/26
 *Author:冰冰凉
 *dec:  视频信息页面，用于展示库房的视频监控的列表
 */
public class PlayerInfoActivity extends BaseActivity {

    @BindView(R2.id.player_list)
    RecyclerView player_list;
    private List<String> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_playerinfo;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        player_list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add("通道一");
        list.add("通道二");
        list.add("通道三");
        list.add("通道四");
        list.add("通道五");
        list.add("通道六");
        player_list.setAdapter(new PlayerInfoAdapter(this,list));
    }

    @OnClick({R2.id.playerinfo_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.playerinfo_back:
                finish();
                break;
        }
    }

}
