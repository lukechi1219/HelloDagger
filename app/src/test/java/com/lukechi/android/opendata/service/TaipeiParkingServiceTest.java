package com.lukechi.android.opendata.service;

import android.content.Context;
import com.lukechi.android.hellodagger.factory.CustomRetrofitFactory;
import com.lukechi.android.hellodagger.util.NetworkUtil;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import com.lukechi.android.opendata.api.observer.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.observer.AllParkingDescJsonObserver;
import com.lukechi.android.opendata.api.observer.GetCMSXmlObserver;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import com.lukechi.android.testrule.RxImmediateSchedulerRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaipeiParkingServiceTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Test
    public void testPrintAvailableLots() throws InterruptedException {

        Context context = null;

        TaipeiOpenDataService todService = new TaipeiOpenDataService(context,
                new TaipeiOpenDataSite(
                        new CustomRetrofitFactory(
                                NetworkUtil.buildOkHttpClientForXml(),
                                NetworkUtil.buildOkHttpClient())));

        todService.getCMSXmlObserver = new GetCMSXmlObserver();
        todService.allParkingDescJsonObserver = new AllParkingDescJsonObserver();
        todService.allAvailableLotsJsonObserver = new AllAvailableLotsJsonObserver();

        todService.syncGetCMS();
        todService.syncAllParkingDesc();
        todService.syncAllAvailableLots();

        ParkingLot parkingLot = ParkingLot.builder().id(1).lid(1).area("here").name("name").build();

        Thread.sleep(3 * 1000);

        assertEquals("test", "test");
    }
}
