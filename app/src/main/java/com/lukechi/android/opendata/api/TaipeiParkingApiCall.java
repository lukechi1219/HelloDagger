package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.AllAvailableLotsJson;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TaipeiParkingApiCall {

    /*
    blobtisv/GetVD.xml.gz
    blobtisv/GetVDDATA.xml.gz
    blobtisv/GetCMS.xml.gz
     */

//    @GET("blobtcmsv/TCMSV_alldesc.gz")
//    Call<AllParkingDescJson> tcmsvSyncAllParkingDesc();

    @GET("blobtcmsv/TCMSV_allavailable.gz")
    Call<AllAvailableLotsJson> tcmsvSyncAllAvailableLots();

//    @GET("blobyoubike/YouBikeTP.gz")
//    Call<YouBikeTPJson> youbikeSyncYouBikeTP();
}
