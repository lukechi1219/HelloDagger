package com.lukechi.android.opendata.service;

import android.content.Context;
import com.lukechi.android.opendata.api.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class TaipeiOpenDataService {

    private final TaipeiOpenDataAPI apiCall;
    private final TaipeiOpenDataAPI apiCallForXml;

    @Inject
    GetCMSXmlObserver getCMSXmlObserver;

    @Inject
    AllParkingDescJsonObserver allParkingDescJsonObserver;

    @Inject
    AllAvailableLotsJsonObserver allAvailableLotsJsonObserver;

    @Inject
    public TaipeiOpenDataService(Context context, TaipeiOpenDataSite taipeiOpenDataSite) {
        // need context?
        System.out.println("context: " + context);

        this.apiCall = taipeiOpenDataSite.getClient(context).create(TaipeiOpenDataAPI.class);
        this.apiCallForXml = taipeiOpenDataSite.getClientForXml(context).create(TaipeiOpenDataAPI.class);
    }

    public void syncGetCMS() {

        apiCallForXml.tisvSyncGetCMS()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCMSXmlObserver);
    }

    public void syncAllParkingDesc() {

        apiCall.tcmsvSyncAllParkingDesc()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allParkingDescJsonObserver);
    }

    public void syncAllAvailableLots() {

        apiCall.tcmsvSyncAllAvailableLots()
                .subscribeOn(Schedulers.io()) // ?? Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allAvailableLotsJsonObserver);
    }
}
