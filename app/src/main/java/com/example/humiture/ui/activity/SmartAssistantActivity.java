package com.example.humiture.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;
import cn.addapp.pickers.picker.TimePicker;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:智能小助手
 */
public class SmartAssistantActivity extends BaseActivity {

    @BindView(R2.id.smart_time_start)
    TextView smart_time_start;
    @BindView(R2.id.smart_time_end)
    TextView smart_time_end;
    @BindView(R2.id.smart_tv_warn)
    TextView smart_tv_warn;
    Calendar cal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.smart_back,R2.id.smart_ll_start,R2.id.smart_ll_end,R2.id.smart_tv_warn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.smart_back:
                finish();
                break;
            case R.id.smart_ll_start:
                //开始时间
                onTimePicker(smart_time_start,null);
                break;
            case R.id.smart_ll_end:
                //结束时间
                onTimePicker(null,smart_time_end);
                break;
            case R.id.smart_tv_warn:
                //异常提醒
                onOptionPicker(smart_tv_warn);
                break;
        }

    }

    /**
     * 显示时间
     * @param start
     * @param end
     */
    public void onTimePicker(View start,View end) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        cal = Calendar.getInstance();
        picker.setRangeStart(Integer.valueOf(cal.get(Calendar.HOUR)), Integer.valueOf(cal.get(Calendar.MINUTE)));//09:00
        picker.setRangeEnd(23, 0);//18:30
        picker.setSubmitText("确定");
        picker.setCancelText("取消");
        picker.setTitleText("请选择时间");
        picker.setTopLineVisible(false);
        picker.setLineVisible(false);
        picker.setWheelModeEnable(false);
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                SmartAssistantActivity.this.showToast(hour + ":" + minute);
                if (start != null) {
                    smart_time_start.setText(hour + ":" + minute);
                } else if (end != null) {
                    smart_time_end.setText(hour + ":" + minute);
                }

            }
        });
        picker.show();
    }

    /**
     * 设置是否提醒
     * @param view
     */
    public void onOptionPicker(View view) {
        ArrayList<String> list = new ArrayList<>();
        list.add("提醒");
        list.add("不提醒");
//        String[] ss = (String[]) list.toArray();
        SinglePicker<String> picker = new SinglePicker<>(this, list);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setTextSize(14);
        picker.setSelectedIndex(2);
        picker.setWheelModeEnable(false);
        //启用权重 setWeightWidth 才起作用
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setHeight(400);
        picker.setCancelText("取消");
        picker.setSubmitText("确定");
        picker.setTitleText("请选择");
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.show();
    }

}
