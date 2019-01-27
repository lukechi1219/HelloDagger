package com.lukechi.android.opendata.api.model;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.lukechi.android.opendata.util.DateUtil;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.sql.Timestamp;
import java.util.List;

@AutoValue
public abstract class AllAvailableLotsJson implements Parcelable {

    @Json(name = "data")
    public abstract AllAvailableLotsData data();

    public static JsonAdapter<AllAvailableLotsJson> typeAdapter(Moshi moshi) {
        return new AutoValue_AllAvailableLotsJson.MoshiJsonAdapter(moshi);
    }

    /**
     * inner
     */
    @AutoValue
    public static abstract class AllAvailableLotsData implements Parcelable {

        // @Expose is optional and it has two configuration parameters: serialize and deserialize. By default they're set to true.
//        @Expose(deserialize = false)
        transient String updateTimeGMT;

        //        @Expose(serialize = false, deserialize = false)
        transient Timestamp updateTimestamp;

        // return GMT
        public String updateTime() {
            if (updateTimeGMT != null) {
                return updateTimeGMT;
            }
            updateTimestamp = DateUtil.parseCST(updateTimeCST());
            updateTimeGMT = DateUtil.formatToGMT(updateTimestamp);

            if (updateTimeGMT != null) {
                return updateTimeGMT;
            }
            return updateTimeCST();
        }

        public Timestamp updateTimestamp() {
            return updateTimestamp;
        }

        // for debug
        // "Thu Jan 24 05:25:00 CST 2019"
        @Json(name = "UPDATETIME")
        public abstract String updateTimeCST();

        @Json(name = "park")
        public abstract List<AllAvailableLot> parkingLots();

        public static JsonAdapter<AllAvailableLotsData> typeAdapter(Moshi moshi) {
            return new AutoValue_AllAvailableLotsJson_AllAvailableLotsData.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    public static abstract class AllAvailableLot implements Parcelable {

        @Json(name = "id")
        public abstract String id();

        @Json(name = "availablecar")
        public abstract String availableCar();

        @Json(name = "availablemotor")
        public abstract String availableMotor();

        @Json(name = "availablebus")
        public abstract String availableBus();

        @Nullable
        @Json(name = "ChargeStation")
        public abstract ChargeStation chargeStations();

        public static JsonAdapter<AllAvailableLot> typeAdapter(Moshi moshi) {
            return new AutoValue_AllAvailableLotsJson_AllAvailableLot.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    public static abstract class ChargeStation implements Parcelable {
        /**
         * mother fucker... API field Socket spell wrongly to Scoket...
         */
        @Json(name = "scoketStatusList")
        public abstract List<SocketStatus> socketStatusList();

        public static JsonAdapter<ChargeStation> typeAdapter(Moshi moshi) {
            return new AutoValue_AllAvailableLotsJson_ChargeStation.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    public static abstract class SocketStatus implements Parcelable {

        @Json(name = "spot_abrv")
        public abstract String spotAbrv();

        @Json(name = "spot_status")
        public abstract String spotStatus();

        public static JsonAdapter<SocketStatus> typeAdapter(Moshi moshi) {
            return new AutoValue_AllAvailableLotsJson_SocketStatus.MoshiJsonAdapter(moshi);
        }
    }
}
