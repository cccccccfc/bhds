package com.baihuodasha.bhds.activity.myself;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import java.text.ParseException;

/**
 * E卡充值
 */
public class ActivityPropertyRecharge extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.tv_property_recharge_hint) TextView tvPropertyRechargeHint;
  @BindView(R.id.tv_property_recharge_name) TextView tvPropertyRechargeName;
  @BindView(R.id.tv_property_recharge_number) TextView tvPropertyRechargeNumber;
  @BindView(R.id.et_property_recharge_number) EditText etPropertyRechargeNumber;
  @BindView(R.id.tv_property_recharge_password) TextView tvPropertyRechargePassword;
  @BindView(R.id.et_property_recharge_password) EditText etPropertyRechargePassword;
  @BindView(R.id.rl_property_recharge_confrom) RelativeLayout rlPropertyRechargeConfrom;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_property_recharge);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("E卡充值");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    rlPropertyRechargeConfrom.setOnClickListener(this);
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
    }
  }
}
