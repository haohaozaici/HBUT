package com.example.hao.hbut.Main.Sch;

/**
 * Created by haohao on 2017/3/16.
 */

public class ColTitle {

    private String timeString;
    private String classString;

    public ColTitle(String timeString, String classString){
        this.timeString = timeString;
        this.classString = classString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getClassString() {
        return classString;
    }

    public void setClassString(String classString) {
        this.classString = classString;
    }
}
