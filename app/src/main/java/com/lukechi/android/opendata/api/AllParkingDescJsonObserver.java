package com.lukechi.android.opendata.api;

import com.lukechi.android.opendata.model.AllParkingDescJson;
import com.lukechi.android.opendata.model.AllParkingDescJson.AllParkingDescData;
import com.lukechi.android.opendata.model.AllParkingDescJson.ParkingLotDesc;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AllParkingDescJsonObserver implements Observer<AllParkingDescJson> {

    @Inject
    public AllParkingDescJsonObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
        System.out.println(d);
    }

    @Override
    public void onNext(AllParkingDescJson json) {

        AllParkingDescData data = json.getData();
        System.out.println(data.getUpdateTime());
        System.out.println(data.getUpdateTimeCST());
        System.out.println(data.getUpdateTimestamp());

        List<ParkingLotDesc> list = data.getParkingDescList();

//        ParkingLotDesc desc = list.get(0);
//        ParkingLotDesc desc = list.get(1);
        ParkingLotDesc desc = list.get(2);

        System.out.println(desc.getId());
        System.out.println(desc.getArea());
        System.out.println(desc.getName());
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
