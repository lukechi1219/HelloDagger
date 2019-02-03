package com.lukechi.android.hellodagger.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.thirdparty.ThirdPartyClass;
import com.lukechi.android.hellodagger.ui.InfiniteScrollListener;
import com.lukechi.android.hellodagger.ui.ParkingLotsAdapter;
import com.lukechi.android.hellodagger.ui.viewmodel.ParkingLotsViewModel;
import com.lukechi.android.hellodagger.ui.viewmodel.ParkingLotsViewModelFactory;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.service.TaipeiOpenDataService;
import dagger.android.support.DaggerAppCompatActivity;
import kotlin.Unit;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * A class shouldn't t know anything about how it is injected. So we hide inject code into DaggerAppCompatActivity
 *
 * https://developer.android.com/jetpack/arch/
 * https://developer.android.com/jetpack/docs/guide
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

    ParkingLotsAdapter parkingLotsAdapter = new ParkingLotsAdapter(new ArrayList<>());

    int currentPage = 0;

    final int OFFSET = 12;
    final int LIMIT = 12;
    final int LIST_SCROLLING = 12;
//    final String START_ZERO_VALUE = "0";
//    final String DATABASE_NAME = "cryptocurrencies_db";

    /*
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parkingLotsViewModel = ViewModelProviders
                .of(this, parkingLotsViewModelFactory)
                .get(ParkingLotsViewModel.class);

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
        https://developer.android.com/guide/topics/ui/layout/recyclerview
        https://developer.android.com/guide/topics/ui/declaring-layout
         */
        initializeRecycler();

        observeViewModel();

        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        loadData();

        // TODO
        showNearByAvailableLots();

        /*
          https://developer.android.com/topic/performance/threads
          For this reason, we suggest that you only use AsyncTask to handle work items shorter than 5ms in duration.
         */
    }

    /*
     */
    @Override
    protected void onDestroy() {
        if (parkingLotsViewModel != null) {
            parkingLotsViewModel.disposeElements();
        }
        super.onDestroy();
    }

    /*
      TODO
      https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-5-8fc4f9cee34d
     */
    private void observeViewModel() {

        TextView hello_world_textview = findViewById(R.id.hello_world_textview);
        RecyclerView recycler = findViewById(R.id.recycler);

        parkingLotsViewModel.parkingLotsResult().observe(this,
                parkingLots -> {
                    if (parkingLots == null || parkingLots.isEmpty()) {
                        hello_world_textview.setText("Hello no parkingLots");
                        return;
                    }
                    String name = parkingLots.get(0).name();
                    String area = parkingLots.get(0).area();
                    hello_world_textview.setText("Hello " + name + " of " + area + " of " + parkingLots.size() + " parkingLots!");

                    int position = parkingLotsAdapter.getItemCount();
                    parkingLotsAdapter.addCryptocurrencies(parkingLots);
                    recycler.setAdapter(parkingLotsAdapter);
                    recycler.scrollToPosition(position - LIST_SCROLLING);
                });

        parkingLotsViewModel.parkingLotsError().observe(this,
                errorStr -> {
                    if (errorStr == null) {
                        hello_world_textview.setText("Hello Null error");
                    } else {
                        hello_world_textview.setText("Hello error " + errorStr);
                    }
//                    String toastMessage = getString(R.string.parking_lot_error_message) + errorStr;
//                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
                });

        parkingLotsViewModel.getParkingLotsLoader().observe(this,
                stillLoading -> {
                    if (!stillLoading) {
                        findViewById(R.id.progressBar).setVisibility(View.GONE);
                    }
                });
    }

    /*
     */
    public void loadData() {
        parkingLotsViewModel.loadParkingLots(LIMIT, currentPage * OFFSET);
        currentPage++;
    }

    /*
    https://android.jlelse.eu/keddit-part-7-infinite-scroll-higher-order-functions-lambdas-3a11fbd5090e

    https://stackoverflow.com/questions/45029368/items-not-removed-properly-from-recyclerview

    https://medium.com/@kitek/recyclerview-swipe-to-delete-easier-than-you-thought-cff67ff5e5f6
    https://thedeveloperworldisyours.com/android/delete-item-recyclerview/
    https://android--code.blogspot.com/2015/12/android-recyclerview-add-remove-item.html
     */
    private void initializeRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.addOnScrollListener(new InfiniteScrollListener(() -> {
            loadData();
            return Unit.INSTANCE; // expected return for kotlin
        }, gridLayoutManager));
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
