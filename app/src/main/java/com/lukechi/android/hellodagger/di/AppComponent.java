package com.lukechi.android.hellodagger.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import com.lukechi.android.hellodagger.di.module.ActivityModule;
import com.lukechi.android.hellodagger.di.module.AppModule;

/**
 * setup module bindings 設定 Application 要有哪些 modules
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityModule.class,
        AppModule.class,
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
    // binds our Application
    void inject(Application application);
}
