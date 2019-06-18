package com.example.humiture.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.custom.CustomEditText;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.Alarm;
import com.example.humiture.mvp.contract.StatAlarmContract;
import com.example.humiture.mvp.presenter.StatAlarmPresenter;
import com.example.humiture.ui.view.adapter.StatAlarmAdapter;
import com.example.humiture.ui.view.CustomPopupWindow;
import com.example.humiture.utils.DateUtils;
import com.example.humiture.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;


public class StatAlarmActivity extends BaseActivity<StatAlarmPresenter> implements StatAlarmContract.View {

    @BindView(R2.id.alarm_list)
    RecyclerView recyclerView;
    @BindView(R2.id.stat_date)
    TextView stat_date;
    @BindView(R2.id.alarm_sort)
    TextView alarm_sort;
    @BindView(R2.id.alarm_search)
    CustomEditText alarm_search;
    @BindView(R2.id.info_no_data)
    LinearLayout no_detail;

    private StatAlarmAdapter statAlarmAdapter;
    private List<Alarm.Data.ListAlarm> mList;

    private Drawable drawable;
    CustomPopupWindow customPopupWindow;
    private String resultTime = null;
    private String time = null;
    private String type = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stat_alarm;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new StatAlarmPresenter(this);
    }

    @Override
    public void initData() {
        super.initData();
        mPresent.getStaticAlarmList("1","2019","1");
    }

    @Override
    protected void initView() {
        super.initView();
        //设置时间选择器的样式
        initTextView(drawable,R.mipmap.stat_date_normal,stat_date);
        //设置分类的样式
        initTextView(drawable,R.mipmap.stat_classify_normal,alarm_sort);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick({R2.id.alarm_back,R2.id.stat_date,R2.id.tv_alarm_search,R2.id.alarm_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alarm_back:
                finish();
                break;
            case R.id.stat_date:
                Intent intent = new Intent(this,DateChooseActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
                break;
            case R.id.tv_alarm_search:
                String search = alarm_search.getText();
                ToastUtils.showToast(search);
                break;
            case R.id.alarm_sort:
                //设置一个PopupWindow
                show(view);
                break;
        }
    }

    /**
     * 设置分类和时间选择的样式
     * @param drawable
     * @param id
     * @param textView
     */
    @Override
    public void initTextView(Drawable drawable, int id, TextView textView) {
        drawable = getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setPadding(30, 0, 30, 0);
        textView.setCompoundDrawables(null, null, drawable, null);
        //设置间距
        textView.setCompoundDrawablePadding(15);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.stat_date_normal, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            resultTime = bundle.getString("time");
            if(resultTime.contains("年")){
                resultTime = resultTime.replace("年","");
                Log.i(TAG, "onActivityResult: " + resultTime);
            }
            stat_date.setText(resultTime);
            if(resultTime.length() < 5){
                Log.i(TAG, "onActivityResultYear: " + resultTime);
                type = "1";
            }else if(resultTime.length() > 5 && resultTime.length() <8){
                Log.i(TAG, "onActivityResultMonth: " + resultTime);
                type = "2";
            }else if(resultTime.length() > 8 ){
                Log.i(TAG, "onActivityResultDay: " + resultTime);
                type = "3";
            }
            mPresent.getStaticAlarmList(type,resultTime,"1");
        }
    }

    @Override
    public void onSuccess(Alarm alarm) {
        mList = new ArrayList<>();
        for (int i = 0;i< alarm.getData().getList().size();i++){
            Alarm.Data.ListAlarm data = new Alarm().new Data().new ListAlarm();
            data.setAlarmType(alarm.getData().getList().get(i).getAlarmType());
            data.setAlarm_value(alarm.getData().getList().get(i).getAlarm_value());
            data.setCreated(alarm.getData().getList().get(i).getCreated());
            mList.add(data);
        }
        recyclerView.setAdapter(new StatAlarmAdapter(this,mList));
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void noDetails() {
        recyclerView.setVisibility(View.GONE);
        no_detail.setVisibility(View.VISIBLE);
    }

    @Override
    public void netWorkError() {

    }

    /**
     * 显示CustomPopupWindow
     * @param view
     */
    private void show(View view){
        CustomPopupWindow customPopupWindow = CustomPopupWindow.builder()
                .contentView(CustomPopupWindow.inflateView(this, R.layout.stat_alarm_sort))
                .isWrap(false)
                .isOutsideTouch(false)
                .customListener(contentView -> handleLogic(contentView))
                .build();
        customPopupWindow.showAsDropDown(view);
    }

    /**
     * 设置点击事件
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(customPopupWindow != null){
                    customPopupWindow.dismiss();
                }
                switch (v.getId()){
                    case R.id.sort_all:
                        Toast.makeText(StatAlarmActivity.this,"显示",Toast.LENGTH_SHORT).show();
                        alarm_sort.setText("全部");
                        break;
                }
            }
        };
        contentView.findViewById(R.id.sort_all).setOnClickListener(listener);
    }

}
