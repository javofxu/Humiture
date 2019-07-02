package com.example.humiture.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;

import butterknife.BindView;

/**
 *Time:2019/6/14
 *Author:冰冰凉
 *dec:系统消息  现在还没有说怎么开发，所以先没有开发
 */
public class AlarmInfoFragment extends BaseFragment {

    @BindView(R2.id.info_list)
    RecyclerView recyclerView;
    @BindView(R2.id.info_no_data)
    LinearLayout no_data;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_info;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setVisibility(View.GONE);
        no_data.setVisibility(View.VISIBLE);
    }
}
