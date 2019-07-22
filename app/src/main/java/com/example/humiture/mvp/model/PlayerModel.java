package com.example.humiture.mvp.model;

import com.example.humiture.data.PlayerListData;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.PlayerContract;

import io.reactivex.Observable;

/**
 *Time:2019/7/8
 *Author:冰冰凉
 *dec:  视频播放
 */
public class PlayerModel implements PlayerContract.Model {

    ApiService api = RetrofitClient.create(ApiService.class);

    /**
     * 获取视频播放详情
     * @param categoryId
     * @param storeId
     * @return
     * {
     *     "status": 0,
     *     "msg": "获取库房设备列表成功",
     *     "data": [
     *         {
     *             "port": "8801",
     *             "status": "1",
     *             "remark": "",
     *             "sortOrder": 1,
     *             "categoryId": 5,
     *             "mtime": 1562115536000,
     *             "password": "fxs12345",
     *             "ctime": 1562115536000,
     *             "brandId": null,
     *             "brandName": null,
     *             "ip": "115.231.60.194",
     *             "creator": 1,
     *             "modifier": 1,
     *             "stroreAreaId": 2,
     *             "pId": null,
     *             "categoryName": "视频",
     *             "username": "admin",
     *             "name": "视频1",
     *             "channel": "1",
     *             "storeId": 2,
     *             "deviceId": 78
     *         }
     *     ]
     * }
     */
    @Override
    public Observable<PlayerListData> getPlayerList(String categoryId, String storeId) {
        return api.getPlayerList(categoryId,storeId).map(playerListData -> playerListData).compose(RetrofitClient.schedulersTransformer);
    }
}
