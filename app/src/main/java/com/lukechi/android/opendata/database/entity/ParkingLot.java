package com.lukechi.android.opendata.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;

/**
 * https://issuetracker.google.com/issues/62408420#comment27
 * <p>
 * Roo + AutoValue
 * <p>
 * // Enabling SQLite FTS in Room 2.1
 * // https://medium.com/@sienatime/enabling-sqlite-fts-in-room-2-1-75e17d0f0ff8
 */
// Use `@Fts3` only if your app has strict disk space requirements or if you
// require compatibility with an older SQLite version.
// https://developer.android.com/reference/androidx/room/FtsOptions
@Entity(tableName = "parking_lot") // use index force unique
// MEMO: comment out @Fts4 because Robolectric has issue recognize CREATE VIRTUAL TABLE statement...
// @Fts4 // (languageId = "lid") // full-text search
// Sometimes, certain fields or groups of fields in a database must be unique.
// @Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
@AutoValue
// @PrimaryKey, @ColumnInfo, @Embedded, and @Relation. When using these annotations, however, you must include
// the @CopyAnnotations annotation each time so that Room can interpret the methods' auto-generated implementations properly.
public abstract class ParkingLot {
    // Specifying a primary key for an FTS-table-backed entity is optional, so no need to define rowId.
    // but if you include one, it must use type int and column name "rowid" with lower case 'i' .
    // Note: FTS-enabled tables always use a primary key of type INTEGER and with the column name "rowid".
    // Supported annotations must include `@CopyAnnotations`.

    // autoGenerate = true:
    // If the field type is long or int (or its TypeConverter converts it to a long or int),
    // Insert methods treat 0 as not-set while inserting the item.
    // https://developer.android.google.cn/reference/android/arch/persistence/room/PrimaryKey#autogenerate
//    @CopyAnnotations
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
//    public abstract int id(); // ?? kapt compile complains no @PrimaryKey... wtf...

    // In cases where a table supports content in multiple languages, use the languageId option
    // to specify the column that stores language information for each row:
//    @ColumnInfo(name = "lid")
//    @Nullable
//    private final Integer lid();

//    @Ignore // example code
//    Bitmap picture;

    @CopyAnnotations
    @ColumnInfo(name = "area")
//    @NonNull
    public abstract String area();

    @CopyAnnotations
    @ColumnInfo(name = "name")
//    @NonNull
    public abstract String name();

//    public ParkingLot(int id, String area, String name) {
//        this.id = id;
//        this.area = area;
//        this.name = name;
//    }

    // MEMO: this is required for Room
    // ref: https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/room/integration-tests/autovaluetestapp/src/androidTest/java/androidx/room/integration/autovaluetestapp/vo/Person.java
    //
    public static ParkingLot create(int id, String area, String name) {
        ParkingLot parkingLot = builder()
//                .id(id)
//                .lid(lid)
                .area(area)
                .name(name)
                .build();

        parkingLot.id(id);
        return parkingLot;
    }

    public static Builder builder() {
        return new AutoValue_ParkingLot.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

//        public abstract Builder id(int id);

//        public abstract Builder lid(Integer lid);

        public abstract Builder area(String area);

        public abstract Builder name(String name);

        public abstract ParkingLot build();
    }

    public int id() {
        return id;
    }

    public void id(int id) {
        this.id = id;
    }

//    public String area() {
//        return area;
//    }
//
//    public String name() {
//        return name;
//    }
}
