package com.example.hao.hbut.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.Setting;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.data.Student;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;


/**
 * Created by hao on 2016-10-25.
 */

public class LoginActivity extends BaseActivity {

    private MaterialLoginView login;

    Observer<Student> observer_log = new Observer<Student>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Student student) {
            Log.e("`````````````", student.toString());
            if (student.Status.equals("0")) {

                saveLoginStatus();

                Snackbar.make(login, student.Message, Snackbar.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);

            } else {
                Snackbar.make(login, student.Message, Snackbar.LENGTH_LONG).show();
            }


        }
    };
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layoutme);

        login = (MaterialLoginView) findViewById(R.id.loginme);
        ((DefaultLoginView) login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                String user = loginUser.getEditText().getText().toString();
                if (user.isEmpty()) {
                    loginUser.setError("User name can't be empty");
                    return;
                }
                loginUser.setError("");

                String pass = loginPass.getEditText().getText().toString();
                if (pass.isEmpty()) {
                    loginPass.setError("Password can't be empty");
                    return;
                }
                loginPass.setError("");

                subscription = network.getHbutApi(HbutApi.Account_HOST).logOn(user, pass, "Student")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer_log);

                Setting.setUserName(user);
                Setting.setPassword(pass);

            }
        });

        ((DefaultRegisterView) login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {

            }
        });
    }

    private void saveLoginStatus() {
        Setting.setHasDefault(true);
        SharedPreferences setting = getSharedPreferences("setting",0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putBoolean("isLogin", Setting.isLogin());
        editor.putString("cookies", Setting.getCookies());
        editor.putString("userName", Setting.getUserName());
        editor.putString("password", Setting.getPassword());
        editor.putBoolean("hasDefault", Setting.isHasDefault());
        editor.apply();

    }

}
