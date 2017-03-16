package com.example.hao.hbut;

import android.app.Application;

import com.example.hao.hbut.model.Database;

/**
 * Created by haohao on 2017/3/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Database.instance.init(this);
    }
}
