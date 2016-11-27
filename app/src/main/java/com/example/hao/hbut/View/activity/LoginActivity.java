package com.example.hao.hbut.View.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.Setting;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.data.Student;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by hao on 2016-10-25.
 */

public class LoginActivity extends BaseActivity {

    private EditText name;
    private EditText password;
    private View line1;
    private ImageView img1;
    private View line2;
    private ImageView img2;
    private Button logon;

    private String user = "", pass = "";

    Observer<Student> observer_log = new Observer<Student>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Student student) {
            if (student.Status.equals("0")) {

                saveLoginStatus();

                Snackbar.make(name, student.Message, Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(name, student.Message, Snackbar.LENGTH_LONG).show();
            }


        }
    };
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon_layout);

        line1 = (View) findViewById(R.id.line1);
        line2 = (View) findViewById(R.id.line2);
        img1 = (ImageView) findViewById(R.id.img_1);
        img2 = (ImageView) findViewById(R.id.img_2);

        name = (EditText) findViewById(R.id.name_number);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    line1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    img1.setImageResource(R.drawable.smile2);
                } else {
                    line1.setBackgroundColor(getResources().getColor(R.color.grey_logon));
                    img1.setImageResource(R.drawable.smile);
                }
            }
        });

        password = (EditText) findViewById(R.id.password_number);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pass = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    line2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    img2.setImageResource(R.drawable.password2);
                } else {
                    line2.setBackgroundColor(getResources().getColor(R.color.grey_logon));
                    img2.setImageResource(R.drawable.password);
                }
            }
        });
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    logon();
                    return true;
                }
                return false;
            }
        });

        logon = (Button) findViewById(R.id.logon);
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logon();
            }
        });

    }

    private void logon() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(logon.getWindowToken(), 0);

        if (user.equals("")) {
            Snackbar.make(logon, "学号不能为空", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Snackbar.make(logon, "密码不能为空", Snackbar.LENGTH_SHORT).show();
            return;
        }

        subscription = network.getHbutApi(HbutApi.Account_HOST).logOn(user, pass, "Student")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer_log);

        Setting.setUserName(user);
        Setting.setPassword(pass);
    }

    private void saveLoginStatus() {
        SharedPreferences setting = getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putBoolean("isLogin", Setting.isLogin());
        editor.putString("cookies", Setting.getCookies());
        editor.putString("userName", Setting.getUserName());
        editor.putString("password", Setting.getPassword());
        editor.apply();
    }

}
