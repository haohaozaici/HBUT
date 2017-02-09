package com.example.hao.hbut.model.data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by hao on 2017/2/9.
 */

public class AllInfo {
    private static AllInfo sAllInfo;


    public static AllInfo get(Context context){
        if (sAllInfo == null){
            sAllInfo = new AllInfo(context);
        }
        return sAllInfo;
    }

    private AllInfo(Context context){

    }

}
