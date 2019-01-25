package com.lukechi.android.hellodagger.di;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.di.module.ActivityModule;
import com.lukechi.android.hellodagger.di.module.AppModule;
import com.lukechi.android.hellodagger.di.module.NetworkModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

/**
 * setup module bindings 設定 Application 要有哪些 modules
 * <p>
 * https://proandroiddev.com/mvp-with-dagger-2-11-847d52c27c5a
 * <p>
 * AndroidSupportInjectionModule includes AndroidInjectionModule
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        NetworkModule.class,
})
public interface AppComponent extends AndroidInjector<HelloApp> {

    /**
     * ref: https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/di/AppComponent.java
     * why need "TasksRepository getTasksRepository()" ?
     * for test ?
     */
//    Heater getHeater(); // 可寫 可不寫?
//    BazService getBazService(); // 可寫 可不寫?

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HelloApp> {
    }
}
