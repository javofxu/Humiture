package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.example.base.rx.RxPresenter;
import com.example.humiture.mvp.contract.EnvironmentContract;
import com.example.humiture.utils.TimeUtils;

import cn.addapp.pickers.picker.DatePicker;

import static com.example.humiture.utils.TimeUtils.LABEL_DAY;
import static com.example.humiture.utils.TimeUtils.LABEL_MONTH;
import static com.example.humiture.utils.TimeUtils.LABEL_YEAR;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec:
 */
public class EnvironmentPresent extends RxPresenter<EnvironmentContract.mView> implements EnvironmentContract.present {
    private Context mContext;
    private int mYear;
    private int mMonth;
    private int mDay;

    public EnvironmentPresent(Context context) {
        this.mContext = context;
    }

    @Override
    public void choseDate(Activity mActivity) {
        String currentTime = TimeUtils.getCurrentStringTime();
        mYear = TimeUtils.getYearAndMonthAndDay(LABEL_YEAR, currentTime);
        mMonth = TimeUtils.getYearAndMonthAndDay(LABEL_MONTH, currentTime);
        mDay = TimeUtils.getYearAndMonthAndDay(LABEL_DAY, currentTime);
        DatePicker picker = new DatePicker(mActivity);
        picker.setCanLoop(true);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(mYear-2, 1, 1);
        picker.setRangeEnd(mYear+5, 1, 1);
        picker.setSelectedItem(mYear, mMonth, mDay);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.BLACK);
        picker.setTitleText("选择时间");
        picker.setOnDatePickListener((DatePicker.OnYearMonthDayPickListener) (year, month, day) -> mView.showDate(year + "-" + month + "-" + day));
        picker.show();
    }
}
