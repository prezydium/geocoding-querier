package com.prezydium;

public class Utils {
    public static String buildAddress(String[] csvRow) {
        return csvRow[3] + " " + csvRow[4] + " ," + csvRow[2];
    }
}
