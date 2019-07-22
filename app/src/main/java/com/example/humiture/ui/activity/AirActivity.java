package com.example.humiture.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.AllList;
import com.example.humiture.data.DeviceData;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.mvp.contract.OperateContract;
import com.example.humiture.mvp.presenter.OperatePresent;
import com.example.humiture.ui.view.Gallery.BannerLayout;
import com.example.humiture.ui.view.adapter.AirAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Time:2019/7/2
 * Author:冰冰凉
 * dec:
 * 空调：
 * 制冷，制热，通风
 * <p>
 * 加湿机：
 * 加湿，负离子
 * <p>
 * 除湿机：
 * 除湿，负离子
 * <p>
 * 消毒机：
 * 消毒，净化
 * <p>
 * 恒湿机：
 * 全部
 */
public class AirActivity extends BaseActivity<OperatePresent> implements OperateContract.mView {

    @BindView(R2.id.air_banner)
    BannerLayout mLayout;
    @BindView(R2.id.iv_op_sm)
    ImageView iv_op_sm;
    @BindView(R2.id.iv_op_hm)
    ImageView iv_op_hm;
    @BindView(R2.id.iv_op_refrigeration)
    ImageView iv_op_refrigeration;
    @BindView(R2.id.iv_op_heating)
    ImageView iv_op_heating;
    @BindView(R2.id.iv_op_ventilate)
    ImageView iv_op_ventilate;
    @BindView(R2.id.iv_op_stop)
    ImageView iv_op_stop;
    @BindView(R2.id.operate_equipment)
    TextView operateEquipment;
    @BindView(R2.id.air_temp)
    TextView airTemp;
    @BindView(R2.id.tv_op_sm)
    TextView tvOpSm;
    @BindView(R2.id.tv_op_hm)
    TextView tvOpHm;
    @BindView(R2.id.tv_op_refrigeration)
    TextView tvOpRefrigeration;
    @BindView(R2.id.tv_op_heating)
    TextView tvOpHeating;
    @BindView(R2.id.tv_op_ventilate)
    TextView tvOpVentilate;
    @BindView(R2.id.tv_op_stop)
    TextView tvOpStop;
    @BindView(R2.id.air_back)
    ImageView airBack;
    @BindView(R2.id.air_air)
    LinearLayout airAir;
    @BindView(R2.id.air_humidification)
    LinearLayout airHumidification;
    @BindView(R2.id.air_dehumidification)
    LinearLayout airDehumidification;
    @BindView(R2.id.air_constanthumidity)
    LinearLayout airConstanthumidity;
    @BindView(R2.id.air_disinfection)
    LinearLayout airDisinfection;
    @BindView(R2.id.iv_op_hm_humidification)
    ImageView ivOpHmHumidification;
    @BindView(R2.id.iv_op_hm_negativeion)
    ImageView ivOpHmNegativeion;
    @BindView(R2.id.iv_op_hm_stop)
    ImageView ivOpHmStop;
    @BindView(R2.id.iv_op_dm_humidification)
    ImageView ivOpDmHumidification;
    @BindView(R2.id.iv_op_dm_negativeion)
    ImageView ivOpDmNegativeion;
    @BindView(R2.id.iv_op_dm_stop)
    ImageView ivOpDmStop;
    @BindView(R2.id.iv_op_ch_humidification)
    ImageView ivOpChHumidification;
    @BindView(R2.id.iv_op_ch_dehumidification)
    ImageView ivOpChDehumidification;
    @BindView(R2.id.iv_op_ch_hm_negativeion)
    ImageView ivOpChHmNegativeion;
    @BindView(R2.id.iv_op_ch_de_negativeion)
    ImageView ivOpChDeNegativeion;
    @BindView(R2.id.iv_op_dis_disinfection)
    ImageView ivOpDisDisinfection;
    @BindView(R2.id.iv_op_dis_purification)
    ImageView ivOpDisPurification;
    @BindView(R2.id.iv_op_dis_stop)
    ImageView ivOpDisStop;
    @BindView(R2.id.iv_op_ch_stop)
    ImageView ivOpChStop;
    @BindView(R2.id.air_img)
    ImageView airImg;
    @BindView(R2.id.air_symbols)
    TextView airSymbols;
    @BindView(R2.id.tv_op_hm_humidification)
    TextView tvOpHmHumidification;
    @BindView(R2.id.tv_op_hm_negativeion)
    TextView tvOpHmNegativeion;
    @BindView(R2.id.tv_op_hm_stop)
    TextView tvOpHmStop;
    @BindView(R2.id.tv_op_dm_humidification)
    TextView tvOpDmHumidification;
    @BindView(R2.id.tv_op_dm_negativeion)
    TextView tvOpDmNegativeion;
    @BindView(R2.id.tv_op_dm_stop)
    TextView tvOpDmStop;
    @BindView(R2.id.tv_op_ch_humidification)
    TextView tvOpChHumidification;
    @BindView(R2.id.tv_op_ch_dehumidification)
    TextView tvOpChDehumidification;
    @BindView(R2.id.tv_op_ch_hm_negativeion)
    TextView tvOpChHmNegativeion;
    @BindView(R2.id.tv_op_ch_de_negativeion)
    TextView tvOpChDeNegativeion;
    @BindView(R2.id.tv_op_ch_stop)
    TextView tvOpChStop;
    @BindView(R2.id.tv_op_dis_disinfection)
    TextView tvOpDisDisinfection;
    @BindView(R2.id.tv_op_dis_purification)
    TextView tvOpDisPurification;
    @BindView(R2.id.tv_op_dis_stop)
    TextView tvOpDisStop;
    private boolean state = false;

