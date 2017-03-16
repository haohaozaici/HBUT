package com.example.hao.hbut.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.hao.hbut.model.bean.AllInfo;
import com.example.hao.hbut.model.bean.Grade;
import com.example.hao.hbut.model.bean.Schedule;
import com.example.hao.hbut.model.bean.Setting;
import com.google.gson.Gson;

/**
 * Created by haohao on 2017/3/16.
 */

public enum Database {
    instance;

    private AllInfo mAllInfo;

    public Grade mGrade;
    public Schedule mSchedule;
    public Setting mSetting;

    private static final String PACKAGE_DATA = Database.class.getSimpleName();

    private SharedPreferences mPreferences;

    public void init(Context context) {
        loadAllData(context);
    }

    public AllInfo getAllInfo() {
        return mAllInfo;
    }

    public Grade getGrade() {
        return mAllInfo.getGrade();
    }

    public Schedule getSchedule() {
        return mAllInfo.getSchedule();
    }

    public Setting getSetting() {
        return mAllInfo.getSetting();
    }

    public void saveGrade(Grade grade) {
        mAllInfo.setGrade(grade);
    }

    public void saveSchedule(Schedule schedule) {
        mAllInfo.setSchedule(schedule);
    }

    public void saveSetting(Setting setting) {
        mAllInfo.setSetting(setting);
    }

    public void loadAllData(Context context) {
        mPreferences = context.getSharedPreferences(PACKAGE_DATA, Context.MODE_PRIVATE);
        String dataJson = mPreferences.getString(PACKAGE_DATA, "");
        Gson gson = new Gson();
        mAllInfo = gson.fromJson(dataJson, AllInfo.class);
    }

    public void saveAllData(Activity activity) {
        mPreferences = activity.getSharedPreferences(PACKAGE_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        AllInfo info = new AllInfo();
        editor.putString(PACKAGE_DATA, new Gson().toJson(info));
        editor.apply();
    }

}
