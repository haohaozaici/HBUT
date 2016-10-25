package com.example.hao.hbut.model;

/**
 * Created by hao on 2016-10-26.
 */

public class Setting {

    public static boolean isLogin = false;
    public static boolean hasDefault = false;
    public static String cookies = "";
    public static String userName = "";
    public static String password = "";


    public static boolean isLogin() {
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        Setting.isLogin = isLogin;
    }

    public static String getCookies() {
        return cookies;
    }

    public static void setCookies(String cookies) {
        Setting.cookies = cookies;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Setting.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Setting.password = password;
    }

    public static boolean isHasDefault() {
        return hasDefault;
    }

    public static void setHasDefault(boolean hasDefault) {
        Setting.hasDefault = hasDefault;
    }
}
