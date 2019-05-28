package com.example.humiture.mvp.contract;

import android.app.Activity;

import com.example.base.BaseView;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec:
 */
public interface EnvironmentContract {

    interface model{

    }

    interface mView extends BaseView{

        /**
         * 显示所选时间
         * @param date
         */
        void showDate(String date);

    }

    interface present{


        void choseDate(Activity mActivity);
    }
}
