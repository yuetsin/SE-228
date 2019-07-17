package com.yue.bookie.server.lib.utils;

public class ValidationChecker {
    public static boolean isLegalUserName(String inputStr) {
        return inputStr.length() >= 8 && inputStr.length() <= 20;
    }
    public static boolean isLegalPassword(String inputStr){
//        System.out.println("Checking " + inputStr);
        int len = inputStr.length();
        if(len < 8 || len > 20)
            return false;
        int up = 0, low = 0, digit = 0;
        for (char ch : inputStr.toCharArray()) {
//            System.out.println("Checking " + ch);
            if (64 < ch && ch < 91) {
                up = 1;
                continue;
            }
            if (96 < ch && ch < 123) {
                low = 1;
                continue;
            }
            if (47 < ch && ch < 58) {
                digit = 1;
                continue;
            }
            return false;
        }
        return up + low + digit == 3;
    }
}
