package com.lukechi.android.opendata.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllAvailableLotsJson {

    // @Expose is optional and it has two configuration parameters: serialize and deserialize. By default they're set to true.
    // @Expose or @Expose(serialize = false)
    @SerializedName("data")
    private AllAvailableLotsData data;

    public AllAvailableLotsData getData() {
        return data;
    }

    /**
     * inner
     */
    public static class AllAvailableLotsData {

        @SerializedName("UPDATETIME")
        String updateTime;

        @SerializedName("park")
        List<AllAvailableLot> parkingLots;

        public String getUpdateTime() {
            return updateTime;
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
