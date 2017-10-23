package com.example.hao.hbut.model.bean;

/**
 * Created by hao on 2016-10-26.
 */

public class Setting {

    private static class SingletonHolder {
        public static Setting instance = new Setting();
    }

    private Setting() {
    }

    public static Setting newInstance() {
        return SingletonHolder.instance;
    }

    public boolean isFirstRun = true;
    public String account = "";
    public boolean isLogin = false;
    public String cookies = "";
    public String userName = "";
    public String password = "";

    //lib
    public boolean libIsLogin = false;
    public String libCookie = "";
    public String libUserName = "";
    public String libUserNameText = "";
    public String libPassword = "";

    public static final String CLASS_PLATFORM = "CLASS_PLATFORM";
    public static final String LIB = "lib";

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isLibIsLogin() {
        return libIsLogin;
    }

    public void setLibIsLogin(boolean libIsLogin) {
        this.libIsLogin = libIsLogin;
    }

    public String getLibCookie() {
        return libCookie;
    }

    public void setLibCookie(String libCookie) {
        this.libCookie = libCookie;
    }

    public String getLibUserName() {
        return libUserName;
    }

    public void setLibUserName(String libUserName) {
        this.libUserName = libUserName;
    }

    public String getLibPassword() {
        return libPassword;
    }

    public void setLibPassword(String libPassword) {
        this.libPassword = libPassword;
    }

    public boolean isFirstRun() {
        return isFirstRun;
    }

    public void setFirstRun(boolean firstRun) {
        isFirstRun = firstRun;
    }

    public String getLibUserNameText() {
        return libUserNameText;
    }

    public void setLibUserNameText(String libUserNameText) {
        this.libUserNameText = libUserNameText;
    }
}
