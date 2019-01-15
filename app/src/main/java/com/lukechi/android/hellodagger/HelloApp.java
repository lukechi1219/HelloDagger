package com.lukechi.android.hellodagger;

import com.lukechi.android.hellodagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

// DaggerApplication
// implements HasActivityInjector
// https://android.jlelse.eu/new-android-injector-with-dagger-2-part-3-fe3924df6a89
// https://github.com/SamYStudiO/beaver
/**
 * 幾乎不需要異動 可以直接用在不同專案
 *
 * A class shouldn’t know anything about how it is injected.
 */
public class HelloApp extends DaggerApplication {

    /**
     * connects HelloApp with (Dagger) AppComponent
     */
    @Override
    protected AndroidInjector<? extends HelloApp> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
