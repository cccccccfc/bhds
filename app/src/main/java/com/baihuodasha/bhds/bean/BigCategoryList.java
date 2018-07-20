package com.baihuodasha.bhds.bean;

import java.util.List;

/**
 * Created by zhang on 2018/7/18.
 */

public class BigCategoryList {

  @Override public String toString() {
    return "BigCategoryList{"
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
   * msg : 获取分类页面大类成功
   * data : [{"cat_id":"2","cat_name":"洗护卫浴","parent_id":"0","is_show":"1","type_img":"images/201805/1527075442121505346.jpg"},{"cat_id":"4","cat_name":"家居生活","parent_id":"0","is_show":"1","type_img":"images/201805/1527141637992290788.jpg"},{"cat_id":"5","cat_name":"享受生活","parent_id":"0","is_show":"1","type_img":"images/201805/1527131835025400111.jpg"},{"cat_id":"6","cat_name":"厨房料理","parent_id":"0","is_show":"1","type_img":"images/201805/1527075430429696629.jpg"},{"cat_id":"356","cat_name":"虚拟商品","parent_id":"0","is_show":"1","type_img":""},{"cat_id":"418","cat_name":"百货超市","parent_id":"0","is_show":"1","type_img":"images/201805/1527141603226162124.jpg"}]
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
     * cat_id : 2
     * cat_name : 洗护卫浴
     * parent_id : 0
     * is_show : 1
     * type_img : images/201805/1527075442121505346.jpg
     */

    private String cat_id;
    private String cat_name;
    private String parent_id;
    private String is_show;

    @Override public String toString() {
      return "DataBean{"
          + "cat_id='"
          + cat_id
          + '\''
          + ", cat_name='"
          + cat_name
          + '\''
          + ", parent_id='"
          + parent_id
          + '\''
          + ", is_show='"
          + is_show
          + '\''
          + ", type_img='"
          + type_img
          + '\''
          + '}';
    }

    private String type_img;

    public String getCat_id() {
      return cat_id;
    }

    public void setCat_id(String cat_id) {
      this.cat_id = cat_id;
    }

    public String getCat_name() {
      return cat_name;
    }

    public void setCat_name(String cat_name) {
      this.cat_name = cat_name;
    }

    public String getParent_id() {
      return parent_id;
    }

    public void setParent_id(String parent_id) {
      this.parent_id = parent_id;
    }

    public String getIs_show() {
      return is_show;
    }

    public void setIs_show(String is_show) {
      this.is_show = is_show;
    }

    public String getType_img() {
      return type_img;
    }

    public void setType_img(String type_img) {
      this.type_img = type_img;
    }
  }
}
