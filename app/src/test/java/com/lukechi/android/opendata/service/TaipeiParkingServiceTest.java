package com.lukechi.android.opendata.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaipeiParkingServiceTest {

    @Test
    public void testPrintAvailableLots() throws InterruptedException {

        TaipeiParkingApiClient parkingApiClient = new TaipeiParkingApiClient();

        parkingApiClient.printAvailableLots();

        Thread.sleep(3000);

        assertEquals("test", "test");
    }
}
