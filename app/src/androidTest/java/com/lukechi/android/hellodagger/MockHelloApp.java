package com.lukechi.android.hellodagger;

import com.lukechi.android.hellodagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MockHelloApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends HelloApp> applicationInjector() {
        return DaggerTestAppComponent.builder().create(this);
    }
}

