package com.example.humiture.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.custom.CustomEditText;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.StatAlarmContract;
import com.example.humiture.mvp.presenter.StatAlarmPresenter;
import com.example.humiture.ui.view.adapter.StatAlarmAdapter;
import com.example.humiture.ui.view.CustomPopupWindow;
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

    private StatAlarmAdapter statAlarmAdapter;
    private List<String> mList;

    private Drawable drawable;
    CustomPopupWindow customPopupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stat_alarm;
    }

    @Override
    protected void initPresent() {
        super.initPresent();

    }

    @Override
    public void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.add("Pm2.5");
        mList.add("30");
        mList.add("超高");
        mList.add("今日");
        mList.add("11:47");
        recyclerView.setAdapter(new StatAlarmAdapter(this,mList));
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
                skipAnotherActivity(DateChooseActivity.class);
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
        textView.setPadding(0, 0, 20, 0);
        textView.setCompoundDrawables(null, null, drawable, null);
        //设置间距
        textView.setCompoundDrawablePadding(8);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.stat_date_normal, 0);
    }

    private void show(View view){
        CustomPopupWindow customPopupWindow = CustomPopupWindow.builder()
                .contentView(CustomPopupWindow.inflateView(this, R.layout.stat_alarm_sort))
                .isWrap(false)
                .isOutsideTouch(false)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {
                        handleLogic(contentView);

                    }
                })
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
                        break;
                }
            }
        };
        contentView.findViewById(R.id.sort_all).setOnClickListener(listener);
    }

}
