package com.baihuodasha.bhds.bean;

/**
 * Created by zhang on 2018/7/11.
 */

public class MainUserInfoModel {

  @Override public String toString() {
    return "MainUserInfoModel{"
        + "nick_name='"
        + nick_name
        + '\''
        + ", sex='"
        + sex
        + '\''
        + ", integration='"
        + integration
        + '\''
        + ", headimg='"
        + headimg
        + '\''
        + ", address="
        + address
        + ", mobile="
        + mobile
        + ", email='"
        + email
        + '\''
        + ", user_money='"
        + user_money
        + '\''
        + ", birthday='"
        + birthday
        + '\''
        + ", consume="
        + consume
        + ", attention='"
        + attention
        + '\''
        + ", cart_num="
        + cart_num
        + ", pay="
        + pay
        + ", shipping="
        + shipping
        + ", shipping_send="
        + shipping_send
        + '}';
  }

  /**
   * nick_name : 1024信
   * sex : 2
   * integration : 0
   * headimg :
   * address : null
   * mobile : null
   * email : zhangjun365380@163.com
   * user_money : 0.00
   * birthday : 0000-00-00
   * consume : null
   * attention : 0
   * cart_num : 0
   * pay : 0
   * shipping : 0
   * shipping_send : 0
   */

  private String nick_name;
  private String sex;
  private String integration;
  private String headimg;
  private Object address;
  private Object mobile;
  private String email;
  private String user_money;
  private String birthday;
  private Object consume;
  private String attention;
  private int cart_num;
  private int pay;
  private int shipping;
  private int shipping_send;

  public String getNick_name() {
    return nick_name;
  }

  public void setNick_name(String nick_name) {
    this.nick_name = nick_name;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getIntegration() {
    return integration;
  }

  public void setIntegration(String integration) {
    this.integration = integration;
  }

  public String getHeadimg() {
    return headimg;
  }

  public void setHeadimg(String headimg) {
    this.headimg = headimg;
  }

  public Object getAddress() {
    return address;
  }

  public void setAddress(Object address) {
    this.address = address;
  }

  public Object getMobile() {
    return mobile;
  }

  public void setMobile(Object mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUser_money() {
    return user_money;
  }

  public void setUser_money(String user_money) {
    this.user_money = user_money;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public Object getConsume() {
    return consume;
  }

  public void setConsume(Object consume) {
    this.consume = consume;
  }

  public String getAttention() {
    return attention;
  }

  public void setAttention(String attention) {
    this.attention = attention;
  }

  public int getCart_num() {
    return cart_num;
  }

  public void setCart_num(int cart_num) {
    this.cart_num = cart_num;
  }

  public int getPay() {
    return pay;
  }

  public void setPay(int pay) {
    this.pay = pay;
  }

  public int getShipping() {
    return shipping;
  }

  public void setShipping(int shipping) {
    this.shipping = shipping;
  }

  public int getShipping_send() {
    return shipping_send;
  }

  public void setShipping_send(int shipping_send) {
    this.shipping_send = shipping_send;
  }
}
