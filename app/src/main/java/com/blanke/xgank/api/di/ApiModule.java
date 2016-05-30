package com.blanke.xgank.api.di;

import com.blanke.xgank.api.GanKAPI;
import com.blanke.xgank.config.ProjectConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by blanke on 16-5-30.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson(DateTimeFormatter dateTimeFormatter) {
        return new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class,
                        (JsonDeserializer<ZonedDateTime>) (json, typeOfT, context) -> {
                            return ZonedDateTime.parse(
                                    json.getAsJsonPrimitive().getAsString()
                                    , dateTimeFormatter);
                        })
                .registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                .create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ProjectConfig.getBaseApiUrl())
                .addConverterFactory(
                        GsonConverterFactory.create(gson)
                )
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public GanKAPI provideGanKAPI(Retrofit retrofit) {
        return retrofit.create(GanKAPI.class);
    }
}
