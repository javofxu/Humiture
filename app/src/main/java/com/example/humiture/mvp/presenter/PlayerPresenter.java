package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.PlayerListData;
import com.example.humiture.mvp.contract.PlayerContract;
import com.example.humiture.mvp.model.PlayerModel;
import com.example.humiture.utils.DensityUtils;

import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.SinglePicker;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 *Time:2019/7/8
 *Author:冰冰凉
 *dec:  视频播放
 */
public class PlayerPresenter extends RxPresenter<PlayerContract.View> implements PlayerContract.Presenter {

    private static final String TAG = "PlayerPresenter";
    private PlayerContract.Model model = new PlayerModel();
    private SinglePicker<String> picker;

    /**
     * 视频播放详情
     * @param categoryId
     * @param storeId
     */
    @Override
    public void getPlayerList(String categoryId, String storeId) {
        Observable<PlayerListData> observable = model.getPlayerList(categoryId,storeId);
        observable.subscribe(new Consumer<PlayerListData>() {
            @Override
            public void accept(PlayerListData playerListData) throws Exception {
                Log.i(TAG, "accept: " + playerListData.getStatus());
                if(playerListData.getStatus() == 0){
                    mView.onSuccessed(playerListData.getData());
                }else{
                    mView.onFailed(playerListData.getMsg());
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //错误,显示没有网络界面
            }
        });
    }

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
}
