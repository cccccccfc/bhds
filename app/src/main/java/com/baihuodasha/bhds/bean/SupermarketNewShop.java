package com.baihuodasha.bhds.bean;

import java.util.List;

/**
 * Created by zhang on 2018/7/18.
 */

public class SupermarketNewShop {

  /**
   * code : 1
   * msg : 获取成功
   * data : [{"goods_img":"images/201805/thumb_img/5028_thumb_G_1527068836793.jpg","goods_id":"562","goods_name":"长帝（cell_num":"1"},{"goods_img":"images/201805/thumb_img/5028_thumb_G_1527068836793.jpg","goods_id":"2751","goods_name":"Midea/美的 M3.00","sell_num":"22"},{"goods_img":"images/2ricp_price":"165.00","sell_num":"60"}]
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
     * goods_img : images/201805/thumb_img/5028_thumb_G_1527068836793.jpg
     * goods_id : 562
     * goods_name : 长帝（changdi）42升L大容量风机上下调温家用电烤箱CRTF42W独立温控多功能烘焙蛋糕披萨旋转烤箱
     * shop_price : 589.00
     * sell_num : null
     */

    private String goods_img;
    private String goods_id;
    private String goods_name;
    private String shop_price;
    private Object sell_num;

    public String getGoods_img() {
      return goods_img;
    }

    public void setGoods_img(String goods_img) {
      this.goods_img = goods_img;
    }

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

    public Object getSell_num() {
      return sell_num;
    }

    public void setSell_num(Object sell_num) {
      this.sell_num = sell_num;
    }
  }
}
