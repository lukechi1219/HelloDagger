package com.lukechi.android.hellodagger;

import android.util.Log;
import com.lukechi.android.hellodagger.di.DaggerAppComponent;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.android.schedulers.AndroidSchedulers;

import javax.inject.Inject;
import java.util.List;

// DaggerApplication
// implements HasActivityInjector
// https://android.jlelse.eu/new-android-injector-with-dagger-2-part-3-fe3924df6a89
// https://github.com/SamYStudiO/beaver
// https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-25de4e1ada14
/**
 * 幾乎不需要異動 可以直接用在不同專案
 *
 * A class shouldn't t know anything about how it is injected.
 */
public class HelloApp extends DaggerApplication {

    @Inject
    ParkingLotDao parkingLotDao;

    /**
     * connects HelloApp with (Dagger) AppComponent
     */
    @Override
    protected AndroidInjector<? extends HelloApp> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        loadAppInfoFromDb();
    }

    private void loadAppInfoFromDb() {

        List<ParkingLot> result = parkingLotDao.loadAllParkingLot()
                .doOnError(tr -> Log.e("REPOSITORY DB *** ", tr.getMessage(), tr))
                .doOnSuccess(list -> Log.e("REPOSITORY DB *** ", String.valueOf(list.size())))
                .blockingGet();

        System.out.println("loadAppInfoFromDb: " + result.size());
    }
}
