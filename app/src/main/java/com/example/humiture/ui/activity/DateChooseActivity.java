package com.example.humiture.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.DateChooseContract;
import com.example.humiture.mvp.presenter.DateChoosePresenter;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.utils.DateUtils;
import com.example.humiture.utils.ToastUtils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.picker.DatePicker;

import static com.example.humiture.utils.DateUtils.DATE_FORMAT;
import static com.example.humiture.utils.DateUtils.FORMAT_YYYY_MM;

/**
 *Time:2019/5/20
 *Author:冰冰凉
 *dec:时间选择器
 */
public class DateChooseActivity extends BaseActivity<DateChoosePresenter> implements DateChooseContract.mView {

    private boolean date_year = false;
    private boolean date_month = true;
    private boolean date_day = false;

    private Drawable drawable;

    @BindView(R2.id.date_year)
    TextView txt_date_year;
    @BindView(R2.id.date_month)
    TextView txt_date_month;
    @BindView(R2.id.date_day)
    TextView txt_date_day;
    @BindView(R2.id.tv_date_time)
    TextView tv_time;

    private List<String> optionYears = new ArrayList<>();
    private List<String> optionMonth = new ArrayList<>();

    Calendar cal;
    private String year, month, day, hour, minute;
    private String time = null;
    private TimePickerView pvYearTime, pvMonthTime,pvDayTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_date_choose;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new DateChoosePresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        checkType();
    }

    @Override
    public void initData() {
        super.initData();
        initYearDate();
        initTimePicker();
        initTimeDayPicker();
        getSystemYear();
    }

    @OnClick({R2.id.date_year, R2.id.date_month, R2.id.date_day, R2.id.stat_delet, R2.id.date_back, R2.id.date_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_year:
                date_year = true;
                date_month = false;
                date_day = false;
                checkType();
                mPresent.showPickerView(optionYears, tv_time);
//                pvYearTime.show();
                break;
            case R.id.date_month:
                date_month = true;
                date_year = false;
                date_day = false;
                checkType();
                pvMonthTime.show();
                break;
            case R.id.date_day:
                date_day = true;
                date_year = false;
                date_month = false;
                checkType();
                pvDayTime.show();
                break;
            case R.id.date_back:
                finish();
                break;
            case R.id.date_ok:
                //跳转回到统计页面
                initIntent();
                break;
            case R.id.stat_delet:
                tv_time.setText("");
                break;
        }
    }

    /**
     * 设置选择时的效果
     */
    private void checkType() {

        if (date_year) {
            drawable = getResources().getDrawable(R.mipmap.stat_unfocus);
            txt_date_year.setTextColor(getResources().getColor(R.color.stat_date_edit));
            setType(drawable, txt_date_year);
        } else {
            drawable = getResources().getDrawable(R.mipmap.stat_focus);
            txt_date_year.setTextColor(getResources().getColor(R.color.stat_date_year));
            setType(drawable, txt_date_year);
        }
        if (date_month) {
            drawable = getResources().getDrawable(R.mipmap.stat_unfocus);
            txt_date_month.setTextColor(getResources().getColor(R.color.stat_date_edit));
            setType(drawable, txt_date_month);
        } else {
            drawable = getResources().getDrawable(R.mipmap.stat_focus);
            txt_date_month.setTextColor(getResources().getColor(R.color.stat_date_year));
            setType(drawable, txt_date_month);
        }
        if (date_day) {
            drawable = getResources().getDrawable(R.mipmap.stat_unfocus);
            txt_date_day.setTextColor(getResources().getColor(R.color.stat_date_edit));
            setType(drawable, txt_date_day);
        } else {
            drawable = getResources().getDrawable(R.mipmap.stat_focus);
            txt_date_day.setTextColor(getResources().getColor(R.color.stat_date_year));
            setType(drawable, txt_date_day);
        }
    }


    /**
     * 初始化年的数据
     */
    private void initYearDate() {
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        for (int i = curYear; i >= 1989; i--) {
            //对应年份的月份数据集合
            List<String> tempMonths = new ArrayList<>();
            if (i == 1989) {
                //设置最早时间“1900以前”
                optionYears.add("1990以前");
                tempMonths.add("1990以前");
            } else {
                //设置常规时间
                optionYears.add(String.valueOf(i) + "年");
            }
        }
    }

    //获取到当前时间年月
    public void getSystemYear() {
        cal = Calendar.getInstance();
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        day = String.valueOf(cal.get(Calendar.DATE));
        tv_time.setText(year + "-0" + month);
        time = year + "-0" + month;
    }


    /**
     * 设置按钮样式
     *
     * @param drawable
     * @param textView
     */
    @Override
    public void setType(Drawable drawable, TextView textView) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setPadding(20, 20, 40, 20);
        textView.setCompoundDrawables(null, null, drawable, null);
        //设置间距
        textView.setCompoundDrawablePadding(10);
    }

    private void initIntent() {
        time = tv_time.getText().toString();
        if (!TextUtils.isEmpty(time)) {
            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();
            bundle.putString("time", time);
            intent.putExtras(bundle);
            this.setResult(Activity.RESULT_OK, intent);
            this.finish();
        } else {
            showToast("日期不能为空");
        }

    }

    /**
     * 选择年月
     */
    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvMonthTime = new TimePickerBuilder(DateChooseActivity.this, (date, v) -> tv_time.setText(DateUtils.convertToDate(date,FORMAT_YYYY_MM))).setType(new boolean[]{true,true,false,false,false,false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .setTitleText("请选择年月")
                .setTitleSize(13)
                .setSubCalSize(13)
                .setSubmitColor(this.getResources().getColor(R.color.stat_date_black))
                .setCancelColor(this.getResources().getColor(R.color.stat_date_black))
                .isDialog(true)
                .build();
        Dialog mDialog = pvMonthTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvMonthTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    /**
     * 选择年月日
     */
    private void initTimeDayPicker() {//Dialog 模式下，在底部弹出
        pvDayTime = new TimePickerBuilder(DateChooseActivity.this, (date, v) -> tv_time.setText(DateUtils.convertToDate(date,DATE_FORMAT))).setType(new boolean[]{true,true,true,false,false,false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .setTitleText("请选择年月")
                .setTitleSize(13)
                .setSubCalSize(13)
                .setSubmitColor(this.getResources().getColor(R.color.stat_date_black))
                .setCancelColor(this.getResources().getColor(R.color.stat_date_black))
                .isDialog(true)
                .build();
        Dialog mDialog = pvDayTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvDayTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

}
