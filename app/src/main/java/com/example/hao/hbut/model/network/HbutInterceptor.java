package com.example.hao.hbut.model.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.elvishew.xlog.XLog;
import com.example.hao.hbut.model.sp.HbutSP;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class HbutInterceptor implements Interceptor {

    private static final String TAG = "HbutInterceptor";


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        //add cookies
        Request.Builder builder = chain.request().newBuilder();

        String cookies = SPUtils.getInstance(HbutSP.TAG).getString(HbutSP.COOKIES);
        if (!StringUtils.isEmpty(cookies)) {
            builder.addHeader("Cookie", cookies);
        }


        //received cookies
        Response originalResponse = chain.proceed(chain.request());

        StringBuilder newCookies = new StringBuilder();
        for (String cookie : originalResponse.headers("Set-Cookie")) {
            XLog.tag(TAG).d("intercept: cookie:  " + cookie);
            if (!StringUtils.isEmpty(cookie)) {
                newCookies.append(cookie.replace("path=/; HttpOnly", ""));
            }
        }

        if (!StringUtils.isTrimEmpty(newCookies.toString())) {
            SPUtils.getInstance(HbutSP.TAG).put(HbutSP.COOKIES, newCookies.toString());
        }

        return chain.proceed(builder.build());

    }

}
