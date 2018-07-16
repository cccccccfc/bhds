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
import com.baihuodasha.bhds.bean.MainLogModel;
import com.baihuodasha.bhds.bean.Msg;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.StringUtil;
import java.text.ParseException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAccountpassword extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.et_account_user_password) EditText etAccountUserPassword;
  @BindView(R.id.tv_account_user_password_hint) TextView tvAccountUserPasswordHint;
  @BindView(R.id.et_account_new_password_one) EditText etAccountNewPasswordOne;
  @BindView(R.id.et_account_new_password_two) EditText etAccountNewPasswordTwo;
  @BindView(R.id.tv_account_new_password_hint) TextView tvAccountNewPasswordHint;
  @BindView(R.id.rl_account_new_password_confirm) RelativeLayout rlAccountNewPasswordConfirm;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_accountpassword);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("账号密码管理");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    rlAccountNewPasswordConfirm.setOnClickListener(this);
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
      case R.id.rl_account_new_password_confirm:
        String userpasswd = etAccountUserPassword.getText().toString();
        String passwdone = etAccountNewPasswordOne.getText().toString();
        String passwdtwo = etAccountNewPasswordTwo.getText().toString();
        if (StringUtil.isNullOrEmpty(userpasswd)) {
          showToast("请输入旧密码");
          return;
        }
        if (StringUtil.isNullOrEmpty(passwdone)) {
          showToast("请输入新密码");
          return;
        }
        if (StringUtil.isNullOrEmpty(passwdtwo)) {
          showToast("请再次输入密码");
          return;
        }
        //Log.i("qaz", "onClickEvent: "+passwdone+passwdtwo);
        if (!passwdone.equals(passwdtwo)) {
         // Log.i("qaz", "onClickEvent: "+passwdone+passwdtwo);
          tvAccountNewPasswordHint.setVisibility(View.VISIBLE);
          return;
        }else {
          tvAccountNewPasswordHint.setVisibility(View.GONE);
        }
        setModifyPasswd(userpasswd,passwdone);
        break;
    }
  }

  private void setModifyPasswd(String user_pwd, String new_pwd) {
    SdjNetWorkManager.setModifyPasswd(user_pwd, new_pwd, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        MainLogModel bean= (MainLogModel) msg.getData();
        if (msg != null ) {
          if (msg.getCode().equals("1")) {
            CommonUtils.toastMessage(msg.getMsg());
            tvAccountUserPasswordHint.setVisibility(View.GONE);
            finish();
          } else if(msg.getCode().equals("2")) {
            tvAccountUserPasswordHint.setVisibility(View.VISIBLE);
          }else {
            CommonUtils.toastMessage(msg.getMsg());
            tvAccountUserPasswordHint.setVisibility(View.GONE);
          }

        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
  }

}
