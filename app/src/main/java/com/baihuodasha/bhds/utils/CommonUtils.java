package com.baihuodasha.bhds.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.baihuodasha.bhds.base.BaseApplication;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
/**
 * @author Admin
 * @time 2017/3/28 14:01
 * @des ${TODO}
 */

public class CommonUtils {
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

    /**
     * 判断是否wife
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否4G
     * @param context
     * @return
     */
    public static boolean isMobile(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**得到上下文*/
    public static Context getContext() {
        return BaseApplication.getContext();
    }

    /**得到Resouce对象*/
    public static Resources getResource() {
        return getContext().getResources();
    }

    /**得到应用程序的包名*/
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**得到主线程id*/
    public static long getMainThreadid() {
        return BaseApplication.getMainTreadId();
    }

    /**得到主线程Handler*/
    public static Handler getMainThreadHandler() {
        return BaseApplication.getHandler();
    }

    /**安全的执行一个任务*/
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();
        if (curThreadId == getMainThreadid()) {// 如果当前线程是主线程
            task.run();
        } else {// 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }
    /**延迟执行任务*/
    public static void postTaskDelay(Runnable task, int delayMillis) {
        getMainThreadHandler().postDelayed(task, delayMillis);
    }

    /**移除任务*/
    public static void removeTask(Runnable task) {
        getMainThreadHandler().removeCallbacks(task);
    }

    /**
     * 改变滑动条的长度 为了滑动的view pager用
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    //public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
    //    Class<?> tabLayout = tabs.getClass();
    //    Field tabStrip = null;
    //    try {
    //        tabStrip = tabLayout.getDeclaredField("mTabStrip");
    //    } catch (NoSuchFieldException e) {
    //        e.printStackTrace();
    //    }
    //
    //    tabStrip.setAccessible(true);
    //    LinearLayout llTab = null;
    //    try {
    //        llTab = (LinearLayout) tabStrip.get(tabs);
    //    } catch (IllegalAccessException e) {
    //        e.printStackTrace();
    //    }
    //
    //    int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
    //    int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
    //
    //    for (int i = 0; i < llTab.getChildCount(); i++) {
    //        View child = llTab.getChildAt(i);
    //        child.setPadding(0, 0, 0, 0);
    //        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
    //        params.leftMargin = left;
    //        params.rightMargin = right;
    //        child.setLayoutParams(params);
    //        child.invalidate();
    //    }
    //}

    public static void toastMessage(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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

    private static boolean isLoging = true;
    /**
     * 打印
     * @param str
     */
    public static void logMes(String str){
        if(isLoging){
            Log.e("------test------",""+str);
        }
    }
    /**
     * 保存图片到本地缓存
     * @param bitmap
     * @param str
     */
    public static void saveBitmapFile(Bitmap bitmap , String str){
        File file = new File(getContext().getFilesDir(),str);//将要保存图片的路径
        try {
            if(file.exists()){
                file.delete();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取图片bitmap
     * @param str
     * @return
     */
    public static Bitmap getCacheFile(String str){
        File file = new File(getContext().getFilesDir(),str);
        if (file != null  && file.exists()) {
            //文件存在
            //把文件转换成bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            //再往内存写
            return bitmap;
        } else {
            //不存在
            return null;
        }
    }

    /**
     * 删除bitmap
     * @param str
     */
    public static void deleteBitmap(String str){
        File file = new File(getContext().getFilesDir(),str);
        if(file != null  && file.exists()){
            file.delete();
        }

    }

    /**
     * 获取bitmap
     * @param path
     * @return
     */
    public static Bitmap getBitmap (String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); //此时的bitmap为null
        //更改
        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int)(options.outHeight / (float)200);
        if (be < 0){
            be = 1;
        }
        options.inSampleSize = be;
        bitmap=BitmapFactory.decodeFile(path,options);//此时的bitmap不为null
        return bitmap;
    }

    /**
     * bitmap变byte
     * @param bitmap
     * @return
     */
    public static byte[] getBitMapByteArray(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();

        return bytes;
    }
}
