
package com.lukechi.android.opendata.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lukechi.android.opendata.util.DateUtil;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.List;

@Generated("net.hexar.json2pojo")
public class AllParkingDescJson {

    @SerializedName("data")
    private AllParkingDescData data;

    public AllParkingDescData getData() {
        return data;
    }

    /**
     * inner
     */
    @Generated("net.hexar.json2pojo")
    public static class AllParkingDescData {

        // "Thu Jan 24 05:25:00 CST 2019"
        @SerializedName("UPDATETIME")
        private String updateTimeCST;

        // @Expose is optional and it has two configuration parameters: serialize and deserialize. By default they're set to true.
        @Expose(deserialize = false)
        private String updateTimeGMT;

        @Expose(serialize = false, deserialize = false)
        private Timestamp updateTimestamp;

        @SerializedName("park")
        private List<ParkingLotDesc> parkingDescList;

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

        public List<ParkingLotDesc> getDescList() {
            return parkingDescList;
        }
    }

    @Generated("net.hexar.json2pojo")
    public static class ParkingLotDesc {

        @SerializedName("id")
        private String id;

        @SerializedName("area")
        private String area;

        @SerializedName("name")
        private String name;

        @SerializedName("AED_Equipment")
        private String mAEDEquipment;
        @SerializedName("Accessibility_Elevator")
        private String mAccessibilityElevator;
        @SerializedName("address")
        private String mAddress;
        @SerializedName("CellSignal_Enhancement")
        private String mCellSignalEnhancement;
        @SerializedName("ChargeStation")
        private ChargeStation mChargeStation;
        @SerializedName("ChargingStation")
        private String mChargingStation;
        @SerializedName("Child_Pickup_Area")
        private String mChildPickupArea;
        @SerializedName("EntranceCoord")
        private EntranceCoord mEntranceCoord;
        @SerializedName("FareInfo")
        private FareInfo mFareInfo;
        @SerializedName("Handicap_First")
        private String mHandicapFirst;
        @SerializedName("payex")
        private String mPayex;
        @SerializedName("Phone_Charge")
        private String mPhoneCharge;
        @SerializedName("Pregnancy_First")
        private String mPregnancyFirst;
        @SerializedName("serviceTime")
        private String mServiceTime;
        @SerializedName("summary")
        private String mSummary;
        @SerializedName("Taxi_OneHR_Free")
        private String mTaxiOneHRFree;
        @SerializedName("tel")
        private String mTel;
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
        @SerializedName("tw97x")
        private String mTw97x;
        @SerializedName("tw97y")
        private String mTw97y;
        @SerializedName("type")
        private String mType;
        @SerializedName("type2")
        private String mType2;

        public String getId() {
            return id;
        }

        public String getArea() {
            return area;
        }

        public String getName() {
            return name;
        }

        public String getAEDEquipment() {
            return mAEDEquipment;
        }

        public String getAccessibilityElevator() {
            return mAccessibilityElevator;
        }

        public String getAddress() {
            return mAddress;
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

        public EntranceCoord getEntranceCoord() {
            return mEntranceCoord;
        }

        public FareInfo getFareInfo() {
            return mFareInfo;
        }

        public String getHandicapFirst() {
            return mHandicapFirst;
        }

        public String getPayex() {
            return mPayex;
        }

        public String getPhoneCharge() {
            return mPhoneCharge;
        }

        public String getPregnancyFirst() {
            return mPregnancyFirst;
        }

        public String getServiceTime() {
            return mServiceTime;
        }

        public String getSummary() {
            return mSummary;
        }

        public String getTaxiOneHRFree() {
            return mTaxiOneHRFree;
        }

        public String getTel() {
            return mTel;
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

        public String getTw97x() {
            return mTw97x;
        }

        public String getTw97y() {
            return mTw97y;
        }

        public String getType() {
            return mType;
        }

        public String getType2() {
            return mType2;
        }
    }

    @Generated("net.hexar.json2pojo")
    public static class EntranceCoord {

        @SerializedName("EntrancecoordInfo")
        private List<EntrancecoordInfo> mEntrancecoordInfo;

        public List<EntrancecoordInfo> getEntrancecoordInfo() {
            return mEntrancecoordInfo;
        }

    }

    @Generated("net.hexar.json2pojo")
    public static class EntrancecoordInfo {

        @SerializedName("Address")
        private String mAddress;
        @SerializedName("Xcod")
        private String mXcod;
        @SerializedName("Ycod")
        private String mYcod;

        public String getAddress() {
            return mAddress;
        }

        public String getXcod() {
            return mXcod;
        }

        public String getYcod() {
            return mYcod;
        }
    }

    @Generated("net.hexar.json2pojo")
    public static class FareInfo {

        @SerializedName("Holiday")
        private List<Holiday> mHoliday;
        @SerializedName("WorkingDay")
        private List<WorkingDay> mWorkingDay;

        public List<Holiday> getHoliday() {
            return mHoliday;
        }

        public List<WorkingDay> getWorkingDay() {
            return mWorkingDay;
        }

    }

    @Generated("net.hexar.json2pojo")
    public static class Holiday {

        @SerializedName("Fare")
        private String mFare;
        @SerializedName("Period")
        private String mPeriod;

        public String getFare() {
            return mFare;
        }

        public String getPeriod() {
            return mPeriod;
        }

    }

    @Generated("net.hexar.json2pojo")
    public class WorkingDay {

        @SerializedName("Fare")
        private String mFare;
        @SerializedName("Period")
        private String mPeriod;

        public String getFare() {
            return mFare;
        }

        public String getPeriod() {
            return mPeriod;
        }

    }

    @Generated("net.hexar.json2pojo")
    public static class ChargeStation {

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

    }

}
