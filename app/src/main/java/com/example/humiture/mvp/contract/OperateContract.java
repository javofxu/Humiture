package com.example.humiture.mvp.contract;

import android.app.Activity;

import com.example.base.BaseView;

import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec:
 */
public interface OperateContract {

    interface model{

    }

    interface mView extends BaseView{
        /**
         * 库房显示
         * @param warehouse 库房名称
         */
        void showWareHouse(String warehouse);
    }

    interface present{
        /**
         * 选择库房
         * @param mActivity 绑定活动
         * @param wareHouse 库房列表
         */
        void choseWareHouse(Activity mActivity, List<String> wareHouse);
    }
}
