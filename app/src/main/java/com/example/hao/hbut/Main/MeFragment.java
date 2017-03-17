package com.example.hao.hbut.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hao.hbut.Login.LoginActivity;
import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseFragment;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import static android.content.ContentValues.TAG;

/**
 * Created by hao on 2016/11/25.
 */

public class MeFragment extends BaseFragment {

    private TextView name;
    private Button signOut;
    private SmileRating smileRating;
    private static int smileRatingChecked = BaseRating.OKAY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout3, container, false);

        name = (TextView) view.findViewById(R.id.name);
        name.setText(setting.userName);

        signOut = (Button) view.findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
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
        setting.setLogin(false);
        setting.setUserName("");
        setting.setPassword("");
        setting.setCookies("");
        data.clearAll(getActivity());

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


}
