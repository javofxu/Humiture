package com.example.humiture.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.MessageData;
import com.example.humiture.mvp.contract.MineInfoContract;
import com.example.humiture.mvp.presenter.MineInfoPresenter;
import com.example.humiture.ui.activity.DateChooseActivity;
import com.example.humiture.ui.view.adapter.MineInfoAdapter;
import com.example.humiture.utils.TimeUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.humiture.utils.TimeUtils.LABEL_DAY;
import static com.example.humiture.utils.TimeUtils.LABEL_MONTH;
import static com.example.humiture.utils.TimeUtils.LABEL_YEAR;

/**
 *Time:2019/6/12
 *Author:冰冰凉
 *dec:
 */
public class MineInfoFragment extends BaseFragment<MineInfoPresenter> implements MineInfoContract.View {

    @BindView(R2.id.info_list)
    RecyclerView recyclerView;
    @BindView(R2.id.info_no_data)
    LinearLayout no_data;

    private MineInfoAdapter mineInfoAdapter;
    private List<MessageData.Data> msg_data = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_info;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new MineInfoPresenter(mContext);
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        super.initData();
        int year = TimeUtils.getYearAndMonthAndDay(LABEL_YEAR,TimeUtils.getCurrentStringTime());
        int month = TimeUtils.getYearAndMonthAndDay(LABEL_MONTH,TimeUtils.getCurrentStringTime());
        int day = TimeUtils.getYearAndMonthAndDay(LABEL_DAY,TimeUtils.getCurrentStringTime());
        String date = year + "-" + month + "-" + day;
        mPresent.getAlarmMessage(date);
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFail(String msg) {
//        dismissProgressDialog();
    }


    @Override
    public void onListSuccess(MessageData messageData) {
//        dismissProgressDialog();
        msg_data = new ArrayList<>();
        for(int i=0;i<messageData.getData().size();i++){
            MessageData.Data data1 = new MessageData().new Data();
            data1.setStorename(messageData.getData().get(i).getStorename());
            data1.setAlarmTypeName(messageData.getData().get(i).getAlarmTypeName());
            data1.setStarttime(messageData.getData().get(i).getStarttime());
            msg_data.add(data1);
        }
        recyclerView.setAdapter(new MineInfoAdapter(getActivity(),msg_data));
    }

    @Override
    public void noDetails() {
        recyclerView.setVisibility(View.GONE);
        no_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void netWorkError() {

    }

}
