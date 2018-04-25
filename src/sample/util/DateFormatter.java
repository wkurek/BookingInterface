package sample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static String format(Date date) {
        return dateFormat.format(date);
    }
}
