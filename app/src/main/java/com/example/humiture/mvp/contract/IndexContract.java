package com.example.humiture.mvp.contract;

import android.widget.LinearLayout;

import com.example.base.BaseView;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public interface IndexContract {

    interface model{

    }

    interface mView extends BaseView{
        void showWareHouse(String warehouse);
    }

    interface present{
        /**
         * 动态添加指示器
         * @param layout
         * @param position
         */
        void drawPoint(LinearLayout layout, int pagerNumber, int position);

        void warehouse(String[] mList);
    }
}
