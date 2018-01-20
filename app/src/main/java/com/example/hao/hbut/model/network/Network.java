package com.example.hao.hbut.model.network;

import com.example.hao.hbut.BuildConfig;
import com.example.hao.hbut.model.network.api.HbutApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hao on 2016/10/25.
 */

public class Network {

    private static Network instance;

    private Network() {
    }

    public static synchronized Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    private static final int TIME_OUT = 10;

    private Retrofit retrofit;

    private Retrofit getRetrofit(boolean addCookies, boolean receivedCookies) {
        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addNetworkInterceptor(interceptor);
            }

            HbutInterceptor hbutInterceptor = new HbutInterceptor(addCookies, receivedCookies);
            builder.addInterceptor(hbutInterceptor);

            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(HbutApi.Account_HOST)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


//    public class AddCookiesInterceptor implements Interceptor {
//
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request.Builder builder = chain.request().newBuilder();
//
////            Log.e("get information name", Setting.getUserName());
//            if (setting.getAccount().equals(Setting.CLASS_PLATFORM)) {
//                builder.addHeader("Cookie", setting.getCookies());
//            } else if (setting.getAccount().equals(Setting.LIB)) {
//                builder.addHeader("Cookie", setting.getLibCookie());
//            }
////            builder.addHeader("Cookie", "");
////            Log.e("OkHttp", "Adding Header: " + Setting.getCookies());
//
//            // This is done so I know which headers are being added;
//            // this interceptor is used after the normal logging of OkHttp
//            return chain.proceed(builder.build());
//        }
//    }

    public HbutApi getHbutService(boolean addCookies, boolean receivedCookies) {
        return getRetrofit(addCookies, receivedCookies).create(HbutApi.class);
    }

}
