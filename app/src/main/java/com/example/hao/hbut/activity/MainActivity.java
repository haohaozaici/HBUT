package com.example.hao.hbut.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.data.Grade;
import com.example.hao.hbut.model.data.Student;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    public static boolean isLogon = false;
    Observer<Student> observer_log = new Observer<Student>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Student student) {
            Log.e("`````````````", student.toString());
        }
    };
    Observer<ResponseBody> observer_get = new Observer<ResponseBody>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String s = responseBody.string();
                s = s.replaceAll("\\\\", "");
                s = s.replaceFirst("\"", "");
                s = s.substring(0, s.length() - 1);

                Gson gson = new Gson();
                Grade grade = gson.fromJson(s, Grade.class);

            } catch (IOException e) {
//                Log.e("11111111", e.toString());
            }

        }
    };
    private String userName = "1310200128";
    private String password = "1310200128";
    private String role = "Student";
    private Network network = new Network();
    private Button logon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logon = (Button) findViewById(R.id.logon);
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

    }

    private void loadData() {
        unsubscribe();
        if (!isLogon) {
            subscription = network.getHbutApi(HbutApi.Account_HOST).logOn(userName, password, role)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer_log);
        } else {
            subscription = network.getHbutApi(HbutApi.StuGrade_HOST).getRecent(userName, "1")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer_get);

        }

    }


}
