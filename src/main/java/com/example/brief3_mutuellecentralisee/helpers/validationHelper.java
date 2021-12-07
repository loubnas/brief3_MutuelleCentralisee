package com.example.brief3_mutuellecentralisee.helpers;

public class validationHelper {
    public static boolean IsValidLength(String value,int length){
        return value.length()<=length;
    }

    public static boolean IsValidCIN(String value){
        return value.matches("^[a-zA-Z]{2}[0-9]{6}$");
    }

    public static boolean IsValidPASSPORT(String value){
        return value.matches("^[a-zA-Z]{2}[0-9]{7}$");
    }

    public static boolean IsValidPhone(String value){
        return value.matches("^(6|7)[0-9]{8}$");
    }

    public static boolean IsValidEmail(String value){
        return value.matches("^[a-zA-Z_][a-zA-Z0-9_]+@[a-zA-Z_][a-zA-Z0-9_]+\\.[a-zA-Z]{2,3}$");
    }
}
