package com.baihuodasha.bhds.activity.login;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.MainRegisterModel;
import com.baihuodasha.bhds.bean.Msg;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.StringUtil;
import java.text.ParseException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends BaseActivity implements View.OnClickListener {

  @BindView(R.id.iv_base_back) ImageView back;
  @BindView(R.id.iv_base_edit) ImageView edit;
  @BindView(R.id.tv_base_title) TextView title;
  @BindView(R.id.et_register_phone) EditText phone;
  @BindView(R.id.et_register_passport) EditText passport;
  @BindView(R.id.et_register_yzm) EditText yzm;
  @BindView(R.id.rl_register_get) RelativeLayout getYzm;
  @BindView(R.id.rl_register_login) RelativeLayout success;
  @BindView(R.id.ll_register_xieyi) LinearLayout xieyi;
  @BindView(R.id.iv_register_select) ImageView select;
  @BindView(R.id.tv_register_yzm) TextView mView;
  @BindView(R.id.rl_login_eye)//密码明文
      RelativeLayout eye;
  @BindView(R.id.img_login_eye) ImageView imgeye;
  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.tv_select_register_phone) TextView tvSelectRegisterPhone;  //1
  @BindView(R.id.tv_select_register_email) TextView tvSelectRegisterEmail;  //2
  @BindView(R.id.tv_select_register_phone_lin) TextView tvSelectRegisterPhoneLin;
  @BindView(R.id.tv_select_register_email_lin) TextView tvSelectRegisterEmailLin;
  @BindView(R.id.ll_register_phone) LinearLayout llRegisterPhone;
  @BindView(R.id.et_register_email) EditText etRegisterEmail;
  @BindView(R.id.ll_register_email) LinearLayout llRegisterEmail;
  @BindView(R.id.et_register_name) EditText etRegisterName;
  @BindView(R.id.ll_register_name) LinearLayout llRegisterName;
  private String phoneNumber;
  private String mVc_code = "";//验证码
  private boolean isTouch;
  private int type = 1;
  private String mCode;
  private String mPhone;
  private String mEmail;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_register);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    ButterKnife.bind(this);
    title.setText("注册");
    title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    tvSelectRegisterPhone.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    edit.setVisibility(View.INVISIBLE);
  }

  @Override public void initView() {
    back.setOnClickListener(this);
    getYzm.setOnClickListener(this);
    success.setOnClickListener(this);
    xieyi.setOnClickListener(this);
    eye.setOnClickListener(this);
    tvSelectRegisterPhone.setOnClickListener(this);
    tvSelectRegisterEmail.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {

  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finishAll();
        finish();
        break;
      case R.id.rl_register_get://获取验证码
        if (isTouch) {
          if (type == 1) { //手机注册
            mPhone = phone.getText().toString();
            if (StringUtil.isNullOrEmpty(mPhone)) {
              showToast("请输入手机号");
              return;
            }
            if (!CommonUtils.isMobilePhone(mPhone)) {
              showToast("请输入正确手机号");
              return;
            }
            isTouch = false;
            getPhoneCode(mPhone);
          } else {  //邮箱注册
            mEmail = etRegisterEmail.getText().toString();
            Log.i("qaz", "onClickEvent: "+mEmail);
            if (StringUtil.isNullOrEmpty(mEmail)) {
              showToast("请输入邮箱");
              return;
            }
            if (!CommonUtils.isEmail(mEmail)) {
              showToast("请输入正确邮箱");
              return;
            }
            isTouch = false;
            getEmailCode(mEmail);
          }
        }

        break;
      case R.id.rl_register_login://完成注册
        mCode = yzm.getText().toString();
        String mPassword = passport.getText().toString();
        String mPhone = phone.getText().toString();
        String mEmail = etRegisterEmail.getText().toString();
        String mName = etRegisterName.getText().toString();

        if (StringUtil.isNullOrEmpty(mPassword)) {
          showToast("请输入账号密码");
          return;
        }
        if (StringUtil.isNullOrEmpty(mName)) {
          showToast("请输入账号");
          return;
        }
        if (type == 1) { //手机注册
          if (StringUtil.isNullOrEmpty(mPhone)) {
            showToast("请输入手机号");
            return;
          }
          if (!CommonUtils.isMobilePhone(mPhone)) {
            showToast("请输入正确手机号");
            return;
          }
          if (!mCode.equals(mVc_code)) {
            showToast("手机验证码不正确");
            return;
          }
          successPhoneLogin(mName, mPassword, mPhone);
        } else {  //邮箱注册

          if (StringUtil.isNullOrEmpty(mEmail)) {
            showToast("请输入邮箱");
            return;
          }
          if (!CommonUtils.isEmail(mEmail)) {
            showToast("请输入邮箱");
            return;
          }
          if (!mCode.equals(mVc_code)) {
            showToast("邮箱验证码不正确");
            return;
          }

          successEmailLogin(mName, mPassword, mEmail);
        }

        break;
      case R.id.ll_register_xieyi://同意协议
        break;
      case R.id.rl_login_eye://密码
        if (!isEye) {
          isEye = !isEye;
          imgeye.setImageDrawable(getResources().getDrawable(R.mipmap.show));
          passport.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
          isEye = !isEye;
          imgeye.setImageDrawable(getResources().getDrawable(R.mipmap.conceal));
          passport.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passport.setSelection(passport.getText().length());
        break;
      case R.id.tv_select_register_phone://手机注册
        isTouch = true;
        etRegisterEmail.setText("");
        yzm.setText("");
        llRegisterPhone.setVisibility(View.VISIBLE);
        llRegisterEmail.setVisibility(View.GONE);
        tvSelectRegisterPhoneLin.setBackgroundColor(Color.parseColor("#D3BF9C"));
        tvSelectRegisterEmailLin.setBackgroundColor(Color.parseColor("#f6f6f6"));
        //tvSelectRegisterPhone.setTextColor(Color.parseColor("#666666"));
        tvSelectRegisterPhone.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvSelectRegisterEmail.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        //  tvSelectRegisterEmail.setTextColor(Color.parseColor("#666666"));
        type = 1;
        break;
      case R.id.tv_select_register_email://邮箱注册
        isTouch = true;
        phone.setText("");
        yzm.setText("");
        llRegisterPhone.setVisibility(View.GONE);
        llRegisterEmail.setVisibility(View.VISIBLE);
        tvSelectRegisterPhoneLin.setBackgroundColor(Color.parseColor("#f6f6f6"));
        tvSelectRegisterEmailLin.setBackgroundColor(Color.parseColor("#D3BF9C"));
        // tvSelectRegisterEmail.setTextColor(Color.parseColor("#666666"));
        tvSelectRegisterPhone.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tvSelectRegisterEmail.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        type = 2;
        break;
    }
  }

  private boolean isEye = false;

  /**
   * 完成注册
   */
  private void successPhoneLogin(String mName, String mPassword, String mPhone) {

    SdjNetWorkManager.MobilePhoneRegister(mName, mPassword, mPhone, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        if (msg != null) {
          if (msg.getCode().equals("1")) {
            CommonUtils.toastMessage(msg.getMsg());
          } else {

          }
        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
  }

  /**
   * 完成注册
   */
  private void successEmailLogin(String mName, String mPassword, String email) {

    SdjNetWorkManager.EmailRegister(mName, mPassword, email, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        //Log.i("qaz", "onResponse: "+msg.toString());
        Toast.makeText(ActivityRegister.this, msg.getMsg(), Toast.LENGTH_LONG).show();
        if (msg != null) {
          if (msg.getCode().equals("1")) {

          }
        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
  }

  private void finishAll() {
  }

  /**
   * 获取验证码
   */
  public void getPhoneCode(String mobilecode) {
    startCount();
    SdjNetWorkManager.sendMobileCode(mobilecode, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        MainRegisterModel bean= (MainRegisterModel) msg.getData();
        if (msg != null) {
          if (msg.getCode().equals("200")) {
            mVc_code = bean.getCode();
          } else {
            CommonUtils.toastMessage(msg.getMsg());
          }
        } else {

        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
  }
  /**
   * 获取验证码
   */
  public void getEmailCode(String email) {
    startCount();
    SdjNetWorkManager.sendEmailCode(email, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        MainRegisterModel bean= (MainRegisterModel) msg.getData();
        if (msg != null) {

          if (msg.getCode().equals("0")) {
            mVc_code = bean.getEmail_code();
          } else {
            CommonUtils.toastMessage(msg.getMsg());
          }
        } else {

        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
  }

  @Override public void onResume() {
    super.onResume();
    isTouch = true;
  }

  //验证码按钮跑秒
  int timecount = 60;

  public void startCount() {
    Runnable runnable = new Runnable() {
      @Override public void run() {
        timecount--;
        if (timecount <= 0) {
          mHandler.sendEmptyMessage(5);
        } else {
          mHandler.sendEmptyMessage(4);
          mHandler.postDelayed(this, 1000);
        }
      }
    };
    new Thread(runnable).start();
  }

  private Handler mHandler = new Handler() {
    public void handleMessage(android.os.Message msg) {
      switch (msg.what) {
        case 4:
          isTouch = false;
          mView.setText(String.valueOf(timecount) + "S重新获取");
          mView.setEnabled(false);
          break;
        case 5:
          isTouch = true;
          mView.setText("重新获取");
          if (phone.getText().toString().equals("") || etRegisterEmail.getText()
              .toString()
              .equals("")) {
            mView.setEnabled(false);
          } else {
            mView.setEnabled(true);
          }
          timecount = 60;
          break;
      }
    }
  };
}
