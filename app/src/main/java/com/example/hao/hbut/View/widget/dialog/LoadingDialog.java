package com.example.hao.hbut.View.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.hao.hbut.R;
import com.example.hao.hbut.View.widget.indcator.AVLoadingIndicatorView;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class LoadingDialog extends Dialog {

    private AVLoadingIndicatorView mAVLoadingIndicatorView;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.loading_dialog_layout);
        mAVLoadingIndicatorView = findViewById(R.id.loading_view);
    }


    @Override
    public void show() {
        mAVLoadingIndicatorView.smoothToShow();
        super.show();
    }

    @Override
    public void dismiss() {
        mAVLoadingIndicatorView.smoothToHide();
        super.dismiss();
    }
}
