package com.lukechi.android.opendata.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaipeiParkingApiCall {

    @GET("blobtcmsv/TCMSV_allavailable.gz")
    Call<AllAvailableLotsJson> getAllAvailableLots();
}
