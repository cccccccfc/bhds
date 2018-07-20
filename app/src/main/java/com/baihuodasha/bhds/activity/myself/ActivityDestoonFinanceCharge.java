package com.baihuodasha.bhds.activity.myself;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import java.text.ParseException;

public class ActivityDestoonFinanceCharge extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.tv_destoon_finance_charge_money) TextView tvDestoonFinanceChargeMoney;
  @BindView(R.id.tv_property_recharge_hint) TextView tvPropertyRechargeHint;
  @BindView(R.id.et_property_recharge_number) EditText etPropertyRechargeNumber;
  @BindView(R.id.cb_destoon_finance_charge_zhifubao) CheckBox cbDestoonFinanceChargeZhifubao;
  @BindView(R.id.cb_destoon_finance_charge_weixin) CheckBox cbDestoonFinanceChargeWeixin;
  @BindView(R.id.cb_destoon_finance_charge_confrim) RelativeLayout cbDestoonFinanceChargeConfrim;
  @BindView(R.id.cb_destoon_finance_charge_reset) RelativeLayout cbDestoonFinanceChargeReset;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_destoon_finance_charge);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("在线充值");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    tvBaseSave.setVisibility(View.VISIBLE);
    tvBaseSave.setText("明细");
    tvBaseSave.setOnClickListener(this);
    cbDestoonFinanceChargeConfrim.setOnClickListener(this);
    cbDestoonFinanceChargeReset.setOnClickListener(this);
    cbDestoonFinanceChargeZhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Log.i("wsx", "onCheckedChanged:选中支付宝并设置微信不可选");

          cbDestoonFinanceChargeWeixin.setChecked(false);
        } else {
          Log.i("wsx", "onCheckedChanged:未选中支付宝并设置微信可选");

          // cbPaymentWeixin.setChecked(true);
        }
      }
    });
    cbDestoonFinanceChargeWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Log.i("wsx", "onCheckedChanged:选中微信并设置支付宝不可选");

          cbDestoonFinanceChargeZhifubao.setChecked(false);
        } else {
          Log.i("wsx", "onCheckedChanged:未选中微信并设置支付宝可选");

          // cbPaymentZhifubao.setChecked(true);
        }
      }
    });
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
