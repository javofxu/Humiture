package com.example.humiture.http;

import com.example.humiture.data.DetailsList;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.data.TrendData;
import com.example.humiture.data.Warehouse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 许格.
 * Date on 2019/5/14.
 * dec:
 */
public interface ApiService {

    String BASE_URL= "/bis_hwhs/api/device";

    String GET_WAREHOUSE = BASE_URL+"/getWmsStore";
    String REAL_TIME = BASE_URL+"/getNewHum";
    String GET_TREND = BASE_URL+"/getEvnFormDateType";
    String GET_DETAILS = BASE_URL+"/getDateByTime";
    /**
     * 获取库房
     * @return
     */
    @POST(GET_WAREHOUSE)
    Observable<Warehouse> getWarehouse();

    /**
     * 获取首页实时数据
     * @param storeId 库房ID
     * @return
     */
    @FormUrlEncoded
    @POST(REAL_TIME)
    Observable<RealTimeData> getRealTimeData(@Field("storeId") int storeId);

    /**
     * 获取库房数据趋势
     * @param time 时间
     * @param type 数据类型（温度、湿度）
     * @param storeId 库房ID
     * @return
     */
    @FormUrlEncoded
    @POST(GET_TREND)
    Observable<TrendData> getTrendData(@Field("strTime") String time, @Field("type") String type, @Field("storeId") int storeId);

    /**
     * 获取库房详细数据
     * @param storeId 库房ID
     * @param type 数据类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param page 分页页码
     * @return
     */
    @FormUrlEncoded
    @POST(GET_DETAILS)
    Observable<DetailsList> getDetailsList(@Field("storeId") int storeId, @Field("type") String type, @Field("strTime") long startTime, @Field("endTime") long endTime, @Field("page") int page);
}
