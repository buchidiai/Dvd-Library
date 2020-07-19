/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.util;

/**
 *
 * @author louie
 */
public class Util {

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
