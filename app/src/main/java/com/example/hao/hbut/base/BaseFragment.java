package com.example.hao.hbut.base;

import android.app.Fragment;

import com.example.hao.hbut.model.Database;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by hao on 2016/11/25.
 */

public class BaseFragment extends Fragment {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public Database data = Database.instance;

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearSubscribe();
        data.saveAllData(getActivity());
    }

    public void clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
