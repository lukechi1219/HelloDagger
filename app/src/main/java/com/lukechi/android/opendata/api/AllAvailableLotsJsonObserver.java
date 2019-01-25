package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.AllAvailableLotsJson;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AllAvailableLotsJsonObserver implements Observer<AllAvailableLotsJson> {

    @Inject
    public AllAvailableLotsJsonObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
        System.out.println(d);
    }

    @Override
    public void onNext(AllAvailableLotsJson json) {

//                        if (response.isSuccessful()) {} // ?? if not successful ??

        AllAvailableLotsJson.AllAvailableLotsData data = json.getData();
        List<AllAvailableLotsJson.AllAvailableLot> lotList = data.getParkingLots();

//            AllAvailableLot lot = lotList.get(0);
//            AllAvailableLot lot = lotList.get(1);
//            AllAvailableLot lot = lotList.get(2);
//            AllAvailableLot lot = lotList.get(3);
        AllAvailableLotsJson.AllAvailableLot lot = lotList.get(4);

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
    }

    @Override
    public void onError(Throwable t) {
//                        System.out.println(response.errorBody());
//                        System.out.println(e.getMessage());
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
