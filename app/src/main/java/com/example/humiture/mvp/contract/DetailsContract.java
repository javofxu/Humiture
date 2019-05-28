package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.example.humiture.data.DetailsList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 * Created by 许格.
 * Date on 2019/5/23.
 * dec:
 */
public interface DetailsContract {

    interface model{
        /**
         * 获取后台详细数据
         * @param storeId
         * @param type
         * @param startTime
         * @param endTime
         * @param page
         * @return
         */
        Observable<List<DetailsList.Data>> getDetailsList(@Field("storeId") int storeId, @Field("type") String type,
                                                    @Field("strTime") long startTime, @Field("endTime") long endTime, @Field("page") int page);
    }

    interface mView extends BaseView{
        /**
         * 获取开始时间和结束时间
         * @param time
         */
        void getStartAndEndTime(String time);

        /**
         * 得到数据展示
         * @param data
         */
        void setDetailsList(List<DetailsList.Data> data);

        /**
         * 显示更多
         * @param data
         */
        void showMore(List<DetailsList.Data> data);

        /**
         * 没有更多
         */
        void noMore();

        /**
         * 更多错误
         */
        void errorMore();

        /**
         * 无数据
         */
        void noDetails();

        /**
         * 网络异常
         */
        void netWorkError();
    }

    interface present{
        /**
         * 获取详细数据
         * @param storeId
         * @param type
         * @param startTime
         * @param endTime
         * @param page
         */
        void getDetailsList(int storeId, String type, long startTime, long endTime, int page);

        /**
         * 获取更多（下一页）
         * @param storeId
         * @param type
         * @param startTime
         * @param endTime
         * @param page
         */
        void getMoreList(int storeId, String type, long startTime, long endTime, int page);
    }
}
