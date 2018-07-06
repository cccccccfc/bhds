package com.baihuodasha.bhds.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
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
import com.baihuodasha.bhds.databases.searchshopdp.HistoryShopShopDataBaseInfo;
import com.baihuodasha.bhds.fragment.FragmentClassification;
import com.baihuodasha.bhds.fragment.FragmentHome;
import com.baihuodasha.bhds.fragment.FragmentMyself;
import com.baihuodasha.bhds.fragment.FragmentShoppingTrolley;
import com.baihuodasha.bhds.fragment.FragmentSupermarket;
import com.baihuodasha.bhds.permission.PermissionsActivity;
import com.baihuodasha.bhds.permission.PermissionsChecker;
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
  // 登录完跳转到myfragment的参数
  private int flag = 0;
  private static RadioGroup main_bottom_rg;
  // 默认页
  private int selectedindex = 0;
  private FragmentUtil fragmentUtil;
  // // 所需的全部权限
  static final String[] PERMISSIONS = new String[] {
      Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CAMERA,
      Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.READ_PHONE_STATE,
      Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS,
      Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.READ_EXTERNAL_STORAGE
  };
  private static final int REQUEST_CODE = 0; // 请求码
  private PermissionsChecker mPermissionsChecker; // 权限检测器

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
    initBottomBar();
  }
  private void initBottomBar() {
    fragmentUtil = new FragmentUtil(fragments, getSupportFragmentManager(), R.id.main_framlayout);
    main_bottom_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup radioGroup, int i) {
        findViewById(R.id.main_bottom_rg).setSelected(false);
        switch (i) {
          case R.id.main_bottom_sy:
            flag = 0;
            fragmentUtil.switchTo(0);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
            // StatusBarCompat.initSystemBar(MainActivityTabHost.this, R.color.titleBar);
            break;
          case R.id.main_bottom_cs:
            flag = 1;
            fragmentUtil.switchTo(1);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(true, getResources().getColor(R.color.titleBar));
            break;
          case R.id.main_bottom_fl:
            flag = 2;
            fragmentUtil.switchTo(2);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
            break;
          case R.id.main_bottom_gw:
            flag = 3;
            fragmentUtil.switchTo(3);
            main_bottom_sy.setChecked(false);
            setStatusBarPlaceVisible(true);
            setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
            break;
          case R.id.main_bottom_wd:
            flag = 4;
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
        HistoryShopShopDataBaseInfo.getInstance(this).clearDataBase();
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
  private static final int REQUEST_LOG = 10; // 请求码
  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
      finish();
    }
    if (requestCode == REQUEST_LOG) {
      fragmentUtil.switchTo(flag);
      switch (flag) {
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
  }

  @Override public void onResume() {
    super.onResume();
    // 缺少权限时, 进入权限配置页面
    if (isMNC()) {
      if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
        startPermissionsActivity();
      }
    }
  //  initBottomBar();
  }
  public static boolean isMNC() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }

  private void startPermissionsActivity() {
    PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
  }
}
