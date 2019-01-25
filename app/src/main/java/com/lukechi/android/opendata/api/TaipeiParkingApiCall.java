package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.AllAvailableLotsJson;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ?? Flowable vs Observable ??
 * <p>
 * Basically, in RxJava2 a Flowable is an Observer that handles back-pressure.
 */
public interface TaipeiParkingApiCall {

    /*
    blobtisv/GetVD.xml.gz
    blobtisv/GetVDDATA.xml.gz
    blobtisv/GetCMS.xml.gz
     */

//    @GET("blobtcmsv/TCMSV_alldesc.gz")
//    Call<AllParkingDescJson> tcmsvSyncAllParkingDesc();

    @GET("blobtcmsv/TCMSV_allavailable.gz")
    Observable<AllAvailableLotsJson> tcmsvSyncAllAvailableLots();

//    @GET("blobyoubike/YouBikeTP.gz")
//    Call<YouBikeTPJson> youbikeSyncYouBikeTP();
}
