package com.lukechi.android.opendata.database;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNull;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {

//    @Inject
//    public SimpleEntityReadWriteTest() {
//    }

    //    @Inject
    private ParkingLotDao mParkingLotDao;
    //    @Inject
    private AppDatabase mDb;

    @Before
    public void createDb() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();

        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        mParkingLotDao = mDb.parkingLotDao();
    }

    @After
    public void closeDb() {
        if (mDb != null) {
            mDb.close();
        }
    }

    @Test
    public void writeUserAndReadInList() throws InterruptedException {

        System.out.println("write test");

//        User user = TestUtil.createUser(3);
//        user.setName("george");

//        ParkingLot parkingLot = ParkingLot.builder().id(1).lid(1).area("area").name("name").build();
//        ParkingLot parkingLotNew = new ParkingLot(1, "area", "name");
        ParkingLot parkingLotNew = ParkingLot.create(1, "area", "name");
        assertEquals(1, parkingLotNew.id());
        assertEquals("area", parkingLotNew.area());
        assertEquals("name", parkingLotNew.name());

        // OnConflictStrategy.REPLACE87
        mParkingLotDao.insertParkingLots(parkingLotNew);

        mParkingLotDao.loadAllParkingLot()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ParkingLot>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe: " + d);
                    }

                    @Override
                    public void onSuccess(List<ParkingLot> parkingLotsArray) {

                        System.out.println("onSuccess");

                        assertEquals(1, parkingLotsArray.size());

                        for (ParkingLot parkingLot : parkingLotsArray) {
                            System.out.println(parkingLot.id());
                            System.out.println(parkingLot.area());
                            System.out.println(parkingLot.name());

                            // assertEquals(0, parkingLot.lid().intValue());
                            assertEquals(1, parkingLot.id());
                            assertEquals("area", parkingLot.area());
                            assertEquals("name", parkingLot.name());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });

        Thread.sleep(1000);
    }
}
