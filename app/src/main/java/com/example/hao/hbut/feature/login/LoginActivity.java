package com.example.hao.hbut.feature.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.StringUtils;
import com.example.hao.hbut.R;
import com.example.hao.hbut.View.util.SoftKeyInputHidWidget;
import com.example.hao.hbut.View.widget.dialog.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by hao on 2016-10-25.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_layout) RelativeLayout mRootLayout;
    @BindView(R.id.login_account_img) ImageView mAccountImg;
    @BindView(R.id.login_account_value) EditText mAccountEditText;
    @BindView(R.id.login_account_line) View mAccountLine;

    @BindView(R.id.login_password_img) ImageView mPasswordImg;
    @BindView(R.id.login_password_value) EditText mPasswordEditText;
    @BindView(R.id.login_password_line) View mPasswordLine;

    @BindView(R.id.login_button) Button mLoginButton;

    private LoadingDialog dialog;

    private String mAccountString;
    private String mPasswordString;

    private LoginViewModel mModel;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        initView();

        mModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mModel.getLoginLiveData().observe(this, loginRes -> {
            dialog.dismiss();
            if (loginRes != null) {
                Snackbar.make(mLoginButton, loginRes.getMessage(), Snackbar.LENGTH_SHORT).show();
                mLoginButton.postDelayed(() -> {
                    // TODO: 2018/1/20 MainActivity
                }, 1000);
            }

        });

        mModel.getThrowableLiveData().observe(this, throwable -> {
            dialog.dismiss();
            mLoginButton.setClickable(true);
            if (throwable != null) {
                throwable.printStackTrace();
                Snackbar.make(mLoginButton, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        dialog = new LoadingDialog(this);
   
        mAccountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAccountString = charSequence.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mAccountEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                mAccountLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mAccountImg.setImageResource(R.drawable.smile2);
            } else {
                mAccountLine.setBackgroundColor(getResources().getColor(R.color.grey_logon));
                mAccountImg.setImageResource(R.drawable.smile);
            }
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordString = charSequence.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPasswordEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                mPasswordLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mPasswordImg.setImageResource(R.drawable.password2);
            } else {
                mPasswordLine.setBackgroundColor(getResources().getColor(R.color.grey_logon));
                mPasswordImg.setImageResource(R.drawable.password);
            }
        });

        mLoginButton.setOnClickListener(v -> {

            v.setClickable(false);
            login();
        });
        //handle login button cover
        SoftKeyInputHidWidget.addLayoutListener(mRootLayout, mLoginButton);

    }

    private void login() {
        dialog.show();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mLoginButton.getWindowToken(), 0);
        }

        if (StringUtils.isEmpty(mAccountString)) {
            Snackbar.make(mLoginButton, "学号不能为空", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(mPasswordString)) {
            Snackbar.make(mLoginButton, "密码不能为空", Snackbar.LENGTH_SHORT).show();
            return;
        }

        mModel.login(mAccountString, mPasswordString);

        // TODO: 2018/1/20 show loadingDialog
    }


}
