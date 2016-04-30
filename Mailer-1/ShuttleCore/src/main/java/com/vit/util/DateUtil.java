package com.vit.util;

import com.vit.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 *         to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";

    public static final String DATE_REFERENCE_TODAY = "today";
    public static final String DATE_REFERENCE_IMMEDIATELY = "immediately";
    public static final String DATE_REFERENCE_TOMORROW = "tomorrow";

    public static final String DATE_FORMAT_DB = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DB_DATE = "yyyy-MM-dd";
    public static final SimpleDateFormat dateParameterFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    public static final SimpleDateFormat dateFormatDB = new SimpleDateFormat(DATE_FORMAT_DB);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)
                    .getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "MM/dd/yyyy";
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws java.text.ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws java.text.ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws java.text.ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the last possible millisecond of the day, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfDay(Date day) {
        return getEndOfDay(day, Calendar.getInstance());
    }

    public static Date getEndOfDay(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the first possible millisecond of the first day of the month, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfMonth(Date day) {
        return getStartOfMonth(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the first possible millisecond of the first day of the month, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfMonth(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getStartOfLastMonth(Date day) {
        return getStartOfLastMonth(day, Calendar.getInstance());
    }

    public static Date getStartOfLastMonth(Date day , Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

        /**
         * Returns a Date set to the first possible millisecond of the first day of the week, just
         * after midnight. If a null day is passed in, a new Date is created.
         * midnight (00m 00h 00s)
         */
    public static Date getStartOfWeek(Date day) {
        return getStartOfWeek(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the first possible millisecond of the first day of the week, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfWeek(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getStartOfYear(Date year) {
        return getStartOfYear(year, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the first possible millisecond of the first day of the year, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfYear(Date year, Calendar cal) {
        if (year == null)
            year = new Date();

        cal.setTime(year);
        cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }


    public static Date getEndOfYear(Date year) {
        return getEndOfYear(year, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the last possible millisecond of the last day of the month, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfYear(Date year, Calendar cal) {
        if (year == null)
            year = new Date();

        cal.setTime(year);
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getStartOfQuarter(Date day) {
        return getStartOfMonth(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the last possible millisecond of the last day of the month, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfQuarter(Date day) {
        return getEndOfQuarter(day, Calendar.getInstance());
    }

    public static Date getEndOfQuarter(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.add(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the last possible millisecond of the last day of the month, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfMonth(Date day) {
        return getEndOfMonth(day, Calendar.getInstance());
    }

    public static Date getEndOfMonth(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getEndOfLastMonth(Date day) {
        return getEndOfLastMonth(day, Calendar.getInstance());
    }

    public static Date getEndOfLastMonth(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the last possible millisecond of the last day of the week, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfWeek(Date day) {
        return getEndOfWeek(day, Calendar.getInstance());
    }

    public static Date getEndOfWeek(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    // checks if  date1 is after date2

    public static boolean isAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        if (c1.after(c2)) {
            return true;
        } else {
            return false;
        }
    }

    // checks if  date1 is before date2

    public static boolean isBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        if (c1.before(c2)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date addDays(Date date, int numberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }
    

    public static Date addWeeks(Date date, int numberOfWeeks) {
        return addDays(date, -7);
    }

    public static Date addMonths(Date date, int numberOfMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, numberOfMonths);
        return calendar.getTime();
    }

    public static Date getDateParameterFormat(String date) throws ParseException {
        return dateParameterFormat.parse(date);
    }

    public static Date getModifiedDateAfterDate(String date){
        Date modifiedAfterDate = null;

        long dateLong = 0;
        try { dateLong = Long.parseLong(date); } catch (Exception ex) { }
        if (dateLong > 0) {
            modifiedAfterDate = new Date(dateLong);
        } else {
            log.error("bad date parameter: " + date);
        }
        return modifiedAfterDate;
    }

    /**
     * Returns a Date set to the first possible millisecond of the hour,
     * If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfHour(Date day) {
        return getStartOfHour(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the first possible millisecond of the hour
     * If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfHour(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the last possible millisecond of the hour,
     * If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfHour(Date day) {
        return getEndOfHour(day, Calendar.getInstance());
    }

    /**
     * Returns a Date set to the last possible millisecond of the hour
     * If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfHour(Date day, Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date addHours(Date date, int numberOfHours) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, numberOfHours);
        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int numberOfSeconds) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numberOfSeconds);
        return calendar.getTime();
    }

    public static List<String> getDateFormats() {
        List<String> dateFormats = new ArrayList<String>();
        dateFormats.add("");
        dateFormats.add("yyyy-MM-dd HH:mm:ss");
        dateFormats.add("dd-MM-yy");
        dateFormats.add("dd-MM-yyyy");
        dateFormats.add("dd-MMM-yy");
        dateFormats.add("EEE MMM dd HH:mm:ss zzz yyyy");
        dateFormats.add("yyyy-MM-dd");
        dateFormats.add("yyyyMMdd");
        dateFormats.add("MM/dd/yy");
        dateFormats.add("MM/dd/yyyy");
        dateFormats.add("yy/MM/dd");
        return dateFormats;
    }

    public static String toDBFormat(Date date) {
        return dateFormatDB.format(date != null ? date : new Date());
    }


    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expiryDate = null;
        try {
            expiryDate = sdf.parse("2015-10-31 23:59:59");
            System.out.println("No error");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("expiryDate : "+expiryDate +" d2 : "+getEndOfMonth(new Date()) + " d3 : "+ DateUtil.getEndOfDay(getEndOfMonth(new Date())));
    }

}
