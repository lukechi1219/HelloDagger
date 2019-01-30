package com.lukechi.android.opendata.api.observer;

import com.lukechi.android.opendata.api.BaseObserver;
import com.lukechi.android.opendata.api.model.AllAvailableLotsJson;
import com.lukechi.android.opendata.api.model.AllAvailableLotsJson.AllAvailableLot;
import com.lukechi.android.opendata.api.model.AllAvailableLotsJson.AllAvailableLotsData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AllAvailableLotsJsonObserver extends BaseObserver<AllAvailableLotsJson> {

    @Inject
    public AllAvailableLotsJsonObserver() {
    }

    /**
     * TODO: add persist data to database
     */
    @Override
    public void onSuccess(AllAvailableLotsJson json) {

//                        if (response.isSuccessful()) {} // ?? if not successful ??

        AllAvailableLotsData data = json.data();
        List<AllAvailableLot> lotList = data.parkingLots();

//            AllAvailableLot lot = lotList.get(0);
//            AllAvailableLot lot = lotList.get(1);
//            AllAvailableLot lot = lotList.get(2);
//            AllAvailableLot lot = lotList.get(3);
        AllAvailableLot lot = lotList.get(4);

        System.out.println(data.updateTime());
        System.out.println(data.updateTimeCST());
        System.out.println(data.updateTimestamp());
        System.out.println("total lots: " + lotList.size());

        System.out.println("lot id: " + lot.id());
        System.out.println("lot availableCar: " + lot.availableCar());
        System.out.println("lot availableMotor: " + lot.availableMotor());
        System.out.println("lot availableBus: " + lot.availableBus());

        AllAvailableLotsJson.ChargeStation chargeStation = lot.chargeStations();

        if (chargeStation == null) {
            System.out.println("no chargeStations");
        } else {
            List<AllAvailableLotsJson.SocketStatus> socketStatusList = chargeStation.socketStatusList();
            System.out.println("total charge sockets: " + socketStatusList.size());

            for (AllAvailableLotsJson.SocketStatus socketStatus : socketStatusList) {
                System.out.println("socketStatus: " + socketStatus.spotAbrv() + " " + socketStatus.spotStatus());
            }
        }
    }
}
