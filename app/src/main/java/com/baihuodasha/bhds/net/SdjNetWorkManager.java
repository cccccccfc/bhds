package com.baihuodasha.bhds.net;

import com.baihuodasha.bhds.bean.MainLogModel;
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
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这句话是打印出来的什么分为4级NONE---什么也没有 BASIC--
        //返回请求响应行 HEADERS---请求响应行和头  BODY---请求响应行和头和体
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
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
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .baseUrl(URLContents.UP_URL+URLContents.getLogin)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLContents.getUrl("100002")).addConverterFactory(ScalarsConverterFactory.create()).build();
        SdjNetWorkService service = retrofit.create(SdjNetWorkService.class);
        Call<MainLogModel> call = service.getLogInfo(api_token, user_name, user_pwd);
        call.enqueue(callback);
    }

}
