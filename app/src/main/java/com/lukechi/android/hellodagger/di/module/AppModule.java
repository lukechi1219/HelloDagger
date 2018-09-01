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
public final class AppModule {

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

    /**
     * 如果是 impl 轉 interface 才要特別寫 interface provides( impl )
     */
    @Singleton
    @Provides
    @NonNull
    Heater providesHeater(GasHeater gasHeater) {
        // important: if not self new instance, dagger will auto new and handle following dependencies
        return gasHeater;
    }

    /**
     * 如果 FooConfig 是 lib class, 就要在這邊 new, 否則不用在這邊寫, 在 FooConfig 加上 @Inject 就好, 但是變成這邊看不出來 dependency 關係
     */
//    @Singleton
//    @Provides
//    @NonNull
//    FooConfig providesFoo() {
//        return new FooConfig();
//    }

}
