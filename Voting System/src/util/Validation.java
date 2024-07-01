package util;

public class Validation {
    public static boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidAge(int age) {
        return age > 18;
    }

    public static boolean isValidVoterKey(String voterKey) {
        return voterKey != null && voterKey.matches("[A-Za-z0-9]+");
    }
}
