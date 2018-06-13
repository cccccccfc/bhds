package com.baihuodasha.bhds.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseApplication;
import com.baihuodasha.bhds.fragment.CategorizeFragment;
import com.baihuodasha.bhds.fragment.FragmentHome;
import com.baihuodasha.bhds.fragment.FragmentMyself;
import com.baihuodasha.bhds.fragment.FragmentOther;
import com.baihuodasha.bhds.fragment.FragmentSupermarket;
import com.baihuodasha.bhds.utils.FragmentUtil;
import com.baihuodasha.bhds.utils.statusbar.StatusBarActivity;

/**
 * @author xqb
 */
public class MainActivityTabHost extends StatusBarActivity {

  //首页
  FragmentHome syFragment = new FragmentHome();
  RadioButton main_bottom_sy;
  //超市
  FragmentSupermarket csFragment = new FragmentSupermarket();
  RadioButton main_bottom_cs;
  //分类
  //FragmentNews flFragment = new FragmentNews();
  CategorizeFragment categorizeFragment = new CategorizeFragment();
  RadioButton main_bottom_fl;
  //购物车
  FragmentOther gwFragment = new FragmentOther();
  RadioButton main_bottom_gw;
  //我的
  FragmentMyself wdFragment = new FragmentMyself();
  RadioButton main_bottom_wd;
  Fragment[] fragments = { syFragment, csFragment, categorizeFragment, gwFragment, wdFragment };

  private FragmentHome fragment0;
  private FragmentSupermarket fragment1;
  private CategorizeFragment fragment2;
  private FragmentOther fragment3;
  private FragmentMyself fragment4;
  private FragmentManager manager;
  private static RadioGroup main_bottom_rg;
  private int selectedindex = 0;
  private FragmentUtil fragmentUtil;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    setContentView(R.layout.activity_tabhost);
    //
    if (null != savedInstanceState) {
      manager = getSupportFragmentManager();
      fragment0 = (FragmentHome) manager.getFragment(savedInstanceState, "FragmentHome");
      fragment1 =
          (FragmentSupermarket) manager.getFragment(savedInstanceState, "FragmentSupermarket");
      fragment2 = (CategorizeFragment) manager.getFragment(savedInstanceState, "FragmentNews");
      fragment3 = (FragmentOther) manager.getFragment(savedInstanceState, "FragmentOther");
      fragment4 = (FragmentMyself) manager.getFragment(savedInstanceState, "FragmentMyself");
      if (null != fragment0) fragments[0] = fragment4;
      if (null != fragment1) fragments[1] = fragment0;
      if (null != fragment2) fragments[2] = fragment1;
      if (null != fragment3) fragments[3] = fragment2;
      if (null != fragment4) fragments[4] = fragment3;
      selectedindex = savedInstanceState.getInt("selectedindex");
    }

    initView();
  }

  /**
   * 初始化组件
   */
  private void initView() {
    main_bottom_sy = (RadioButton) findViewById(R.id.main_bottom_sy);
    main_bottom_cs = (RadioButton) findViewById(R.id.main_bottom_cs);
    main_bottom_fl = (RadioButton) findViewById(R.id.main_bottom_fl);
    main_bottom_wd = (RadioButton) findViewById(R.id.main_bottom_wd);
    main_bottom_gw = (RadioButton) findViewById(R.id.main_bottom_gw);
    main_bottom_rg = (RadioGroup) findViewById(R.id.main_bottom_rg);
    initBottomBar();
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

  private void initBottomBar() {
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

  @Override protected void onSaveInstanceState(Bundle outState) {
    //super.onSaveInstanceState(outState);
  }
}
