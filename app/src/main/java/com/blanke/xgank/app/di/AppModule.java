package com.blanke.xgank.app.di;

import com.blanke.xgank.app.GankApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by blanke on 16-5-30.
 */
@Module
public class AppModule {
    private GankApplication gankApplication;

    public AppModule(GankApplication gankApplication) {
        this.gankApplication = gankApplication;
    }

    @Provides
    @Singleton
    public GankApplication provideApplication() {
        return gankApplication;
    }
}
