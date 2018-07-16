package com.baihuodasha.bhds.activity.order;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.view.PasswordInputView;
import java.text.ParseException;

/**
 * 选择支付方式
 */
public class ActivityModePayment extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.cb_payment_zhifubao) CheckBox cbPaymentZhifubao;
  @BindView(R.id.rl_payment_zhifubao) RelativeLayout rlPaymentZhifubao;
  @BindView(R.id.cb_payment_weixin) CheckBox cbPaymentWeixin;
  @BindView(R.id.rl_payment_weixin) RelativeLayout rlPaymentWeixin;
  @BindView(R.id.cb_payment_yue) CheckBox cbPaymentYue;
  @BindView(R.id.rl_payment_yue) RelativeLayout rlPaymentYue;
  @BindView(R.id.et_payment_info_price) TextView etPaymentInfoPrice;
  @BindView(R.id.et_payment_info_carriage) TextView etPaymentInfoCarriage;
  @BindView(R.id.et_payment_redpacket) TextView etPaymentRedpacket;
  @BindView(R.id.et_payment_perch) TextView etPaymentPerch;
  @BindView(R.id.cb_payment_cnfrim) RelativeLayout cbPaymentCnfrim;

  private PasswordInputView dialog_pass;
  private RelativeLayout dialog_confrim;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_mode_of_payment);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("选择支付方式");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    cbPaymentZhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Log.i("wsx", "onCheckedChanged:选中支付宝并设置微信不可选");

          cbPaymentWeixin.setChecked(false);
        } else {
          Log.i("wsx", "onCheckedChanged:未选中支付宝并设置微信可选");

          // cbPaymentWeixin.setChecked(true);
        }
      }
    });
    cbPaymentWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Log.i("wsx", "onCheckedChanged:选中微信并设置支付宝不可选");

          cbPaymentZhifubao.setChecked(false);
        } else {
          Log.i("wsx", "onCheckedChanged:未选中微信并设置支付宝可选");

          // cbPaymentZhifubao.setChecked(true);
        }
      }
    });
    cbPaymentYue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

      }
    });
    cbPaymentCnfrim.setOnClickListener(this);
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
      case R.id.cb_payment_cnfrim:

        showEndDialog();
        break;
      case R.id.confrim:

        CommonUtils.toastMessage("支付");
        break;
    }
  }

  public void showEndDialog() {
    AlertDialog.Builder endDialog = new AlertDialog.Builder(this);
    View view = View.inflate(this, R.layout.dialog_mode_payment, null);
    endDialog.setView(view);
    endDialog.setCancelable(true);
    dialog_pass = (PasswordInputView) view.findViewById(R.id.password);
    dialog_confrim = (RelativeLayout) view.findViewById(R.id.confrim);
   // dialog_pass.setOnClickListener(this);
    dialog_confrim.setOnClickListener(this);
    dialog_pass.setOnInputFinishListener(new PasswordInputView.OnInputFinishListener() {
      @Override public void onInputFinish(String password) {
        Log.i("wsx", "onInputFinish" + password);
      }
    });
    AlertDialog dialog = endDialog.create();
    dialog.show();
  }
}
