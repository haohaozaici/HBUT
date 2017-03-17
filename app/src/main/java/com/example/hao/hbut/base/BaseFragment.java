package com.example.hao.hbut.base;

import android.app.Fragment;

import com.example.hao.hbut.model.Database;
import com.example.hao.hbut.model.bean.Setting;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by hao on 2016/11/25.
 */

public class BaseFragment extends Fragment {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public Database data = Database.instance;
    public Setting setting = BaseActivity.getSetting();

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearSubscribe();
    }

    public void clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
