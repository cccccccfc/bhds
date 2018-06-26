package com.baihuodasha.bhds.activity.myself.receiveraddress;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.StringUtil;
import java.text.ParseException;

public class ActivityNewAddress extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView ivBaseSave;//
  @BindView(R.id.tv_myself_newaddress_location) TextView tvMyselfNewaddressLocation;
  @BindView(R.id.rl_myself_newaddress_location) RelativeLayout rlMyselfNewaddressLocation;
  @BindView(R.id.tv_myself_newaddress_particularlocation) TextView
      tvMyselfNewaddressParticularlocation;
  @BindView(R.id.et_myself_newaddress_particularlocation) EditText
      etMyselfNewaddressParticularlocation;
  @BindView(R.id.rl_myself_newaddress_particularlocation) RelativeLayout
      rlMyselfNewaddressParticularlocation;
  @BindView(R.id.tv_myself_newaddress_name) TextView tvMyselfNewaddressName;
  @BindView(R.id.et_myself_newaddress_name) EditText etMyselfNewaddressName;
  @BindView(R.id.rl_myself_newaddress_name) RelativeLayout rlMyselfNewaddressName;
  @BindView(R.id.tv_myself_newaddress_phone) TextView tvMyselfNewaddressPhone;
  @BindView(R.id.et_myself_newaddress_phone) EditText EtMyselfNewaddressPhone;
  @BindView(R.id.rl_myself_newaddress_phone) RelativeLayout rlMyselfNewaddressPhone;
  @BindView(R.id.ck_myself_newaddress_selector) CheckBox ckMyselfNewaddressSelector;
  private int type;
  private String addressphone;
  private String location;
  private String addressname;
  private String addresslocation;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_new_address);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    type = getIntent().getIntExtra("type", 1);
    if (type == 1) {
      tvBaseTitle.setText("新建地址");
    } else {
      tvBaseTitle.setText("编辑地址");
    }
    ivBaseSave.setVisibility(View.VISIBLE);
    ivBaseSave.setOnClickListener(this);
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
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
      case R.id.tv_base_save:
        addressphone = EtMyselfNewaddressPhone.getText().toString();//电话
        location = etMyselfNewaddressParticularlocation.getText().toString();  //详细地址
        addressname = EtMyselfNewaddressPhone.getText().toString(); // 名字
        addresslocation = tvMyselfNewaddressLocation.getText().toString(); //省市
        // finish();
        if (StringUtil.isNullOrEmpty(addresslocation)) {
          showToast("请选择收货地址");
          //  return;
        } else if (StringUtil.isNullOrEmpty(location)) {
          showToast("请输入详细地址");
          return;
        } else if (StringUtil.isNullOrEmpty(addressphone)) {
          showToast("请输入手机号");
          return;
        } else if (!CommonUtils.isMobilePhone(addressphone)) {
          showToast("请输入正确的手机号");
          return;
        } else if (StringUtil.isNullOrEmpty(addressname)) {
          showToast("请输入收件人姓名");
          return;
        } else {
          CommonUtils.toastMessage("创建成功");
        }
        break;
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }
}
