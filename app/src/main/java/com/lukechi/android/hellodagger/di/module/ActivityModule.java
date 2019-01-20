package com.lukechi.android.hellodagger.di.module;

import com.lukechi.android.hellodagger.activity.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * A class shouldn't t know anything about how it is injected.
 */
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    // @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();

//    @ActivityScoped
//    @ContributesAndroidInjector
//    abstract DisplayMessageActivity contributeDisplayMessageActivity();
}
