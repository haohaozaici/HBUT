package com.example.hao.hbut.base;

import android.support.v7.app.AppCompatActivity;

import com.example.hao.hbut.model.Database;
import com.example.hao.hbut.model.bean.Setting;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by hao on 2016-09-22.
 */

public class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public Database data = Database.instance;
    public static Setting setting = Setting.newInstance();


    @Override
    protected void onPause() {
        super.onPause();
        data.saveSetting(setting);
        data.saveAllData(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearSubscribe();
    }


    protected void clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public static Setting getSetting() {
        return setting;
    }

//    public static void openWebsite(Activity context, String url) {
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        builder.setToolbarColor(context.getResources().getColor(R.color.colorAccent))
//                .setShowTitle(true);
//        builder.build().launchUrl(context, Uri.parse(url));
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        CustomTabsClient.connectAndInitialize(getApplicationContext(), "com.android.chrome");
//    }
}
