package com.example.hao.hbut;

import android.app.Application;

import com.example.hao.hbut.model.Database;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by haohao on 2017/3/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "95745e5bb2", false);
        Bugly.init(getApplicationContext(), "95745e5bb2", false);
        Database.instance.init(this);
    }
}
