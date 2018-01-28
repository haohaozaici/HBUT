package com.example.hao.hbut.feature.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.SPUtils;
import com.example.hao.hbut.feature.login.bean.LoginRes;
import com.example.hao.hbut.model.sp.HbutSP;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class LoginViewModel extends ViewModel {

    private MediatorLiveData<LoginRes> mLoginLiveData = new MediatorLiveData<>();

    private MutableLiveData<Throwable> mThrowableLiveData = new MutableLiveData<>();


    public void login(String account, String password) {

        final LiveData<LoginRes> loginRes = LoginRepo.login(account, password);

        mLoginLiveData.addSource(loginRes, res -> {
            mLoginLiveData.removeSource(loginRes);

            assert res != null;
            switch (res.getStatus()) {
                case 0:
                    SPUtils.getInstance(HbutSP.TAG).put(HbutSP.LOGIN, true);
                    mLoginLiveData.setValue(res);
                    break;
                case 1:
                    SPUtils.getInstance(HbutSP.TAG).put(HbutSP.LOGIN, false);
                    mThrowableLiveData.setValue(res.getThrowable());
                    break;
                default:
                    mThrowableLiveData.setValue(new Throwable("未知错误"));
                    break;
            }

        });

    }

    public MediatorLiveData<LoginRes> getLoginLiveData() {
        return mLoginLiveData;
    }

    public MutableLiveData<Throwable> getThrowableLiveData() {
        return mThrowableLiveData;
    }
}
