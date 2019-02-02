package com.lukechi.android.hellodagger.di.module;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.core.impl.FakeBazService;
import com.lukechi.android.hellodagger.core.impl.FakeGasHeater;
import com.lukechi.android.hellodagger.thirdparty.ThirdPartyClass;
import com.lukechi.android.opendata.database.AppDatabase;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * provide fake instances for testing
 */
@Module
public abstract class TestAppModule {

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

    @Singleton
    @Binds
    @NonNull
    abstract Heater bindsHeater(FakeGasHeater heater);

    /**
     * AppModule 沒有 providesBazService() 是 Dagger 自動 inject , 但是為了測試, 我們可以在這邊指定 dependency .
     */
    @Singleton
    @Binds
    @NonNull
    abstract BazService bindsBazService(FakeBazService bazService);

    /**
     * 如果是第 3 方 lib , 無法加上 @Inject , 就要在這邊 new 或是產生 instance
     */
    @Singleton
    @Provides
    static ThirdPartyClass provideThirdParty(Context context) {
        return new ThirdPartyClass(context);
    }

    @Singleton
    @Provides
    static ParkingLotDao provideParkingLotDao(@Named("ApplicationContext") Context appContext) {
        AppDatabase database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        return database.parkingLotDao();
    }
}
