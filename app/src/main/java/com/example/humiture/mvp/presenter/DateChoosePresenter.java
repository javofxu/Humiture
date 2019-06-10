package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.base.rx.RxPresenter;
import com.example.humiture.R;
import com.example.humiture.mvp.contract.DateChooseContract;

import java.util.List;

import cn.addapp.pickers.picker.DatePicker;


/**
 *Time:2019/5/21
 *Author:冰冰凉
 *dec:
 */
public class DateChoosePresenter extends RxPresenter<DateChooseContract.mView> implements DateChooseContract.Presenter {

    private Context mContext;

    public DateChoosePresenter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 设置年的选择
     * @param optionYears
     * @param textView
     */
    @Override
    public void showPickerView(List<String> optionYears, TextView textView) {
        OptionsPickerView multipleOp = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (options1 == 0 || options1 == optionYears.size() - 1) {
                    //选中最新和最早时间时直接显示文字，不需要拼接月份
                    textView.setText(optionYears.get(options1));
                } else {
                    //常规的时间，需要拼接年份和月份
                    textView.setText(new StringBuffer(optionYears.get(options1)));
                }
            }
        }).setTitleText("请按年选择")
                .setTitleSize(13)
                .setSubCalSize(13)
                .setSubmitText("确定")
                .setSubmitColor(mContext.getResources().getColor(R.color.stat_date_black))
                .setCancelText("取消")
                .setCancelColor(mContext.getResources().getColor(R.color.stat_date_black))
                .build();
        multipleOp.setPicker(optionYears);
        multipleOp.show();
    }

    /**
     * 设置年月选择
     * @param textView
     */
    @Override
    public void onYearMonthPicker(TextView textView) {
        DatePicker picker = new DatePicker((Activity) mContext, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        picker.setWidth((int) (picker.getScreenWidthPixels()) * 1);
        picker.setRangeStart(2016, 10, 14);
        picker.setRangeEnd(2020, 11, 11);
        picker.setSelectedItem(2017, 9);
        picker.setTitleText("请按月选择");
        picker.setCanLinkage(true);
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        picker.setSubmitText(R.string.confirm);
        picker.setCancelText(R.string.cancel);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                textView.setText(year + "-" + month);
                String date_time_month = textView.getText().toString();
                Log.i("","onDatePicked: " + date_time_month);
            }
        });
        picker.show();
    }

    /**
     * 设置年月日选择
     * @param textView
     */
    @Override
    public void onYearMonthDayPicker(TextView textView) {
        final DatePicker picker = new DatePicker((Activity) mContext);
        picker.setCanLoop(true);
        picker.setWheelModeEnable(true);
        picker.setTitleText("请按日选择");
        picker.setTopPadding(15);
        picker.setRangeStart(2016, 8, 29);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(2050, 10, 14);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.BLACK);
        picker.setSubmitText(R.string.confirm);
        picker.setCancelText(R.string.cancel);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                textView.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }
}
