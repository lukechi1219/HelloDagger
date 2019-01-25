package com.lukechi.android.opendata.service;

import android.content.Context;
import com.lukechi.android.opendata.api.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import com.lukechi.android.opendata.api.TaipeiParkingApiCall;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class TaipeiParkingApiClient {

    private final TaipeiParkingApiCall apiCall;

    @Inject
    AllAvailableLotsJsonObserver allAvailableLotsJsonObserver;

    @Inject
    public TaipeiParkingApiClient(Context context, TaipeiOpenDataSite taipeiOpenDataSite) {
        // need context?
        System.out.println(context);

        this.apiCall = taipeiOpenDataSite.getClient(context).create(TaipeiParkingApiCall.class);
    }

    public void SyncAllAvailableLots() {

        apiCall.tcmsvSyncAllAvailableLots()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allAvailableLotsJsonObserver);
    }
}
