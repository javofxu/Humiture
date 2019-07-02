package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.MessageData;
import com.example.humiture.mvp.contract.MineInfoContract;
import com.example.humiture.mvp.presenter.MineInfoPresenter;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.MineSetActivity;
import com.example.humiture.ui.activity.SmartAssistantActivity;
import com.example.humiture.utils.helper.SpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    @BindView(R2.id.mine_tv_name)
    TextView tv_name;
    private String name = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        name = SpUtils.getInstance(getActivity()).getString("name","");
        tv_name.setText(name);
    }

    @OnClick({R.id.mine_ll_info,R.id.mine_ll_helper,R.id.mine_ll_response,R.id.mine_ll_about,R.id.mine_ll_set})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_ll_info:
                skipAnotherActivity(MineInfoActivity.class);
                break;
            case R.id.mine_ll_helper:
                //智能小助手
                skipAnotherActivity(SmartAssistantActivity.class);
                break;
            case R.id.mine_ll_response:
                break;
            case R.id.mine_ll_about:
                break;
            case R.id.mine_ll_set:
                skipAnotherActivity(MineSetActivity.class);
                break;
        }

    }

}