package com.lukechi.android.hellodagger.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;
import com.lukechi.android.hellodagger.helper.ViewModelHelper;
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
 * <p>
 * https://developer.android.com/jetpack/arch/
 * https://developer.android.com/jetpack/docs/guide
 * <p>
 * https://github.com/codepath/android_guides/wiki
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

        /*
         */
        RecyclerView recycler = findViewById(R.id.recycler);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        ViewModelHelper.viewModelDoObserve(this, parkingLotsViewModel, parkingLotsAdapter, recycler, progressBar, LIST_SCROLLING);

        /*
         */
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        loadData();

        // TODO
        showNearByAvailableLots();

        /*
          https://developer.android.com/topic/performance/threads
          For this reason, we suggest that you only use AsyncTask to handle work items shorter than 5ms in duration.
         */

        try {
            Thread.sleep(1000); // wait api return for test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inflates the options menu and adds items to the menu.
     *
     * @param menu Options menu
     * @return True if menu is inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        // if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map:
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                // intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            case R.id.action_status:
                return true;
            case R.id.action_settings:
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
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
