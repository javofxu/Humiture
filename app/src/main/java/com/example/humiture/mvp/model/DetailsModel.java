package com.example.humiture.mvp.model;

import com.example.humiture.data.DetailsList;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.DetailsContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by 许格.
 * Date on 2019/5/23.
 * dec:
 */
public class DetailsModel implements DetailsContract.model {

    ApiService mApi = RetrofitClient.create(ApiService.class);

    @Override
    public Observable<List<DetailsList.Data>> getDetailsList(int storeId, String type, long startTime, long endTime, int page) {
        return mApi.getDetailsList(storeId, type, startTime, endTime, page).map(new Function<DetailsList, List<DetailsList.Data>>() {
            @Override
            public List<DetailsList.Data> apply(DetailsList detailsList) throws Exception {
                return detailsList.getData();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
