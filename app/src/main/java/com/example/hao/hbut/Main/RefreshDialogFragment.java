package com.example.hao.hbut.Main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.example.hao.hbut.R;

/**
 * Created by haohao on 2017/3/22.
 */

public class RefreshDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(R.layout.refresh_layout);

        return builder.create();
    }
}
