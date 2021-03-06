package com.lukechi.android.hellodagger.di;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.di.module.ActivityModule;
import com.lukechi.android.hellodagger.di.module.NetworkModule;
import com.lukechi.android.hellodagger.di.module.TestAppModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

/**
 * AndroidSupportInjectionModule includes AndroidInjectionModule
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        TestAppModule.class,
        ActivityModule.class,
        NetworkModule.class,
})
// public interface TestAppComponent extends AppComponent {
public interface TestAppComponent extends AndroidInjector<HelloApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HelloApp> {
    }
}
