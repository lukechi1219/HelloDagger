package com.lukechi.android.opendata.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.lukechi.android.opendata.database.dao.ParkingLotDao;
import com.lukechi.android.opendata.database.entity.ParkingLot;

/**
 * https://medium.com/mindorks/room-with-rxjava-and-dagger-2722f4420651
 * https://medium.com/mindorks/migration-from-mvp-to-mvvm-using-android-architecture-components-4bc058a1f73c
 * https://medium.com/mindorks/pagination-using-paging-library-with-rxjava-and-dagger-d9d05dbd8eac
 * <p>
 * https://android.jlelse.eu/sqlite-on-android-made-simple-room-persistence-library-with-a-touch-of-rxjava-55e8dc5301cf
 * <p>
 * https://android.jlelse.eu/rest-api-on-android-made-simple-or-how-i-learned-to-stop-worrying-and-love-the-rxjava-b3c2c949cad4
 * https://medium.com/@alahammad/database-with-room-using-rxjava-764ee6124974
 * <p>
 * https://github.com/ashwini009/TvMaze/pull/3
 */
@Database(
        entities = {ParkingLot.class},
//        views = {UserDetail.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ParkingLotDao parkingLotDao();
}

/*
https://dev.to/node/implementation-of-full-text-search-on-android--1n
https://stackoverflow.com/questions/29815248/full-text-search-example-in-android
https://developer.android.com/training/search/search
https://developer.android.com/training/search/

 */