package com.baihuodasha.bhds.net;

import android.content.Context;
import android.content.SharedPreferences;
import com.baihuodasha.bhds.base.BaseApplication;

/**
 * Created by qiaoyaqian on 16/12/15.
 */

public class SharePrefHelper {
    //SharePreference名字
    private static final String SP_FILE_NAME = "FENQIFU_SHAREDPREFERENCES";
    private static SharePrefHelper sharePrefHelper;
    private static SharedPreferences sharedPreferences;

    public static synchronized SharePrefHelper getInstance() {
        if (null == sharePrefHelper) {
            sharePrefHelper = new SharePrefHelper();
        }
        return sharePrefHelper;
    }

    private SharePrefHelper() {
        sharedPreferences = BaseApplication.getContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }
    /**
     * 是否第一次经过loading
     */
    public void setFirstGoLoading(int num) {
        sharedPreferences.edit().putInt("first_go_loading", num).commit();
    }

    public int getFirstGoLoading() {
        return sharedPreferences.getInt("first_go_loading", 0);
    }
    /**
     * 第一次進入
     */
    public void setFirstStartInto(int num) {
        sharedPreferences.edit().putInt("first_into", num).commit();
    }

    public int getFirstStartInto() {
        return sharedPreferences.getInt("first_into", 0);
    }
    /**
     * 是否开启推送声音
     */
    public void setJPushSound(boolean flag) {
        sharedPreferences.edit().putBoolean("user_sound", flag).commit();
    }

    public boolean getJPushSound() {
        return sharedPreferences.getBoolean("user_sound", false);
    }
    /**
     * 下载记录
     */
    public void setDownLoadApp(int num) {
        sharedPreferences.edit().putInt("down_load_app", num).commit();
    }

    public int getDownLoadApp() {
        return sharedPreferences.getInt("down_load_app", 0);
    }

    public void clear() {
        sharedPreferences.edit().clear().commit();
    }


    public static void setLoginUserInfo(String model) {
        sharedPreferences.edit().putString("orderInfo", model).commit();

    }

    /**
     * 记录是否绑定支付密码
     */
    /**
     * 用户id
     *
     * @param
     */
    public void setPayPwd(int code) {
        sharedPreferences.edit().putInt("pay_id",code).commit();
    }

    public int getPayPwd() {
        return sharedPreferences.getInt("pay_id", 0);
    }
    /*
    * 是否第一次获得推送
    */
    public void setFirstGetTuisong(int num) {
        sharedPreferences.edit().putInt("first_get_tuisong", num).commit();
    }

    public int getFirstGetTuisong() {
        return sharedPreferences.getInt("first_get_tuisong", 0);
    }
    /**
     * 地址手机号
     *
     * @param id
     */
    public void setAddrPhone(String id) {
        sharedPreferences.edit().putString("addr_phone", id).commit();
    }

    public String getAddrPhone() {
        return sharedPreferences.getString("addr_phone", "");
    }
    /**
     * 地址炫男炫女
     */
    public void setChoiceSex(int num) {
        sharedPreferences.edit().putInt("addr_sex", num).commit();
    }

    public int getChoiceSex() {
        return sharedPreferences.getInt("addr_sex", 2);
    }

    /**
     * 地址姓名
     *
     * @param id
     */
    public void setAddrName(String id) {
        sharedPreferences.edit().putString("addr_name", id).commit();
    }

    public String getAddrName() {
        return sharedPreferences.getString("addr_name", "");
    }
    /**
     * 用户id
     *
     * @param id
     */
    public void setUserId(String id) {
        sharedPreferences.edit().putString("user_id", id).commit();
    }

    public String getUserId() {
        return sharedPreferences.getString("user_id", "");
    }

    /**
     * 用户机关推送id
     *
     * @param id
     */
    public void setRegistrationID(String id) {
        sharedPreferences.edit().putString("registrationId", id).commit();
    }

