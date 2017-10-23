package com.example.hao.hbut.Main.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hao.hbut.Login.LoginActivity;
import com.example.hao.hbut.Main.Sch.SchFragment;
import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseFragment;
import com.example.hao.hbut.model.bean.Setting;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * Created by hao on 2016/11/25.
 */

public class MeFragment extends BaseFragment {

    private TextView name;
    private TextView signOut;
    private TextView libName;
    private TextView libSignOut;
    private Button send_reply;
    private SmileRating smileRating;
    private static int smileRatingChecked = BaseRating.OKAY;

    private static final int ME_FRAGMENT = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout3, container, false);

        name = (TextView) view.findViewById(R.id.name);
        name.setText(setting.userName);

        signOut = (TextView) view.findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        libName = (TextView) view.findViewById(R.id.lib_name);
        libSignOut = (TextView) view.findViewById(R.id.lib_sign_out);
        libSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libSign();
            }
        });
        if (setting.isLibIsLogin()) {
            libName.setText(setting.getLibUserNameText());
            libSignOut.setText("已绑定");
            libSignOut.setClickable(false);
        } else {
            libName.setHint("未绑定");
        }

        send_reply = (Button) view.findViewById(R.id.send_reply);
        send_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(send_reply, getString(R.string.weixin_summary), Snackbar.LENGTH_LONG).show();
            }
        });

        smileRating = (SmileRating) view.findViewById(R.id.smile_rating);
        smileRating.setSelectedSmile(smileRatingChecked);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        smileRatingChecked = BaseRating.BAD;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        smileRatingChecked = BaseRating.GOOD;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        smileRatingChecked = BaseRating.GREAT;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        smileRatingChecked = BaseRating.OKAY;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        smileRatingChecked = BaseRating.TERRIBLE;
                        break;
                }
            }
        });
        return view;
    }


    public void signOut() {
        setting.setFirstRun(true);
        setting.setLogin(false);
        setting.setUserName("");
        setting.setPassword("");
        setting.setCookies("");
        setting.setLibIsLogin(false);
        setting.setLibUserName("");
        setting.setLibPassword("");
        setting.setLibCookie("");
        SchFragment.clearCells();
        data.clearAll(getActivity());


        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("login", Setting.CLASS_PLATFORM);
        startActivity(intent);
        getActivity().finish();
    }

    private void libSign() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("login", Setting.LIB);
        startActivityForResult(intent, ME_FRAGMENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            libName.setText(setting.getLibUserNameText());
            libSignOut.setText("退出登录");
        }
        if (resultCode == RESULT_CANCELED) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Snackbar.make(send_reply, "已取消", Snackbar.LENGTH_SHORT).show();
                }
            }, 500);

        }
    }


}
