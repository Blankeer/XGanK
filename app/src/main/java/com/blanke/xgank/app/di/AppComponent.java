package com.blanke.xgank.app.di;

import com.blanke.xgank.api.GanKAPI;
import com.blanke.xgank.api.di.ApiModule;
import com.blanke.xgank.app.GankApplication;

import org.threeten.bp.format.DateTimeFormatter;

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
    GankApplication provideApplication();

    DateTimeFormatter provideDateTimeFormatter();

    GanKAPI provideGanKAPI();
}
