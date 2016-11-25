package com.example.hao.hbut.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.hbut.R;
import com.example.hao.hbut.adapter.MainAdapter;
import com.example.hao.hbut.model.Setting;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hao on 2016/11/24.
 */

public class TestActivity extends BaseActivity {


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

                SharedPreferences setting = getSharedPreferences("setting", 0);
                SharedPreferences.Editor editor = setting.edit();
                editor.putString("grade", s);
                editor.apply();

//                Gson gson = new Gson();
//                grade = gson.fromJson(s, Grade.class);
//                mMainAdapter.setItem(grade);

            } catch (IOException e) {
//                Log.e("11111111", e.toString());
            }

        }
    };
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter = new MainAdapter();
    private Network network = new Network();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        loadData();

    }


    private void loadData() {
        unsubscribe();
        subscription = network.getHbutApi(HbutApi.Schedule_Host).getSchedule("20151", Setting.userName, "Student")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_get);

    }
}
