package com.example.hao.hbut.model.network.bean;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class BaseRes {

    private Throwable mThrowable;

    public BaseRes() {
    }

    public BaseRes(Throwable throwable) {
        mThrowable = throwable;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public void setThrowable(Throwable throwable) {
        mThrowable = throwable;
    }
}
