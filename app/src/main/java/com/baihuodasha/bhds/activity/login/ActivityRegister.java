package com.baihuodasha.bhds.activity.login;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.view.LoadingProgressLayout;
import com.base.utilslibrary.internet.PersonalInternetRequestUtils;
import java.util.Random;

public class ActivityRegister extends FragmentActivity implements View.OnClickListener{

    @BindView(R.id.iv_base_back)
    ImageView back;
    @BindView(R.id.iv_base_edit)
    ImageView edit;
    @BindView(R.id.tv_base_title)
    TextView title;

    @BindView(R.id.et_register_phone)
    EditText phone;
    @BindView(R.id.et_register_passport)
    EditText passport;
    @BindView(R.id.et_register_yzm)
    EditText yzm;
    @BindView(R.id.rl_register_get)
    RelativeLayout getYzm;
    @BindView(R.id.rl_register_login)
    RelativeLayout success;
    @BindView(R.id.ll_register_xieyi)
    LinearLayout xieyi;
    @BindView(R.id.iv_register_select)
    ImageView select;
    @BindView(R.id.tv_register_yzm)
    TextView mView;

    private String phoneNumber;
    private String mCode;
    private String mPassword;
    private String mVc_code ="";//验证码

    private LoadingProgressLayout mLayout;  //loading界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new LoadingProgressLayout(this) {
            @Override
            public View initSuccessView() {
                return loadingSuccessView();
            }

            @Override
            public LoadedResult initData() {
                return visitInternet();
            }
        };
        setContentView(mLayout);
//        setContentView(R.layout.activity_register);
//        ButterKnife.bind(this);
//        title.setText("注册");
//        edit.setVisibility(View.INVISIBLE);
        initdata();
//        initListener();
    }

    private LoadingProgressLayout.LoadedResult visitInternet() {
        Random random = new Random();
        int i = random.nextInt(100);
        SystemClock.sleep(2000);
        if(i%4 == 0){
            return LoadingProgressLayout.LoadedResult.SUCCESS;
        }else {
            return LoadingProgressLayout.LoadedResult.ERROR;
        }
    }

    private View loadingSuccessView() {
        View view = View.inflate(CommonUtils.getContext(), R.layout.activity_register, null);
        ButterKnife.bind(this,view);
        initListener();
        return view;
    }

    private void initdata() {
        mLayout.loadData();
    }
    private void initListener() {
        back.setOnClickListener(this);
        getYzm.setOnClickListener(this);
        success.setOnClickListener(this);
        xieyi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

        }
    }

    /**
     * 完成注册
     */
    private void successLogin() {
        mCode = yzm.getText().toString();
        mPassword = passport.getText().toString();
        phoneNumber = phone.getText().toString();

        PersonalInternetRequestUtils.register(this,phoneNumber, mVc_code, mPassword, mCode,mView, new PersonalInternetRequestUtils.ForResultListener() {
            @Override
            public void onResponseMessage(String code) {
                if(code.equals("成功")){
                    CommonUtils.toastMessage("注册成功");
                    finish();
                }
            }
        });
    }

    private void finishAll() {
    }

    /**
     * 获取验证码
     */
    public void getCode() {
        phoneNumber = phone.getText().toString();
        PersonalInternetRequestUtils.verificationCode(this,phoneNumber,mView, new PersonalInternetRequestUtils.ForResultListener() {
            @Override
            public void onResponseMessage(String code) {
                mVc_code = code;
            }
        });
    }
}
