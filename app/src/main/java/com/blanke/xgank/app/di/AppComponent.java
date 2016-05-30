package com.blanke.xgank.app.di;

import com.blanke.xgank.api.di.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by blanke on 16-5-30.
 */
@Singleton
@Component(modules = {AppModule.class,
        ApiModule.class,
        ConfigModule.class})
public interface AppComponent {

}
