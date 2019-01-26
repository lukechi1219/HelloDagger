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

        AllParkingDescData data = json.getData();
        System.out.println(data.getUpdateTime());
        System.out.println(data.getUpdateTimeCST());
        System.out.println(data.getUpdateTimestamp());

        List<ParkingLotDesc> list = data.getDescList();

//        ParkingLotDesc desc = list.get(0);
//        ParkingLotDesc desc = list.get(1);
        ParkingLotDesc desc = list.get(2);

        System.out.println(desc.getId());
        System.out.println(desc.getArea());
        System.out.println(desc.getName());

        System.out.println(desc.getTel());
        System.out.println(desc.getAddress());
        System.out.println(desc.getSummary());
        System.out.println(desc.getServiceTime());
        System.out.println(desc.getType()); // ?
        System.out.println(desc.getType2()); // ?
        System.out.println(desc.getTw97x());
        System.out.println(desc.getTw97y());
        // Pay Explain: 計時：30元/時(9-18)，夜間10元/時(18-9)月票：全日3,400元，.....
        System.out.println(desc.getPayex());

        List<EntrancecoordInfo> entranceList = desc.getEntranceCoord().getEntrancecoordInfo();
        for (EntrancecoordInfo entrance : entranceList) {
            System.out.println(entrance.getAddress());
            System.out.println(entrance.getXcod());
            System.out.println(entrance.getYcod());
        }

        List<Holiday> holidayList = desc.getFareInfo().getHoliday();
        for (Holiday holiday : holidayList) {
            System.out.println(holiday.getFare());
            System.out.println(holiday.getPeriod());
        }

        List<WorkingDay> workingDayList = desc.getFareInfo().getWorkingDay();
        for (WorkingDay workingDay : workingDayList) {
            System.out.println(workingDay.getFare());
            System.out.println(workingDay.getPeriod());
        }
    }
}
