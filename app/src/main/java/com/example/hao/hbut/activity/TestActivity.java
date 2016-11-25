package com.example.hao.hbut.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.hao.hbut.R;

/**
 * Created by hao on 2016/11/24.
 */

public class TestActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_layout);

        navigationView = (BottomNavigationView)findViewById(R.id.design_navigation_view);


    }
}
