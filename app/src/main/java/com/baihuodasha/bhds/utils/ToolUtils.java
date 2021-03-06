package com.baihuodasha.bhds.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import java.util.Random;

/**
 * @author Admin
 * @time 2017/4/7 9:41
 * @des ${TODO}
 */

public class ToolUtils {
    /**
     * 判断是否有网
     * @param context
     * @return
     */

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable()){
            return true;//当前有可用网络
        }
        else{
            return false;//当前无可用网络
        }
    }

    public static void toastMessage(String msg,Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断手机号的正则表达式
     * @param username
     * @return
     */
    public static boolean isMobilePhone(String username) {
        String telRegex = "[1][34578]\\d{9}";
        return username.matches(telRegex);
    }

  /**
     * 打印
     * @param str
     */
    public static void logMes(String str){
      boolean isLoging = true;
      if(isLoging){
            Log.e("------test------",""+str);
        }
    }
  /**
   * 生成一个startNum 到 endNum之间的随机数(不包含endNum的随机数)
   * @param startNum
   * @param endNum
   * @return
   */
  public static int getNum(int startNum,int endNum){
    if(endNum > startNum){
      Random random = new Random();
      return random.nextInt(endNum - startNum) + startNum;
    }
    return 0;
  }
}
