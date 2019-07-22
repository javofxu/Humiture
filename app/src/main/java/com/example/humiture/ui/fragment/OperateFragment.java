package com.example.humiture.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.AllList;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.mvp.contract.OperateContract;
import com.example.humiture.mvp.presenter.OperatePresent;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.PlayerInfoActivity;
import com.example.humiture.ui.view.adapter.OperateAdapter;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.example.humiture.utils.helper.GreenDaoHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 操作页面
 */
public class OperateFragment extends BaseFragment<OperatePresent> implements OperateContract.mView {

    @BindView(R2.id.operate_list)
    RecyclerView mRecyclerView;
    @BindView(R2.id.operate_swipe)
    PullRefreshLayout mLayout;
    @BindView(R2.id.operate_title)
    TextView mTitle;
    @BindView(R2.id.operate_alarm_number)
    TextView mNumber;

    private OperateAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<KuFangSetData> list = null;
    private List<String> wareHouseList = null;
    private List<KuFangSetData> queryList = null;
    private List<AllList> allLists = new ArrayList<>();
    private RealTimeData realTimeData = new RealTimeData();
    private String wareHouseName = null;
    private String temperature;     //温度
    private String humidity;        //湿度
    private String pm2;             //pm2.5
    private String tvoc;            //tvoc
    private String formaldehyde;    //甲醛
    private String eco2;            //eco2
    private String colony;          //菌落
    private String harmful;         //有害气体

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operate;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new OperatePresent();
    }

    @Override
    protected void initView() {
        super.initView();
        GreenDaoHelp.getInstance(getActivity()).initGreenDao(getActivity());
        mLayout.setOnRefreshListener(() -> mLayout.postDelayed(() -> mLayout.setRefreshing(false), 1000));
        mManager = new LinearLayoutManager(mContext);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
    }

    @Override
    protected void initData() {
        super.initData();
        list = GreenDaoHelp.getInstance(mContext).queryAllList();
        if (list.size() <= 0) {
            mTitle.setText("一库房");
            wareHouseName = "一库房";
        } else {
            mTitle.setText(list.get(0).getName());
            wareHouseName = list.get(0).getName();
        }

        //库房列表和状态
        mPresent.getOperateData("1");
        mPresent.getRealTimeData(1);
    }

    @Override
    protected void initListener() {
        super.initListener();
        //解决下拉刷新与列表的滑动冲突
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                if (firstCompletelyVisibleItemPosition <= 0) {
                    mRecyclerView.setEnabled(false);
                    mLayout.setEnabled(true);
                } else {
                    mRecyclerView.setEnabled(true);
                    mLayout.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R2.id.operate_title, R2.id.operate_alarm, R2.id.operate_player})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.operate_title:
                //加载本地数据库的库房
                wareHouseList = new ArrayList<>();
                if(list.size() <=0 ){
                    mPresent.choseWareHouse(mContext, DataTypeHelper.getWarehouse());
                }else{
                    for (int i = 0; i < list.size(); i++) {
                        wareHouseList.add(list.get(i).getName());
                    }
                    mPresent.choseWareHouse(mContext, wareHouseList);
                }

                break;
            case R.id.operate_alarm:
                skipAnotherActivity(MineInfoActivity.class);
                break;
            case R.id.operate_player:
                Bundle bundle = new Bundle();
                bundle.putString("warehouse", wareHouseName);
                skipAnotherActivity(bundle, PlayerInfoActivity.class);
                break;
        }
    }

    @Override
    public void showWareHouse(String warehouse) {
        mTitle.setText(warehouse);
        wareHouseName = warehouse;
        //查出选中的库房的id然后获取库房列表和状态
        queryList = GreenDaoHelp.getInstance(mContext).getStorId(warehouse);
        for (int i = 0; i < queryList.size(); i++) {
            Log.i(TAG, "showWareHouse: " + queryList.get(i).getStoreId());
            mPresent.getRealTimeData(queryList.get(i).getStoreId());
        }
    }

    @Override
    public void onSuccesss(List<AllList> lists) {
        mAdapter = new OperateAdapter(mContext, lists,realTimeData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onFails(String msg) {

    }

    @Override
    public void onSuccessed(String msg) {

    }

    /**
     * 获取库房的温度和湿度，菌落，pm2.5等和首页的环境数据一样
     * @param mData
     */
    @Override
    public void updateRealTimeData(RealTimeData mData,int storeId) {
        Log.i(TAG, "updateRealTimeData: " + mData.getData().getHumidity());
        temperature = mData.getData().getTemperature();
        humidity = mData.getData().getHumidity();
        pm2 = mData.getData().getPm2();
        colony = mData.getData().getColony();
        formaldehyde = mData.getData().getFormaldehyde();
        tvoc = mData.getData().getTvoc();
        harmful = mData.getData().getHarmful();
        eco2 = mData.getData().getEco2();
        realTimeData = mData;
        //获取到库房列表
        mPresent.getOperateData(String.valueOf(storeId));
    }


}
