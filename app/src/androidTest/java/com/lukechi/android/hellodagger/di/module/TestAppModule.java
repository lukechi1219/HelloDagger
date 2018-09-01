package com.lukechi.android.hellodagger.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.core.impl.FakeBazService;
import com.lukechi.android.hellodagger.core.impl.FakeGasHeater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * provide fake instances for testing
 */
@Module
public final class TestAppModule {

    @Singleton
    @Provides
    Context providesApplicationContext(HelloApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    @NonNull
    Heater providesHeater(FakeGasHeater fakeGasHeater) {
        // important: if not self new instance, dagger will auto new and handle following dependencies
        return fakeGasHeater;
    }

    /**
     * AppModule 沒有 providesBazService() 是 Dagger 自動 inject , 但是為了測試, 我們可以在這邊指定 dependency .
     */
    @Singleton
    @Provides
    @NonNull
    BazService providesBazService(FakeBazService fakeBazService) {
        return fakeBazService;
    }
}
