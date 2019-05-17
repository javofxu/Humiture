package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.IndexContract;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.ui.view.adapter.LoopShowAdapter;
import com.example.humiture.utils.ItemDecorationUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by 许格.
 * Date on 2019/5/14.
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class IndexFragment extends BaseFragment<IndexPresent> implements IndexContract.mView{

    @BindView(R2.id.picker)
    DiscreteScrollView picker;
    @BindView(R2.id.index_point)
    LinearLayout point;
    @BindView(R2.id.index_chart)
    LineChart chartView;
    @BindView(R2.id.index_title)
    TextView title;
    @BindView(R2.id.swipeRefreshLayout)
    PullRefreshLayout layout;

    private LoopShowAdapter adapter;
    private int pagerNumber;
    private HashMap<String, Integer> map;
    private String[] warehouse = new String[]{"一库房", "二库房", "三库房"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new IndexPresent(mContext);
    }

    @Override
    protected void initView() {
        super.initView();
        pagerNumber = 4;
        map = new HashMap<>();
        map.put(ItemDecorationUtils.LEFT_DECORATION,20);//右间距
        map.put(ItemDecorationUtils.RIGHT_DECORATION,20);//右间距
        adapter = new LoopShowAdapter(mContext,pagerNumber);
        picker.addItemDecoration(new ItemDecorationUtils(map));
        picker.setAdapter(adapter);
        picker.setCurrentItemChangeListener((viewHolder, adapterPosition) -> {
            mPresent.drawPoint(point,pagerNumber,adapterPosition);
        });
    }

    @Override
    protected void initData() {
        super.initData();
        title.setOnClickListener(v -> mPresent.warehouse(warehouse));
        layout.setOnRefreshListener(()-> {
            layout.postDelayed(() -> layout.setRefreshing(false),1000);
        });
    }

    @Override
    public void showWareHouse(String warehouse) {
        title.setText(warehouse);
    }
}
