package com.example.hao.hbut.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hao.hbut.R;
import com.example.hao.hbut.activity.EntryActivity;
import com.example.hao.hbut.activity.MainActivity;
import com.example.hao.hbut.model.Setting;

/**
 * Created by hao on 2016/11/25.
 */

public class MeFragment extends BaseFragment {

    private TextView name;
    private Button signOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout3, container, false);

        name = (TextView) view.findViewById(R.id.name);
        name.setText(Setting.userName);

        signOut = (Button) view.findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        return view;
    }

    public void signOut() {
        SharedPreferences setting = getActivity().getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putBoolean("isLogin", false);
        editor.putString("cookies", "");
        editor.apply();
        Intent intent = new Intent(getActivity(), EntryActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
