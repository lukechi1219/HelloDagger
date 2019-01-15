package com.lukechi.android.hellodagger;

import com.lukechi.android.hellodagger.di.DaggerTestAppComponent;

import dagger.android.AndroidInjector;

/**
 * 幾乎不需要異動 可以直接用在不同專案
 *
 * A class shouldn’t know anything about how it is injected.
 */
public class FakeHelloApp extends HelloApp {

    /**
     * connects FakeHelloApp with (Dagger) TestAppComponent
     */
    @Override
    protected AndroidInjector<? extends HelloApp> applicationInjector() {
        return DaggerTestAppComponent.builder().create(this);
    }
}

