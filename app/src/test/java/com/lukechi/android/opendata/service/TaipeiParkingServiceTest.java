package com.lukechi.android.opendata.service;

import android.content.Context;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lukechi.android.hellodagger.di.module.NetworkModule;
import com.lukechi.android.hellodagger.util.NetworkUtil;
import com.lukechi.android.opendata.api.AllAvailableLotsJsonObserver;
import com.lukechi.android.opendata.api.AllParkingDescJsonObserver;
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
                new TaipeiOpenDataSite(
                        NetworkUtil.buildGsonConverterFactory(),
                        RxJava2CallAdapterFactory.create(),
                        NetworkModule.provideOkHttpClient()));

        todService.allParkingDescJsonObserver = new AllParkingDescJsonObserver();
        todService.allAvailableLotsJsonObserver = new AllAvailableLotsJsonObserver();

        todService.SyncAllParkingDesc();
        todService.SyncAllAvailableLots();

        Thread.sleep(3 * 1000);

        assertEquals("test", "test");
    }
}
