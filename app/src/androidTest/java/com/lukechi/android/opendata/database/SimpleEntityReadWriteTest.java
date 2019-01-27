package com.lukechi.android.opendata.database;

import android.content.Context;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {

    private ParkingLotDao mParkingLotDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

//        Context context = ApplicationProvider.getApplicationContext();
        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class).build();
        mParkingLotDao = mDb.parkingLotDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {

        System.out.println("write test");

        mParkingLotDao.loadAllParkingLot();

//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        mUserDao.insert(user);
//        List<User> byName = mUserDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
    }
}
