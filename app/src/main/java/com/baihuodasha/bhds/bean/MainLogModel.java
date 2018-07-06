package com.baihuodasha.bhds.bean;

import java.io.Serializable;

/**
 * Created by zhang on 2018/7/6.
 */

public class MainLogModel implements Serializable {

  /**
   * code : 1
   * msg : 登录成功
   * data : {"key":"0de2762a03d69c62d5c088c163190961","userid":"1953","data":"0de2762a03d69c62d5c088c163190961"}
   */

  private String code;
  private String msg;
  private LoginDataModel data;

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

  public LoginDataModel getData() {
    return data;
  }

  public void setData(LoginDataModel data) {
    this.data = data;
  }

  public static class LoginDataModel {
    /**
     * key : 0de2762a03d69c62d5c088c163190961
     * userid : 1953
     * data : 0de2762a03d69c62d5c088c163190961
     */

    private String key;
    private String userid;
    private String data;

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getUserid() {
      return userid;
    }

    public void setUserid(String userid) {
      this.userid = userid;
    }

    public String getData() {
      return data;
    }

    public void setData(String data) {
      this.data = data;
    }
  }
}
