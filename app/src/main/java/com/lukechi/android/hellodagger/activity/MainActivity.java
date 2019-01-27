package com.lukechi.android.hellodagger.activity;

import android.os.Bundle;
import androidx.room.Room;
import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.thirdparty.ThirdPartyClass;
import com.lukechi.android.opendata.database.AppDatabase;
import com.lukechi.android.opendata.database.entity.ParkingLot;
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

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        ParkingLot[] parkingLotsArray = db.parkingLotDao().loadAllParkingLot();

        for (ParkingLot parkingLot : parkingLotsArray) {
            System.out.println(parkingLot.name());
        }
    }
}
