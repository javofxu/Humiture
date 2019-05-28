package com.example.humiture.mvp.presenter;

import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.DetailsList;
import com.example.humiture.mvp.contract.DetailsContract;
import com.example.humiture.mvp.model.DetailsModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 许格.
 * Date on 2019/5/23.
 * dec:
 */
public class DetailsPresent extends RxPresenter<DetailsContract.mView> implements DetailsContract.present {

    private DetailsContract.model mModel = new DetailsModel();

    @Override
    public void getDetailsList(int storeId, String type, long startTime, long endTime, int page) {
        Observable<List<DetailsList.Data>> mData = mModel.getDetailsList(storeId, type, startTime, endTime, page);
        mData.subscribe(data -> {
            if (data.size()>0){
                mView.setDetailsList(data);
            }else {
                mView.noDetails();
            }
        }, throwable -> {
            Log.d("AAA", "getDetailsList: "+throwable.toString());
            mView.netWorkError();
        });
    }

    @Override
    public void getMoreList(int storeId, String type, long startTime, long endTime, int page) {
        Observable<List<DetailsList.Data>> mData = mModel.getDetailsList(storeId, type, startTime, endTime, page);
        mData.subscribe(data -> {
            if (data.size()>0){
                mView.showMore(data);
            }else {
                mView.noDetails();
            }
        }, throwable -> mView.netWorkError());
    }
}
