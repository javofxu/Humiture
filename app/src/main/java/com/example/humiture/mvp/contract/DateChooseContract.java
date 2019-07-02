package com.example.humiture.mvp.contract;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.example.base.BaseView;
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
    }
}
