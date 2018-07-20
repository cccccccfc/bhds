package com.baihuodasha.bhds.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang on 2018/7/6.
 */

public class MainIndexBannerModel implements Serializable {

  @Override public String toString() {
    return "MainIndexBannerModel{"
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
   * code : 1
   * msg : 获取成功
   * data : [{"name":"智能穿戴","url":"affiche.php?ad_id=75&uri=http://www.baihuodasha.com/mobile/category.php?id=455","image":"data/afficheimg/1531279335788437167.jpg","content":"<a href='' target='_blank'><img src='data/afficheimg/1531279335788437167.jpg' width='520' height='280' /><\/a>","ad_code":"1531279335788437167.jpg"},{"name":"家居装饰","url":"affiche.php?ad_id=71&uri=http://www.baihuodasha.com/mobile/category.php?id=401","image":"data/afficheimg/1521189618759975270.jpg","content":"<a href='' target='_blank'><img src='data/afficheimg/1521189618759975270.jpg' width='520' height='280' /><\/a>","ad_code":"1521189618759975270.jpg"},{"name":"食品","url":"affiche.php?ad_id=70&uri=http://www.baihuodasha.com/mobile/category.php?id=342","image":"data/afficheimg/1521189941117678111.jpg","content":"<a href='' target='_blank'><img src='data/afficheimg/1521189941117678111.jpg' width='520' height='280' /><\/a>","ad_code":"1521189941117678111.jpg"},{"name":"智能家居","url":"affiche.php?ad_id=66&uri=http://www.baihuodasha.com/mobile/goods.php?id=4927","image":"data/afficheimg/1521189818366778385.jpg","content":"<a href='' target='_blank'><img src='data/afficheimg/1521189818366778385.jpg' width='520' height='280' /><\/a>","ad_code":"1521189818366778385.jpg"}]
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
     * name : 智能穿戴
     * url : affiche.php?ad_id=75&uri=http://www.baihuodasha.com/mobile/category.php?id=455
     * image : data/afficheimg/1531279335788437167.jpg
     * content : <a href='' target='_blank'><img src='data/afficheimg/1531279335788437167.jpg' width='520' height='280' /></a>
     * ad_code : 1531279335788437167.jpg
     */

    private String name;
    private String url;
    private String image;
    private String content;
    private String ad_code;

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

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getAd_code() {
      return ad_code;
    }

    public void setAd_code(String ad_code) {
      this.ad_code = ad_code;
    }

    private String goods_id;
    private String goods_name;
    private String shop_price;
    private String goods_img;
    private String sell_num;

    public String getGoods_id() {
      return goods_id;
    }

    public void setGoods_id(String goods_id) {
      this.goods_id = goods_id;
    }

    public String getGoods_name() {
      return goods_name;
    }

    public void setGoods_name(String goods_name) {
      this.goods_name = goods_name;
    }

    public String getShop_price() {
      return shop_price;
    }

    public void setShop_price(String shop_price) {
      this.shop_price = shop_price;
    }

    public String getGoods_img() {
      return goods_img;
    }

    public void setGoods_img(String goods_img) {
      this.goods_img = goods_img;
    }

    public String getSell_num() {
      return sell_num;
    }

    public void setSell_num(String sell_num) {
      this.sell_num = sell_num;
    }
  }
}
