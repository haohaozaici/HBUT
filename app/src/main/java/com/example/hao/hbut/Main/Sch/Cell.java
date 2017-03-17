package com.example.hao.hbut.Main.Sch;

/**
 * Created by haohao on 2017/3/16.
 */

public class Cell {

    private String schName;
    private String teacherName;
    private String address;
    private String week;

    public Cell(String schName, String teacherName, String address, String week) {
        this.schName = schName;
        this.teacherName = teacherName;
        this.address = address;
        this.week = week;

    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
