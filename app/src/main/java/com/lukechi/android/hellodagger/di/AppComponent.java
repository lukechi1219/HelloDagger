package com.lukechi.android.hellodagger.di;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.di.module.ActivityModule;
import com.lukechi.android.hellodagger.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * setup module bindings 設定 Application 要有哪些 modules
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, // <- includes = AndroidInjectionModule.class
        ActivityModule.class,
        AppModule.class,
})
public interface AppComponent extends AndroidInjector<HelloApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HelloApp> {}
}
