package com.baihuodasha.bhds.activity.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.view.LoadingProgressLayout;
import java.text.ParseException;

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
  private String phoneNumber;
  private String mVc_code = "";//验证码

  private LoadingProgressLayout mLayout;  //loading界面

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //mLayout = new LoadingProgressLayout(this) {
    //    @Override
    //    public View initSuccessView() {
    //        return loadingSuccessView();
    //    }
    //
    //    @Override
    //    public LoadedResult initData() {
    //        return visitInternet();
    //    }
    //};
    //setContentView(mLayout);

  }

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_register);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    ButterKnife.bind(this);
    title.setText("注册");
    title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    edit.setVisibility(View.INVISIBLE);
  }

  @Override public void initView() {
    back.setOnClickListener(this);
    getYzm.setOnClickListener(this);
    success.setOnClickListener(this);
    xieyi.setOnClickListener(this);
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
        getCode();
        break;
      case R.id.rl_register_login://完成注册
        successLogin();
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
    }
  }

  //private LoadingProgressLayout.LoadedResult visitInternet() {
  //    Random random = new Random();
  //    int i = random.nextInt(100);
  //    SystemClock.sleep(2000);
  //    if(i%4 == 0){
  //        return LoadingProgressLayout.LoadedResult.SUCCESS;
  //    }else {
  //        return LoadingProgressLayout.LoadedResult.ERROR;
  //    }
  //}

  private View loadingSuccessView() {
    View view = View.inflate(CommonUtils.getContext(), R.layout.activity_register, null);
    ButterKnife.bind(this, view);
    return view;
  }

  private boolean isEye = false;
  /**
   * 完成注册
   */
  private void successLogin() {
    String mCode = yzm.getText().toString();
    String mPassword = passport.getText().toString();
    phoneNumber = phone.getText().toString();


  }

  private void finishAll() {
  }

  /**
   * 获取验证码
   */
  public void getCode() {
    phoneNumber = phone.getText().toString();

  }
}
