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
    public abstract AllAvailableLotsData getData();

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
        public String getUpdateTime() {
            if (updateTimeGMT != null) {
                return updateTimeGMT;
            }
            updateTimestamp = DateUtil.parseCST(getUpdateTimeCST());
            updateTimeGMT = DateUtil.formatToGMT(updateTimestamp);

            if (updateTimeGMT != null) {
                return updateTimeGMT;
            }
            return getUpdateTimeCST();
        }

        public Timestamp getUpdateTimestamp() {
            return updateTimestamp;
        }

        // for debug
        // "Thu Jan 24 05:25:00 CST 2019"
        @SerializedName("UPDATETIME")
        public abstract String getUpdateTimeCST();

        @SerializedName("park")
        public abstract List<AllAvailableLot> getParkingLots();

        public static TypeAdapter<AllAvailableLotsData> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_AllAvailableLotsData.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public static abstract class AllAvailableLot implements Parcelable {

        @SerializedName("id")
        public abstract String getId();

        @SerializedName("availablecar")
        public abstract String getAvailableCar();

        @SerializedName("availablemotor")
        public abstract String getAvailableMotor();

        @SerializedName("availablebus")
        public abstract String getAvailableBus();

        @Nullable
        @SerializedName("ChargeStation")
        public abstract ChargeStation getChargeStation();

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
        public abstract List<SocketStatus> getSocketStatusList();

        public static TypeAdapter<ChargeStation> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_ChargeStation.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public static abstract class SocketStatus implements Parcelable {

        @SerializedName("spot_abrv")
        public abstract String getSpotAbrv();

        @SerializedName("spot_status")
        public abstract String getSpotStatus();

        public static TypeAdapter<SocketStatus> typeAdapter(Gson gson) {
            return new AutoValue_AllAvailableLotsJson_SocketStatus.GsonTypeAdapter(gson);
        }
    }
}
