package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.NumberData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.mvp.contract.MineKuFangContract;
import com.example.humiture.mvp.model.MineKuFangModel;
import com.example.humiture.utils.helper.GreenDaoHelp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 *Time:2019/6/19
 *Author:冰冰凉
 *dec: 库房环境设置
 */
public class MineKuFangPresenter extends RxPresenter<MineKuFangContract.View> implements MineKuFangContract.Presenter {

    private static final String TAG = "MineKuFangPresenter";

    private Context mContext;
    private MineKuFangContract.Model model = new MineKuFangModel();

    public MineKuFangPresenter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取库房列表
     */
    @Override
    public void getWareHouse() {
        Observable<List<Warehouse.Data>> observable = model.getWarehouse();
        observable.subscribe(data -> {
            Log.d(TAG, "getWareHouse: "+data.size());
            if (data.size() > 0){
                mView.getWareHouse(data);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
//                mView.netWorkError();
            }
        });
    }

    /**
     * 库房环境设置
     * @param storeId
     * @param humUp
     * @param humDown
     * @param temUp
     * @param temDown
     * @param pm2Up
     * @param tvocUp
     */
    @Override
    public void getKuFangData(String storeId, String humUp, String humDown, String temUp, String temDown, String pm2Up, String tvocUp) {
        Observable<Common> observable = model.getKuFangData(storeId,humUp,humDown,temUp,temDown,pm2Up,tvocUp);
        observable.subscribe(kuFangData -> {
            Log.i(TAG, "accept: " + kuFangData.getStatus());
            if(kuFangData.getStatus() == 0){
                //成功
//                mView.onSuccess();
                mView.onFail(kuFangData.getMsg());
            }else{
                //失败
                mView.onSuccess();
//                mView.onFail(kuFangData.getMsg());
            }
        }, throwable -> {
            //错误
        });
    }

    /**
     * 加载数据
     * @param list
     */
    @Override
    public void show(List<KuFangSetData> list, String name, TextView up_wendu,TextView down_wendu,
                     TextView up_shidu,TextView down_shidu,TextView up_pm,TextView down_pm,
                     TextView up_tvoc,TextView down_tvoc,TextView up_junluo,TextView down_junluo,
                     TextView up_jiaquan,TextView down_jiaquan,TextView up_eoc2,TextView down_eoc2,
                     TextView up_gas,TextView down_gas) {
        list = GreenDaoHelp.getInstance(mContext).getStorId(name);
        for (int i = 0; i < list.size(); i++) {
            up_wendu.setText(list.get(i).getUp_WD());
            down_wendu.setText(list.get(i).getDown_WD());
            up_shidu.setText(list.get(i).getUp_SD());
            down_shidu.setText(list.get(i).getDown_SD());
            up_pm.setText(list.get(i).getUp_PM());
            down_pm.setText(list.get(i).getDown_PM());
            up_tvoc.setText(list.get(i).getUp_TVOC());
            down_tvoc.setText(list.get(i).getDown_TVOC());
            up_junluo.setText(list.get(i).getUp_JL());
            down_junluo.setText(list.get(i).getDown_JL());
            up_jiaquan.setText(list.get(i).getUp_JQ());
            down_jiaquan.setText(list.get(i).getDown_JQ());
            up_eoc2.setText(list.get(i).getUp_EOC2());
            down_eoc2.setText(list.get(i).getDown_EOC2());
            up_gas.setText(list.get(i).getUp_GAS());
            down_gas.setText(list.get(i).getDown_GAS());
        }
    }

    /**
     * 初始化选择器的数字
     */
    @Override
    public void getWenDuData(List<NumberData> numberData) {
        for(int i = 1;i<=100;i++){
            numberData.add(new NumberData(i,i+""));
        }

        for(int i =0;i<numberData.size();i++){
            if(numberData.get(i).getNumber().length() > 0){
                String num = numberData.get(i).getNumber();
                numberData.get(i).setNumber(num);
            }
        }
    }
}
