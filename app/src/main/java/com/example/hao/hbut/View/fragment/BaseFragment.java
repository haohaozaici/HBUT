package com.example.hao.hbut.View.fragment;

import android.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by hao on 2016/11/25.
 */

public class BaseFragment extends Fragment {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearSubscribe();
    }

    protected void clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
