package com.lukechi.android.opendata.api.observer;

import com.lukechi.android.opendata.api.BaseObserver;
import com.lukechi.android.opendata.api.model.AllParkingDescJson;
import com.lukechi.android.opendata.api.model.AllParkingDescJson.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AllParkingDescJsonObserver extends BaseObserver<AllParkingDescJson> {

    @Inject
    public AllParkingDescJsonObserver() {
    }

    @Override
    public void onNext(AllParkingDescJson json) {

        AllParkingDescData data = json.data();
        System.out.println(data.updateTime());
        System.out.println(data.updateTimeCST());
        System.out.println(data.updateTimestamp());

        List<ParkingLotDesc> list = data.descList();

//        ParkingLotDesc desc = list.get(0);
//        ParkingLotDesc desc = list.get(1);
        ParkingLotDesc desc = list.get(2);

        System.out.println(desc.id());
        System.out.println(desc.area());
        System.out.println(desc.name());

        System.out.println(desc.tel());
        System.out.println(desc.address());
        System.out.println(desc.summary());
        System.out.println(desc.serviceTime());
        System.out.println(desc.type()); // ?
        System.out.println(desc.type2()); // ?
        System.out.println(desc.tw97x());
        System.out.println(desc.tw97y());
        // Pay Explain: 計時：30元/時(9-18)，夜間10元/時(18-9)月票：全日3,400元，.....
        System.out.println(desc.payExplain());

        List<EntrancecoordInfo> entranceList = desc.entranceCoord().getEntrancecoordInfo();
        for (EntrancecoordInfo entrance : entranceList) {
            System.out.println(entrance.getAddress());
            System.out.println(entrance.getXcod());
            System.out.println(entrance.getYcod());
        }

        List<Holiday> holidayList = desc.fareInfo().getHoliday();
        for (Holiday holiday : holidayList) {
            System.out.println(holiday.getFare());
            System.out.println(holiday.getPeriod());
        }

        List<WorkingDay> workingDayList = desc.fareInfo().getWorkingDay();
        for (WorkingDay workingDay : workingDayList) {
            System.out.println(workingDay.getFare());
            System.out.println(workingDay.getPeriod());
        }
    }
}
