package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.MineSetActivity;
import com.example.humiture.ui.activity.SmartAssistantActivity;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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