package com.lukechi.android.opendata.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.lukechi.android.opendata.database.entity.ParkingLot;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface ParkingLotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertParkingLots(ParkingLot... parkingLots);

    // "Single" class of RxJava2
    @Query("SELECT * FROM parking_lot")
    Single<List<ParkingLot>> loadAllParkingLot();

//    /**
//     * @Transaction annotation is useful when we need to perform multiple operations in one method, which is more efficient than running each operation on it's own.
//     */
//    // !!! Interface abstract methods cannot have body !!!
//    @Transaction
//    void updateTable(ParkingLot... parkingLots) {
//        clearTable();
//        insertParkingLots(parkingLots);
//    }
}
