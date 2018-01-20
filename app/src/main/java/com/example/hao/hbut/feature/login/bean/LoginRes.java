package com.example.hao.hbut.feature.login.bean;

import com.example.hao.hbut.model.network.bean.BaseRes;

/**
 * Created by haoyuan on 2018/1/20.
 */

public class LoginRes extends BaseRes {

    /**
     * Status : 0
     * Message : 登陆成功
     * InputType : null
     * OutData : null
     * ObjData : null
     */


    private int Status;
    private String Message;

    public int getStatus() {
        return Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
