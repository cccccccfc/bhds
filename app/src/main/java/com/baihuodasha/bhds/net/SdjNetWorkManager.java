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
import com.baihuodasha.bhds.utils.MD5Utils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.baihuodasha.bhds.utils.ToolUtils.getNum;

/**
 * Created by qiaoyaqian on 16/12/16.
 */

public class SdjNetWorkManager {

  private static SharePrefHelper mSh;

  static {
    mSh = SharePrefHelper.getInstance();
  }

  public static OkHttpClient getOkHttpClient() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(
        HttpLoggingInterceptor.Level.BODY);//这句话是打印出来的什么分为4级NONE---什么也没有 BASIC--
    //返回请求响应行 HEADERS---请求响应行和头  BODY---请求响应行和头和体
    OkHttpClient okHttpClient =
        new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    return okHttpClient;
  }

  /**
   * 登陆
   */
  public static void getLogInfo(String user_name, String user_pwd, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.getLogin)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLContents.getUrl("100002")).addConverterFactory(ScalarsConverterFactory.create()).build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg<MainLogModel>> call = service.getLogInfo(api_token, user_name, user_pwd);
    call.enqueue(callback);
  }

  /**
   * 修改账号密码
   */
  public static void setModifyPasswd(String user_pwd, String new_pwd, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.getLogin)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg<MainLogModel>> call =
        service.setModifyPasswd(api_token, mSh.getUserName(), user_pwd, new_pwd);
    call.enqueue(callback);
  }

  /**
   * 手机注册
   */
  public static void MobilePhoneRegister(String username, String passwd, String mobile_phone,
      Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.Register)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg> call = service.MobilePhoneRegister(api_token, username, passwd, mobile_phone);
    call.enqueue(callback);
  }

  /**
   * 邮箱注册
   */
  public static void EmailRegister(String username, String passwd, String email,
      Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.Register)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg> call = service.EmailRegister(api_token, username, passwd, email);
    call.enqueue(callback);
  }

  /**
   * 获取手机验证码
   */
  public static void sendMobileCode(String mobile_phone, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendMobileCode)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg<MainRegisterModel>> call = service.sendMobileCode(api_token, mobile_phone);
    call.enqueue(callback);
  }

  /**
   * 获取邮箱验证码
   */
  public static void sendEmailCode(String email, Callback callback) {
    String timestamp = "send_email_code";
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.sendEmailCode)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg<MainRegisterModel>> call = service.sendEmailCode(timestamp, email);
    call.enqueue(callback);
  }

  /**
   * 获取用户信息
   */
  public static void sendUserInfo(Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    String key = mSh.getUserToken();
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendUserInfo)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<Msg<MainUserInfoModel>> call = service.sendUserInfo(api_token, key);
    call.enqueue(callback);
  }

  /**
   * 获取首页导航栏
   */
  public static void sendIndexNavigation(Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendIndexNavigation)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainIndexNavigaitonModel> call = service.sendIndexNavigation(api_token);
    call.enqueue(callback);
  }

  /**
   * 获取销售排名  type = ads轮播图  promoteGoods促销商品  	top10销售排名  best精品   hot热品  new新品
   */
  public static void sendIndexBanner(String mold, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendIndexBanner)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainIndexBannerModel> call = service.sendIndexBanner(api_token, mold);
    call.enqueue(callback);
  }

  /**
   * 获取首页分类商品
   */
  public static void sendCategoryGoodsList(Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendCategoryGoodsList)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainCategoryGoodsListMdel> call = service.sendCategoryGoodsList(api_token);
    call.enqueue(callback);
  }

  /**
   * 获取首页推荐商品 //公用
   */
  public static void sendBestGoodsList(int page, String size, String cat_id, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendBestGoodsList)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainIndexBestGoodsList> call = service.sendBestGoodsList(api_token, page, size, cat_id);
    call.enqueue(callback);
  }

  /**
   * 首页二级页面轮播图
   */
  public static void sendcategoryBanner(String cat_id, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendcategoryBanner)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainIndexBannerModel> call = service.sendcategoryBanner(api_token, cat_id);
    call.enqueue(callback);
  }

  /**
   * 获得百货超市新品推荐
   */
  public static void sendSupermarkNewShopList(String cat_id, String type, String size,
      Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendIndexBanner)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<SupermarketNewShop> call = service.sendSupermarkNewShopList(api_token, type, cat_id, size);
    call.enqueue(callback);
  }

  /**
   * 获得百货超市推荐图
   */
  public static void sendSupermarkNewBanner(String cat_id, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendSupermarkNewBanner)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainIndexBannerModel> call = service.sendSupermarkNewBanner(api_token, cat_id);
    call.enqueue(callback);
  }

  /**
   * 获得该类下级分类列表
   */
  public static void sendcatgoryNextInformationList(String cat_id, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendcatgoryNextInformationList)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<MainCategoryGoodsListMdel> call =
        service.sendcatgoryNextInformationList(api_token, cat_id);
    call.enqueue(callback);
  }

  /**
   * 获得分类页面 左侧列表
   */
  public static void sendBigCategoryList(Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendBigCategoryList)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<BigCategoryList> call = service.sendBigCategoryList(api_token);
    call.enqueue(callback);
  }

  /**
   * 获得分类页面 右侧数据
   */
  public static void sendGetChildTreeByCatId(String cat_id, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendGetChildTreeByCatId)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<GetChildTreeByCatId> call = service.sendGetChildTreeByCatId(api_token, cat_id);
    call.enqueue(callback);
  }

  /**
   * 获取分类商品列表
   */
  public static void sendgetCategoryGoodsListByCatId(String cat_id, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.UP_URL + URLContents.sendgetCategoryGoodsListByCatId)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<CategoryGoodsListByCatId> call =
        service.sendgetCategoryGoodsListByCatId(api_token, cat_id);
    call.enqueue(callback);
  }

  /**
   * 获取限时团购活动商品列表
   */
  public static void sendGroupbuy(String act, int page, String page_size, Callback callback) {
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String[] temp = URLContents.getLogin.split("/");
    String api_token =
        MD5Utils.getMd5Value(temp[0] + temp[1] + timestamp + getNum(1, 99)).substring(8, 24);
    Retrofit retrofit =
        new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(getOkHttpClient())
            .baseUrl(URLContents.sendGroupbuy)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
    Call<FlashSaleModel> call = service.sendGroupbuy(api_token, act, page, page_size);
    call.enqueue(callback);
  }
}
