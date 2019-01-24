package com.lukechi.android.opendata.service;

import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import com.lukechi.android.opendata.api.TaipeiParkingApiCall;
import com.lukechi.android.opendata.model.AllAvailableLotsJson;
import com.lukechi.android.opendata.model.AllAvailableLotsJson.AllAvailableLot;
import com.lukechi.android.opendata.model.AllAvailableLotsJson.AllAvailableLotsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.List;

public class TaipeiParkingApiClient implements Callback<AllAvailableLotsJson> {

    private final TaipeiOpenDataSite taipeiOpenDataSite;

    @Inject
    public TaipeiParkingApiClient(TaipeiOpenDataSite taipeiOpenDataSite) {
        this.taipeiOpenDataSite = taipeiOpenDataSite;
    }

    public void printAvailableLots() {

        // need context? just pass null?
        TaipeiParkingApiCall apiCall = taipeiOpenDataSite.getClient(null).create(TaipeiParkingApiCall.class);

        Call<AllAvailableLotsJson> call = apiCall.tcmsvSyncAllAvailableLots();

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<AllAvailableLotsJson> call, Response<AllAvailableLotsJson> response) {

        if (response.isSuccessful()) {
            AllAvailableLotsJson json = response.body();
            AllAvailableLotsData data = json.getData();
            List<AllAvailableLot> lotList = data.getParkingLots();

//            AllAvailableLot lot = lotList.get(0);
//            AllAvailableLot lot = lotList.get(1);
//            AllAvailableLot lot = lotList.get(2);
//            AllAvailableLot lot = lotList.get(3);
            AllAvailableLot lot = lotList.get(4);

            System.out.println(data.getUpdateTime());
            System.out.println(data.getUpdateTimeCST());
            System.out.println(data.getUpdateTimestamp());
            System.out.println("total lots: " + lotList.size());

            System.out.println("lot id: " + lot.getId());
            System.out.println("lot availableCar: " + lot.getAvailableCar());
            System.out.println("lot availableMotor: " + lot.getAvailableMotor());
            System.out.println("lot availableBus: " + lot.getAvailableBus());

            AllAvailableLotsJson.ChargeStation chargeStation = lot.getChargeStation();

            if (chargeStation == null) {
                System.out.println("no chargeStations");
            } else {
                List<AllAvailableLotsJson.SocketStatus> socketStatusList = chargeStation.getSocketStatusList();
                System.out.println("total charge sockets: " + socketStatusList.size());

                for (AllAvailableLotsJson.SocketStatus socketStatus : socketStatusList) {
                    System.out.println("socketStatus: " + socketStatus.getSpotAbrv() + " " + socketStatus.getSpotStatus());
                }
            }

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<AllAvailableLotsJson> call, Throwable t) {
        t.printStackTrace();
    }
}
