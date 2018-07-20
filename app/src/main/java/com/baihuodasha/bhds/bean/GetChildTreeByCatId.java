package com.baihuodasha.bhds.bean;

import java.util.List;

/**
 * Created by zhang on 2018/7/18.
 */

public class GetChildTreeByCatId {

  /**
   * code : 200
   * msg : 获取分类树成功
   * data : [{"id":"306","name":"餐具茶具","img":"images/201805/1527134223398921743.png","cat_id":[{"id":"314","name":"茶具套装","img":"images/201805/1527145341571611352.jpg","cat_id":[]},{"id":"317","name":"餐具套装","img":"images/201805/1527145294792439769.jpg","cat_id":[]},{"id":"318","name":"咖啡具","img":"images/201805/1527148858223918198.jpg","cat_id":[]},{"id":"521","name":"茶叶罐","img":"images/201805/1527145351939811366.jpg","cat_id":[]},{"id":"522","name":"茶盘","img":"images/201805/1527145318831036967.jpg","cat_id":[]},{"id":"523","name":"茶道/其他","img":"images/201805/1527145330758184757.jpg","cat_id":[]},{"id":"525","name":"单杯/主人杯","img":"images/201805/1527145307001896445.jpg","cat_id":[]},{"id":"526","name":"茶壶","img":"","cat_id":[]}]},{"id":"307","name":"水具酒具","img":"images/201805/1527134289245392615.png","cat_id":[{"id":"528","name":"保温杯","img":"images/201805/1527145088764590589.jpg","cat_id":[]},{"id":"529","name":"塑料杯","img":"images/201805/1527145118274387302.jpg","cat_id":[]},{"id":"530","name":"玻璃杯","img":"images/201805/1527145055227132694.jpg","cat_id":[]},{"id":"531","name":"酒杯/酒具","img":"images/201805/1527145074339811539.jpg","cat_id":[]}]},{"id":"308","name":"厨房厨具","img":"images/201805/1527134201903685038.png","cat_id":[{"id":"332","name":"厨具","img":"images/201805/1527145269143780997.jpg","cat_id":[]},{"id":"476","name":"饭盒","img":"images/201805/1527145230933800400.jpg","cat_id":[]},{"id":"527","name":"保温壶/保温瓶","img":"images/201805/1527145215525499409.jpg","cat_id":[]}]},{"id":"310","name":"烹饪锅具","img":"images/201805/1527134153763367895.png","cat_id":[{"id":"342","name":"炒锅","img":"images/201805/1527148895931780274.jpg","cat_id":[]},{"id":"343","name":"锅具套装","img":"images/201805/1527145026569739319.jpg","cat_id":[]},{"id":"346","name":"压力锅","img":"images/201805/1527144978937719980.jpg","cat_id":[]},{"id":"347","name":"火锅","img":"images/201805/1527144989764877079.jpg","cat_id":[]},{"id":"349","name":"蒸锅","img":"images/201805/1527145015380116032.jpg","cat_id":[]},{"id":"450","name":"多功能锅","img":"images/201805/1527144964243361462.jpg","cat_id":[]}]},{"id":"311","name":"厨房配件","img":"images/201805/1527134177781772081.png","cat_id":[{"id":"350","name":"厨房小工具","img":"images/201805/1527145152547234475.jpg","cat_id":[]},{"id":"352","name":"储物/置物架","img":"images/201805/1527145182817047262.jpg","cat_id":[]},{"id":"498","name":"厨房配件","img":"images/201805/1527148927093978450.jpg","cat_id":[]},{"id":"518","name":"桌布","img":"images/201805/1527145171296735725.jpg","cat_id":[]}]}]
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
     * id : 306
     * name : 餐具茶具
     * img : images/201805/1527134223398921743.png
     * cat_id : [{"id":"314","name":"茶具套装","img":"images/201805/1527145341571611352.jpg","cat_id":[]},{"id":"317","name":"餐具套装","img":"images/201805/1527145294792439769.jpg","cat_id":[]},{"id":"318","name":"咖啡具","img":"images/201805/1527148858223918198.jpg","cat_id":[]},{"id":"521","name":"茶叶罐","img":"images/201805/1527145351939811366.jpg","cat_id":[]},{"id":"522","name":"茶盘","img":"images/201805/1527145318831036967.jpg","cat_id":[]},{"id":"523","name":"茶道/其他","img":"images/201805/1527145330758184757.jpg","cat_id":[]},{"id":"525","name":"单杯/主人杯","img":"images/201805/1527145307001896445.jpg","cat_id":[]},{"id":"526","name":"茶壶","img":"","cat_id":[]}]
     */

    private String id;
    private String name;
    private String img;
    private List<CatIdBean> cat_id;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getImg() {
      return img;
    }

    public void setImg(String img) {
      this.img = img;
    }

    public List<CatIdBean> getCat_id() {
      return cat_id;
    }

    public void setCat_id(List<CatIdBean> cat_id) {
      this.cat_id = cat_id;
    }

    public static class CatIdBean {
      /**
       * id : 314
       * name : 茶具套装
       * img : images/201805/1527145341571611352.jpg
       * cat_id : []
       */

      private String id;
      private String name;
      private String img;
      private List<?> cat_id;

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getImg() {
        return img;
      }

      public void setImg(String img) {
        this.img = img;
      }

      public List<?> getCat_id() {
        return cat_id;
      }

      public void setCat_id(List<?> cat_id) {
        this.cat_id = cat_id;
      }
    }
  }
}
