package com.lukechi.android.opendata.database;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {

    private ParkingLotDao mParkingLotDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();

        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class).build();
        mParkingLotDao = mDb.parkingLotDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() {

        System.out.println("write test");

//        User user = TestUtil.createUser(3);
//        user.setName("george");

//        ParkingLot parkingLot = ParkingLot.builder().id(1).lid(1).area("area").name("name").build();
        ParkingLot parkingLotNew = ParkingLot.create(1, 2, "area", "name");
        assertEquals(1, parkingLotNew.id());
        assertEquals(2, parkingLotNew.lid());
        assertEquals("area", parkingLotNew.area());
        assertEquals("name", parkingLotNew.name());

        // OnConflictStrategy.REPLACE
        mParkingLotDao.insertParkingLots(parkingLotNew);

        ParkingLot[] parkingLotsArray = mParkingLotDao.loadAllParkingLot();

        assertEquals(1, parkingLotsArray.length);

        for (ParkingLot parkingLot : parkingLotsArray) {
            System.out.println(parkingLot.id());
            System.out.println(parkingLot.lid());
            System.out.println(parkingLot.area());
            System.out.println(parkingLot.name());

            assertEquals(1, parkingLot.id());
            assertEquals(2, parkingLot.lid());
            assertEquals("area", parkingLot.area());
            assertEquals("name", parkingLot.name());
        }
    }
}
