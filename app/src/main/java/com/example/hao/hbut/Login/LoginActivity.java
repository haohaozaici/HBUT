package com.example.hao.hbut.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hao.hbut.Main.MainActivity;
import com.example.hao.hbut.Main.me.LibInfo;
import com.example.hao.hbut.R;
import com.example.hao.hbut.View.widget.ENRefreshView;
import com.example.hao.hbut.base.BaseActivity;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.bean.LogInfo;
import com.example.hao.hbut.model.bean.Setting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * Created by hao on 2016-10-25.
 */

public class LoginActivity extends BaseActivity {

    private EditText name;
    private EditText password;
    private EditText captcha;
    private View line1;
    private ImageView img1;
    private View line2;
    private ImageView img2;
    private View line3;
    private ImageView img3;
    private ImageView captcha_img;
    private Button logon;
    private ENRefreshView refresh;
    private RelativeLayout captcha_layout;

    private String user = "", pass = "", captcha_code = "";
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        String from = "";
        if (setting.isFirstRun) {
            from = Setting.CLASS_PLATFORM;
        } else {
            from = getIntent().getStringExtra("login");
        }

        line1 = (View) findViewById(R.id.line1);
        line2 = (View) findViewById(R.id.line2);
        line3 = (View) findViewById(R.id.line3);
        img1 = (ImageView) findViewById(R.id.img_1);
        img2 = (ImageView) findViewById(R.id.img_2);
        img3 = (ImageView) findViewById(R.id.img_3);
        captcha_img = (ImageView) findViewById(R.id.captcha_img);
        captcha_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLibCaptcha();
            }
        });

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

        captcha = (EditText) findViewById(R.id.captcha_code);
        captcha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                captcha_code = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        captcha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    line3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    img3.setImageResource(R.drawable.captcha2);
                } else {
                    line3.setBackgroundColor(getResources().getColor(R.color.grey_logon));
                    img3.setImageResource(R.drawable.captcha);
                }
            }
        });

        logon = (Button) findViewById(R.id.logon);
        refresh = (ENRefreshView) findViewById(R.id.refresh_all);
        captcha_layout = (RelativeLayout) findViewById(R.id.captcha_layout);
        if (from.equals(Setting.CLASS_PLATFORM)) {
            captcha_layout.setVisibility(View.GONE);
            password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        logon(Setting.CLASS_PLATFORM);
                        return true;
                    }
                    return false;
                }
            });
            logon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logon(Setting.CLASS_PLATFORM);
                }
            });
        } else if (from.equals(Setting.LIB)) {
            getLibCaptcha();
            password.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            captcha.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        logon(Setting.LIB);
                        return true;
                    }
                    return false;
                }
            });
            logon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logon(Setting.LIB);
                }
            });
        }


    }

    private void logon(String from) {
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

        if (from.equals(Setting.CLASS_PLATFORM)) {
            getLoginInfo(user, pass);
        } else if (from.equals(Setting.LIB)) {
            if (captcha_code.isEmpty()) {
                Snackbar.make(logon, "验证码不能为空", Snackbar.LENGTH_SHORT).show();
                return;
            }
            getLibLoginInfo(user, pass, captcha_code);
        }
        refresh.setDuration(3000);
        refresh.startRefresh();

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

    private void getLibCaptcha() {

        network.getHbutApi(HbutApi.LIB_RECEIVED_COOKIE).getCaptchaAndCookie()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap apply(ResponseBody responseBody) throws Exception {
                        return BitmapFactory.decodeStream(responseBody.byteStream());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }


                    @Override
                    public void onNext(Bitmap bitmap) {
                        captcha_img.setImageBitmap(bitmap);
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Snackbar.make(name, getString(R.string.captcha_error), Snackbar.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void getLibLoginInfo(final String user, String pass, String captcha_code) {

        network.getHbutApi(HbutApi.LIB_ADD_COOKIE)
                .verifyCookie(user, pass, captcha_code, "cert_no")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ResponseBody responseBody) throws Exception {
                        String html = "";
                        try {
                            html = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Document doc = Jsoup.parse(html);
                        Element mylib_info = doc.getElementById("mylib_info");
                        Elements info_td = mylib_info.getElementsByTag("td");

                        ArrayList<String> infoList = new ArrayList<String>();
                        for (Element infoEle : info_td) {
                            String info = infoEle.text() + "\n";
                            infoList.add(info);
                        }
                        LibInfo lib = new LibInfo();
                        lib.setInfoList(infoList);
                        data.saveLibInfo(lib);

                        return infoList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }


                    @Override
                    public void onNext(ArrayList<String> value) {

                        String[] cut = value.get(2).split(" ");
                        String userName = cut[1].replace("\n", "");

                        String fullNameText = value.get(1);
                        String nameText = fullNameText.replace("\n", "");
                        setting.setLibUserNameText(nameText.substring(3, nameText.length()));
                        if (userName.equals(user)) {

                            saveLibLoginStatus();

                            Snackbar.make(name, getString(R.string.lib_verify_success), Snackbar.LENGTH_SHORT).show();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 5s = 5000ms
                                    setResult(RESULT_OK);
                                    finish();
                                }
                            }, 1000);
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("getLibLoginInfo", e.toString());
                        Snackbar.make(name, getString(R.string.lib_error), Snackbar.LENGTH_SHORT).show();
                        logon.setVisibility(View.VISIBLE);
                    }


                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void saveLoginStatus() {
        setting.setLogin(true);
        setting.setUserName(user);
        setting.setPassword(pass);
    }

    private void saveLibLoginStatus() {
        setting.setLibIsLogin(true);
        setting.setLibUserName(user);
        setting.setLibPassword(pass);
    }


}
