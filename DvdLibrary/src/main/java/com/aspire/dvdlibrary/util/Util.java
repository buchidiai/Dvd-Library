/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.util;

import java.util.UUID;

/**
 *
 * @author louie
 */
public class Util {

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    public static String intToString(int num) {

        return Integer.toString(num);
    }

    public static String UUID() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");

        return uuid;
    }

    public static boolean isEmptyString(String str) {

        return str.isEmpty();
    }

    public static String capitalizeFirstWord(String str) {

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String capitalizeEachWord(String str) {
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    public static String alltoLowerCase(String str) {
        return str.toLowerCase();
    }

}
