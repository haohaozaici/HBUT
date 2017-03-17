package com.example.hao.hbut.Main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.hao.hbut.Main.Grade.GradeFragment;
import com.example.hao.hbut.Main.Sch.SchFragment;
import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseActivity;
import com.example.hao.hbut.base.BaseFragment;

public class MainActivity extends BaseActivity {

    private BottomNavigationView navigationView;
    private GradeFragment gradeFragment = new GradeFragment();
    private SchFragment schFragment = new SchFragment();
    private MeFragment meFragment = new MeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_layout);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.container, gradeFragment).commit();

        navigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                switch (item.getItemId()) {
                    case R.id.tab1:
                        if (!gradeFragment.isAdded()){
                            ft.add(R.id.container, gradeFragment);
                        }
                        ft.hide(schFragment).hide(meFragment).show(gradeFragment);

                        break;
                    case R.id.tab2:
                        if (!schFragment.isAdded()){
                            ft.add(R.id.container, schFragment);
                        }
                        ft.hide(gradeFragment).hide(meFragment).show(schFragment);

                        break;
                    case R.id.tab3:
                        if (!meFragment.isAdded()){
                            ft.add(R.id.container, meFragment);
                        }
                        ft.hide(schFragment).hide(gradeFragment).show(meFragment);

                        break;
                }
                ft.commit();
                return true;
            }
        });
    }


}
