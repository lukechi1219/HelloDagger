package com.lukechi.android.hellodagger.activity;

import android.os.Bundle;
import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.thirdparty.ThirdPartyClass;
import com.lukechi.android.opendata.service.TaipeiOpenDataService;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

/**
 * A class shouldn't t know anything about how it is injected. So we hide inject code into DaggerAppCompatActivity
 */
// DaggerAppCompatActivity
// https://medium.com/@ffvanderlaan/you-could-also-have-your-baseactivity-extend-daggerappcompatactivity-then-you-would-not-need-e5faf54fad4e
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Heater myHeater;

    @Inject
    BazService bazService;

    @Inject
    ThirdPartyClass thirdParty;

    @Inject
    TaipeiOpenDataService todService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // auto injected!!!
        myHeater.heat();
        bazService.work();
        thirdParty.getInfo();

        todService.syncAllAvailableLots();

        /*
          https://developer.android.com/topic/performance/threads
          For this reason, we suggest that you only use AsyncTask to handle work items shorter than 5ms in duration.
         */

        /*
          !!!!!
          https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-25de4e1ada14
          https://proandroiddev.com/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-2-72716e3520
         https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-3-af6eeafeb29b
          https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-4-2b476142e769
          https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-5-8fc4f9cee34d
         */

        /*
         * Cannot access database on the main thread since it may potentially lock the UI for a long periods of time.
         * Room, by default, won’t allow you to run database operations on the main thread.
         * -> .allowMainThreadQueries()
         * Use this approach for testing purposes only, or when dealing with a really tiny database.
         */
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database")
//                .allowMainThreadQueries()
//                .build();
//        UserDatabase db = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user_db").build();
//
//        System.out.println("db: " + db);
//
//        ParkingLot[] parkingLotsArray = db.parkingLotDao().loadAllParkingLot();
//
//        for (ParkingLot parkingLot : parkingLotsArray) {
//            System.out.println(parkingLot.name());
//        }


    }
}
