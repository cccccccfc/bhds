package com.baihuodasha.bhds.bean;

/**
 * Created by qiaoyaqian on 16/12/16.
 */

public class Msg<T> {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private T data;

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
