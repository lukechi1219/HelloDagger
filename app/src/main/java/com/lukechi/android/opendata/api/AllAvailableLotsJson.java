package com.lukechi.android.opendata.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllAvailableLotsJson {

    @SerializedName("data")
    @Expose
    private AllAvailableLotsData data;

    public AllAvailableLotsData getData() {
        return data;
    }

    /**
     * inner
     */
    public static class AllAvailableLotsData {

        @SerializedName("UPDATETIME")
        @Expose
        String updateTime;

        @SerializedName("park")
        @Expose
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
        @Expose
        String id;

        @SerializedName("availablecar")
        @Expose
        String availableCar;

        @SerializedName("availablemotor")
        @Expose
        String availableMotor;

        @SerializedName("availablebus")
        @Expose
        String availableBus;

        @SerializedName("ChargeStation")
        @Expose
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
        @Expose
        List<SocketStatus> socketStatusList;

        public List<SocketStatus> getSocketStatusList() {
            return socketStatusList;
        }
    }

    public static class SocketStatus {

        @SerializedName("spot_abrv")
        @Expose
        String spotAbrv;

        @SerializedName("spot_status")
        @Expose
        String spotStatus;

        public String getSpotAbrv() {
            return spotAbrv;
        }

        public String getSpotStatus() {
            return spotStatus;
        }
    }
}
