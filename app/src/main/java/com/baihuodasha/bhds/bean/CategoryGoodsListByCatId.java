package com.baihuodasha.bhds.bean;

import java.util.List;

/**
 * Created by zhang on 2018/7/20.
 */

public class CategoryGoodsListByCatId {

  /**
   * code : 200
   * msg : 获取分类商品列表成功
   * data : {"cat_name":"五金工具","type_img":"images/201805/1527133082991962413.png","type_img_url":"","goodsList":[{"goods_id":"4838","goods_name":"多用途清洁刀地板铲刀 多功能瓷砖清洁刀 铝合金清洁铲刀玻璃刮刀","click_count":"128","market_price":"ds_thumb":"images/201802/goods_img/4838_P_1518341793133.jpg","goods_img":"images/201805/go","market_price":"35.87","org_price":"29.90","shop_price":"29.90","promote_price":"0.00","promote_start_date":"0","promote_end_date":"0","goods_brief":"声_count":"18","market_price":"38.40","org_price":"32.00","shop_price":"32.00","proG_1527068836307.jpg"}]}
   */

  private String code;
  private String msg;
  private DataBean data;

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

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * cat_name : 五金工具
     * type_img : images/201805/1527133082991962413.png
     * type_img_url :
     * goodsList : [{"goods_id":"4838","goods_name":"多用途清洁刀地板铲刀 多功能瓷砖清洁刀 铝合金清洁铲刀玻璃刮刀","click_count":"128","market_price屉把手","goods_thumb":"images/201802/goods_img/4662_P_1517984580550.jpg","goods_img":"images/201805/goods_img/5028_G_1527068836307.jpg"}]
     */

    private String cat_name;
    private String type_img;
    private String type_img_url;
    private List<GoodsListBean> goodsList;

    public String getCat_name() {
      return cat_name;
    }

    public void setCat_name(String cat_name) {
      this.cat_name = cat_name;
    }

    public String getType_img() {
      return type_img;
    }

    public void setType_img(String type_img) {
      this.type_img = type_img;
    }

    public String getType_img_url() {
      return type_img_url;
    }

    public void setType_img_url(String type_img_url) {
      this.type_img_url = type_img_url;
    }

    public List<GoodsListBean> getGoodsList() {
      return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
      this.goodsList = goodsList;
    }

    public static class GoodsListBean {
      /**
       * goods_id : 4838
       * goods_name : 多用途清洁刀地板铲刀 多功能瓷砖清洁刀 铝合金清洁铲刀玻璃刮刀
       * click_count : 128
       * market_price : 31.08
       * org_price : 25.90
       * shop_price : 25.90
       * promote_price : 0.00
       * promote_start_date : 0
       * promote_end_date : 0
       * goods_brief : 多用途清洁刀地板铲刀 多功能瓷砖清洁刀 铝合金清洁铲刀玻璃刮刀
       * goods_thumb : images/201802/goods_img/4838_P_1518341793133.jpg
       * goods_img : images/201805/goods_img/5028_G_1527068836307.jpg
       */

      private String goods_id;
      private String goods_name;
      private String click_count;
      private String market_price;
      private String org_price;
      private String shop_price;
      private String promote_price;
      private String promote_start_date;
      private String promote_end_date;
      private String goods_brief;
      private String goods_thumb;
      private String goods_img;

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

      public String getClick_count() {
        return click_count;
      }

      public void setClick_count(String click_count) {
        this.click_count = click_count;
      }

      public String getMarket_price() {
        return market_price;
      }

      public void setMarket_price(String market_price) {
        this.market_price = market_price;
      }

      public String getOrg_price() {
        return org_price;
      }

      public void setOrg_price(String org_price) {
        this.org_price = org_price;
      }

      public String getShop_price() {
        return shop_price;
      }

      public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
      }

      public String getPromote_price() {
        return promote_price;
      }

      public void setPromote_price(String promote_price) {
        this.promote_price = promote_price;
      }

      public String getPromote_start_date() {
        return promote_start_date;
      }

      public void setPromote_start_date(String promote_start_date) {
        this.promote_start_date = promote_start_date;
      }

      public String getPromote_end_date() {
        return promote_end_date;
      }

      public void setPromote_end_date(String promote_end_date) {
        this.promote_end_date = promote_end_date;
      }

      public String getGoods_brief() {
        return goods_brief;
      }

      public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
      }

      public String getGoods_thumb() {
        return goods_thumb;
      }

      public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
      }

      public String getGoods_img() {
        return goods_img;
      }

      public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
      }
    }
  }
}
