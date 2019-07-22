package com.example.humiture.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.PlayerListData;
import com.example.humiture.mvp.contract.PlayerContract;
import com.example.humiture.mvp.presenter.PlayerPresenter;
import com.example.humiture.ui.view.adapter.PlayerInfoAdapter;
import com.example.humiture.utils.helper.GreenDaoHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Time:2019/6/26
 * Author:冰冰凉
 * dec:  视频信息页面，用于展示库房的视频监控的列表
 */
public class PlayerInfoActivity extends BaseActivity<PlayerPresenter> implements PlayerContract.View {

    @BindView(R2.id.player_list)
    RecyclerView player_list;
    @BindView(R.id.index_title)
    TextView indexTitle;
    @BindView(R2.id.info_no_data)
    LinearLayout infoNoData;
    @BindView(R2.id.alarm)
    FrameLayout alarm;
    @BindView(R2.id.player_scrollView)
    ScrollView playerScrollView;
    private List<PlayerListData.Data> lists = null;
    private List<KuFangSetData> list = null;
    private List<KuFangSetData> stordIdList = null;
    private List<String> wareHouseList = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_playerinfo;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new PlayerPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        GreenDaoHelp.getInstance(this).initGreenDao(this);
        list = GreenDaoHelp.getInstance(this).queryAllList();
        player_list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        String wareHouse = intent.getStringExtra("warehouse");
        indexTitle.setText(wareHouse);
        getData(wareHouse);
    }

    @OnClick({R2.id.playerinfo_back, R2.id.alarm, R2.id.index_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playerinfo_back:
                finish();
                break;
            case R.id.alarm:
                skipAnotherActivity(MineInfoActivity.class);
                break;
            case R.id.index_title:
                wareHouseList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    wareHouseList.add(list.get(i).getName());
                }
                mPresent.choseWareHouse(this, wareHouseList);
                break;
        }
    }

    @Override
    public void showWareHouse(String warehouse) {
        indexTitle.setText(warehouse);
        getData(warehouse);

    }

    @Override
    public void onSuccessed(List<PlayerListData.Data> list) {
        Log.i(TAG, "onSuccessed: PlayerInfoActivity" + list.size());
        if (list.size() > 0) {
            infoNoData.setVisibility(View.GONE);
            playerScrollView.setVisibility(View.VISIBLE);
            player_list.setAdapter(new PlayerInfoAdapter(this, list));
        } else {
            infoNoData.setVisibility(View.VISIBLE);
            playerScrollView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailed(String msg) {
        showToast(msg);
    }

    /**
     * 根据库房来进行网络操作
     *
     * @param msg
     */
    private void getData(String msg) {
        stordIdList = GreenDaoHelp.getInstance(this).getStorId(msg);
        for (int i = 0; i < stordIdList.size(); i++) {
            int storeId = stordIdList.get(i).getStoreId();
            Log.i(TAG, "initData: " + storeId);
            //接口请求,没有数据时显示没有数据界面
            mPresent.getPlayerList("5", String.valueOf(storeId));
        }
    }
}
