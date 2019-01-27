
package com.lukechi.android.opendata.api.model;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lukechi.android.opendata.util.DateUtil;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.List;

@AutoValue
@Generated("net.hexar.json2pojo")
public abstract class AllParkingDescJson implements Parcelable {

    @SerializedName("data")
    public abstract AllParkingDescData data();

    public static TypeAdapter<AllParkingDescJson> typeAdapter(Gson gson) {
        return new AutoValue_AllParkingDescJson.GsonTypeAdapter(gson);
    }

    /**
     * inner
     */
    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class AllParkingDescData implements Parcelable {

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
        public abstract List<ParkingLotDesc> descList();

        public static TypeAdapter<AllParkingDescData> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_AllParkingDescData.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class ParkingLotDesc {

        @SerializedName("AED_Equipment")
        private String mAEDEquipment;
        @SerializedName("Accessibility_Elevator")
        private String mAccessibilityElevator;
        @SerializedName("CellSignal_Enhancement")
        private String mCellSignalEnhancement;
        @SerializedName("ChargeStation")
        private ChargeStation mChargeStation;
        @SerializedName("ChargingStation")
        private String mChargingStation;
        @SerializedName("Child_Pickup_Area")
        private String mChildPickupArea;
        @SerializedName("Handicap_First")
        private String mHandicapFirst;
        @SerializedName("Phone_Charge")
        private String mPhoneCharge;
        @SerializedName("Pregnancy_First")
        private String mPregnancyFirst;
        @SerializedName("Taxi_OneHR_Free")
        private String mTaxiOneHRFree;
        @SerializedName("totalbike")
        private Long mTotalbike;
        @SerializedName("totalbus")
        private Long mTotalbus;
        @SerializedName("totalcar")
        private Long mTotalcar;
        @SerializedName("totallargemotor")
        private String mTotallargemotor;
        @SerializedName("totalmotor")
        private Long mTotalmotor;

        @SerializedName("id")
        public abstract String id();

        @SerializedName("area")
        public abstract String area();

        @SerializedName("name")
        public abstract String name();

        @SerializedName("address")
        public abstract String address();

        @SerializedName("tel")
        public abstract String tel();

        @SerializedName("summary")
        public abstract String summary();

        @SerializedName("payex")
        public abstract String payExplain();

        @SerializedName("serviceTime")
        public abstract String serviceTime();

        @Nullable
        @SerializedName("tw97x")
        public abstract String tw97x();

        @Nullable
        @SerializedName("tw97y")
        public abstract String tw97y();

        @SerializedName("type")
        public abstract String type();

        @Nullable
        @SerializedName("type2")
        public abstract String type2();


        @SerializedName("FareInfo")
        public abstract FareInfo fareInfo();

        @SerializedName("EntranceCoord")
        public abstract EntranceCoord entranceCoord();

        public String getAEDEquipment() {
            return mAEDEquipment;
        }

        public String getAccessibilityElevator() {
            return mAccessibilityElevator;
        }

        public String getCellSignalEnhancement() {
            return mCellSignalEnhancement;
        }

        public ChargeStation getChargeStation() {
            return mChargeStation;
        }

        public String getChargingStation() {
            return mChargingStation;
        }

        public String getChildPickupArea() {
            return mChildPickupArea;
        }


        public String getHandicapFirst() {
            return mHandicapFirst;
        }


        public String getPhoneCharge() {
            return mPhoneCharge;
        }

        public String getPregnancyFirst() {
            return mPregnancyFirst;
        }


        public String getTaxiOneHRFree() {
            return mTaxiOneHRFree;
        }

        public Long getTotalbike() {
            return mTotalbike;
        }

        public Long getTotalbus() {
            return mTotalbus;
        }

        public Long getTotalcar() {
            return mTotalcar;
        }

        public String getTotallargemotor() {
            return mTotallargemotor;
        }

        public Long getTotalmotor() {
            return mTotalmotor;
        }

        public static TypeAdapter<ParkingLotDesc> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_ParkingLotDesc.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class EntranceCoord {

        @SerializedName("EntrancecoordInfo")
        public abstract List<EntrancecoordInfo> getEntrancecoordInfo();

        public static TypeAdapter<EntranceCoord> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_EntranceCoord.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class EntrancecoordInfo {

        @SerializedName("Address")
        public abstract String getAddress();

        @SerializedName("Xcod")
        public abstract String getXcod();

        @SerializedName("Ycod")
        public abstract String getYcod();

        public static TypeAdapter<EntrancecoordInfo> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_EntrancecoordInfo.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class FareInfo {

        @SerializedName("Holiday")
        public abstract List<Holiday> getHoliday();

        @SerializedName("WorkingDay")
        public abstract List<WorkingDay> getWorkingDay();

        public static TypeAdapter<FareInfo> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_FareInfo.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class Holiday {

        @SerializedName("Fare")
        public abstract String getFare();

        @SerializedName("Period")
        public abstract String getPeriod();

        public static TypeAdapter<Holiday> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_Holiday.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class WorkingDay {

        @SerializedName("Fare")
        public abstract String getFare();

        @SerializedName("Period")
        public abstract String getPeriod();

        public static TypeAdapter<WorkingDay> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_WorkingDay.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class ChargeStation {

        @SerializedName("availableCount")
        private Long mAvailableCount;
        @SerializedName("contactMobilNo")
        private String mContactMobilNo;
        @SerializedName("contactName")
        private String mContactName;
        @SerializedName("country")
        private String mCountry;
        @SerializedName("isCharge")
        private String mIsCharge;
        @SerializedName("locLatitude")
        private Double mLocLatitude;
        @SerializedName("locLongitude")
        private Double mLocLongitude;
        @SerializedName("openFlag")
        private String mOpenFlag;
        @SerializedName("scoketCount")
        private Long mScoketCount;
        @SerializedName("StationAddr")
        private String mStationAddr;
        @SerializedName("StationName")
        private String mStationName;
        @SerializedName("town")
        private String mTown;

        public Long getAvailableCount() {
            return mAvailableCount;
        }

        public String getContactMobilNo() {
            return mContactMobilNo;
        }

        public String getContactName() {
            return mContactName;
        }

        public String getCountry() {
            return mCountry;
        }

        public String getIsCharge() {
            return mIsCharge;
        }

        public Double getLocLatitude() {
            return mLocLatitude;
        }

        public Double getLocLongitude() {
            return mLocLongitude;
        }

        public String getOpenFlag() {
            return mOpenFlag;
        }

        public Long getScoketCount() {
            return mScoketCount;
        }

        public String getStationAddr() {
            return mStationAddr;
        }

        public String getStationName() {
            return mStationName;
        }

        public String getTown() {
            return mTown;
        }

        public static TypeAdapter<ChargeStation> typeAdapter(Gson gson) {
            return new AutoValue_AllParkingDescJson_ChargeStation.GsonTypeAdapter(gson);
        }
    }

}
