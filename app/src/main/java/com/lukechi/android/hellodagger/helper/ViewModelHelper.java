package com.lukechi.android.hellodagger.helper;

import android.view.View;
import android.widget.ProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.lukechi.android.hellodagger.ui.ParkingLotsAdapter;
import com.lukechi.android.hellodagger.ui.viewmodel.ParkingLotsViewModel;

public class ViewModelHelper {

    /*
      TODO
      https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-5-8fc4f9cee34d
     */
    public static void viewModelDoObserve(LifecycleOwner lifecycleOwner, ParkingLotsViewModel _parkingLotsViewModel, ParkingLotsAdapter _parkingLotsAdapter, RecyclerView _recycler, ProgressBar _progressBar, int LIST_SCROLLING) {

//        TextView hello_world_textview = findViewById(R.id.hello_world_textview);

        _parkingLotsViewModel.parkingLotsResult().observe(lifecycleOwner,
                parkingLots -> {
                    if (parkingLots == null || parkingLots.isEmpty()) {
                        // change to Toast
//                        hello_world_textview.setText("Hello no parkingLots");
                        return;
                    }
                    String name = parkingLots.get(0).name();
                    String area = parkingLots.get(0).area();
//                    hello_world_textview.setText("Hello " + name + " of " + area + " of " + parkingLots.size() + " parkingLots!");

                    int position = _parkingLotsAdapter.getItemCount();
                    _parkingLotsAdapter.addParkingLots(parkingLots);

                    _recycler.setAdapter(_parkingLotsAdapter);
                    // ???
                    _recycler.scrollToPosition(position - LIST_SCROLLING);
                });

        _parkingLotsViewModel.parkingLotsError().observe(lifecycleOwner,
                errorStr -> {
                    if (errorStr == null) {
                        // change to Toast
//                        hello_world_textview.setText("Hello Null error");
                    } else {
//                        hello_world_textview.setText("Hello error " + errorStr);
                    }
//                    String toastMessage = getString(R.string.parking_lot_error_message) + errorStr;
//                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
                });

        _parkingLotsViewModel.getParkingLotsLoader().observe(lifecycleOwner,
                stillLoading -> {
                    if (!stillLoading) {
                        _progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
