package com.baihuodasha.bhds.activity.myself.setting;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.login.ActivityLogin;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.net.SharePrefHelper;
import java.text.ParseException;

public class ActivitySetting extends BaseActivity implements View.OnClickListener {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.rl_myself_setting_phone) RelativeLayout rlMyselfSettingPhone;
  @BindView(R.id.rl_myself_setting_accountpassword) RelativeLayout rlMyselfSettingAccountpassword;
  @BindView(R.id.rl_myself_setting_paymentcode) RelativeLayout rlMyselfSettingPaymentcode;
  @BindView(R.id.rl_myself_setting_aboutus) RelativeLayout rlMyselfSettingAboutus;
  @BindView(R.id.rl_myself_setting_logout) RelativeLayout rlMyselfSettingLogout;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_setting);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }
  private SharePrefHelper mSh;
  @Override public void initView() {
    mSh = SharePrefHelper.getInstance();
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("设置");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    rlMyselfSettingLogout.setOnClickListener(this);
    rlMyselfSettingAboutus.setOnClickListener(this);
    rlMyselfSettingPaymentcode.setOnClickListener(this);
    rlMyselfSettingAccountpassword.setOnClickListener(this);
    rlMyselfSettingPhone.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {

  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
      case R.id.rl_myself_setting_phone:
        Intent intent = new Intent(this, ActivityCellphonebinding.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        //手机号绑定
        break;
      case R.id.rl_myself_setting_accountpassword:
        Intent intentac = new Intent(this, ActivityAccountpassword.class);
        startActivity(intentac);
        overridePendingTransition(0, 0);
        //账号密码管理
        break;
      case R.id.rl_myself_setting_paymentcode:
        Intent intentpa = new Intent(this, ActivityPaymentcode.class);
        startActivity(intentpa);
        overridePendingTransition(0, 0);
        //支付密码管理
        break;
      case R.id.rl_myself_setting_aboutus:
        if (mSh.getLoginSuccess()) {
          //WebViewActivity.start(this, "关于我们", "http://www.baihuodasha.com/mobile/user.php?act=aboutus");
        } else {
          Intent help = new Intent(this, ActivityLogin.class);
          startActivity(help);
        }

        //Intent intentab = new Intent(this, ActivityAboutus.class);
        //startActivity(intentab);
        //overridePendingTransition(0, 0);
        //关于我们
        break;
      case R.id.rl_myself_binding_confirm:

        //点击退出
        break;
    }
  }
}
