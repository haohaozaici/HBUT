package com.example.hao.hbut.feature.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import com.example.hao.hbut.feature.login.bean.LoginRes;
import com.example.hao.hbut.model.network.Network;
import com.example.hao.hbut.model.network.NetworkErrorListener;
import com.example.hao.hbut.model.network.bean.BaseRes;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class LoginRepo {

    public static LiveData<LoginRes> login(String account, String password) {

        Flowable<LoginRes> flowable = Network.getInstance().getHbutService(false, true)
                .logOn(account, password, "Student")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    LoginRes res = new LoginRes();
                    res.setStatus(1);
                    res.setThrowable(throwable);
                    return res;
                });

        return LiveDataReactiveStreams.fromPublisher(flowable);

    }
}
