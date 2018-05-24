package sample.util;

import java.text.ParseException;

/**
 * Class which consists of data validation methods.
 * The purpose of these methods is to prevent from providing invalid data by users.
 */
public class Validator {
    /**
     * Method validates telephone number provided by user. Regular expression is used to provide that feature.
     * Valid telephone number should consists of digits and have at least 9 characters.
     * @param telephoneNumber telephone number provided by user
     * @return whether provided telephone number is in valid format
     */
    public static boolean validateTelephoneNumber(String telephoneNumber) {
        return !telephoneNumber.isEmpty() && telephoneNumber.matches("[0-9]+$") && telephoneNumber.length() <= 13
                && telephoneNumber.length() >= 9;
    }

    /**
     * Method validates email provided by user. Regular expression is used to provide that feature.
     * @param email email address provided by user
     * @return whether provided email is in valid format
     */
    public static boolean validateEmail(String email) {
        return !email.isEmpty() && email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$") && email.length() < 20;
    }

    /**
     * Method validates name provided by user. Regular expression is used to provide that feature.
     * Name can have up to 20 characters and have to consists only of letters.
     * @param name name provided by user
     * @return whether provided name is in valid format
     */
    public static boolean validateName(String name) {
        return !name.isEmpty() && name.matches("[a-zA-Z]+$") && name.length() < 20;
    }

    public static boolean validateSurname(String surname) {
        return validateName(surname);
    }

    /**
     * Method validates date provided by user. Regular expression is used to provide that feature.
     * Date should be in yyyy/mm/dd format which is suitable for database.
     * @param date date provided by user
     * @return whether provided date is in valid format
     */
    public static boolean validateDate(String date) {
        if(date.isEmpty() || date.length() != 10 || !date.matches("^20[0-9]{2}/[0-9]{2}/[0-9]{2}$")) return false;

        try {
            DateFormatter.parseDate(date);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Method validates room numbers provided by user. Regular expression is used to provide that feature.
     * Room number must consist of three digits. Different room numbers should be separated by semicolon.
     * @param roomNumbers room numbers provided by user
     * @return whether provided room numbers is in valid format
     */
    public static boolean validateRoomNumbers(String roomNumbers) {
        return !roomNumbers.isEmpty() && roomNumbers.matches("^[0-9]{3}(;[0-9]{3})*$");
    }
    /**
     * Method validates customer Id provided by user. Regular expression is used to provide that feature.
     * @param customerId customer Id provided by user
     * @return whether provided customer Id is in valid format
     */
    public static boolean validateCustomerId(String customerId) {
        return !customerId.isEmpty() && customerId.matches("^\\d+$");
    }
}
