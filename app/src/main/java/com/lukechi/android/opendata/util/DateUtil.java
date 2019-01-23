package com.lukechi.android.opendata.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    private static final String UPDATE_TIME_PATTERN_OF_CST = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static Timestamp parseCST(String updateTimeCST) {
        try {
            // Must be Locale.US or will have parse exp
            DateFormat dfCST = new SimpleDateFormat(UPDATE_TIME_PATTERN_OF_CST, Locale.US);
//            dfCST.setTimeZone(TimeZone.getTimeZone("GMT")); // no need modify Time Zone
            return new Timestamp(dfCST.parse(updateTimeCST).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatToGMT(Timestamp updateTimestamp) {
        if (updateTimestamp == null) {
            return null;
        }
        // Locale.TAIWAN seems not necessary?
        DateFormat dfGMT = new SimpleDateFormat(UPDATE_TIME_PATTERN_OF_CST, Locale.TAIWAN);
        // Must modify Time Zone. otherwise show TST
        dfGMT.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // expect output "星期四 一月 24 05:34:00 GMT+08:00 2019"
        return dfGMT.format(updateTimestamp);// CST to GMT
    }
}
