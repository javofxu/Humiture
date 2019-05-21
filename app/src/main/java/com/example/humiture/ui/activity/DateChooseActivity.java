package com.example.humiture.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.DateChooseContract;
import com.example.humiture.mvp.presenter.DateChoosePresenter;
import com.example.humiture.mvp.presenter.IndexPresent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.picker.DatePicker;

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

    private String date_time_month = null;

    private List<String> optionYears = new ArrayList<>();

    Calendar cal;
    private String year,month;

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
        getSystemYear();
    }

    @OnClick({R.id.date_year,R.id.date_month,R.id.date_day,R.id.stat_delet,R.id.date_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.date_year:
                date_year = true;
                date_month = false;
                date_day =false;
                checkType();
                mPresent.showPickerView(optionYears,tv_time);
                break;
            case R.id.date_month:
                date_month = true;
                date_year = false;
                date_day = false;
                checkType();
                mPresent.onYearMonthPicker(tv_time);
                break;
            case R.id.date_day:
                date_day = true;
                date_year = false;
                date_month = false;
                checkType();
                mPresent.onYearMonthDayPicker(tv_time);
                break;
            case R.id.date_back:
                finish();
                break;
            case R.id.date_ok:

                break;
            case R.id.stat_delet:
                tv_time.setText("");
                date_time_month = tv_time.getText().toString();
                Log.i(TAG, "onClick: " + date_time_month);
                break;
        }
    }

    /**
     * 设置选择时的效果
     */
    private void checkType(){

        if(date_year){
            drawable=getResources().getDrawable(R.mipmap.stat_unfocus);
            txt_date_year.setTextColor(getResources().getColor(R.color.stat_date_edit));
            setType(drawable,txt_date_year);
        }else {
            drawable=getResources().getDrawable(R.mipmap.stat_focus);
            txt_date_year.setTextColor(getResources().getColor(R.color.stat_date_year));
            setType(drawable,txt_date_year);
        }
        if(date_month){
            drawable=getResources().getDrawable(R.mipmap.stat_unfocus);
            txt_date_month.setTextColor(getResources().getColor(R.color.stat_date_edit));
            setType(drawable,txt_date_month);
        }else{
            drawable=getResources().getDrawable(R.mipmap.stat_focus);
            txt_date_month.setTextColor(getResources().getColor(R.color.stat_date_year));
            setType(drawable,txt_date_month);
        }
        if(date_day){
            drawable=getResources().getDrawable(R.mipmap.stat_unfocus);
            txt_date_day.setTextColor(getResources().getColor(R.color.stat_date_edit));
            setType(drawable,txt_date_day);
        }else {
            drawable=getResources().getDrawable(R.mipmap.stat_focus);
            txt_date_day.setTextColor(getResources().getColor(R.color.stat_date_year));
            setType(drawable,txt_date_day);
        }
    }


    /**
     * 初始化年的数据
     */
    private void initYearDate() {
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        for (int i = curYear + 1; i >= 1989; i--) {
            //对应年份的月份数据集合
            List<String> tempMonths = new ArrayList<>();
            if (i == curYear + 1) {
                //设置最新时间“至今”
                optionYears.add("至今");
            }else if (i == 1989) {
                //设置最早时间“1900以前”
                optionYears.add("1990以前");
                tempMonths.add("1990以前");
            } else {
                //设置常规时间
                optionYears.add(String.valueOf(i));
            }
        }
    }
    //获取到当前时间年月
    private void getSystemYear(){
        cal = Calendar.getInstance();
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        tv_time.setText(year + "-" + month);
    }


    /**
     * 设置按钮样式
     * @param drawable
     * @param textView
     */
    @Override
    public void setType(Drawable drawable, TextView textView) {
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        textView.setPadding(20,20,40,20);
        textView.setCompoundDrawables(null,null,drawable,null);
        //设置间距
        textView.setCompoundDrawablePadding(10);
    }
}
