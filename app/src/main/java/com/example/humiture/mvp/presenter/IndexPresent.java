package com.example.humiture.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.base.rx.RxPresenter;
import com.example.base.rx.RxTimerUtil;
import com.example.humiture.R;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.data.TrendData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.mvp.contract.IndexContract;
import com.example.humiture.mvp.model.IndexModel;
import com.example.humiture.utils.DensityUtils;
import com.example.humiture.utils.LineChartManager;
import com.example.humiture.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.SinglePicker;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.example.humiture.utils.ToastUtils.showToast;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec: 首页业务逻辑
 */
public class IndexPresent extends RxPresenter<IndexContract.mView> implements IndexContract.present {

    private static final String TAG = IndexPresent.class.getSimpleName();
    private Context mContext;
    private ImageView[] mImageViews;
    private SinglePicker<String> picker;
    private IndexContract.model mModel = new IndexModel();

    public IndexPresent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getWareHouse() {
        Observable<List<Warehouse.Data>> observable = mModel.getWarehouse();
        observable.subscribe(data -> {
            Log.d(TAG, "getWareHouse: "+data.size());
            List<Integer> storeId = new ArrayList<>();
            List<String> wareHouse = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                storeId.add(data.get(i).getStoreId());
                wareHouse.add(data.get(i).getName());
            }
            mView.getWareHouse(storeId,wareHouse);
        }, throwable -> mView.netWorkError());
    }

    @Override
    public void getRealTimeData(int storeId) {
        Observable<RealTimeData> observable = mModel.getRealTimeData(storeId);
        observable.subscribe(new Consumer<RealTimeData>() {
            @Override
            public void accept(RealTimeData realTimeData) throws Exception {
                Log.d(TAG, "getRealTimeData: " + realTimeData.getData().getHumidity());
                mView.updateRealTimeData(realTimeData);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.netWorkError();
            }
        });
    }

    @Override
    public void timingData(int time, int storeId) {
        RxTimerUtil.interval(time, () -> getRealTimeData(storeId));
    }

    @Override
    public void getTrendData(String today, String yesterday, String type, int storeId) {
        Observable<List<TrendData.Data>> observable = mModel.getTrendData(today,type,storeId);
        Observable<List<TrendData.Data>> observable1 = mModel.getTrendData(yesterday,type,storeId);
        observable.subscribe(data -> {
            observable1.subscribe(data1 -> {
                mView.showTrendData(data,data1);
            });
        }, throwable -> {
            Log.d(TAG, "getTrendData: BBB");
            mView.netWorkError();
        });
    }


    @Override
    public void drawPoint(LinearLayout layout, int pagerNumber,int position) {
        layout.removeAllViews();
        mImageViews = new ImageView[pagerNumber];
        for (int i = 0; i < pagerNumber; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params);
            if(i==position){
                imageView.setImageResource(R.mipmap.index_yuan_sel);
            }
            else {
                imageView.setImageResource(R.mipmap.index_yuan);
            }
            mImageViews[i]=imageView;
            layout.addView(imageView);
        }
    }

    @Override
    public void designation(List<String> name, int type) {
        picker = new SinglePicker<>((Activity) mContext,name);
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
        picker.setItemWidth(DensityUtils.getScreenWidth(mContext)*5/10);
        picker.setBackgroundColor(0xFFFFFFFF);
        picker.setSelectedIndex(name.size());
        if (type == 1){
            picker.setTitleText("请选择数据类型");
        }else if (type == 2){
            picker.setTitleText("请选择库房");
        }
        picker.setOnItemPickListener((index, item) -> {
            if (name.size() == 8){
                mView.showDataType(index, item);
            }else {
                mView.showWareHouse(index,item);
            }
        });
        picker.show();
    }

    @Override
    public void showLineChart(LineChartManager manager, ArrayList<Integer> xValues, List<Float> toadyValues, List<Float> yesterdayValues, int type) {
        manager.showLineChart(xValues, toadyValues, yesterdayValues, type,false);
        manager.setYAxis(60, 10, 6);
    }
}
