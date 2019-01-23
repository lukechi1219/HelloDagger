package com.lukechi.android.opendata.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaipeiParkingServiceTest {

    @Test
    public void testPrintAvailableLots() throws InterruptedException {

        TaipeiParkingService parkingService = new TaipeiParkingService();

        parkingService.printAvailableLots();

        Thread.sleep(3000);

        assertEquals("test", "test");
    }
}
