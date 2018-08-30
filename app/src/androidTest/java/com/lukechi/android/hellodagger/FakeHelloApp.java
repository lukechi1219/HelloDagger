package com.lukechi.android.hellodagger;

import com.lukechi.android.hellodagger.di.DaggerTestAppComponent;

import dagger.android.AndroidInjector;

// need this to call DaggerTestAppComponent
public class FakeHelloApp extends HelloApp {

    @Override
    protected AndroidInjector<? extends HelloApp> applicationInjector() {
        return DaggerTestAppComponent.builder().create(this);
    }
}

