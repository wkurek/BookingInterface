package sample.util;

import java.text.ParseException;

public class Validator {
    public static boolean validateTelephoneNumber(String telephoneNumber) {
        return !telephoneNumber.isEmpty() && telephoneNumber.matches("[0-9]+$") && telephoneNumber.length() <= 13
                && telephoneNumber.length() >= 9;
    }

    public static boolean validateEmail(String email) {
        return !email.isEmpty() && email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$") && email.length() < 20;
    }

    public static boolean validateName(String name) {
        return !name.isEmpty() && name.matches("[a-zA-Z]+$") && name.length() < 20;
    }

    public static boolean validateSurname(String surname) {
        return validateName(surname);
    }

    public static boolean validateDate(String date) {
        if(date.isEmpty() || date.length() != 10 || !date.matches("^20[0-9]{2}/[0-9]{2}/[0-9]{2}$")) return false;

        try {
            DateFormatter.parseDate(date);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean validateRoomNumbers(String roomNumbers) {
        return !roomNumbers.isEmpty() && roomNumbers.matches("^[0-9]{3}(;[0-9]{3})*$");
    }

    public static boolean validateCustomerId(String customerId) {
        return !customerId.isEmpty() && customerId.matches("^\\d+$");
    }
}
