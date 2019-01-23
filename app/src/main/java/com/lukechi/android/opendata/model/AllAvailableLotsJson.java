package com.lukechi.android.opendata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lukechi.android.opendata.util.DateUtil;

import java.sql.Timestamp;
import java.util.List;

public class AllAvailableLotsJson {

    @SerializedName("data")
    private AllAvailableLotsData data;

    public AllAvailableLotsData getData() {
        return data;
    }

    /**
     * inner
     */
    public static class AllAvailableLotsData {

        // "Thu Jan 24 05:25:00 CST 2019"
        @SerializedName("UPDATETIME")
        String updateTimeCST;

        // @Expose is optional and it has two configuration parameters: serialize and deserialize. By default they're set to true.
        @Expose(deserialize = false)
        String updateTimeGMT;

        @Expose(serialize = false, deserialize = false)
        Timestamp updateTimestamp;

        @SerializedName("park")
        List<AllAvailableLot> parkingLots;

        // return GMT
        public String getUpdateTime() {
            if (updateTimeGMT != null) {
                return updateTimeGMT;
            }
            updateTimestamp = DateUtil.parseCST(updateTimeCST);
            updateTimeGMT = DateUtil.formatToGMT(updateTimestamp);

            if (updateTimeGMT != null) {
                return updateTimeGMT;
            }
            return updateTimeCST;
        }

        public Timestamp getUpdateTimestamp() {
            return updateTimestamp;
        }

        // for debug
        public String getUpdateTimeCST() {
            return updateTimeCST;
        }

        public List<AllAvailableLot> getParkingLots() {
            return parkingLots;
        }
    }

    public static class AllAvailableLot {

        @SerializedName("id")
        String id;

        @SerializedName("availablecar")
        String availableCar;

        @SerializedName("availablemotor")
        String availableMotor;

        @SerializedName("availablebus")
        String availableBus;

        @SerializedName("ChargeStation")
        ChargeStation chargeStation;

        public String getId() {
            return id;
        }

        public String getAvailableCar() {
            return availableCar;
        }

        public String getAvailableMotor() {
            return availableMotor;
        }

        public String getAvailableBus() {
            return availableBus;
        }

        public ChargeStation getChargeStation() {
            return chargeStation;
        }
    }

    public static class ChargeStation {

        /**
         * mother fucker... API field Socket spell wrongly to Scoket...
         */
        @SerializedName("scoketStatusList")
        List<SocketStatus> socketStatusList;

        public List<SocketStatus> getSocketStatusList() {
            return socketStatusList;
        }
    }

    public static class SocketStatus {

        @SerializedName("spot_abrv")
        String spotAbrv;

        @SerializedName("spot_status")
        String spotStatus;

        public String getSpotAbrv() {
            return spotAbrv;
        }

        public String getSpotStatus() {
            return spotStatus;
        }
    }
}
