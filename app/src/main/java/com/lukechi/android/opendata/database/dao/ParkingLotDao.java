package com.lukechi.android.opendata.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.lukechi.android.opendata.database.entity.ParkingLot;

@Dao
public interface ParkingLotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertParkingLots(ParkingLot... parkingLots);

    @Query("SELECT * FROM parking_lot")
    ParkingLot[] loadAllParkingLot();

}
