package com.example.humiture.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.view.adapter.MineInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineInfoFragment extends BaseFragment {

    @BindView(R2.id.info_list)
    RecyclerView recyclerView;

    private MineInfoAdapter mineInfoAdapter;
    private List<String> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_info;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add("一库房温度过高");
        recyclerView.setAdapter(new MineInfoAdapter(getActivity(),list));
    }

}
