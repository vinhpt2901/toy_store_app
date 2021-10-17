package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

    public static boolean isEmpty(String... strings) {
        for (String s : strings) {
            if (s.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
