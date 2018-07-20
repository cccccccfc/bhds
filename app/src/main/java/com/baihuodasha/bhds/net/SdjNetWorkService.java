package com.baihuodasha.bhds.net;

import com.baihuodasha.bhds.bean.BigCategoryList;
import com.baihuodasha.bhds.bean.CategoryGoodsListByCatId;
import com.baihuodasha.bhds.bean.FlashSaleModel;
import com.baihuodasha.bhds.bean.GetChildTreeByCatId;
import com.baihuodasha.bhds.bean.MainCategoryGoodsListMdel;
import com.baihuodasha.bhds.bean.MainIndexBannerModel;
import com.baihuodasha.bhds.bean.MainIndexBestGoodsList;
import com.baihuodasha.bhds.bean.MainIndexNavigaitonModel;
import com.baihuodasha.bhds.bean.MainLogModel;
import com.baihuodasha.bhds.bean.MainRegisterModel;
import com.baihuodasha.bhds.bean.MainUserInfoModel;
import com.baihuodasha.bhds.bean.Msg;
import com.baihuodasha.bhds.bean.SupermarketNewShop;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by qiaoyaqian on 16/12/16.
 */

public interface SdjNetWorkService {

  //登陆
  @FormUrlEncoded @POST("interface/") Call<Msg<MainLogModel>> getLogInfo(
      @Field("api_token") String apitoken, @Field("user_name") String username,
      @Field("user_pwd") String userpwd);

  //修改账户密码
  @FormUrlEncoded @POST("interface/") Call<Msg<MainLogModel>> setModifyPasswd(
      @Field("api_token") String apitoken, @Field("key") String username,
      @Field("user_pwd") String userpwd, @Field("new_pwd") String new_pwd);

  //获取手机验证码
  @FormUrlEncoded @POST("interface/") Call<Msg<MainRegisterModel>> sendMobileCode(
      @Field("api_token") String apitoken, @Field("mobile_phone") String mobile_phone);

  //获取邮箱验证码
  @FormUrlEncoded @POST("interface/") Call<Msg<MainRegisterModel>> sendEmailCode(
      @Field("act") String timestamp, @Field("email") String email);

  //手机号注册
  @FormUrlEncoded @POST("interface/") Call<Msg> MobilePhoneRegister(
      @Field("api_token") String apitoken, @Field("username") String username,
      @Field("passwd") String passwd, @Field("mobile_phone") String mobile_phone);

  //邮箱注册
  @FormUrlEncoded @POST("interface/") Call<Msg> EmailRegister(@Field("api_token") String apitoken,
      @Field("username") String username, @Field("passwd") String passwd,
      @Field("email") String email);

  //邮箱注册
  @FormUrlEncoded @POST("interface/") Call<Msg<MainUserInfoModel>> sendUserInfo(
      @Field("api_token") String api_token, @Field("key") String key);

  //获取首页导航栏
  @FormUrlEncoded @POST("interface/") Call<MainIndexNavigaitonModel> sendIndexNavigation(
      @Field("api_token") String api_token);

  //获取首页信息
  @FormUrlEncoded @POST("interface/") Call<MainIndexBannerModel> sendIndexBanner(
      @Field("api_token") String api_token, @Field("type") String mold);

  //获取首页分类商品
  @FormUrlEncoded @POST("interface/") Call<MainCategoryGoodsListMdel> sendCategoryGoodsList(
      @Field("api_token") String api_token);

  //获取首页推荐商品
  @FormUrlEncoded @POST("interface/") Call<MainIndexBestGoodsList> sendBestGoodsList(
      @Field("api_token") String api_token, @Field("page") int page, @Field("size") String size,
      @Field("cat_id") String cat_id);

  //获取首页tab分类轮播图
  @FormUrlEncoded @POST("interface/") Call<MainIndexBannerModel> sendcategoryBanner(
      @Field("api_token") String api_token, @Field("cat_id") String cat_id);

  //获得该类下级分类列表
  @FormUrlEncoded @POST("interface/")
  Call<MainCategoryGoodsListMdel> sendcatgoryNextInformationList(
      @Field("api_token") String api_token, @Field("cat_id") String cat_id);

  //获得百货超市新品推荐
  @FormUrlEncoded @POST("interface/") Call<SupermarketNewShop> sendSupermarkNewShopList(
      @Field("api_token") String api_token, @Field("type") String type, @Field("size") String size,
      @Field("cat_id") String cat_id);

  //获得百货超市推荐图
  @FormUrlEncoded @POST("interface/") Call<MainIndexBannerModel> sendSupermarkNewBanner(
      @Field("api_token") String api_token, @Field("cat_id") String cat_id);

  //获得分类页面 左侧列表
  @FormUrlEncoded @POST("interface/") Call<BigCategoryList> sendBigCategoryList(
      @Field("api_token") String api_token);

  //获得分类页面 右侧列表
  @FormUrlEncoded @POST("interface/") Call<GetChildTreeByCatId> sendGetChildTreeByCatId(
      @Field("api_token") String api_token, @Field("cat_id") String cat_id);

  //获取分类商品列表
  @FormUrlEncoded @POST("interface/")
  Call<CategoryGoodsListByCatId> sendgetCategoryGoodsListByCatId(
      @Field("api_token") String api_token, @Field("cat_id") String cat_id);

  //获取限时团购商品列表
  @FormUrlEncoded @POST("interface/") Call<FlashSaleModel> sendGroupbuy(
      @Field("api_token") String api_token, @Field("act") String act, @Field("page") int page,
      @Field("page_size") String page_size);
}
