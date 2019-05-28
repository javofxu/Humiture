package com.example.humiture.mvp.contract;

import android.support.design.widget.TabLayout;

import com.example.base.BaseView;

public interface MineInfoContract {
    interface Model {
    }

    interface View extends BaseView {
    }

    interface Presenter {

        //为TabLayout设置宽度
        void reflex(final TabLayout tabLayout);

    }
}
