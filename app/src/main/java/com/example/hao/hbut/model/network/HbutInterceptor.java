package com.example.hao.hbut.model.network;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class HbutInterceptor implements Interceptor {

    private boolean mAddCookies = false;
    private boolean mReceivedCookies = false;

    public HbutInterceptor() {
    }

    public HbutInterceptor(boolean add_cookies, boolean received_cookies) {
        this.mAddCookies = add_cookies;
        this.mReceivedCookies = received_cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (mAddCookies) {


        }


        if (mReceivedCookies) {


        }

        return null;
    }
}
