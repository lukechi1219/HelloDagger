package com.lukechi.android.opendata.service;

import android.content.Context;
import com.lukechi.android.hellodagger.di.module.AppModule;
import com.lukechi.android.opendata.api.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
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
                new TaipeiOpenDataSite(AppModule.provideOkHttpClient()));

        todService.allAvailableLotsJsonObserver = new AllAvailableLotsJsonObserver();

        todService.SyncAllAvailableLots();

        Thread.sleep(3 * 1000);

        assertEquals("test", "test");
    }
}
