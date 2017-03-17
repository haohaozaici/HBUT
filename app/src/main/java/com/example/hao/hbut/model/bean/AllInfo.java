package com.example.hao.hbut.model.bean;

import android.content.Context;

import com.example.hao.hbut.Main.Sch.Cell;

import java.util.List;

/**
 * Created by hao on 2017/2/9.
 */

public class AllInfo {

    public Grade Grade;
    public Schedule Schedule;
    public Setting Setting;
    public List<List<Cell>> cells;

    public Grade getGrade() {
        return Grade;
    }

    public void setGrade(Grade grade) {
        Grade = grade;
    }

    public Schedule getSchedule() {
        return Schedule;
    }

    public void setSchedule(Schedule schedule) {
        Schedule = schedule;
    }

    public Setting getSetting() {
        return Setting;
    }

    public void setSetting(Setting setting) {
        Setting = setting;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
}
