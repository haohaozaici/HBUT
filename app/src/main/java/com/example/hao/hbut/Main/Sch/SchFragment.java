package com.example.hao.hbut.Main.Sch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseFragment;
import com.example.hao.hbut.model.bean.Setting;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.bean.Schedule;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by hao on 2016/11/25.
 */

public class SchFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private Button refresh;
    private SchAdapter mSchAdapter = new SchAdapter();
    private Network network = new Network();
    private Schedule schedule;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataDisk();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout2, container, false);

        refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_2_list);
        setupRecyclerView();

        return view;
    }

    private void loadData() {
        clearSubscribe();
        network.getHbutApi(HbutApi.Schedule_Host).getSchedule("20161", Setting.userName, "LogInfo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String s = responseBody.string();
                            s = s.replaceAll("\\\\", "");
                            s = s.replaceFirst("\"", "");
                            s = s.substring(0, s.length() - 1);

//                            SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
//                            SharedPreferences.Editor editor = setting.edit();
//                            editor.putString("Schedule", s);
//                            editor.apply();

                            Gson gson = new Gson();
                            schedule = gson.fromJson(s, Schedule.class);
                            mSchAdapter.setItem(schedule);
                            Snackbar.make(mRecyclerView, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

                            data.saveSchedule(schedule);

                        } catch (IOException e) {
//                Log.e("11111111", e.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setupRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mSchAdapter);

    }

    private void loadDataDisk() {
//        SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
//        String stringSchedule = setting.getString("Schedule", "");
//        if (!stringSchedule.equals("")) {
//            Gson gson = new Gson();
//            schedule = gson.fromJson(stringSchedule, Schedule.class);
//            mSchAdapter.setItem(schedule);
//        }

        if (!data.getSchedule().Content.equals("")){
            schedule = data.getSchedule();
            mSchAdapter.setItem(schedule);
        }

    }
}
