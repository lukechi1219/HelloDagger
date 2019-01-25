package com.lukechi.android.opendata.service;

import android.content.Context;
import com.lukechi.android.opendata.api.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.AllParkingDescJsonObserver;
import com.lukechi.android.opendata.api.TaipeiOpenDataAPI;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class TaipeiOpenDataService {

    private final TaipeiOpenDataAPI apiCall;

    @Inject
    AllParkingDescJsonObserver allParkingDescJsonObserver;

    @Inject
    AllAvailableLotsJsonObserver allAvailableLotsJsonObserver;

    @Inject
    public TaipeiOpenDataService(Context context, TaipeiOpenDataSite taipeiOpenDataSite) {
        // need context?
        System.out.println("context: " + context);

        this.apiCall = taipeiOpenDataSite.getClient(context).create(TaipeiOpenDataAPI.class);
    }

    public void SyncAllParkingDesc() {

        apiCall.tcmsvSyncAllParkingDesc()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allParkingDescJsonObserver);
    }

    public void SyncAllAvailableLots() {

        apiCall.tcmsvSyncAllAvailableLots()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allAvailableLotsJsonObserver);
    }
}
