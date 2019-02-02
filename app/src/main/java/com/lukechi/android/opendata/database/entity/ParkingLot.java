package com.lukechi.android.opendata.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

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
@Fts4 // (languageId = "lid") // full-text search
// Sometimes, certain fields or groups of fields in a database must be unique.
// @Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
// @AutoValue
// @PrimaryKey, @ColumnInfo, @Embedded, and @Relation. When using these annotations, however, you must include
// the @CopyAnnotations annotation each time so that Room can interpret the methods' auto-generated implementations properly.
public class ParkingLot {
    // Specifying a primary key for an FTS-table-backed entity is optional, but
    // if you include one, it must use this type and column name.
    // Note: FTS-enabled tables always use a primary key of type INTEGER and with the column name "rowid".
    // Supported annotations must include `@CopyAnnotations`.

    // autoGenerate = true:
    // If the field type is long or int (or its TypeConverter converts it to a long or int),
    // Insert methods treat 0 as not-set while inserting the item.
    // https://developer.android.google.cn/reference/android/arch/persistence/room/PrimaryKey#autogenerate
    @PrimaryKey(autoGenerate = false) // use json data 'id' as rowId. or should use docid?
    @ColumnInfo(name = "rowid")
    private final Integer rowId; // Integer makes this nullable...

    @ColumnInfo(name = "id")
    private final int id;

    // In cases where a table supports content in multiple languages, use the languageId option
    // to specify the column that stores language information for each row:
//    @ColumnInfo(name = "lid")
//    @Nullable
//    private final Integer lid();

//    @Ignore // example code
//    Bitmap picture;

    @ColumnInfo(name = "area")
    @NonNull
    private final String area;

    @ColumnInfo(name = "name")
    @NonNull
    private final String name;

    public ParkingLot(Integer rowId, int id, @NonNull String area, @NonNull String name) {
        this.rowId = rowId;
        this.id = id;
        this.area = area;
        this.name = name;
    }

//    // MEMO: this is required for Room
//    // ref: https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/room/integration-tests/autovaluetestapp/src/androidTest/java/androidx/room/integration/autovaluetestapp/vo/Person.java
//    //
////    public static ParkingLot create(int rowId, Integer lid, String area, String name) {
//    public static ParkingLot create(int rowId, String area, String name) {
//        return builder()
//                .rowId(rowId)
////                .lid(lid)
//                .area(area)
//                .name(name)
//                .build();
//    }
//
//    public static Builder builder() {
//        return new AutoValue_ParkingLot.Builder();
//    }
//
//    @AutoValue.Builder
//    public abstract static class Builder {
//
//        public abstract Builder rowId(int rowId);
//
//        public abstract Builder lid(Integer lid);
//
//        public abstract Builder area(String area);
//
//        public abstract Builder name(String name);
//
//        public abstract ParkingLot build();
//    }

    public Integer rowId() {
        return rowId;
    }

    public int id() {
        return id;
    }

    public String area() {
        return area;
    }

    public String name() {
        return name;
    }
}
