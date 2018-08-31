package com.lukechi.android.hellodagger.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
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
    Heater providesHeater(FakeGasHeater mockGasHeater) {
        // important: if not self new instance, dagger will auto new and handle following dependencies
        return mockGasHeater;
    }
}
