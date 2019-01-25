package com.lukechi.android.opendata.service;

import com.lukechi.android.opendata.api.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import com.lukechi.android.opendata.api.TaipeiParkingApiCall;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class TaipeiParkingApiClient {

    private final TaipeiOpenDataSite taipeiOpenDataSite;

    @Inject
    AllAvailableLotsJsonObserver allAvailableLotsJsonObserver;

    @Inject
    public TaipeiParkingApiClient(TaipeiOpenDataSite taipeiOpenDataSite) {
        this.taipeiOpenDataSite = taipeiOpenDataSite;
    }

    public void printAvailableLots() {

        // need context? just pass null?
        TaipeiParkingApiCall apiCall = taipeiOpenDataSite.getClient(null).create(TaipeiParkingApiCall.class);

        apiCall.tcmsvSyncAllAvailableLots()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allAvailableLotsJsonObserver);
    }
}
