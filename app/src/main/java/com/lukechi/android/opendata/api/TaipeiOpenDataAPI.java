package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.api.model.AllAvailableLotsJson;
import com.lukechi.android.opendata.api.model.AllParkingDescJson;
import com.lukechi.android.opendata.api.model.tisv.GetCMSXml;
import io.reactivex.Single;
import retrofit2.http.GET;

//import io.reactivex.Observable;

/**
 * ?? Flowable vs Observable ??
 * <p>
 * Basically, in RxJava2 a Flowable is an Observer that handles back-pressure.
 *
 * https://stackoverflow.com/questions/42757924/what-is-the-difference-between-observable-completable-and-single-in-rxjava
 *
 * Single can be appropriate when you have task oriented Observable and you expect single value, like Network request
 * which is performed once and return value (or error), network call is operated in one time fashion, meaning you
 * don't expect it to return additional values over time. Another example is DB fetch data operation.
 *
 * https://android.jlelse.eu/rest-api-on-android-made-simple-or-how-i-learned-to-stop-worrying-and-love-the-rxjava-b3c2c949cad4
 */
public interface TaipeiOpenDataAPI {

    /*
    blobtisv/GetVD.xml.gz
    blobtisv/GetVDDATA.xml.gz
     */
    @GET("blobtisv/GetCMS.xml.gz")
    Single<GetCMSXml> tisvSyncGetCMS();

    @GET("blobtcmsv/TCMSV_alldesc.gz")
    Single<AllParkingDescJson> tcmsvSyncAllParkingDesc();

    @GET("blobtcmsv/TCMSV_allavailable.gz")
    Single<AllAvailableLotsJson> tcmsvSyncAllAvailableLots();

//    @GET("blobyoubike/YouBikeTP.gz")
//    Single<YouBikeTPJson> youbikeSyncYouBikeTP();
}
