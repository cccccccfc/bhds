package com.baihuodasha.bhds.bean;

import java.util.List;

/**
 * Created by zhang on 2018/7/10.
 */

public class MainIndexTopModel {

  /**
   * code : 1
   * msg : 获取成功
   * data : [{"ad_id":"1","ad_type":"1","ad_name":"65432","add_time":"1445369576","content":"data/afficheimg/20151021nqgkvz.swf","url":"http://","ad_status":"0"}]
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
     * ad_id : 1
     * ad_type : 1
     * ad_name : 65432
     * add_time : 1445369576
     * content : data/afficheimg/20151021nqgkvz.swf
     * url : http://
     * ad_status : 0
     */

    private String ad_id;
    private String ad_type;
    private String ad_name;
    private String add_time;
    private String content;
    private String url;
    private String ad_status;

    public String getAd_id() {
      return ad_id;
    }

    public void setAd_id(String ad_id) {
      this.ad_id = ad_id;
    }

    public String getAd_type() {
      return ad_type;
    }

    public void setAd_type(String ad_type) {
      this.ad_type = ad_type;
    }

    public String getAd_name() {
      return ad_name;
    }

    public void setAd_name(String ad_name) {
      this.ad_name = ad_name;
    }

    public String getAdd_time() {
      return add_time;
    }

    public void setAdd_time(String add_time) {
      this.add_time = add_time;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getAd_status() {
      return ad_status;
    }

    public void setAd_status(String ad_status) {
      this.ad_status = ad_status;
    }
  }
}
