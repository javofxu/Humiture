package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.base.rx.RxPresenter;
import com.example.humiture.R;
import com.example.humiture.mvp.contract.DateChooseContract;
import com.example.humiture.ui.activity.DateChooseActivity;
import com.example.humiture.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.addapp.pickers.picker.DatePicker;

import static com.example.humiture.utils.DateUtils.DATE_FORMAT;
import static com.example.humiture.utils.DateUtils.FORMAT_YYYY;
import static com.example.humiture.utils.DateUtils.FORMAT_YYYY_MM;


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
                    String yearTime = null;
                    if(optionYears.get(options1).contains("年")){
                        yearTime = optionYears.get(options1).replace("年","");
                    }
                    textView.setText(yearTime);
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
}
