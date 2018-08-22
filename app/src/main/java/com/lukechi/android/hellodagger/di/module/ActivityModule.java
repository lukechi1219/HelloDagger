package com.lukechi.android.hellodagger.di.module;

import com.lukechi.android.hellodagger.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

//    @ContributesAndroidInjector
//    abstract DisplayMessageActivity contributeDisplayMessageActivity();
}
