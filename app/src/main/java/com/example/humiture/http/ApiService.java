package com.example.humiture.http;

import com.example.humiture.data.Alarm;
import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.DetailsList;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.LoginData;
import com.example.humiture.data.MessageData;
import com.example.humiture.data.PlayerListData;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.data.StaticAlarmList;
import com.example.humiture.data.TrendData;
import com.example.humiture.data.Warehouse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 许格.
 * Date on 2019/5/14.
 * dec:
 */
public interface ApiService {

    String BASE_URL = "/bis_hwhs/api/device";

    String GET_WAREHOUSE = BASE_URL + "/getWmsStore";
    String REAL_TIME = BASE_URL + "/getNewHum";
    String GET_TREND = BASE_URL + "/getEvnFormDateType";
    String GET_DETAILS = BASE_URL + "/getDateByTime";
    String LOGIN = "/bis_hwhs/api/admin/login";     //登录
    String REGISTER = "/bis_hwhs/api/admin/reg";    //注册
    String MINE_SET_SAFE = "/bis_hwhs/api/admin/changepwd";     //修改密码
    String MINE_MESSAGE_ALARM = "/bis_hwhs/api/alarm/selectAllAlarmlist";   //我的消息 报警信息
    String STATIC_ALARMLIST = "/bis_hwhs/api/alarm/getAlarmSataList";       //报警统计 更多
    String STATIC_ALARM = "/bis_hwhs/api/alarm/getAlarmSata";               //报警统计首页
    String KUFANG_SET = "/bis_hwhs/api/device/updateHcsLimit";              //库房设置
    String OPERATE_LIST = "/bis_hwhs/api/device/selectDeviceStutas";        //设备列表和状态
    String OPERATE_CONTROL = "/bis_hwhs/api/device/deviceStatusChenge";     //设备控制
    String PLAYER_LIST = "/bis_hwhs/api/device/getStoreDeviceList";         //摄像头列表

    /**
     * 获取库房
     *
     * @return
     */
    @POST(GET_WAREHOUSE)
    Observable<Warehouse> getWarehouse();

    /**
     * 获取首页实时数据
     *
     * @param storeId 库房ID
     * @return
     */
    @FormUrlEncoded
    @POST(REAL_TIME)
    Observable<RealTimeData> getRealTimeData(@Field("storeId") int storeId);

    /**
     * 获取库房数据趋势
     *
     * @param time    时间
     * @param type    数据类型（温度、湿度）
     * @param storeId 库房ID
     * @return
     */
    @FormUrlEncoded
    @POST(GET_TREND)
    Observable<TrendData> getTrendData(@Field("strTime") String time, @Field("type") String type, @Field("storeId") int storeId);

    /**
     * 获取库房详细数据
     *
     * @param storeId   库房ID
     * @param type      数据类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param page      分页页码
     * @return
     */
    @FormUrlEncoded
    @POST(GET_DETAILS)
    Observable<DetailsList> getDetailsList(@Field("storeId") int storeId, @Field("type") String type, @Field("strTime") long startTime, @Field("endTime") long endTime, @Field("page") int page);

    /**
     * 登录的实现
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginData> getLogin(@Field("username") String username, @Field("password") String password);

    /**
     * 注册的实现
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 确认密码
     * @return
     */
    @FormUrlEncoded
    @POST(REGISTER)
    Observable<Common> getRegister(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(MINE_SET_SAFE)
    Observable<Common> getChangePwd(@Field("userId") int userId, @Field("password") String password);

    /**
     * 我的消息  报警信息列表
     *
     * @param strtime
     * @return
     */
    @FormUrlEncoded
    @POST(MINE_MESSAGE_ALARM)
    Observable<MessageData> getAlarmMessage(@Field("strtime") String strtime);

    /**
     * 报警统计 更多
     *
     * @param type
     * @param date
     * @param storeId
     * @return
     */
    @FormUrlEncoded
    @POST(STATIC_ALARMLIST)
    Observable<Alarm> getStaticAlarmList(@Field("type") String type, @Field("date") String date, @Field("storeId") String storeId);

    /**
     * 报警统计  首页
     *
     * @param type
     * @param date
     * @param storeId
     * @return
     */
    @FormUrlEncoded
    @POST(STATIC_ALARM)
    Observable<StaticAlarmList> getStaticAlarm(@Field("type") String type, @Field("date") String date, @Field("storeId") String storeId);

    /**
     * 库房环境设置
     *
     * @param storeId
     * @param humUp
     * @param humDown
     * @param temUp
     * @param temDown
     * @param pm2Up
     * @param tvocUp
     * @return
     */
    @FormUrlEncoded
    @POST(KUFANG_SET)
    /*Observable<AllList> getKuFangData(@Field("storeId") String storeId, @Field("humUp") String humUp,
                                      @Field("humDown") String humDown, @Field("temUp") String temUp,
                                      @Field("temDown") String temDown, @Field("pm2Up") String pm2Up,
                                      @Field("tvocUp") String tvocUp);*/
    Observable<Common> getKuFangData(@Field("storeId") String storeId, @Field("humUp") String humUp,
                                     @Field("humDown") String humDown, @Field("temUp") String temUp,
                                     @Field("temDown") String temDown, @Field("pm2Up") String pm2Up,
                                     @Field("tvocUp") String tvocUp);

    /**
     * 库房设备列表和状态
     *
     * @return
     */
    @FormUrlEncoded
    @POST(OPERATE_LIST)
    Observable<KuFangData> getOperateData(@Field("stroreId") String stroreId);

    /**
     * 设备控制
     * 0停止 1打开 2制热 3制冷 4增湿 5除湿 6净化 7消毒 8负离子 9增湿负离子 10除湿负离子
     *
     * @param deviceId
     * @param brandId
     * @return
     */
    @FormUrlEncoded
    @POST(OPERATE_CONTROL)
    Observable<Common> getOperate(@Field("deviceId") String deviceId, @Field("brandId") String brandId);

    /**
     * 获取摄像头
     * @param categoryId
     * @param storeId
     * @return
     */
    @GET(PLAYER_LIST)
    Observable<PlayerListData> getPlayerList(@Query("categoryId") String categoryId, @Query("storeId") String storeId);

}
