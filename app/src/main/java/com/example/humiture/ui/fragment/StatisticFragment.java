package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.ui.view.RadarData;
import com.example.humiture.ui.view.RadarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends BaseFragment {

    @BindView(R.id.radarView)
    RadarView radarView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistic;
    }

    @Override
    protected void initView() {
        super.initView();

        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            //传入数据
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setDataList(dataList);
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
