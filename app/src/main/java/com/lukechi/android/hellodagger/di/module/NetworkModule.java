package com.lukechi.android.hellodagger.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lukechi.android.hellodagger.util.NetworkUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public abstract class NetworkModule {

    @Singleton
    @Provides
    public static GsonConverterFactory provideGsonConverterFactory() {
        return NetworkUtil.buildGsonConverterFactory();
    }

    @Singleton
    @Provides
    public static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        // what the difference of createWithScheduler ?
        // RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return NetworkUtil.buildOkHttpClient();
    }
}
