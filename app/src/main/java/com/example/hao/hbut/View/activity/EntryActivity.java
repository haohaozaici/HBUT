package com.example.hao.hbut.View.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hao.hbut.model.Setting;

/**
 * Created by hao on 2016-10-26.
 */

public class EntryActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("setting", Context.MODE_PRIVATE);
        Setting.setIsLogin(settings.getBoolean("isLogin", false));
        Setting.setUserName(settings.getString("userName", ""));
        Setting.setCookies(settings.getString("cookies", ""));

        if (Setting.isLogin()) {
            Intent intent = new Intent(EntryActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(EntryActivity.this, LoginActivity.class);
            startActivity(intent);

        }
        finish();


    }

}