package com.example.hao.hbut.Main.Grade;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseFragment;
import com.example.hao.hbut.View.widget.ENRefreshView;
import com.example.hao.hbut.model.bean.Setting;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.bean.Grade;
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

public class GradeFragment extends BaseFragment {

    private Grade grade;
    private RecyclerView mRecyclerView;
    private GradeAdapter mGradeAdapter = new GradeAdapter();
    private Network network = new Network();
    private ENRefreshView refreshAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataDisk();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout1, container, false);

        refreshAll = (ENRefreshView) view.findViewById(R.id.refresh_all);
        refreshAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshAll.startRefresh();
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
        mRecyclerView.setAdapter(mGradeAdapter);

    }

    private void loadData() {
        clearSubscribe();
        network.getHbutApi(HbutApi.StuAllGrade_HOST).getAllGrade(Setting.userName, "1")
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
//                String s2 = responseBody.string();
//
//                Document document = Jsoup.parse(s2);
//                Element content = document.getElementById("mainContent");
//                Elements elements = content.getElementsByClass("table-list");
//                String text = elements.text();
//                String text2 = elements.get(0).attr("th");

                            String s = responseBody.string();
                            s = s.replaceAll("\\\\", "");
                            s = s.replaceFirst("\"", "");
                            s = s.substring(0, s.length() - 1);

//                            SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
//                            SharedPreferences.Editor editor = setting.edit();
//                            editor.putString("grade", s);
//                            editor.apply();

                            Gson gson = new Gson();
                            grade = gson.fromJson(s, Grade.class);
                            mGradeAdapter.setItem(grade);
                            Snackbar.make(mRecyclerView, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

                            data.saveGrade(grade);


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

    private void loadDataDisk() {
//        SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
//        String stringGrade = setting.getString("grade", "");
//        if (!stringGrade.equals("")) {
//            Gson gson = new Gson();
//            grade = gson.fromJson(stringGrade, Grade.class);
//            mGradeAdapter.setItem(grade);
//        }

        if (!data.getGrade().AverageGradePoint.equals("")){
            grade = data.getGrade();
            mGradeAdapter.setItem(grade);
        }

    }


}
