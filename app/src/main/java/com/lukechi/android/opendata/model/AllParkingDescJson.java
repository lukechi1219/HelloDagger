package com.lukechi.android.opendata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lukechi.android.opendata.util.DateUtil;

import java.sql.Timestamp;
import java.util.List;

public class AllParkingDescJson {

    @SerializedName("data")
    private AllParkingDescData data;

    public AllParkingDescData getData() {
        return data;
    }

    /**
     * inner
     */
    public class AllParkingDescData {

        // "Thu Jan 24 05:25:00 CST 2019"
        @SerializedName("UPDATETIME")
        String updateTimeCST;

        // @Expose is optional and it has two configuration parameters: serialize and deserialize. By default they're set to true.
        @Expose(deserialize = false)
        String updateTimeGMT;

        @Expose(serialize = false, deserialize = false)
        Timestamp updateTimestamp;

        @SerializedName("park")
        List<ParkingLotDesc> parkingDescList;

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

        public List<ParkingLotDesc> getParkingDescList() {
            return parkingDescList;
        }
    }

    public class ParkingLotDesc {

        @SerializedName("id")
        String id;

        @SerializedName("area")
        String area;

        @SerializedName("name")
        String name;
        /*
      "id" : "001",
      "area" : "信義區",
      "name" : "府前廣場地下停車場",
      "type" : "1",
      "type2" : "2",
      "summary" : "為地下二層停車場，計有2024個小型車停車格，1137個機車停車位",
      "address" : "松壽路1號地下",
      "tel" : "25420001",
      "payex" : "計時：30元/時(9-18)，夜間10元/時(18-9)月票：全日3,400元，所在里優惠月票3,000元(信義區西村里及興隆里)，日間2,400元(7-19)，夜間1,000元(週一至週五19-8時及週六、日與行政機關放假之紀念日、民俗日)。市府員工全日月票2,900元，市府員工日間月票2,400元(7-22)，機車計次20元(當日當次，隔日另計)，機車月票300元，市府員工機車月票150元。",
      "serviceTime" : "00:00:00~23:59:59",
      "tw97x" : "306812.928",
      "tw97y" : "2769892.95",
      "totalcar" : 2025,
      "totalmotor" : 1360,
      "totalbike" : 31,
      "totalbus" : 0,
      "ChargeStation" : {
        "StationName" : "北市府前停車場",
        "StationAddr" : "台北市信義區松壽路1號B1",
        "locLongitude" : 121.56306,
        "locLatitude" : 25.036037,
        "openFlag" : "N",
        "isCharge" : "N",
        "contactName" : "詹朝清場長",
        "contactMobilNo" : "0928-228-830",
        "scoketCount" : 4,
        "availableCount" : 0,
        "country" : "台北市",
        "town" : "信義區"
      },
      "Pregnancy_First" : "40",
      "Handicap_First" : "45",
      "totallargemotor" : "0",
      "ChargingStation" : "4",
      "Taxi_OneHR_Free" : "0",
      "AED_Equipment" : "0",
      "CellSignal_Enhancement" : "0",
      "Accessibility_Elevator" : "0",
      "Phone_Charge" : "0",
      "Child_Pickup_Area" : "0",
      "FareInfo" : {
        "WorkingDay" : [ {
          "Period" : "00~09",
          "Fare" : "10"
        }, {
          "Period" : "09~18",
          "Fare" : "30"
        }, {
          "Period" : "18~24",
          "Fare" : "10"
        } ],
        "Holiday" : [ {
          "Period" : "00~09",
          "Fare" : "10"
        }, {
          "Period" : "09~18",
          "Fare" : "30"
        }, {
          "Period" : "18~24",
          "Fare" : "10"
        } ]
      },
      "EntranceCoord" : {
        "EntrancecoordInfo" : [ {
          "Xcod" : "25.03648987",
          "Ycod" : "121.5621068",
          "Address" : "基隆路一段"
        }, {
          "Xcod" : "25.036014",
          "Ycod" : "121.563163",
          "Address" : "松壽路"
        }, {
          "Xcod" : "25.035975",
          "Ycod" : "121.561532",
          "Address" : "基隆路一段車行地下道"
        } ]
      }
       */

        public String getId() {
            return id;
        }

        public String getArea() {
            return area;
        }

        public String getName() {
            return name;
        }
    }
}
