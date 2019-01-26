package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.AllAvailableLotsJson;
import com.lukechi.android.opendata.model.AllParkingDescJson;
import com.lukechi.android.opendata.model.GetCMSXml;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ?? Flowable vs Observable ??
 * <p>
 * Basically, in RxJava2 a Flowable is an Observer that handles back-pressure.
 */
public interface TaipeiOpenDataAPI {

    /*
    blobtisv/GetVD.xml.gz
    blobtisv/GetVDDATA.xml.gz
     */
    @GET("blobtisv/GetCMS.xml.gz")
    Observable<GetCMSXml> tisvSyncGetCMS();

    @GET("blobtcmsv/TCMSV_alldesc.gz")
    Observable<AllParkingDescJson> tcmsvSyncAllParkingDesc();

    @GET("blobtcmsv/TCMSV_allavailable.gz")
    Observable<AllAvailableLotsJson> tcmsvSyncAllAvailableLots();

//    @GET("blobyoubike/YouBikeTP.gz")
//    Observable<YouBikeTPJson> youbikeSyncYouBikeTP();
}
