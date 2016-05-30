package com.blanke.xgank.app.di;

import com.blanke.xgank.utils.GankDateTimeForMatter;

import org.threeten.bp.format.DateTimeFormatter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by blanke on 16-5-30.
 */
@Module
public class ConfigModule {

    @Provides
    @Singleton
    public DateTimeFormatter provideDateTimeFormatter() {
        return GankDateTimeForMatter.getmDateTimeFormatter();
    }

}
