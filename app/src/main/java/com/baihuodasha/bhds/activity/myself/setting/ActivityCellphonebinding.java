package com.baihuodasha.bhds.activity.myself.setting;

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

public class ActivityCellphonebinding extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_myself_binding_phone) TextView tvMyselfBindingPhone;
  @BindView(R.id.tv_myself_binding_verificationcode) TextView tvMyselfBindingVerificationcode;
  @BindView(R.id.rl_myself_binding_verificationcode) RelativeLayout rlMyselfBindingVerificationcode;
  @BindView(R.id.et_myself_binding_verificationcode) EditText etMyselfBindingVerificationcode;
  @BindView(R.id.tv_myself_binding_error) TextView tvMyselfBindingError;
  @BindView(R.id.rl_myself_binding_confirm) RelativeLayout rlMyselfBindingConfirm;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_cellphonebinding);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    tvBaseTitle.setText("手机号绑定");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    ivBaseBack.setOnClickListener(this);
    rlMyselfBindingVerificationcode.setOnClickListener(this);
    rlMyselfBindingConfirm.setOnClickListener(this);
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
      case R.id.rl_myself_binding_verificationcode:
      //获取验证码
        break;
      case R.id.rl_myself_binding_confirm:
        //点击绑定
        break;

    }
  }


}
