package com.example.humiture.ui.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.DetailsList;
import com.example.humiture.mvp.contract.DetailsContract;
import com.example.humiture.mvp.presenter.DetailsPresent;
import com.example.humiture.ui.activity.EnvironmentActivity;
import com.example.humiture.ui.view.adapter.DetailsListAdapter;
import com.example.humiture.utils.TimeUtils;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.example.refreshview.CustomRefreshView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BaseFragment<DetailsPresent> implements DetailsContract.mView {

    @BindView(R2.id.details_list)
    CustomRefreshView mRefreshView;
    @BindView(R2.id.details_no_data)
    LinearLayout noDataLayout;
    @BindView(R2.id.details_refresh)
    LinearLayout mRefresh;

    private DetailsListAdapter mAdapter;
    private String mTime;
    private int mType = 0;
    private int mStoreId;
    private int mNumber = 0;
    private int page = 1;
    private long startTime;
    private long endTime;
    private static callBack callBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EnvironmentActivity.setCallBacks(new EnvironmentActivity.callBack() {
            @Override
            public void refresh(String newTime) {
                mTime = newTime;
                getStartAndEndTime(newTime);
                mPresent.getDetailsList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, 1);
            }

            @Override
            public void getTab(int select) {
                Log.d(TAG, "getTab: "+select);
                mType = select;
                getStartAndEndTime(mTime);
                mPresent.getDetailsList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, 1);
            }

            @Override
            public void current(int state) {
                Log.d(TAG, "current: "+state);
                mNumber = state;
                getStartAndEndTime(mTime);
                mPresent.getDetailsList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, 1);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void processBeforeInitView() {
        super.processBeforeInitView();
        mTime = getArguments().getString("time");
        mStoreId = getArguments().getInt("storeId");
        Log.d(TAG, "processBeforeInitView: "+mTime);
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new DetailsPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        mAdapter = new DetailsListAdapter(mContext);
        mRefreshView.setRefreshing(true);
        mRefreshView.getSwipeRefreshLayout().setColorSchemeColors(getResources().getColor(R.color.index_title));
        mRefreshView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        if (mNumber==0){ //今日数据
            startTime = TimeUtils.getStringToDate(TimeUtils.todayFirstDate(),TimeUtils.DEFAULT_TIME_FORMAT);
            endTime = TimeUtils.getStringToDate(TimeUtils.todayLastDate(), TimeUtils.DEFAULT_TIME_FORMAT);
        }else if (mNumber==1){ //昨日数据
            startTime = TimeUtils.getStringToDate(TimeUtils.yesterdayFirstDate(),TimeUtils.DEFAULT_TIME_FORMAT);
            endTime = TimeUtils.getStringToDate(TimeUtils.todayFirstDate(), TimeUtils.DEFAULT_TIME_FORMAT);
        }
        mPresent.getDetailsList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, 1);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRefreshView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                mPresent.getDetailsList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, 1);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresent.getMoreList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, page);
            }
        });
        mRefreshView.setOnTouchListener((v, event) -> {
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    if (callBack!=null){
                        callBack.moveView();
                    }
                    break;
            }
            return true;
        });
    }

    @OnClick({R2.id.details_refresh})
    void onClick(View view){
        switch (view.getId()){
            case R.id.details_refresh:
                mPresent.getDetailsList(mStoreId, DataTypeHelper.getDataTypes().get(mType), startTime, endTime, 1);
                break;
        }
    }

    @Override
    public void getStartAndEndTime(String time) {
        if (mNumber==0){
            startTime = TimeUtils.getStringToDate(time+" 00:00:00", TimeUtils.DEFAULT_TIME_FORMAT);
            endTime = TimeUtils.getStringToDate(time+" 23:59:59", TimeUtils.DEFAULT_TIME_FORMAT);
        }else if (mNumber==1){
            startTime = TimeUtils.getStringToDate(TimeUtils.dataForYesterday(time)+" 00:00:00", TimeUtils.DEFAULT_TIME_FORMAT);
            endTime = TimeUtils.getStringToDate(TimeUtils.dataForYesterday(time)+" 23:59:59", TimeUtils.DEFAULT_TIME_FORMAT);
        }
        Log.d(TAG, "getStartAndEndTime: "+TimeUtils.getDateToString(startTime)+"--"+TimeUtils.getDateToString(endTime));
        Log.d(TAG, "getStartAndEndTime: "+startTime +"--"+endTime);
    }

    @Override
    public void setDetailsList(List<DetailsList.Data> data) {
        mRefreshView.setVisibility(View.VISIBLE);
        noDataLayout.setVisibility(View.GONE);
        mAdapter.getDetailsList(data);
        mAdapter.notifyDataSetChanged();
        mRefreshView.complete();
        mRefreshView.stopLoadingMore();
    }

    @Override
    public void showMore(List<DetailsList.Data> data) {
        mAdapter.getMoreList(data);
        mAdapter.notifyDataSetChanged();
        mRefreshView.complete();
        mRefreshView.stopLoadingMore();
    }

    @Override
    public void noMore() {
        mRefreshView.onNoMore();
    }

    @Override
    public void errorMore() {
        mRefreshView.onError();
    }

    @Override
    public void noDetails() {
        mAdapter.getDetailsList(null);
        mAdapter.notifyDataSetChanged();
        mRefreshView.setEmptyView("暂无数据");
        mRefreshView.complete();
    }

    @Override
    public void netWorkError() {
        mRefreshView.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
        mRefreshView.complete();
    }

    public static void setCallBack(callBack back){
        callBack = back;
    }

    public interface callBack{
        void moveView();
    }
}
