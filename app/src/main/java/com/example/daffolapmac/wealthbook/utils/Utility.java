package com.example.daffolapmac.wealthbook.utils;

import java.util.regex.Pattern;

public class Utility {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?\\d{10,14}$";

    /**
     * To verify email is valid or not
     *
     * @param email Email text
     * @return True/false
     */
    public static boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * To verify pass is valid or not
     *
     * @param pass Password text
     * @return True/false
     */
    public static boolean isPasswordValid(String pass) {
        return pass.length() >= 6;
    }
}
