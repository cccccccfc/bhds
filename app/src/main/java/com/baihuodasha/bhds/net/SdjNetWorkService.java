package com.baihuodasha.bhds.net;

import com.baihuodasha.bhds.bean.MainLogModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by qiaoyaqian on 16/12/16.
 */

public interface SdjNetWorkService {

  //登陆
  @FormUrlEncoded @POST("interface/") Call<MainLogModel> getLogInfo(
      @Field("api_token") String apitoken, @Field("user_name") String username,
      @Field("user_pwd") String userpwd);

}
