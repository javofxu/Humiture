package com.example.humiture.app;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.amitshekhar.DebugDB;
import com.example.humiture.greenDao.DaoMaster;
import com.example.humiture.greenDao.DaoSession;
import com.example.humiture.service.MyService;
import com.example.humiture.utils.helper.GreenDaoHelp;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by 许格.
 * Date on 2019/5/14.
 * dec:
 */
public class App extends Application{

    private static final String TAG = App.class.getSimpleName();
    private static App instance;

    public static App getInstance(){
        if (instance ==null){
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        GreenDaoHelp.getInstance(this).initGreenDao(this);
        Log.i(TAG, "onCreate: " + DebugDB.getAddressLog());
        RxJavaPlugins.setErrorHandler(throwable -> Log.e(TAG, "onCreate: 检查网络" ));
    }

}
