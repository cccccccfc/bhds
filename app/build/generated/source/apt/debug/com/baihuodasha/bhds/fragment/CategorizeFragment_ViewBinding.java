// Generated code from Butter Knife. Do not modify!
package com.baihuodasha.bhds.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.utils.CustomViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategorizeFragment_ViewBinding<T extends CategorizeFragment> implements Unbinder {
  protected T target;

  @UiThread
  public CategorizeFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.goods, "field 'viewPager'", CustomViewPager.class);
    target.tools_scrollView = Utils.findRequiredViewAsType(source, R.id.tools_scrollView, "field 'tools_scrollView'", ScrollView.class);
    target.title_return = Utils.findRequiredViewAsType(source, R.id.title_return, "field 'title_return'", ImageView.class);
    target.lin_seach = Utils.findRequiredViewAsType(source, R.id.lin_seach, "field 'lin_seach'", LinearLayout.class);
    target.img_message = Utils.findRequiredViewAsType(source, R.id.img_message, "field 'img_message'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.viewPager = null;
    target.tools_scrollView = null;
    target.title_return = null;
    target.lin_seach = null;
    target.img_message = null;

    this.target = null;
  }
}
