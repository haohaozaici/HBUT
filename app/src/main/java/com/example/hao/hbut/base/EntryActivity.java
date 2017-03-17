package com.example.hao.hbut.base;

import android.content.Intent;
import android.os.Bundle;
import com.example.hao.hbut.Login.LoginActivity;
import com.example.hao.hbut.Main.MainActivity;

/**
 * Created by hao on 2016-10-26.
 */

public class EntryActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (data.getSetting() != null){
            setting = data.getSetting();
        }

        if (setting.isLogin()) {
            Intent intent = new Intent(EntryActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(EntryActivity.this, LoginActivity.class);
            startActivity(intent);

        }
        finish();

    }

}
