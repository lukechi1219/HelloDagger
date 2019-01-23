package com.lukechi.android.opendata.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    private static final String UPDATE_TIME_PATTERN_OF_CST = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * // "Thu Jan 24 05:25:00 CST 2019" 的 CST 中原標準時間 不是 java 的 CST 中央標準時間 (US GMT-6)
     */
    public static Timestamp parseCST(String updateTimeCST) {
        try {
            // Must be Locale.US or will have parse exp
            DateFormat dfCST = new SimpleDateFormat(UPDATE_TIME_PATTERN_OF_CST, Locale.US);
//            dfCST.setTimeZone(TimeZone.getTimeZone("GMT-6")); // apply US Central Time Zone ?
            Date dateInGMTMinusSix = dfCST.parse(updateTimeCST);

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateInGMTMinusSix);
            // GMT-6 -> GMT+8 ??
            cal.add(Calendar.HOUR_OF_DAY, -14);

            return new Timestamp(cal.getTime().getTime());
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
