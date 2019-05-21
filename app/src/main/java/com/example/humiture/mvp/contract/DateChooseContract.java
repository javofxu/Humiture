package com.example.humiture.mvp.contract;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.base.BaseView;

import org.w3c.dom.Text;

import java.util.List;

/**
 *Time:2019/5/21
 *Author:冰冰凉
 *dec:
 */
public interface DateChooseContract {
    interface Model {
    }

    interface mView extends BaseView {
        //设置按钮的样式
        void setType(Drawable drawable, TextView textView);
    }

    interface Presenter {
        //设置年的选择
        void showPickerView(List<String> optionYears,TextView textView);
        //设置年月的选择
        void onYearMonthPicker(TextView textView);
        //设置年月日的选择
        void onYearMonthDayPicker(TextView textView);
    }
}
