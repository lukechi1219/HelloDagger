package com.lukechi.android.hellodagger.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.GasHeater;
import com.lukechi.android.hellodagger.core.impl.MockGasHeater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockAppModule {

    @Singleton
    @Provides
    protected Context providesApplicationContext(HelloApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    @NonNull
    protected Heater providesHeater(MockGasHeater mockGasHeater) {
        // important: if not self new instance, dagger will auto new and handle following dependencies
        return mockGasHeater;
    }
}
