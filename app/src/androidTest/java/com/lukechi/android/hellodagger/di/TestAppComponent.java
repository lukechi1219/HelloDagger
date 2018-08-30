package com.lukechi.android.hellodagger.di;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.di.module.ActivityModule;
import com.lukechi.android.hellodagger.di.module.MockAppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, // <- includes = AndroidInjectionModule.class
        ActivityModule.class,
        MockAppModule.class,
})
// need this to bind MockAppModule
public interface TestAppComponent extends AppComponent {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HelloApp> {}
}
