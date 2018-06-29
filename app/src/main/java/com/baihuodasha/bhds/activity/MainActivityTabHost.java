package com.baihuodasha.bhds.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.base.BaseApplication;
import com.baihuodasha.bhds.fragment.FragmentClassification;
import com.baihuodasha.bhds.fragment.FragmentHome;
import com.baihuodasha.bhds.fragment.FragmentMyself;
import com.baihuodasha.bhds.fragment.FragmentShoppingTrolley;
import com.baihuodasha.bhds.fragment.FragmentSupermarket;
import com.baihuodasha.bhds.utils.FragmentUtil;
import java.text.ParseException;

/**
 * @author xqb
 */
public class MainActivityTabHost extends BaseActivity {

  //首页
  FragmentHome syFragment = new FragmentHome();
  RadioButton main_bottom_sy;
  //超市
  FragmentSupermarket csFragment = new FragmentSupermarket();
  RadioButton main_bottom_cs;
  //分类
  //FragmentNews flFragment = new FragmentNews();
  FragmentClassification categorizeFragment = new FragmentClassification();
  RadioButton main_bottom_fl;
  //购物车
  FragmentShoppingTrolley gwFragment = new FragmentShoppingTrolley();
  RadioButton main_bottom_gw;
  //我的
  FragmentMyself wdFragment = new FragmentMyself();
  RadioButton main_bottom_wd;
  Fragment[] fragments = { syFragment, csFragment, categorizeFragment, gwFragment, wdFragment };

  private static RadioGroup main_bottom_rg;
  private int selectedindex = 0;
  private FragmentUtil fragmentUtil;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    setContentView(R.layout.activity_tabhost);
    //
    if (null != savedInstanceState) {
      FragmentManager manager = getSupportFragmentManager();
      FragmentHome fragment0 =
          (FragmentHome) manager.getFragment(savedInstanceState, "FragmentHome");
      FragmentSupermarket fragment1 =
          (FragmentSupermarket) manager.getFragment(savedInstanceState, "FragmentSupermarket");
      FragmentClassification fragment2 =
          (FragmentClassification) manager.getFragment(savedInstanceState,
              "FragmentClassification");
      FragmentShoppingTrolley fragment3 =
          (FragmentShoppingTrolley) manager.getFragment(savedInstanceState,
              "FragmentShoppingTrolley");
      FragmentMyself fragment4 =
          (FragmentMyself) manager.getFragment(savedInstanceState, "FragmentMyself");
      if (null != fragment0) fragments[0] = fragment4;
      if (null != fragment1) fragments[1] = fragment0;
      if (null != fragment2) fragments[2] = fragment1;
      if (null != fragment3) fragments[3] = fragment2;
      if (null != fragment4) fragments[4] = fragment3;
      selectedindex = savedInstanceState.getInt("selectedindex");
    }
  }

  /**
   * 初始化组件
   */
  public void initView() {
    main_bottom_sy = (RadioButton) findViewById(R.id.main_bottom_sy);
    main_bottom_cs = (RadioButton) findViewById(R.id.main_bottom_cs);
    main_bottom_fl = (RadioButton) findViewById(R.id.main_bottom_fl);
    main_bottom_wd = (RadioButton) findViewById(R.id.main_bottom_wd);
    main_bottom_gw = (RadioButton) findViewById(R.id.main_bottom_gw);
    main_bottom_rg = (RadioGroup) findViewById(R.id.main_bottom_rg);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {
    fragmentUtil = new FragmentUtil(fragments, getSupportFragmentManager(), R.id.main_framlayout);
    main_bottom_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup radioGroup, int i) {
        findViewById(R.id.main_bottom_rg).setSelected(false);
        switch (i) {
          case R.id.main_bottom_sy:
            fragmentUtil.switchTo(0);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
            // StatusBarCompat.initSystemBar(MainActivityTabHost.this, R.color.titleBar);
            break;
          case R.id.main_bottom_cs:
            fragmentUtil.switchTo(1);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(true, getResources().getColor(R.color.titleBar));
            break;
          case R.id.main_bottom_fl:
            fragmentUtil.switchTo(2);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
            break;
          case R.id.main_bottom_gw:
            fragmentUtil.switchTo(3);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
            break;
          case R.id.main_bottom_wd:
            fragmentUtil.switchTo(4);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(false);
            setViewColorStatusBar(false, getResources().getColor(R.color.white));
            break;
        }
      }
    });

    fragmentUtil.switchTo(selectedindex);

    switch (selectedindex) {
      case 0:
        ((RadioButton) main_bottom_rg.findViewById(R.id.main_bottom_sy)).setChecked(true);
        break;
      case 1:
        ((RadioButton) main_bottom_rg.findViewById(R.id.main_bottom_cs)).setChecked(true);
        break;
      case 2:
        ((RadioButton) main_bottom_rg.findViewById(R.id.main_bottom_fl)).setChecked(true);
        break;
      case 3:
        ((RadioButton) main_bottom_rg.findViewById(R.id.main_bottom_gw)).setChecked(true);
        break;
      case 4:
        ((RadioButton) main_bottom_rg.findViewById(R.id.main_bottom_wd)).setChecked(true);
        break;
    }
  }

  @Override public void onClickEvent(View view) throws ParseException {

  }

  private long exitTime = 0;

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
      if ((System.currentTimeMillis() - exitTime) > 2000) {
        Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
        exitTime = System.currentTimeMillis();
      } else {
        BaseApplication.getThreadPool().shutdownNow();//关闭线程池
        finish();
        System.exit(0);
      }
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
  }


  @Override protected void onSaveInstanceState(Bundle outState) {
    //super.onSaveInstanceState(outState);
  }
}
