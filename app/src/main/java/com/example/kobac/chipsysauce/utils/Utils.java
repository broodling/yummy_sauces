package com.example.kobac.chipsysauce;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class.
 */
public class Utils {

    /**
     * Convert dp to px.
     *
     * @param context The application context.
     * @param dp      The dp to convert.
     * @return The converted dp into px.
     */
    public static int dpToPx(final Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * Convert px to dp.
     *
     * @param context The application context.
     * @param px      The px to convert.
     * @return The converted px into dp.
     */
    public static int pxToDp(final Context context, final float px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * Format date by supplied patterns.
     *
     * @param date       The date to format.
     * @param patternOut The desirable pattern.
     * @return The formatted date.
     */
    public static String getFormattedDate(final String date, final String patternOut) {
        SimpleDateFormat dateFormatIn = new SimpleDateFormat(DATE_FORMAT);

        Date dateIn = null;
        try {
            dateIn = dateFormatIn.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormatOut = new SimpleDateFormat(patternOut);

        return dateFormatOut.format(dateIn);
    }

    public static String getFormatedDay(final String date, final String patternOut) {
        SimpleDateFormat dayFormatIn = new SimpleDateFormat(DATE_FORMAT);

        Date dayIn = null;
        try {
            dayIn = dayFormatIn.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dayFormatOut = new SimpleDateFormat(patternOut);

        return dayFormatOut.format(dayIn);
    }

    /**
     * The date format used for the whole application.
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static int round(String value) {
        return Math.round(Float.parseFloat(value));
    }

}
