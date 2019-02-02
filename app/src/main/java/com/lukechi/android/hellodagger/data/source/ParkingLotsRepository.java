package com.lukechi.android.hellodagger.data.source;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.lukechi.android.opendata.api.TaipeiOpenDataAPI;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import com.lukechi.android.opendata.api.model.AllAvailableLotsJson.AllAvailableLot;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-4-2b476142e769
 */
public class ParkingLotsRepository {

    private final ConnectivityManager connectivityManager;
    private final TaipeiOpenDataAPI apiCall;

    @Inject
    ParkingLotDao parkingLotDao;

    @Inject
    public ParkingLotsRepository(Context context, TaipeiOpenDataSite taipeiOpenDataSite) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.apiCall = taipeiOpenDataSite.getClient(context).create(TaipeiOpenDataAPI.class);
    }

    private boolean isConnectedToInternet() {
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }
        return false;
    }

    /*
    Show the cached data while waiting for the network.
    https://medium.com/yammer-engineering/chaining-multiple-sources-with-rxjava-20eb6850e5d9
     */
    @NotNull
    public Observable<List<ParkingLot>> getParkingLots(int limit, int offset) {

        Observable<List<ParkingLot>> observableFromDb = getParkingLotsFromDb(limit, offset);

        if (isConnectedToInternet()) {
            Observable<List<ParkingLot>> observableFromApi = getParkingLotsFromApi();

            return Observable.merge(observableFromApi, observableFromDb);
            // return Observable.concatArrayEager(observableFromApi, observableFromDb);
        }
        return observableFromDb;
    }

    private Observable<List<ParkingLot>> getParkingLotsFromDb(int limit, int offset) {
//        TODO add paging
        return parkingLotDao.loadAllParkingLot()
                .toObservable()
                .doOnError(tr -> Log.e("REPOSITORY DB *** ", tr.getMessage(), tr))
                .doOnNext(list -> Log.e("REPOSITORY DB *** ", String.valueOf(list.size())));
    }

    private Observable<List<ParkingLot>> getParkingLotsFromApi() {

        return apiCall.tcmsvSyncAllAvailableLots()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .doOnError(tr -> Log.e("REPOSITORY API *** ", tr.getMessage(), tr))
                .map(json -> {
                    List<ParkingLot> list = new ArrayList<>();

                    for (AllAvailableLot lotFromJson : json.data().parkingLots()) {

                        Integer rowId = Integer.valueOf(lotFromJson.id());
                        int id = rowId;

                        ParkingLot parkingLot = new ParkingLot(rowId, id, "area", "name " + lotFromJson.id());

                        list.add(parkingLot);
                    }
                    return list;

                })
                .doOnNext(list -> Log.e("REPOSITORY API *** ", String.valueOf(list.size())));
    }
}

