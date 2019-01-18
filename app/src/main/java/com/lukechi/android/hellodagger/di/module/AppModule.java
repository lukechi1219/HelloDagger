package com.lukechi.android.hellodagger.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.GasHeater;
import com.lukechi.android.hellodagger.thirdparty.ThirdPartyClass;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * {@link ..AppComponent}.
 * <p>
 * 提供 被 inject 物件 的實作
 * <p>
 * https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/di/ApplicationModule.java
 *
 * ref: https://stackoverflow.com/questions/46618763/dagger2-how-to-use-provides-and-binds-in-same-module
 *
 * @Binds and @ContributesAndroidInjector methods must be abstract, because they don't have method bodies.
 * That means that they must go on an interface or abstract class.
 * @Provides methods may be static, which means they can go on abstract classes and Java-8-compiled interfaces,
 * but non-static ("instance") @Provides methods don't work on abstract classes. This is explicitly listed in the Dagger FAQ,
 * under the sections "Why can’t @Binds and instance @Provides methods go in the same module?" and "What do I do instead?".
 *
 * ref: https://google.github.io/dagger/faq.html#why-cant-binds-and-instance-provides-methods-go-in-the-same-module
 *
 * <p>
 * If your @Provides method doesn't use instance state, you can mark it static, and it can go onto an abstract class adjacent to your @Binds methods.
 * If not, consider putting the bindings like @Binds and @ContributesAndroidInjector into a separate class
 * -- possibly a static nested class -- and including that using the includes attribute on Dagger's @Module annotation.
 */
@Module
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Application bindsApplication(HelloApp application);

    @Singleton
    @Provides
    @Named("ApplicationContext")
    static Context providesApplicationContext(HelloApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Binds
    abstract Context bindsContext(HelloApp application);

    /**
     * 如果是 impl 轉 interface 才要特別寫 provid or bind
     * interface provide( impl ) or interface bind( impl )
     *
     * important: if not self new instance, dagger will auto new and handle following dependencies
     */
//    @Singleton @Provides @NonNull
//    static Heater providesHeater(GasHeater gasHeater) { return gasHeater; }
    @Singleton
    @Binds
    @NonNull
    abstract Heater bindsHeater(GasHeater gasHeater);

//    /**
//     * 如果 FooConfig 是 lib class, 就要在這邊 new, 否則不用在這邊寫 new,
//     * 在 FooConfig 加上 @Inject Constructor 就好, 但是變成這邊看不出來 dependency 關係
//     * 同理 , BazService 也不用在這邊 new , Dagger 會自動 new
//     */
//    @Singleton @Provides @NonNull
//    FooConfig providesFoo() {
//        return new FooConfig();
//    }

    /**
     * 如果是第 3 方 lib , 無法加上 @Inject , 就要在這邊 new 或是產生 instance
     */
    @Singleton
    @Provides
    static ThirdPartyClass provideThirdParty(Context context) {
        return new ThirdPartyClass(context);
    }
}
