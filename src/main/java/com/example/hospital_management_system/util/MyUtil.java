package com.example.hospital_management_system.util;

import java.util.Base64;

public class MyUtil {
    public static String activationCodeGenerator() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
    public static String encodedActivationCode() {
        return Base64.getEncoder().encodeToString(activationCodeGenerator().getBytes());

    }
}
