package com.example.hao.hbut.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.data.Grade;
import com.example.hao.hbut.model.data.Student;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private String userName = "1310200128";
    private String password = "1310200128";
    private String role = "Student";

    private Network network = new Network();
    public static boolean isLogon = false;

    private Button logon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logon = (Button)findViewById(R.id.logon);
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

    }

    private void loadData() {
        unsubscribe();
        if (!isLogon){
            subscription = network.getHbutApi(HbutApi.Account_HOST).logOn(userName, password, role)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer_log);
        }else {
//            subscription = network.getHbutApi(HbutApi.StuGrade_HOST).getRecent(userName,"1")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(observer_get);

            Call<ResponseBody> call = network.getHbutApi(HbutApi.StuGrade_HOST).getRecent2("1310200128", "1");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.e("eeeeeeeeeee", response.toString());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

    }

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

    Observer<Grade> observer_get = new Observer<Grade>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Grade grade) {
            Log.e("`````````````", grade.toString());
        }
    };


}