    public String getRegistrationID() {
        return sharedPreferences.getString("registrationId", "");
    }

    /**
     * 获取token
     */
    public void setUserToken(String token) {
        sharedPreferences.edit().putString("Token1", token).commit();
    }

    public String getUserToken() {
        return sharedPreferences.getString("Token1", "");
    }

    /**
     * 会员级别
     */
    public void setRoleid(String roleid) {
        sharedPreferences.edit().putString("Roleid", roleid).commit();
    }

    public String getRoleid() {
        return sharedPreferences.getString("Roleid", "");
    }

    /**
     * 会员级别
     */
    public void setShopId(String shopId) {
        sharedPreferences.edit().putString("ShopId", shopId).commit();
    }

    public String getShopId() {
        return sharedPreferences.getString("ShopId", "");
    }

    /**
     * 会员昵称
     */
    public String getNickName() {
        return sharedPreferences.getString("NickName", "");
    }

    public void setNickName(String nickname) {
        sharedPreferences.edit().putString("NickName", nickname).commit();
    }

    /**
     * 电话号码
     */
    public String getMobile() {
        return sharedPreferences.getString("mobile", "");
    }

    public void setMobile(String mobile) {
        sharedPreferences.edit().putString("mobile", mobile).commit();
    }

    //定位地址
    public String getLocation() {
        return sharedPreferences.getString("location", "");
    }

    public void setLocation(String location) {
        sharedPreferences.edit().putString("location", location).commit();
    }

    //维度
    public String getLatitude() {
        return sharedPreferences.getString("latitude", "");
    }

    public void setLatitude(String latitude) {
        sharedPreferences.edit().putString("latitude", latitude).commit();
    }

    //经度
    public String getLontitude() {
        return sharedPreferences.getString("lontitude", "");
    }

    public void setLontitude(String lontitude) {
        sharedPreferences.edit().putString("lontitude", lontitude).commit();
    }

    //登陆成功
    public boolean getLoginSuccess() {
        return sharedPreferences.getBoolean("loginsuccess", false);
    }

    public void setLoginSuccess(boolean loginsuccess) {
        sharedPreferences.edit().putBoolean("loginsuccess", loginsuccess).commit();
    }

    //上传头像路径
    public String getHeadUrl() {
        return sharedPreferences.getString("headUrl", "");
    }

    public void setHeadUrl(String headUrl) {
        sharedPreferences.edit().putString("headUrl", headUrl).commit();
    }

    //店铺id
    public String getSyShopId() {
        return sharedPreferences.getString("shopid", "");
    }

    public void setSyShopId(String shopid) {
        sharedPreferences.edit().putString("shopid", shopid).commit();
    }
    /**
     * 是否是点击升级
     *
     *
     */
    public void setIsClick(boolean isFirstIn) {
        sharedPreferences.edit().putBoolean("isClick", isFirstIn).commit();
    }

    public boolean isClick() {
        return sharedPreferences.getBoolean("isClick", true);
    }


    /**
     * 是否有消息
     */
    public void setIsMes(boolean hasmes) {
        sharedPreferences.edit().putBoolean("isMes", hasmes).commit();
    }

    public boolean getIsMes() {
        return sharedPreferences.getBoolean("isMes", true);
    }

    //店铺类型
    public String getStoreType() {
        return sharedPreferences.getString("type", "");
    }

    public void setStoreType(String type) {
        sharedPreferences.edit().putString("type", type).commit();
    }
    //起送费
    public String getStoreQs() {
        return sharedPreferences.getString("qs", "");
    }

    public void setStoreQs(String type) {
        sharedPreferences.edit().putString("qs", type).commit();
    }
    //配送费
    public String getStorePs() {
        return sharedPreferences.getString("ps", "");
    }

    public void setStorePs(String type) {
        sharedPreferences.edit().putString("ps", type).commit();
    }

}
