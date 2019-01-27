package com.lukechi.android.opendata.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;

@Database(
        entities = {ParkingLot.class},
//        views = {UserDetail.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ParkingLotDao parkingLotDao();
}
