package com.lukechi.android.opendata.service;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.lukechi.android.hellodagger.factory.CustomRetrofitFactory;
import com.lukechi.android.hellodagger.util.NetworkUtil;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import com.lukechi.android.opendata.api.observer.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.observer.AllParkingDescJsonObserver;
import com.lukechi.android.opendata.api.observer.GetCMSXmlObserver;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TaipeiParkingServiceTest {

//    // seems can be replaced by RxAndroidPlugins.setInitMainThreadSchedulerHandler for simple test scenario
//    @ClassRule
//    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Test
    public void testPrintAvailableLots() throws InterruptedException {

        Context appContext = ApplicationProvider.getApplicationContext();

        TaipeiOpenDataService todService = new TaipeiOpenDataService(appContext,
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

//        ParkingLot parkingLot = new ParkingLot(1, "here", "name");
        ParkingLot parkingLot = ParkingLot.create(1, "here", "name");

        Thread.sleep(3 * 1000);

        assertEquals("test", "test");
    }
}
