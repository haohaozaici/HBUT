package com.example.hao.hbut.fragment;

import android.app.Fragment;

import rx.Subscription;

/**
 * Created by hao on 2016/11/25.
 */

public class BaseFragment extends Fragment {

    protected Subscription subscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
