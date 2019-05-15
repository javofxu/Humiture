package com.example.humiture.app;

import android.app.Application;
import android.util.Log;

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
        RxJavaPlugins.setErrorHandler(throwable -> Log.e(TAG, "onCreate: 检查网络" ));
    }
}
