// Generated code from Butter Knife. Do not modify!
package com.baihuodasha.bhds.activity.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihuodasha.bhds.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityRegister_ViewBinding<T extends ActivityRegister> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityRegister_ViewBinding(T target, View source) {
    this.target = target;

    target.back = Utils.findRequiredViewAsType(source, R.id.iv_base_back, "field 'back'", ImageView.class);
    target.edit = Utils.findRequiredViewAsType(source, R.id.iv_base_edit, "field 'edit'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.tv_base_title, "field 'title'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.et_register_phone, "field 'phone'", EditText.class);
    target.passport = Utils.findRequiredViewAsType(source, R.id.et_register_passport, "field 'passport'", EditText.class);
    target.yzm = Utils.findRequiredViewAsType(source, R.id.et_register_yzm, "field 'yzm'", EditText.class);
    target.getYzm = Utils.findRequiredViewAsType(source, R.id.rl_register_get, "field 'getYzm'", RelativeLayout.class);
    target.success = Utils.findRequiredViewAsType(source, R.id.rl_register_login, "field 'success'", RelativeLayout.class);
    target.xieyi = Utils.findRequiredViewAsType(source, R.id.ll_register_xieyi, "field 'xieyi'", LinearLayout.class);
    target.select = Utils.findRequiredViewAsType(source, R.id.iv_register_select, "field 'select'", ImageView.class);
    target.mView = Utils.findRequiredViewAsType(source, R.id.tv_register_yzm, "field 'mView'", TextView.class);
    target.eye = Utils.findRequiredViewAsType(source, R.id.rl_login_eye, "field 'eye'", RelativeLayout.class);
    target.imgeye = Utils.findRequiredViewAsType(source, R.id.img_login_eye, "field 'imgeye'", ImageView.class);
    target.ivBaseBackto = Utils.findRequiredViewAsType(source, R.id.iv_base_backto, "field 'ivBaseBackto'", ImageView.class);
    target.ivBaseMore = Utils.findRequiredViewAsType(source, R.id.iv_base_more, "field 'ivBaseMore'", ImageView.class);
    target.tvBaseSave = Utils.findRequiredViewAsType(source, R.id.tv_base_save, "field 'tvBaseSave'", TextView.class);
    target.tvSelectRegisterPhone = Utils.findRequiredViewAsType(source, R.id.tv_select_register_phone, "field 'tvSelectRegisterPhone'", TextView.class);
    target.tvSelectRegisterEmail = Utils.findRequiredViewAsType(source, R.id.tv_select_register_email, "field 'tvSelectRegisterEmail'", TextView.class);
    target.tvSelectRegisterPhoneLin = Utils.findRequiredViewAsType(source, R.id.tv_select_register_phone_lin, "field 'tvSelectRegisterPhoneLin'", TextView.class);
    target.tvSelectRegisterEmailLin = Utils.findRequiredViewAsType(source, R.id.tv_select_register_email_lin, "field 'tvSelectRegisterEmailLin'", TextView.class);
    target.llRegisterPhone = Utils.findRequiredViewAsType(source, R.id.ll_register_phone, "field 'llRegisterPhone'", LinearLayout.class);
    target.etRegisterEmail = Utils.findRequiredViewAsType(source, R.id.et_register_email, "field 'etRegisterEmail'", EditText.class);
    target.llRegisterEmail = Utils.findRequiredViewAsType(source, R.id.ll_register_email, "field 'llRegisterEmail'", LinearLayout.class);
    target.etRegisterName = Utils.findRequiredViewAsType(source, R.id.et_register_name, "field 'etRegisterName'", EditText.class);
    target.llRegisterName = Utils.findRequiredViewAsType(source, R.id.ll_register_name, "field 'llRegisterName'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.back = null;
    target.edit = null;
    target.title = null;
    target.phone = null;
    target.passport = null;
    target.yzm = null;
    target.getYzm = null;
    target.success = null;
    target.xieyi = null;
    target.select = null;
    target.mView = null;
    target.eye = null;
    target.imgeye = null;
    target.ivBaseBackto = null;
    target.ivBaseMore = null;
    target.tvBaseSave = null;
    target.tvSelectRegisterPhone = null;
    target.tvSelectRegisterEmail = null;
    target.tvSelectRegisterPhoneLin = null;
    target.tvSelectRegisterEmailLin = null;
    target.llRegisterPhone = null;
    target.etRegisterEmail = null;
    target.llRegisterEmail = null;
    target.etRegisterName = null;
    target.llRegisterName = null;

    this.target = null;
  }
}
