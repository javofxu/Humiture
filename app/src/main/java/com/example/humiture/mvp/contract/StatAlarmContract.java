package com.example.humiture.mvp.contract;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.example.base.BaseView;
import com.example.humiture.data.Alarm;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 *Time:2019/6/14
 *Author:冰冰凉
 *dec:
 */
public interface StatAlarmContract {
    interface Model {
        /**
         *报警统计  更多
         * @param type
         * @param date
         * @param storeId
         * @return
         */
        Observable<Alarm> getStaticAlarmList(@Field("type") String type, @Field("date") String date, @Field("storeId") String storeId);
    }

    interface View extends BaseView {
        //设置分类和时间选择器 TextView的样式
        void initTextView(Drawable drawable, int id, TextView textView);
        void onSuccess(Alarm alarm);
        void onFail(String msg);
        /**
         * 无数据
         */
        void noDetails();

        /**
         * 网络异常
         */
        void netWorkError();

    }

    interface Presenter {
        //报警统计 更多
        void getStaticAlarmList(String type,String date,String storeId);
    }
}
