package com.lukechi.android.opendata.service;

import com.lukechi.android.opendata.api.AllAvailableLotsJson;
import com.lukechi.android.opendata.api.AllAvailableLotsJson.AllAvailableLot;
import com.lukechi.android.opendata.api.AllAvailableLotsJson.AllAvailableLotsData;
import com.lukechi.android.opendata.api.AllAvailableLotsJson.ChargeStation;
import com.lukechi.android.opendata.api.AllAvailableLotsJson.SocketStatus;
import com.lukechi.android.opendata.api.TaipeiParkingApi;
import com.lukechi.android.opendata.api.TaipeiParkingApiCall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.List;

public class TaipeiParkingService implements Callback<AllAvailableLotsJson> {

    @Inject
    public TaipeiParkingService() {
    }

    public void printAvailableLots() {

        TaipeiParkingApiCall apiCall = TaipeiParkingApi.getClient(null).create(TaipeiParkingApiCall.class);

        Call<AllAvailableLotsJson> call = apiCall.getAllavailableLots();

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
            System.out.println("total lots: " + lotList.size());

            System.out.println("lot id: " + lot.getId());
            System.out.println("lot availableCar: " + lot.getAvailableCar());
            System.out.println("lot availableMotor: " + lot.getAvailableMotor());
            System.out.println("lot availableBus: " + lot.getAvailableBus());

            ChargeStation chargeStation = lot.getChargeStation();

            if (chargeStation == null) {
                System.out.println("no chargeStations");
            } else {
                List<SocketStatus> socketStatusList = chargeStation.getSocketStatusList();
                System.out.println("total charge sockets: " + socketStatusList.size());

                for (SocketStatus socketStatus : socketStatusList) {
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
