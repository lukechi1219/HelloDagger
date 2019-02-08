package com.lukechi.android.opendata.database;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import io.reactivex.SingleObserver;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

// import androidx.test.filters.SmallTest;
// import static org.junit.Assert.assertNull;

/*
https://www.bouvet.no/bouvet-deler/utbrudd/building-and-testing-android-with-gradle-robolectric-and-espresso

http://fedepaol.github.io/blog/2015/09/05/mocking-with-robolectric-and-dagger-2/

Robolectric is intended to be fully compatible with Android’s official testing libraries since version 4.0.
As such we encourage you to try these new APIs and provide feedback.
At some point the Robolectric equivalents will be deprecated and removed. <---
Using the AndroidX Test APIs reduces the cognative load for you as a developer,
with just one set of APIs to learn for the same Android concept, no matter if you are writing an Robolectric test or an instrumentation test.
Furthermore it will make your tests more portable and compatible with our future plans.

http://robolectric.org/androidx_test/

if need ApplicationProvider.getApplicationContext(), must have @RunWith(AndroidJUnit4.class)
 */
// @SmallTest // ??
// @RunWith(RobolectricTestRunner.class)
@RunWith(AndroidJUnit4.class) // since Robolectric 4.0 , can use AndroidX to replace Robolectric
public class SimpleEntityReadWriteTestRobolectric {

    private ParkingLotDao mParkingLotDao;
    private AppDatabase mDb;

    /**
     * IMPORTANT for testing RxJava with Robolectric
     * <p>
     * https://medium.com/@peter.tackage/overriding-rxandroid-schedulers-in-rxjava-2-5561b3d14212
     * https://medium.com/@peter.tackage/an-alternative-to-rxandroidplugins-and-rxjavaplugins-scheduler-injection-9831bbc3dfaf
     * https://stackoverflow.com/questions/43285064/rxjava-2-overriding-io-scheduler-in-unit-test
     */
    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

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

        ParkingLot parkingLotNew = ParkingLot.create(1, "area", "name");
//        ParkingLot parkingLotNew = new ParkingLot(1, "area", "name");
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

        Thread.sleep(3000);
    }
}
