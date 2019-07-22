package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.mvp.contract.OperateContract;
import com.example.humiture.mvp.model.OperateModel;
import com.example.humiture.utils.DensityUtils;

import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.SinglePicker;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec:
 */
public class OperatePresent extends RxPresenter<OperateContract.mView> implements OperateContract.present {

    private static final String TAG = "OperatePresent";

    private SinglePicker<String> picker;
    private OperateContract.Model model = new OperateModel();

    @Override
    public void choseWareHouse(Activity mActivity, List<String> wareHouse) {
        picker = new SinglePicker<>(mActivity, wareHouse);
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
        picker.setItemWidth(DensityUtils.getScreenWidth(mActivity) * 5 / 10);
        picker.setBackgroundColor(0xFFFFFFFF);
        picker.setSelectedIndex(wareHouse.size());
        picker.setTitleText("请选择库房");
        picker.setOnItemPickListener((index, item) -> mView.showWareHouse(item));
        picker.show();
    }

    /**
     * 库房列表和状态
     *
     * @param stroreId
     * @return
     */
    @Override
    public void getOperateData(String stroreId) {
        Observable<List<AllList>> observable = model.getOperateData(stroreId);
        observable.subscribe(new Consumer<List<AllList>>() {
            @Override
            public void accept(List<AllList> allLists) throws Exception {
                Log.i(TAG, "accept: " + allLists.size());
                if(allLists.size() > 0){
                    mView.onSuccesss(allLists);
                }else{
                    mView.onFails("获取失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    /**
     * 设备控制
     *
     * @param deviceId
     * @param brandId
     */
    @Override
    public void getOperate(String deviceId, String brandId) {
        Observable<Common> observable = model.getOperate(deviceId, brandId);
        observable.subscribe(new Consumer<Common>() {
            @Override
            public void accept(Common common) throws Exception {
                Log.i(TAG, "accept:设备控制： " + common.getStatus() + "----" + common.getMsg());
                if (common.getStatus() == 0){
                    mView.onSuccessed(common.getMsg());
                }else{
                    mView.onSuccessed(common.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    /**
     * 获取温湿度的实时数据
     * @param storeId
     */
    @Override
    public void getRealTimeData(int storeId) {
        Observable<RealTimeData> observable = model.getRealTimeData(storeId);
        observable.subscribe(new Consumer<RealTimeData>() {
            @Override
            public void accept(RealTimeData realTimeData) throws Exception {
                Log.i(TAG, "getRealTimeData: " + realTimeData.getData().getHumidity() + "---" + storeId);
                mView.updateRealTimeData(realTimeData,storeId);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
//                mView.netWorkError();
            }
        });
    }


}
