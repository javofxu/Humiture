package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.graphics.Color;

import com.example.base.rx.RxPresenter;
import com.example.humiture.mvp.contract.OperateContract;
import com.example.humiture.utils.DensityUtils;

import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec:
 */
public class OperatePresent extends RxPresenter<OperateContract.mView> implements OperateContract.present {

    private SinglePicker<String> picker;

    @Override
    public void choseWareHouse(Activity mActivity, List<String> wareHouse) {
        picker = new SinglePicker<>(mActivity,wareHouse);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(40);
        picker.setTitleTextColor(0xFF000000);
        picker.setTitleTextSize(16);
        picker.setCancelTextColor(0xFF999999);
        picker.setCancelTextSize(14);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(14);
        picker.setSelectedTextColor(0xFF33B5E5);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.GRAY);//线颜色
        config.setAlpha(120);//线透明度
        picker.setLineConfig(config);
        picker.setItemWidth(DensityUtils.getScreenWidth(mActivity)*5/10);
        picker.setBackgroundColor(0xFFFFFFFF);
        picker.setSelectedIndex(wareHouse.size());
        picker.setTitleText("请选择库房");
        picker.setOnItemPickListener((index, item) -> mView.showWareHouse(item));
        picker.show();
    }
}
