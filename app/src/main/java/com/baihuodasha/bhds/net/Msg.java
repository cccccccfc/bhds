package com.baihuodasha.bhds.net;

/**
 * Created by qiaoyaqian on 16/12/16.
 */

public class Msg<T> {
    private int code;
    private String msg;
    private T data;

    public int getRetValue() {
        return code;
    }

    public void setRetValue(int retValue) {
        this.code = retValue;
    }

    public String getRetMessage() {
        return msg;
    }

    public void setRetMessage(String retMessage) {
        this.msg = retMessage;
    }

//    public LoginModel getData() {
//        return data;
//    }
//
//    public void setData(LoginModel data) {
//        this.data = data;
//    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "retValue=" + code +
                ", retMessage='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
