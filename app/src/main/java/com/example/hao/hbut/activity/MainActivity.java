package com.example.hao.hbut.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.hao.hbut.R;
import com.example.hao.hbut.adapter.MainAdapter;
import com.example.hao.hbut.model.Setting;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.data.Grade;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

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
                mMainAdapter.setItem(grade);

            } catch (IOException e) {
//                Log.e("11111111", e.toString());
            }

        }
    };
    private String role = "Student";
    private Network network = new Network();
    private Button logon;
    private Button logonOut;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter = new MainAdapter();

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
        logonOut = (Button)findViewById(R.id.logon_out);
        logonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences setting = getSharedPreferences("setting",0);
                SharedPreferences.Editor editor = setting.edit();
                editor.putBoolean("isLogin", false);
                editor.putString("cookies", "");
                editor.apply();
                Intent intent = new Intent(MainActivity.this, EntryActivity.class);
                startActivity(intent);
                finish();

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main_list);
        setupRecyclerView();

    }


    private void setupRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMainAdapter);

    }

    private void loadData() {
        unsubscribe();
        subscription = network.getHbutApi(HbutApi.StuGrade_HOST).getRecent(Setting.userName, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_get);

    }

}
