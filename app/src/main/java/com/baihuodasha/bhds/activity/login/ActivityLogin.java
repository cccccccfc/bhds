package com.baihuodasha.bhds.activity.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.MainActivityTabHost;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.MainLogModel;
import com.baihuodasha.bhds.bean.Msg;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.net.SharePrefHelper;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.StringUtil;
import java.text.ParseException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends BaseActivity {

  @BindView(R.id.et_login_phone)//登录手机号
      EditText phone;
  @BindView(R.id.et_login_passport)//登录密码
      EditText passport;
  @BindView(R.id.rl_login_eye)//密码明文
      RelativeLayout eye;
  @BindView(R.id.rl_login_login)//登录
      RelativeLayout login;
  @BindView(R.id.tv_login_register)//新用户注册
      TextView register;
  @BindView(R.id.tv_login_forget)//忘记密码
      TextView forget;
  @BindView(R.id.iv_login_qq)//qq登陆
      ImageView qq;
  @BindView(R.id.iv_login_wx)//微信登录
      ImageView wx;

  @BindView(R.id.img_full_selector) CheckBox imgFullSelector;
  @BindView(R.id.img_login_eye) ImageView imgeye;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    tvBaseTitle.setVisibility(View.VISIBLE);
    tvBaseTitle.setText("登录");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  @Override public void initView() {
    mSh = SharePrefHelper.getInstance();
    ivBaseBack.setOnClickListener(this);
    eye.setOnClickListener(this);
    login.setOnClickListener(this);
    register.setOnClickListener(this);
    forget.setOnClickListener(this);
    qq.setOnClickListener(this);
    wx.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {

  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        Intent intentfin = new Intent(this, MainActivityTabHost.class);
        startActivity(intentfin);
        finish();
        break;
      case R.id.rl_login_eye:
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
      case R.id.rl_login_login://登录
        String name = phone.getText().toString();
        String pass = passport.getText().toString();
        if (StringUtil.isNullOrEmpty(name)) {
          showToast("请输入帐号");
          return;
        }
        if (StringUtil.isNullOrEmpty(pass)) {
          showToast("请输入账户密码");
          return;
        }
        getLoginInfo(name, pass);
        break;
      case R.id.tv_login_register://跳转注册
        intent = new Intent(this, ActivityRegister.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        break;
      case R.id.tv_login_forget://跳转忘记密码
        intent = new Intent(this, ActivityForget.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        break;
      case R.id.iv_login_qq:
        CommonUtils.toastMessage("QQ登录");
        break;

      case R.id.iv_login_wx:
        CommonUtils.toastMessage("微信登录");
        break;
    }
    intent = null;
  }

  private boolean isEye = false;
  Intent intent;

  //activity回调  第三方登录的回调
  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    //  UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
  }

  private SharePrefHelper mSh;

  /**
   * 联网方式
   */
  public void getLoginInfo(final String name, String pwd) {

    SdjNetWorkManager.getLogInfo(name, pwd, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        if (msg != null) {
          MainLogModel bean= (MainLogModel) msg.getData();
          Log.i("qaz", "onResponse: "+bean.toString());
          mSh.setUserId(bean.getUserid() + "");
          mSh.setUserToken(bean.getKey() + "");
         // mSh.setUserName(name + "");
          if (msg.getMsg().equals("登录成功")) {
            // mSh.setLoginSuccess(true);
            //mSh.setFirstStartInto(1);
            Intent i = new Intent(ActivityLogin.this, MainActivityTabHost.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
          } else {
            CommonUtils.toastMessage(msg.getMsg());
            //  mSh.setLoginSuccess(false);
            //mSh.setFirstStartInto(0);
          }
        } else {
         // CommonUtils.toastMessage(msg.getMsg());
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        mSh.setLoginSuccess(false);
      }
    });
  }
}
