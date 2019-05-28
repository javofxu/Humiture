package com.example.humiture.ui.fragment;

import android.view.View;

import com.example.base.BaseFragment;
import com.example.humiture.R;

import butterknife.OnClick;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:库房环境设置
 */
public class MineKuFangFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_kufang;
    }

    @OnClick(R.id.set_ok)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.set_ok:
                //执行上传代码
                break;
        }
    }

}
