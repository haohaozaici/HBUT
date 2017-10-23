package com.example.hao.hbut.Main.Grade;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.hbut.Main.RefreshDialogFragment;
import com.example.hao.hbut.R;
import com.example.hao.hbut.View.widget.ENRefreshView;
import com.example.hao.hbut.View.widget.ILayoutAnimationController;
import com.example.hao.hbut.base.BaseFragment;
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
    private GradeAdapter mGradeAdapter;
    private Network network = new Network();
    private ENRefreshView refreshAll;
    private DialogFragment refreshDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout1, container, false);

        refreshDialog = new RefreshDialogFragment();
        refreshDialog.setCancelable(false);
        refreshAll = (ENRefreshView) view.findViewById(R.id.refresh_all);
        refreshAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshAll.startRefresh();
                refreshAll.setClickable(false);
                refreshDialog.show(getFragmentManager(), "missiles");
                loadData();

            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_main_list);
        setupRecyclerView();

        loadDataDisk();

        return view;
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mGradeAdapter = new GradeAdapter(getActivity());
        mRecyclerView.setAdapter(mGradeAdapter);

        ILayoutAnimationController.setLayoutAnimation(mRecyclerView,
                R.anim.activity_open_enter,
                0.8f,
                ILayoutAnimationController.IndexAlgorithm.INDEX135246
        );

    }

    private void loadData() {
        clearSubscribe();
        network.getHbutApi(HbutApi.StuGrade_HOST).getRecent(setting.getUserName(), "1")
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

                            if (!s.substring(0, 1).equals("<")) {
                                Gson gson = new Gson();
                                grade = gson.fromJson(s, Grade.class);
                                mGradeAdapter.setItem(grade);
                                Snackbar.make(mRecyclerView, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

                                data.saveGrade(grade);

                            } else {
                                Snackbar.make(mRecyclerView, getString(R.string.cookie_unable), Snackbar.LENGTH_SHORT).show();
                            }
                            refreshDialog.dismiss();
                            refreshAll.setClickable(true);
                        } catch (IOException e) {
                            Log.e("11111111", e.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Snackbar.make(mRecyclerView, getString(R.string.error), Snackbar.LENGTH_SHORT).show();
                        refreshAll.setClickable(true);
                        refreshDialog.dismiss();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void loadDataDisk() {

        if (data.getGrade() != null) {
            grade = data.getGrade();
            mGradeAdapter.setItem(grade);
        }
//        loadData();

    }


}
