package com.example.hao.hbut.model.bean;

/**
 * Created by hao on 2016-10-26.
 */

public class Setting {

    private static class SingletonHolder{
        public static Setting instance = new Setting();
    }

    private Setting(){}

    public static Setting newInstance(){
        return SingletonHolder.instance;
    }

    public boolean isLogin = false;
    public String cookies = "";
    public String userName = "";
    public String password = "";

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
}
