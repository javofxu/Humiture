package com.example.humiture.mvp.contract;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.example.base.BaseView;

public interface StatAlarmContract {
    interface Model {
    }

    interface View extends BaseView {
        //设置分类和时间选择器 TextView的样式
        void initTextView(Drawable drawable, int id, TextView textView);

    }

    interface Presenter {
    }
}
