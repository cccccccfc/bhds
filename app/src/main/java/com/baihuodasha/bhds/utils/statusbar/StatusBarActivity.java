package com.baihuodasha.bhds.utils.statusbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.baihuodasha.bhds.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhang on 2018/6/13.
 */

public class StatusBarActivity extends AppCompatActivity {
  private FrameLayout mFrameLayoutContent;
  private View mViewStatusBarPlace;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStatusBar();
    super.setContentView(R.layout.activity_compat_status_bar);
    mViewStatusBarPlace = findViewById(R.id.view_status_bar_place);
    mFrameLayoutContent = (FrameLayout) findViewById(R.id.frame_layout_content_place);
    ViewGroup.LayoutParams params = mViewStatusBarPlace.getLayoutParams();
    params.height = getStatusBarHeight();
    mViewStatusBarPlace.setLayoutParams(params);
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    //这里能够看到 我们这里其实是一个适配基类。布局中增加了一个View 用来适配状态栏的高度并调整颜色
    //contentLayout会将继承自这个Activity的页面的layout添加进去以达到通用的目的
    View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
    mFrameLayoutContent.addView(contentView);
  }

  /**
   * 根据版本不同 修改添加View的颜色
   * 适配白底标题栏(方案二)顶部添加View,改变View颜色
   * 适配方案2, 4.4以下的不适配，4.4-6.0修改View颜色为浅灰色，6.0以上修改View颜色为白色，修改状态栏字体颜色
   *
   * @param isLight 标题栏颜色是否为浅色(白色)
   */
  protected void setViewColorStatusBar(boolean isLight, int statusBarPlaceColor) {
    //6.0+ 小米 魅族 可以直接适配 一般情况下6.0以上都是透明
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M || OsUtil.isMIUI() || OsUtil.isFlyme()) {
      setStatusBarTextDark(isLight);
      setStatusBarPlaceColor(statusBarPlaceColor);
    } else {
      if (statusBarPlaceColor == Color.WHITE) {
        statusBarPlaceColor = 0xFFFFFFFF;
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //4.4以上修改为浅灰色
        setStatusBarPlaceColor(statusBarPlaceColor);
      } else { //4.4以下不适配
        setStatusBarPlaceVisible(false);
      }
    }
  }

  protected void setStatusBarPlaceVisible(boolean isVisible) {
    if (isVisible) {
      mViewStatusBarPlace.setVisibility(View.VISIBLE);
    } else {
      mViewStatusBarPlace.setVisibility(View.GONE);
    }
  }

  protected void setStatusBarPlaceColor(int statusColor) {
    if (mViewStatusBarPlace != null) {
      mViewStatusBarPlace.setBackgroundColor(statusColor);
    }
  }
  /**
   * 适配透明状态栏-沉浸式状态栏
   */
  protected void setStatusBar() {
    //4.4以上才有透明状态栏一说。低版本暂时无法适配
    Window window = getWindow();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //兼容5.0 状态栏半透明情况， 貌似并没有什么卵用(机型锤子T2 5.0系统)(跟厂商定制有关原生有用)
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.TRANSPARENT);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      // Translucent status bar
      window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
  }

  /**
   * 获取状态栏的高度
   *
   * @return
   */
  protected int getStatusBarHeight() {
    int statusBarHeight = 0;
    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      statusBarHeight = getResources().getDimensionPixelSize(resourceId);
    }
    return statusBarHeight;
  }

  /**
   * 根据版本不同设置状态栏颜色不同
   * 适适配白底标题栏(方案一)改变状态栏字体颜色
   * 适配方案1, 4.4以下的不适配，4.4-5.0的也不适配，5.0-6.0修改状态栏颜色为浅灰色，6.0以上修改状态栏字体颜色
   * 因为修改状态栏颜色5.0以上才支持，所以5.0以下的不适配
   *
   * @param isLight 标题栏颜色是否为浅色(白色)
   */
  protected void setColorStatusBar(boolean isLight, int statsBarcolor) {
    if (OsUtil.isMIUI() || OsUtil.isFlyme() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      setStatusBarTextDark(isLight);
    } else {
      Window window = getWindow();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //5.0以上
        //兼容5.0 状态栏半透明情况， 貌似并没有什么卵用(机型锤子T2 5.0系统)(跟厂商定制有关原生有用)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(statsBarcolor);
      } else { //4.4-5.0 以及 4.4以下
        //这是一个开源库，支持4.4以上修改状态栏颜色 这里我就不适配了
        //                compile 'com.readystatesoftware.systembartint:systembartint:1.0.3'
        //                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        //                tintManager.setStatusBarTintEnabled(true);
        //                tintManager.setStatusBarTintResource(colorId);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      }
    }
  }

  /**
   * 修改状态栏字体颜色只能在android6.0以上原生系统中，同时小米和魅族提供有方法，其他厂商可能无效过
   * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
   *
   * @param dark 状态栏字体和图标是否为深色
   */
  protected void setStatusBarTextDark(boolean dark) {
    if (OsUtil.isMIUI()) {
      // 小米MIUI
      try {
        Window window = getWindow();
        Class clazz = getWindow().getClass();
        Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
        Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
        int darkModeFlag = field.getInt(layoutParams);
        Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
        if (dark) {    //状态栏亮色且黑色字体
          extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
        } else {       //清除黑色字体
          extraFlagField.invoke(window, 0, darkModeFlag);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return;
    }
    if (OsUtil.isFlyme()) {
      // 魅族FlymeUI
      try {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
        Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
        darkFlag.setAccessible(true);
        meizuFlags.setAccessible(true);
        int bit = darkFlag.getInt(null);
        int value = meizuFlags.getInt(lp);
        if (dark) {
          value |= bit;
        } else {
          value &= ~bit;
        }
        meizuFlags.setInt(lp, value);
        window.setAttributes(lp);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return;
    }

    // android6.0+系统
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (dark) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      } else {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
      }
    }
  }
}
