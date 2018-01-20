package com.example.hao.hbut;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.XLog;

/**
 * Created by haohao on 2017/3/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        XLog.init();
        Utils.init(this);

    }
}
