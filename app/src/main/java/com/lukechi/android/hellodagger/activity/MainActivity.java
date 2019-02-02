package com.lukechi.android.hellodagger.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.thirdparty.ThirdPartyClass;
import com.lukechi.android.hellodagger.ui.viewmodel.ParkingLotsViewModel;
import com.lukechi.android.hellodagger.ui.viewmodel.ParkingLotsViewModelFactory;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
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

    @Inject
    ParkingLotDao parkingLotDao;

    @Inject
    ParkingLotsViewModelFactory parkingLotsViewModelFactory;

    // lateinit
    ParkingLotsViewModel parkingLotsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hello_world_textview = findViewById(R.id.hello_world_textview);

        // auto injected!!!
        myHeater.heat();
        bazService.work();
        thirdParty.getInfo();

//        ParkingLot parkingLotNew = new ParkingLot(3, 3, "area", "name1");
//        // OnConflictStrategy.REPLACE87
//        parkingLotDao.insertParkingLots(parkingLotNew);
//
//        ParkingLot parkingLotNew2 = new ParkingLot(3, 3, "area", "name2");
//        // OnConflictStrategy.REPLACE87
//        parkingLotDao.insertParkingLots(parkingLotNew2); // will replace old row with same rowid

        /*
         */
        parkingLotsViewModel = ViewModelProviders
                .of(this, parkingLotsViewModelFactory)
                .get(ParkingLotsViewModel.class);

        parkingLotsViewModel.loadParkingLots(10, 0);

        parkingLotsViewModel.parkingLotsResult().observe(this,
                parkingLots -> {
                    if (parkingLots.isEmpty()) {
                        hello_world_textview.setText("Hello no parkingLots");
                    } else {
                        String name = parkingLots.get(0).name();
                        String area = parkingLots.get(0).area();
                        hello_world_textview.setText("Hello " + name + " of " + area + " of " + parkingLots.size() + " parkingLots!");
                    }
                });

        parkingLotsViewModel.parkingLotssError().observe(this,
                errorStr -> hello_world_textview.setText("Hello error " + errorStr));

        // TODO
        showNearByAvailableLots();

        /*
          https://developer.android.com/topic/performance/threads
          For this reason, we suggest that you only use AsyncTask to handle work items shorter than 5ms in duration.
         */

        /*
          done
          https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-4-2b476142e769
          TODO
          https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-5-8fc4f9cee34d
         */
    }

    @Override
    protected void onDestroy() {
        parkingLotsViewModel.disposeElements();
        super.onDestroy();
    }

    /*
     */
    private void showNearByAvailableLots() {

        getAllAvailableLots();
    }

    private void getAllAvailableLots() {

        todService.syncAllAvailableLots();

    }
}
