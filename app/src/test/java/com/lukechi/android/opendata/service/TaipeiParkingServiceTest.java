package com.lukechi.android.opendata.service;

import com.lukechi.android.hellodagger.di.module.AppModule;
import com.lukechi.android.opendata.api.TaipeiOpenDataSite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaipeiParkingServiceTest {

    @Test
    public void testPrintAvailableLots() throws InterruptedException {

        TaipeiParkingApiClient parkingApiClient = new TaipeiParkingApiClient(
                new TaipeiOpenDataSite(AppModule.provideOkHttpClient()));

        parkingApiClient.printAvailableLots();

        Thread.sleep(3 * 1000);

        assertEquals("test", "test");
    }
}
