package com.example.hao.hbut.Main.Sch;

/**
 * Created by haohao on 2017/3/16.
 */

public class RowTitle {

    private String dateString;
    private String weekString;

    public RowTitle(String weekString){
//        this.dateString = dateString;
        this.weekString = weekString;

    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getWeekString() {
        return weekString;
    }

    public void setWeekString(String weekString) {
        this.weekString = weekString;
    }
}
