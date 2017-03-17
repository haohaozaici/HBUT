package com.example.hao.hbut.Login;

import android.content.Context;
import android.content.Intent;
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
import com.example.hao.hbut.Main.MainActivity;
import com.example.hao.hbut.View.widget.ENRefreshView;
import com.example.hao.hbut.base.BaseActivity;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.bean.LogInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
    private ENRefreshView refresh;

    private String user = "", pass = "";
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

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
        refresh = (ENRefreshView) findViewById(R.id.refresh_all);
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
        refresh.setDuration(3000);
        refresh.startRefresh();

        getLoginInfo(user, pass);
        logon.setVisibility(View.INVISIBLE);

    }

    public void getLoginInfo(String user, String pass) {

        network.getHbutApi(HbutApi.Account_HOST).logOn(user, pass, "Student")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LogInfo logInfo) {
                        if (logInfo.Status.equals("0")) {

                            saveLoginStatus();

                            Snackbar.make(name, logInfo.Message, Snackbar.LENGTH_SHORT).show();
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
                            Snackbar.make(name, logInfo.Message, Snackbar.LENGTH_LONG).show();
                            logon.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        logon.setVisibility(View.VISIBLE);
                        Snackbar.make(name, getString(R.string.error), Snackbar.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void saveLoginStatus() {
        setting.setUserName(user);
        setting.setPassword(pass);
    }


}
