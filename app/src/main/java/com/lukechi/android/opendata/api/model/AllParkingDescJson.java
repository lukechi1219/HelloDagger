
package com.lukechi.android.opendata.api.model;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.lukechi.android.opendata.util.DateUtil;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.List;

@AutoValue
@Generated("net.hexar.json2pojo")
public abstract class AllParkingDescJson implements Parcelable {

    @Json(name = "data")
    public abstract AllParkingDescData data();

    public static JsonAdapter<AllParkingDescJson> typeAdapter(Moshi moshi) {
        return new AutoValue_AllParkingDescJson.MoshiJsonAdapter(moshi);
    }

    /**
     * inner
     */
    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class AllParkingDescData implements Parcelable {

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
        public abstract List<ParkingLotDesc> descList();

        public static JsonAdapter<AllParkingDescData> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_AllParkingDescData.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class ParkingLotDesc {

        @Json(name = "AED_Equipment")
        private String mAEDEquipment;
        @Json(name = "Accessibility_Elevator")
        private String mAccessibilityElevator;
        @Json(name = "CellSignal_Enhancement")
        private String mCellSignalEnhancement;
        @Json(name = "ChargeStation")
        private ChargeStation mChargeStation;
        @Json(name = "ChargingStation")
        private String mChargingStation;
        @Json(name = "Child_Pickup_Area")
        private String mChildPickupArea;
        @Json(name = "Handicap_First")
        private String mHandicapFirst;
        @Json(name = "Phone_Charge")
        private String mPhoneCharge;
        @Json(name = "Pregnancy_First")
        private String mPregnancyFirst;
        @Json(name = "Taxi_OneHR_Free")
        private String mTaxiOneHRFree;
        @Json(name = "totalbike")
        private Long mTotalbike;
        @Json(name = "totalbus")
        private Long mTotalbus;
        @Json(name = "totalcar")
        private Long mTotalcar;
        @Json(name = "totallargemotor")
        private String mTotallargemotor;
        @Json(name = "totalmotor")
        private Long mTotalmotor;

        @Json(name = "id")
        public abstract String id();

        @Json(name = "area")
        public abstract String area();

        @Json(name = "name")
        public abstract String name();

        @Json(name = "address")
        public abstract String address();

        @Json(name = "tel")
        public abstract String tel();

        @Json(name = "summary")
        public abstract String summary();

        @Json(name = "payex")
        public abstract String payExplain();

        @Json(name = "serviceTime")
        public abstract String serviceTime();

        @Nullable
        @Json(name = "tw97x")
        public abstract String tw97x();

        @Nullable
        @Json(name = "tw97y")
        public abstract String tw97y();

        @Json(name = "type")
        public abstract String type();

        @Nullable
        @Json(name = "type2")
        public abstract String type2();


        @Json(name = "FareInfo")
        public abstract FareInfo fareInfo();

        @Json(name = "EntranceCoord")
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

        public static JsonAdapter<ParkingLotDesc> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_ParkingLotDesc.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class EntranceCoord {

        @Nullable
        @Json(name = "EntrancecoordInfo")
        public abstract List<EntrancecoordInfo> getEntrancecoordInfo();

        public static JsonAdapter<EntranceCoord> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_EntranceCoord.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class EntrancecoordInfo {

        @Json(name = "Address")
        public abstract String getAddress();

        @Json(name = "Xcod")
        public abstract String getXcod();

        @Json(name = "Ycod")
        public abstract String getYcod();

        public static JsonAdapter<EntrancecoordInfo> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_EntrancecoordInfo.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class FareInfo {

        @Nullable
        @Json(name = "Holiday")
        public abstract List<Holiday> getHoliday();

        @Nullable
        @Json(name = "WorkingDay")
        public abstract List<WorkingDay> getWorkingDay();

        public static JsonAdapter<FareInfo> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_FareInfo.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class Holiday {

        @Json(name = "Fare")
        public abstract String getFare();

        @Json(name = "Period")
        public abstract String getPeriod();

        public static JsonAdapter<Holiday> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_Holiday.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class WorkingDay {

        @Json(name = "Fare")
        public abstract String getFare();

        @Json(name = "Period")
        public abstract String getPeriod();

        public static JsonAdapter<WorkingDay> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_WorkingDay.MoshiJsonAdapter(moshi);
        }
    }

    @AutoValue
    @Generated("net.hexar.json2pojo")
    public static abstract class ChargeStation {

        @Json(name = "availableCount")
        private Long mAvailableCount;
        @Json(name = "contactMobilNo")
        private String mContactMobilNo;
        @Json(name = "contactName")
        private String mContactName;
        @Json(name = "country")
        private String mCountry;
        @Json(name = "isCharge")
        private String mIsCharge;
        @Json(name = "locLatitude")
        private Double mLocLatitude;
        @Json(name = "locLongitude")
        private Double mLocLongitude;
        @Json(name = "openFlag")
        private String mOpenFlag;
        @Json(name = "scoketCount")
        private Long mScoketCount;
        @Json(name = "StationAddr")
        private String mStationAddr;
        @Json(name = "StationName")
        private String mStationName;
        @Json(name = "town")
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

        public static JsonAdapter<ChargeStation> typeAdapter(Moshi moshi) {
            return new AutoValue_AllParkingDescJson_ChargeStation.MoshiJsonAdapter(moshi);
        }
    }

}
