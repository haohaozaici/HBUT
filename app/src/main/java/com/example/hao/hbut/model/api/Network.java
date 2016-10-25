package com.example.hao.hbut.model.api;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hao.hbut.activity.MainActivity.isLogon;

/**
 * Created by hao on 2016/10/25.
 */

public class Network {

    private static HbutApi hbutApi_logon;
    private static HbutApi hbutApi_get;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxjavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public OkHttpClient okHttpClient_received = new OkHttpClient.Builder()
            .addInterceptor(new ReceivedCookiesInterceptor())
            .build();
    public OkHttpClient okHttpClient_add = new OkHttpClient.Builder()
            .addInterceptor(new AddCookiesInterceptor())
            .build();

    public HbutApi getHbutApi(String instance) {

        if (hbutApi_logon == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient_received)
                    .baseUrl(HbutApi.Account_HOST)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxjavaCallAdapterFactory)
                    .build();
            hbutApi_logon = retrofit.create(HbutApi.class);
        }

        if (hbutApi_get == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient_add)
                    .baseUrl(HbutApi.StuGrade_HOST)
//                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxjavaCallAdapterFactory)
                    .build();
            hbutApi_get = retrofit.create(HbutApi.class);

        }
        if (instance.equals(HbutApi.Account_HOST)) {
            return hbutApi_logon;
        } else {
            return hbutApi_get;
        }
    }


    /*
    * 持久化cookie
    * 2016-10-25 16:21:49 by haohaozaici
    * */
    private HashSet<String> cookies = new HashSet<>();

    public class ReceivedCookiesInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }

                Log.e("eeeeeeeeeee", cookies.toString());
                isLogon = true;
            }
            return originalResponse;
        }
    }

    public class AddCookiesInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();

            ArrayList<String> cookiesArray = new ArrayList<>();
            for (String cookie : cookies) {
                cookie = cookie.substring(0, cookie.length() - 16);
                cookiesArray.add(cookie);
            }
            int position = cookiesArray.size();
            String cookie2 = "";
            for (int i = 0; i < position; i++) {
                String cookie = cookiesArray.get(position - i - 1);
                cookie2 += cookie;

            }
            builder.addHeader("Cookie", cookie2);
            Log.e("OkHttp", "Adding Header: " + cookie2);
            // This is done so I know which headers are being added;
            // this interceptor is used after the normal logging of OkHttp
            return chain.proceed(builder.build());
        }
    }

}
