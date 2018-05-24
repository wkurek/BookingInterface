package sample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class aim is to unify date format.
 * Standard date format in this program is yyyy/MM/dd.
 */
public class DateFormatter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * Method tries to parse string literal for Date object.
     * @param date string literal which represents date.
     * @return Date object which represents provided string literal date.
     * @throws ParseException thrown when wrong format of string literal is provided.
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * @param date object representing date.
     * @return string representing provided Date object.
     */
    public static String format(Date date) {
        return dateFormat.format(date);
    }
}
