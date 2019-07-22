package com.example.humiture.mvp.contract;

import android.app.Activity;

import com.example.base.BaseView;
import com.example.humiture.data.PlayerListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PlayerContract {
    interface Model {
        //视频播放详情信息获取
        Observable<PlayerListData> getPlayerList(@Header("categoryId") String categoryId, @Query("storeId") String storeId);
    }

    interface View extends BaseView {
        /**
         * 库房显示
         *
         * @param warehouse 库房名称
         */
        void showWareHouse(String warehouse);
        void onSuccessed(List<PlayerListData.Data> list);
        void onFailed(String msg);
    }

    interface Presenter {
        //视频播放详情信息获取
        void getPlayerList(String categoryId,String storeId);
        /**
         * 选择库房
         *
         * @param mActivity 绑定活动
         * @param wareHouse 库房列表
         */
        void choseWareHouse(Activity mActivity, List<String> wareHouse);
    }
}
