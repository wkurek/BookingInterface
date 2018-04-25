package sample.util;

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
}
