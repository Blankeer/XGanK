package com.blanke.xgank.app;

import android.app.Application;

import com.blanke.xgank.api.di.ApiModule;
import com.blanke.xgank.app.di.AppComponent;
import com.blanke.xgank.app.di.AppModule;
import com.blanke.xgank.app.di.ConfigModule;
import com.blanke.xgank.app.di.DaggerAppComponent;

/**
 * Created by blanke on 16-5-27.
 */
public class GankApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .configModule(new ConfigModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
