package com.lukechi.android.hellodagger.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.GasHeater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 提供 被 inject 物件 的實作
 */
@Module
public class AppModule {

//    @Singleton
//    @Provides
//    Application providesApplication(HelloApp application) {
//        return application;
//    }

    @Singleton
    @Provides
    Context providesApplicationContext(HelloApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    @NonNull
    public Heater providesHeater(GasHeater gasHeater) {
        // important: if not self new instance, dagger will auto new and handle following dependencies
        return gasHeater;
    }
}
