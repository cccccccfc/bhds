package com.baihuodasha.bhds.activity.login;

import android.content.Intent;
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
import com.baihuodasha.bhds.utils.CommonUtils;
import com.base.utilslibrary.internet.PersonalInternetRequestUtils;
import java.text.ParseException;

public class ActivityForget extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.iv_base_back)
    ImageView back;
    @BindView(R.id.iv_base_edit)
    ImageView edit;
    @BindView(R.id.tv_base_title)
    TextView title;

    @BindView(R.id.et_forget_phone)
    EditText phone;
    @BindView(R.id.et_forget_passport)
    EditText passport;
    @BindView(R.id.et_forget_next_passport)
    EditText nextPassport;
    @BindView(R.id.et_forget_yzm)
    EditText yzm;
    @BindView(R.id.rl_forget_get)
    RelativeLayout getYzm;
    @BindView(R.id.rl_forget_success)
    RelativeLayout success;
    @BindView(R.id.tv_forget_yzm)
    TextView mView;
    private String phoneNumber;
    private String mCode;
    private String mPassword;
    private String nextPassword;
    private String mVc_code ="";//验证码
    @Override public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        title.setText("忘记密码");
        title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        edit.setVisibility(View.GONE);
        setStatusBarPlaceVisible(true);
        setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    }

    @Override public void initView() {
        back.setOnClickListener(this);
        getYzm.setOnClickListener(this);
        success.setOnClickListener(this);
    }

    @Override public void dealLogicBeforeInitView() {

    }

    @Override public void dealLogicAfterInitView() {

    }

    @Override public void onClickEvent(View view) throws ParseException {
        switch (view.getId()){
            case R.id.iv_base_back:
                finishAll();
                finish();
                break;
            case R.id.rl_forget_get://获取验证码
                getCode();
                break;
            case R.id.rl_forget_success://完成修改密码
                successForget();
                break;
        }
    }

    private void finishAll() {
    }

    public void getCode() {
        phoneNumber = phone.getText().toString();
        PersonalInternetRequestUtils.verificationCode(this,phoneNumber, mView,
                new PersonalInternetRequestUtils.ForResultListener() {
            @Override
            public void onResponseMessage(String code) {
                mVc_code = code;
            }
        });
    }
    private void successForget() {
        phoneNumber = phone.getText().toString();
        mPassword = passport.getText().toString();
        nextPassword = nextPassport.getText().toString();
        mCode = yzm.getText().toString();
        PersonalInternetRequestUtils.forgetPassword(this,phoneNumber, mVc_code, mCode, mPassword, nextPassword,
                mView, passport,nextPassport,new PersonalInternetRequestUtils.ForResultListener() {
            @Override
            public void onResponseMessage(String code) {
                if(code.equals("成功")){
                    CommonUtils.toastMessage("修改密码成功");
                    Intent intent = new Intent(CommonUtils.getContext(),ActivityLogin.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    finishAll();
                    finish();
                }
            }
        });
    }

}
