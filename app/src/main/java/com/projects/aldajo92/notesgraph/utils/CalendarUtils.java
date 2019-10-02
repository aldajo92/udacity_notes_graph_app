package com.projects.aldajo92.notesgraph.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarUtils {

    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_LABEL_FORMAT = "MMM dd yyyy - HH:mm";
    public static String HEADER_FORMAT = "MMM d";

    public static Calendar stringToCalendar(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault());
        calendar.setTime(dateFormat.parse(date));
        return calendar;
    }

    public static Calendar timestampToCalendar(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }

    public static String calendarToString(Calendar calendar, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static String timestampToCalendarString(long timestamp, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(timestamp);
    }
}
