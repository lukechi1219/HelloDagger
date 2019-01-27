package com.lukechi.android.opendata.api.model;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lukechi.android.opendata.util.DateUtil;

import java.sql.Timestamp;
import java.util.List;

@AutoValue
public abstract class AllAvailableLotsJson implements Parcelable {

    @SerializedName("data")
    public abstract AllAvailableLotsData data();

    public static TypeAdapter<AllAvailableLotsJson> typeAdapter(Gson gson) {
        return new AutoValue_AllAvailableLotsJson.GsonTypeAdapter(gson);
    }

    /**
     * inner
     */
    @AutoValue
    public static abstract class AllAvailableLotsData implements Parcelable {

        // @Expose is optional and it has two configuration parameters: serialize and deserialize. By default they're set to true.
        @Expose(deserialize = false)
        String updateTimeGMT;

        @Expose(serialize = false, deserialize = false)
        Timestamp updateTimestamp;

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
        @SerializedName("UPDATETIME")
        public abstract String updateTimeCST();

        @SerializedName("park")
        public abstract List<AllAvailableLot> parkingLots();

        public static TypeAdapter<AllAvailableLotsData> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_AllAvailableLotsData.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public static abstract class AllAvailableLot implements Parcelable {

        @SerializedName("id")
        public abstract String id();

        @SerializedName("availablecar")
        public abstract String availableCar();

        @SerializedName("availablemotor")
        public abstract String availableMotor();

        @SerializedName("availablebus")
        public abstract String availableBus();

        @Nullable
        @SerializedName("ChargeStation")
        public abstract ChargeStation chargeStations();

        public static TypeAdapter<AllAvailableLot> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_AllAvailableLot.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public static abstract class ChargeStation implements Parcelable {
        /**
         * mother fucker... API field Socket spell wrongly to Scoket...
         */
        @SerializedName("scoketStatusList")
        public abstract List<SocketStatus> socketStatusList();

        public static TypeAdapter<ChargeStation> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_ChargeStation.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public static abstract class SocketStatus implements Parcelable {

        @SerializedName("spot_abrv")
        public abstract String spotAbrv();

        @SerializedName("spot_status")
        public abstract String spotStatus();

        public static TypeAdapter<SocketStatus> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_SocketStatus.GsonTypeAdapter(gson);
        }
    }
}
