package com.example.hao.hbut.model.bean;

import android.content.Context;

/**
 * Created by hao on 2017/2/9.
 */

public class AllInfo {

    public Grade mGrade;
    public Schedule mSchedule;
    public Setting mSetting;

    public Grade getGrade() {
        return mGrade;
    }

    public void setGrade(Grade grade) {
        mGrade = grade;
    }

    public Schedule getSchedule() {
        return mSchedule;
    }

    public void setSchedule(Schedule schedule) {
        mSchedule = schedule;
    }

    public Setting getSetting() {
        return mSetting;
    }

    public void setSetting(Setting setting) {
        mSetting = setting;
    }
}
