package com.baihuodasha.bhds.bean;

import java.io.Serializable;

/**
 * Created by zhang on 2018/7/6.
 */

public class MainLogModel implements Serializable {

  @Override public String toString() {
    return "MainLogModel{"
        + "key='"
        + key
        + '\''
        + ", userid='"
        + userid
        + '\''
        + ", data='"
        + data
        + '\''
        + '}';
  }

  /**
     * key : 07ce06e30b0fc0cba7b875f5d8a50613
     * userid : 1964
     * data : 07ce06e30b0fc0cba7b875f5d8a50613
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
