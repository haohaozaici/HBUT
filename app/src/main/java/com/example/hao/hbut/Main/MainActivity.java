package com.example.hao.hbut.Main;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.hao.hbut.Main.Grade.GradeFragment;
import com.example.hao.hbut.Main.Sch.SchFragment;
import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity {

    private BottomNavigationView navigationView;
    private GradeFragment gradeFragment = new GradeFragment();
    private SchFragment schFragment = new SchFragment();
    private MeFragment meFragment = new MeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_layout);

        MainActivityPermissionsDispatcher.needPhoneStateWithCheck(this);

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
                        if (!gradeFragment.isAdded()) {
                            ft.add(R.id.container, gradeFragment);
                        }
                        ft.hide(schFragment).hide(meFragment).show(gradeFragment);

                        break;
                    case R.id.tab2:
                        if (!schFragment.isAdded()) {
                            ft.add(R.id.container, schFragment);
                        }
                        ft.hide(gradeFragment).hide(meFragment).show(schFragment);

                        break;
                    case R.id.tab3:
                        if (!meFragment.isAdded()) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_me:
                startActivity(new Intent(MainActivity.this, AboutMe.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    public void needPhoneState() {

    }

    //请求权限前提示消息
    @OnShowRationale(Manifest.permission.READ_PHONE_STATE)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("我们申请手机权限仅用来统计用户，请允许哟...")
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    //权限被拒绝提醒
    @OnPermissionDenied(Manifest.permission.READ_PHONE_STATE)
    void showDeniedForCamera() {
        Snackbar.make(navigationView, "权限被拒绝，呜呜呜...", Snackbar.LENGTH_LONG)
                .setAction("允许", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivityPermissionsDispatcher.needPhoneStateWithCheck(MainActivity.this);
                    }
                }).show();
    }
}
