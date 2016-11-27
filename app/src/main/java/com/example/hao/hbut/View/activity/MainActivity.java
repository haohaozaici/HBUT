package com.example.hao.hbut.View.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.hao.hbut.R;
import com.example.hao.hbut.View.fragment.GradeFragment;
import com.example.hao.hbut.View.fragment.MeFragment;
import com.example.hao.hbut.View.fragment.SchFragment;

public class MainActivity extends BaseActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_layout);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.container, new GradeFragment());
        ft.commit();
        navigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                switch (item.getItemId()) {
                    case R.id.tab1:
                        ft.replace(R.id.container, new GradeFragment());

                        break;
                    case R.id.tab2:
                        ft.replace(R.id.container, new SchFragment());

                        break;
                    case R.id.tab3:
                        ft.replace(R.id.container, new MeFragment());

                        break;
                }
                ft.commit();
                return true;
            }
        });
    }
}
