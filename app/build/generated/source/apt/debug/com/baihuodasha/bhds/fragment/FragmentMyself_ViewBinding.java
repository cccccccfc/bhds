// Generated code from Butter Knife. Do not modify!
package com.baihuodasha.bhds.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihuodasha.bhds.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FragmentMyself_ViewBinding<T extends FragmentMyself> implements Unbinder {
  protected T target;

  @UiThread
  public FragmentMyself_ViewBinding(T target, View source) {
    this.target = target;

    target.ll_home = Utils.findRequiredViewAsType(source, R.id.ll_myself_home, "field 'll_home'", LinearLayout.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.rl_myself_img, "field 'login'", RelativeLayout.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.tv_myself_name, "field 'name'", TextView.class);
    target.pic = Utils.findRequiredViewAsType(source, R.id.tv_myself_pic, "field 'pic'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.iv_myself_img, "field 'img'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_home = null;
    target.login = null;
    target.name = null;
    target.pic = null;
    target.img = null;

    this.target = null;
  }
}
