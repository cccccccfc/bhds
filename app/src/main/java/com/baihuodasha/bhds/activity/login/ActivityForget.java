package com.baihuodasha.bhds.activity.login;

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

    }
    private void successForget() {
        phoneNumber = phone.getText().toString();
        String mPassword = passport.getText().toString();
        String nextPassword = nextPassport.getText().toString();
        String mCode = yzm.getText().toString();

    }

}