    private AirAdapter mAdapter;
    private String name = null;
    private List<DeviceData> deviceData;
    private List<AllList> list;
    private List<DeviceData> deviceDataList = new ArrayList<>();
    private String deviceId = null;
    private RealTimeData realTimeData;

    //更新UI,确保界面只有一个是标记的
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    break;
                case 1:
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_air;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new OperatePresent();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        list = (List<AllList>) intent.getSerializableExtra("data");
        realTimeData = (RealTimeData) intent.getSerializableExtra("realTimeData");
        operateEquipment.setText(name);
        showView(name);
        for (int i = 0; i < list.size(); i++) {
            Log.i(TAG, "initData: AirActivity" + list.get(i).getDeviceData().size() + "---" + realTimeData.getData().getHumidity());
            deviceData = new ArrayList<>();
            if (list.get(i).getName().equals(name)) {
                deviceData = list.get(i).getDeviceData();
                deviceDataList = deviceData;
                if (deviceData.size() <= 0) {
                    showToast("没有设备");
                } else {
                    mAdapter = new AirAdapter(this, deviceData);
                    mLayout.setAdapter(mAdapter);
                    //利用接口回调，调出item的位置，利用位置获取deviceId,然后控制设备
                    mAdapter.setSelected(position -> {
                        deviceId = String.valueOf(deviceDataList.get(position).getDeviceId());
                        Log.i(TAG, "selected: " + deviceId);
                    });
                }
                mLayout.setOnBannerItemClickListener(position -> {
                    deviceId = String.valueOf(deviceDataList.get(position).getDeviceId());
                    Log.i(TAG, "onItemClick: " + position + "---" + deviceId);
                });
            }
        }
        Log.i(TAG, "initData: " + name + "---" + deviceId);
    }

    @OnClick({R2.id.air_back, R2.id.iv_op_sm, R2.id.iv_op_hm, R2.id.iv_op_refrigeration, R2.id.iv_op_heating,
            R2.id.iv_op_ventilate, R2.id.iv_op_stop,
            R2.id.iv_op_hm_humidification, R2.id.iv_op_hm_negativeion, R2.id.iv_op_hm_stop,
            R2.id.iv_op_dm_humidification, R2.id.iv_op_dm_negativeion, R2.id.iv_op_dm_stop,
            R2.id.iv_op_dis_disinfection, R2.id.iv_op_dis_purification, R2.id.iv_op_dis_stop,
            R2.id.iv_op_ch_dehumidification, R2.id.iv_op_ch_humidification, R2.id.iv_op_ch_hm_negativeion, R2.id.iv_op_ch_de_negativeion, R2.id.iv_op_ch_stop})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.air_back:
                Log.i(TAG, "onClick: " + deviceId);
                finish();
                break;
            case R.id.iv_op_sm:
                //自动
                if (state) {
                    iv_op_sm.setImageResource(R.mipmap.auto_click);
                    mPresent.getOperate(deviceId,"11");
                    state = false;
                } else {
                    iv_op_sm.setImageResource(R.mipmap.auto_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_hm:
                //手动
                if (state) {
                    iv_op_hm.setImageResource(R.mipmap.manual_click);
                    mPresent.getOperate(deviceId,"12");
                    state = false;
                } else {
                    iv_op_hm.setImageResource(R.mipmap.manual_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_refrigeration:
                //制冷
                if (state) {
                    iv_op_refrigeration.setImageResource(R.mipmap.refrigeration_click);
                    state = false;
                    mPresent.getOperate(deviceId, "3");
                } else {
                    iv_op_refrigeration.setImageResource(R.mipmap.refrigeration_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_heating:
                //制热
                if (state) {
                    iv_op_heating.setImageResource(R.mipmap.heating_click);
                    state = false;
                    Log.i(TAG, "onClick:制热： " + deviceId);
                    mPresent.getOperate(deviceId, "2");
                } else {
                    iv_op_heating.setImageResource(R.mipmap.heating_normal);
                    state = true;
                }

                break;
            case R.id.iv_op_ventilate:
                //通风
                if (state) {
                    iv_op_ventilate.setImageResource(R.mipmap.ventilate_click);
                    state = false;
                } else {
                    iv_op_ventilate.setImageResource(R.mipmap.ventilate_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_stop:
                //停止
                if (state) {
                    iv_op_stop.setImageResource(R.mipmap.stop_click);
                    state = false;
                    mPresent.getOperate(deviceId, "0");
                } else {
                    iv_op_stop.setImageResource(R.mipmap.stop_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_hm_humidification:
                //加湿机加湿
                if (state) {
                    ivOpHmHumidification.setImageResource(R.mipmap.humidification_click);
                    state = false;
                    mPresent.getOperate(deviceId, "4");
                } else {
                    ivOpHmHumidification.setImageResource(R.mipmap.humidification_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_hm_negativeion:
                //加湿机负离子
                if (state) {
                    ivOpHmNegativeion.setImageResource(R.mipmap.anion_click);
                    state = false;
                    mPresent.getOperate(deviceId, "9");
                } else {
                    ivOpHmNegativeion.setImageResource(R.mipmap.anion_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_hm_stop:
                //加湿机停止
                if (state) {
                    ivOpHmStop.setImageResource(R.mipmap.stop_click);
                    state = false;
                    mPresent.getOperate(deviceId, "0");
                } else {
                    ivOpHmStop.setImageResource(R.mipmap.stop_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_dm_humidification:
                //除湿机除湿
                if (state) {
                    ivOpDmHumidification.setImageResource(R.mipmap.dehumidification_click);
                    state = false;
                    mPresent.getOperate(deviceId, "5");
                } else {
                    ivOpDmHumidification.setImageResource(R.mipmap.dehumidification_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_dm_negativeion:
                //除湿机负离子
                if (state) {
                    ivOpDmNegativeion.setImageResource(R.mipmap.anion_click);
                    state = false;
                    mPresent.getOperate(deviceId, "10");
                } else {
                    ivOpDmNegativeion.setImageResource(R.mipmap.anion_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_dm_stop:
                //除湿机停止
                if (state) {
                    ivOpDmStop.setImageResource(R.mipmap.stop_click);
                    state = false;
                    mPresent.getOperate(deviceId, "0");
                } else {
                    ivOpDmStop.setImageResource(R.mipmap.stop_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_dis_disinfection:
                //消毒机消毒
                if (state) {
                    ivOpDisDisinfection.setImageResource(R.mipmap.bacterial_normal);
                    state = false;
                    mPresent.getOperate(deviceId, "7");
                } else {
                    ivOpDisDisinfection.setImageResource(R.mipmap.bacterial_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_dis_purification:
                //消毒机净化
                if (state) {
                    ivOpDisPurification.setImageResource(R.mipmap.purification_click);
                    state = false;
                    mPresent.getOperate(deviceId, "6");
                } else {
                    ivOpDisPurification.setImageResource(R.mipmap.purification_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_dis_stop:
                //消毒机停止
                if (state) {
                    ivOpDisStop.setImageResource(R.mipmap.stop_click);
                    state = false;
                    mPresent.getOperate(deviceId, "0");
                } else {
                    ivOpDisStop.setImageResource(R.mipmap.stop_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_ch_dehumidification:
                //一体机除湿
                if (state) {
                    ivOpChDehumidification.setImageResource(R.mipmap.dehumidification_click);
                    state = false;
                    mPresent.getOperate(deviceId, "5");
                } else {
                    ivOpChDehumidification.setImageResource(R.mipmap.dehumidification_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_ch_humidification:
                //一体机加湿
                if (state) {
                    ivOpChHumidification.setImageResource(R.mipmap.humidification_click);
                    state = false;
                    mPresent.getOperate(deviceId, "4");
                } else {
                    ivOpChHumidification.setImageResource(R.mipmap.humidification_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_ch_hm_negativeion:
                //一体机加湿负离子
                if (state) {
                    ivOpChHmNegativeion.setImageResource(R.mipmap.anion_click);
                    state = false;
                    mPresent.getOperate(deviceId, "9");
                } else {
                    ivOpChHmNegativeion.setImageResource(R.mipmap.anion_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_ch_de_negativeion:
                //一体机除湿负离子
                if (state) {
                    ivOpChDeNegativeion.setImageResource(R.mipmap.anion_click);
                    state = false;
                    mPresent.getOperate(deviceId, "10");
                } else {
                    ivOpChDeNegativeion.setImageResource(R.mipmap.anion_normal);
                    state = true;
                }
                break;
            case R.id.iv_op_ch_stop:
                //一体机停止
                if (state) {
                    ivOpChStop.setImageResource(R.mipmap.stop_click);
                    state = false;
                    mPresent.getOperate(deviceId, "0");
                } else {
                    ivOpChStop.setImageResource(R.mipmap.stop_normal);
                    state = true;
                }
                break;
        }
    }

    /**
     * 用来显示不同机器的界面
     *
     * @param name
     */
    private void showView(String name) {
        if (name.equals("一体机")) {
            airAir.setVisibility(View.GONE);
            airHumidification.setVisibility(View.GONE);
            airDehumidification.setVisibility(View.GONE);
            airDisinfection.setVisibility(View.GONE);
            airConstanthumidity.setVisibility(View.VISIBLE);
        } else if (name.equals("消毒机")) {
            airAir.setVisibility(View.GONE);
            airHumidification.setVisibility(View.GONE);
            airDehumidification.setVisibility(View.GONE);
            airDisinfection.setVisibility(View.VISIBLE);
            airConstanthumidity.setVisibility(View.GONE);

            airImg.setImageResource(R.mipmap.icon_bacterial);
            airSymbols.setText(R.string.dw_colony);
            airTemp.setText(realTimeData.getData().getColony());
        } else if (name.equals("加湿机")) {
            airAir.setVisibility(View.GONE);
            airHumidification.setVisibility(View.VISIBLE);
            airDehumidification.setVisibility(View.GONE);
            airDisinfection.setVisibility(View.GONE);
            airConstanthumidity.setVisibility(View.GONE);

            airImg.setImageResource(R.mipmap.icon_humidity);
            airSymbols.setText(R.string.dw_hum);
            airTemp.setText(realTimeData.getData().getHumidity());
        } else if (name.equals("除湿机")) {
            airAir.setVisibility(View.GONE);
            airHumidification.setVisibility(View.GONE);
            airDehumidification.setVisibility(View.VISIBLE);
            airDisinfection.setVisibility(View.GONE);
            airConstanthumidity.setVisibility(View.GONE);

            airImg.setImageResource(R.mipmap.icon_humidity);
            airSymbols.setText(R.string.dw_hum);
            airTemp.setText(realTimeData.getData().getHumidity());
        } else if (name.equals("净化机")) {
            airAir.setVisibility(View.GONE);
            airHumidification.setVisibility(View.GONE);
            airDehumidification.setVisibility(View.GONE);
            airDisinfection.setVisibility(View.VISIBLE);
            airConstanthumidity.setVisibility(View.GONE);

            airImg.setImageResource(R.mipmap.icon_pm25);
            airSymbols.setText(R.string.dw_pm2);
            airTemp.setText(realTimeData.getData().getPm2());
        }else if(name.equals("空调")){
            airAir.setVisibility(View.VISIBLE);
            airHumidification.setVisibility(View.GONE);
            airDehumidification.setVisibility(View.GONE);
            airDisinfection.setVisibility(View.GONE);
            airConstanthumidity.setVisibility(View.GONE);

            airImg.setImageResource(R.mipmap.icon_temperature);
            airSymbols.setText(R.string.dw_temp);
            airTemp.setText(realTimeData.getData().getTemperature());
        }
    }

    @Override
    public void showWareHouse(String warehouse) {

    }

    @Override
    public void onSuccesss(List<AllList> lists) {

    }

    @Override
    public void onFails(String msg) {
        showToast(msg);
    }

    @Override
    public void onSuccessed(String msg) {
        showToast(msg);
    }

    @Override
    public void updateRealTimeData(RealTimeData mData, int storeId) {

    }
}
