package com.lukechi.android.hellodagger.di.module;

import com.lukechi.android.hellodagger.util.NetworkUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public abstract class NetworkModule {

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return NetworkUtil.buildOkHttpClient();
    }

    @Singleton
    @Provides
    @Named("OkHttpClientForXml")
    public static OkHttpClient provideOkHttpClientForXml() {
        return NetworkUtil.buildOkHttpClientForXml();
    }
}
