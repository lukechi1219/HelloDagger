package com.lukechi.android.opendata.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.lukechi.android.opendata.database.entity.ParkingLot;

@Dao
public interface ParkingLotDao {

    @Query("SELECT * FROM parking_lot")
    ParkingLot[] loadAllParkingLot();

}
