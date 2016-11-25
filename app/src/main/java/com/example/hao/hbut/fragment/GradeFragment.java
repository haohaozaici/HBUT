package com.example.hao.hbut.fragment;

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

/**
 * Created by hao on 2016/11/25.
 */

public class GradeFragment extends BaseFragment {

    private Grade grade;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter = new MainAdapter();
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

                SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
                SharedPreferences.Editor editor = setting.edit();
                editor.putString("grade", s);
                editor.apply();

                Gson gson = new Gson();
                grade = gson.fromJson(s, Grade.class);
                mMainAdapter.setItem(grade);
                Snackbar.make(mRecyclerView, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

            } catch (IOException e) {
//                Log.e("11111111", e.toString());
            }

        }
    };
    private Network network = new Network();
    private Button refresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataDisk();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout1, container, false);

        refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_main_list);
        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
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

    private void loadDataDisk() {
        SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
        String stringGrade = setting.getString("grade", "");
        if (!stringGrade.equals("")) {
            Gson gson = new Gson();
            grade = gson.fromJson(stringGrade, Grade.class);
            mMainAdapter.setItem(grade);
        }

    }

}
