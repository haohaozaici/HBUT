package com.example.hao.hbut.feature.about;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hao.hbut.R;


/**
 * Created by wwfm on 2017/3/18.
 */

public class AboutMe extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_me);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, new AboutMeFragment()).commit();

    }

}
