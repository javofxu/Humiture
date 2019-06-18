package com.example.humiture.ui.fragment;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.OperateContract;
import com.example.humiture.mvp.presenter.OperatePresent;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.NewsActivity;
import com.example.humiture.ui.view.adapter.OperateAdapter;
import com.example.humiture.utils.helper.DataTypeHelper;

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
        mLayout.setOnRefreshListener(()-> mLayout.postDelayed(() -> mLayout.setRefreshing(false),1000));
        mAdapter = new OperateAdapter(mContext);
        mManager = new LinearLayoutManager(mContext);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mTitle.setText(DataTypeHelper.getWarehouse().get(1));
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
                if (firstCompletelyVisibleItemPosition <=0 ){
                    mRecyclerView.setEnabled(false);
                    mLayout.setEnabled(true);
                }else {
                    mRecyclerView.setEnabled(true);
                    mLayout.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R2.id.operate_title, R2.id.operate_alarm})
    void onClick(View view){
        switch (view.getId()){
            case R.id.operate_title:
                mPresent.choseWareHouse(mContext,DataTypeHelper.getWarehouse());
                break;
            case R.id.operate_alarm:
                skipAnotherActivity(MineInfoActivity.class);
                break;
        }
    }
    @Override
    public void showWareHouse(String warehouse) {
        mTitle.setText(warehouse);
    }
}
