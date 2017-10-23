package com.example.hao.hbut.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.hao.hbut.Main.Sch.Cell;
import com.example.hao.hbut.Main.me.LibInfo;
import com.example.hao.hbut.base.BaseActivity;
import com.example.hao.hbut.model.bean.AllInfo;
import com.example.hao.hbut.model.bean.Grade;
import com.example.hao.hbut.model.bean.Schedule;
import com.example.hao.hbut.model.bean.Setting;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohao on 2017/3/16.
 */

public enum Database {

    instance;

    private AllInfo mAllInfo;

    private static final String PACKAGE_DATA = Database.class.getSimpleName();

    private SharedPreferences mPreferences;

    public void init(Context context) {
        loadAllData(context);
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

    public List<List<Cell>> getCells() {
        return mAllInfo.getCells();
    }

    public ArrayList<String> getLibInfo() {
        return mAllInfo.getLibInfo().getInfoList();
    }

    public void saveCells(List<List<Cell>> cells) {
        mAllInfo.setCells(cells);
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

    public void saveLibInfo(LibInfo libInfo) {
        mAllInfo.setLibInfo(libInfo);
    }

    public void loadAllData(Context context) {
        mPreferences = context.getSharedPreferences(PACKAGE_DATA, Context.MODE_PRIVATE);
        String dataJson = mPreferences.getString(PACKAGE_DATA, "");
        mAllInfo = new AllInfo();

        if (!dataJson.equals("")) {
            Gson gson = new Gson();
            mAllInfo = gson.fromJson(dataJson, AllInfo.class);
        }

    }

    public void saveAllData(Activity activity) {
        mPreferences = activity.getSharedPreferences(PACKAGE_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
//        if (mAllInfo.getGrade() == null){
//            mAllInfo.setGrade(new Grade());
//        }
//        if (mAllInfo.getSchedule() == null){
//            mAllInfo.setSchedule(new Schedule());
//        }
//        if (mAllInfo.getSetting() == null){
//            mAllInfo.setSetting(new Setting());
//        }
        editor.putString(PACKAGE_DATA, new Gson().toJson(mAllInfo));
        editor.apply();
    }

    public void clearAll(Activity activity) {
        mAllInfo.setCells(null);
        mAllInfo.setSetting(null);
        mAllInfo.setGrade(null);
        mAllInfo.setSchedule(null);
        mAllInfo.setLibInfo(null);
        saveAllData(activity);
    }

}
