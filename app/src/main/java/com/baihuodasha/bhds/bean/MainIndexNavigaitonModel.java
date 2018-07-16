package com.baihuodasha.bhds.bean;

import java.util.List;

/**
 * Created by zhang on 2018/7/10.
 */

public class MainIndexNavigaitonModel {

  @Override public String toString() {
    return "MainIndexNavigaitonModel{"
        + "code='"
        + code
        + '\''
        + ", msg='"
        + msg
        + '\''
        + ", data="
        + data
        + '}';
  }

  /**
   * code : 200
   * msg : 获取导航成功
   * data : [{"id":"27","cid":"0","name":"百货超市","url":"search.php"},{"id":"32","cid":"0","name":"智能家居","url":"http://www.baihuodasha.com/topic.php?topic_id=7"}]
   */

  private String code;
  private String msg;
  private List<DataBean> data;

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

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * id : 27
     * cid : 0
     * name : 百货超市
     * url : search.php
     */

    private String id;
    private String cid;
    private String name;
    private String url;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getCid() {
      return cid;
    }

    public void setCid(String cid) {
      this.cid = cid;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}
