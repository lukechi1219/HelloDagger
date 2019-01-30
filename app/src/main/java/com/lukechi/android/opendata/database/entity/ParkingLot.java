package com.lukechi.android.opendata.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;
import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;

/**
 * https://issuetracker.google.com/issues/62408420#comment27
 * <p>
 * Roo + AutoValue
 */
// Use `@Fts3` only if your app has strict disk space requirements or if you
// require compatibility with an older SQLite version.
// https://developer.android.com/reference/androidx/room/FtsOptions
@Fts4 // this cause error... (languageId = "lid") // full-text search
@Entity(tableName = "parking_lot")
// Sometimes, certain fields or groups of fields in a database must be unique.
// @Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
@AutoValue
// @PrimaryKey, @ColumnInfo, @Embedded, and @Relation. When using these annotations, however, you must include
// the @CopyAnnotations annotation each time so that Room can interpret the methods' auto-generated implementations properly.
public abstract class ParkingLot {
    // Specifying a primary key for an FTS-table-backed entity is optional, but
    // if you include one, it must use this type and column name.
    // Note: FTS-enabled tables always use a primary key of type INTEGER and with the column name "rowid".
    // Supported annotations must include `@CopyAnnotations`.
    @CopyAnnotations
    @PrimaryKey // default (autoGenerate = false)
    @ColumnInfo(name = "rowid")
    public abstract int rowId();

    // In cases where a table supports content in multiple languages, use the languageId option
    // to specify the column that stores language information for each row:
    @CopyAnnotations
    @ColumnInfo(name = "lid")
    public abstract int lid();

    @CopyAnnotations
    @ColumnInfo(name = "id")
    public abstract int id();

//    @CopyAnnotations
//    @Ignore // example code
//    Bitmap picture;

    //    @ColumnInfo(name = "area")
    public abstract String area();

    //    @ColumnInfo(name = "name")
    public abstract String name();

    // MEMO: this is required for Room
    // ref: https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/room/integration-tests/autovaluetestapp/src/androidTest/java/androidx/room/integration/autovaluetestapp/vo/Person.java
    public static ParkingLot create(int rowId, int lid, int id, String area, String name) {
        return builder()
                .rowId(rowId)
                .lid(lid)
                .id(id)
                .area(area)
                .name(name)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_ParkingLot.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder rowId(int rowId);

        public abstract Builder lid(int lid);

        public abstract Builder id(int id);

        public abstract Builder area(String area);

        public abstract Builder name(String name);

        public abstract ParkingLot build();
    }
}
