package com.baihuodasha.bhds.activity.order;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import java.text.ParseException;

/**
 * 支付结果
 */
public class ActivityPaymenResult extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.iv_paymen_result_success) ImageView ivPaymenResultSuccess;
  @BindView(R.id.tv_paymen_result_title) TextView tvPaymenResultTitle;
  @BindView(R.id.tv_paymen_result_Integral) TextView tvPaymenResultIntegral;
  @BindView(R.id.et_payment_number) TextView etPaymentNumber;
  @BindView(R.id.tv_paymen_result_yu) TextView tvPaymenResultYu;
  @BindView(R.id.tv_payment_fukuan) TextView tvPaymentFukuan;
  @BindView(R.id.et_payment_money) TextView etPaymentMoney;
  @BindView(R.id.tv_payment_name) TextView tvPaymentName;
  @BindView(R.id.tv_payment_phone) TextView tvPaymentPhone;
  @BindView(R.id.tv_payment_deliveryaddress) TextView tvPaymentDeliveryaddress;
  @BindView(R.id.cb_payment_home) RelativeLayout cbPaymentHome;
  @BindView(R.id.cb_payment_cnfrim) RelativeLayout cbPaymentCnfrim;
  @BindView(R.id.item_tx_title) TextView itemTxTitle;
  @BindView(R.id.rec_bhds_choiceness) RecyclerView recBhdsChoiceness;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_paymen_result);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("付款结果");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {

  }

  @Override public void onClickEvent(View view) throws ParseException {

  }
}
