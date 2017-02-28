package com.gymworkout.example.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pfranca on 5/3/2016.
 */
public final class CalendarUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String getDefaultFormattedDate(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return sdf.format(cal.getTime());
    }

    public static String getDefaultFormattedDate(Calendar cal) {
        return sdf.format(cal.getTime());
    }

    public static Calendar cutOffTimeInformation(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static long cutOffTimeInformation(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
