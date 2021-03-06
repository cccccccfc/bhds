// Generated code from Butter Knife. Do not modify!
package com.baihuodasha.bhds.activity.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihuodasha.bhds.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityLogin_ViewBinding<T extends ActivityLogin> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityLogin_ViewBinding(T target, View source) {
    this.target = target;

    target.phone = Utils.findRequiredViewAsType(source, R.id.et_login_phone, "field 'phone'", EditText.class);
    target.passport = Utils.findRequiredViewAsType(source, R.id.et_login_passport, "field 'passport'", EditText.class);
    target.eye = Utils.findRequiredViewAsType(source, R.id.rl_login_eye, "field 'eye'", RelativeLayout.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.rl_login_login, "field 'login'", RelativeLayout.class);
    target.register = Utils.findRequiredViewAsType(source, R.id.tv_login_register, "field 'register'", TextView.class);
    target.forget = Utils.findRequiredViewAsType(source, R.id.tv_login_forget, "field 'forget'", TextView.class);
    target.qq = Utils.findRequiredViewAsType(source, R.id.iv_login_qq, "field 'qq'", ImageView.class);
    target.wx = Utils.findRequiredViewAsType(source, R.id.iv_login_wx, "field 'wx'", ImageView.class);
    target.imgFullSelector = Utils.findRequiredViewAsType(source, R.id.img_full_selector, "field 'imgFullSelector'", CheckBox.class);
    target.imgeye = Utils.findRequiredViewAsType(source, R.id.img_login_eye, "field 'imgeye'", ImageView.class);
    target.ivBaseBack = Utils.findRequiredViewAsType(source, R.id.iv_base_back, "field 'ivBaseBack'", ImageView.class);
    target.tvBaseTitle = Utils.findRequiredViewAsType(source, R.id.tv_base_title, "field 'tvBaseTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.phone = null;
    target.passport = null;
    target.eye = null;
    target.login = null;
    target.register = null;
    target.forget = null;
    target.qq = null;
    target.wx = null;
    target.imgFullSelector = null;
    target.imgeye = null;
    target.ivBaseBack = null;
    target.tvBaseTitle = null;

    this.target = null;
  }
}
