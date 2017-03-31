package com.example.hao.hbut.Main.Sch.ListDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.Database;

/**
 * Created by haohao on 2017/3/21.
 */

public class SchDialogFragment extends DialogFragment implements View.OnClickListener{

    public Database data = Database.instance;
    private TextView dateTime;
    private Button add;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todolist_dialog_layout, container, false);

        dateTime = (TextView) view.findViewById(R.id.time_text);
//        add =


        return view;
    }

    @Override
    public void onClick(View v) {

    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        super.onCreateDialog(savedInstanceState);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
//                .setView(R.layout.todolist_dialog_layout);
//
////        date = (TextView)
//
//        return builder.create();
//    }
}
