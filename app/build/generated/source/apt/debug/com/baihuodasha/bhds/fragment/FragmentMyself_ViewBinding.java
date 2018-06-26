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
import com.baihuodasha.bhds.utils.HeadZoomScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FragmentMyself_ViewBinding<T extends FragmentMyself> implements Unbinder {
  protected T target;

  @UiThread
  public FragmentMyself_ViewBinding(T target, View source) {
    this.target = target;

    target.ivMyselfHeadportrait = Utils.findRequiredViewAsType(source, R.id.iv_myself_headportrait, "field 'ivMyselfHeadportrait'", ImageView.class);
    target.rlMyselfHeadportrait = Utils.findRequiredViewAsType(source, R.id.rl_myself_headportrait, "field 'rlMyselfHeadportrait'", RelativeLayout.class);
    target.tvMyselfSignin = Utils.findRequiredViewAsType(source, R.id.tv_myself_signin, "field 'tvMyselfSignin'", TextView.class);
    target.tvMyselfOrderform = Utils.findRequiredViewAsType(source, R.id.tv_myself_orderform, "field 'tvMyselfOrderform'", TextView.class);
    target.tvMyselfOrderformSeeall = Utils.findRequiredViewAsType(source, R.id.tv_myself_orderform_seeall, "field 'tvMyselfOrderformSeeall'", TextView.class);
    target.llMyselfOrderformObligation = Utils.findRequiredViewAsType(source, R.id.ll_myself_orderform_obligation, "field 'llMyselfOrderformObligation'", LinearLayout.class);
    target.llMyselfOrderformDelivergoods = Utils.findRequiredViewAsType(source, R.id.ll_myself_orderform_delivergoods, "field 'llMyselfOrderformDelivergoods'", LinearLayout.class);
    target.llMyselfOrderformDelivered = Utils.findRequiredViewAsType(source, R.id.ll_myself_orderform_delivered, "field 'llMyselfOrderformDelivered'", LinearLayout.class);
    target.llMyselfOrderformEvaluate = Utils.findRequiredViewAsType(source, R.id.ll_myself_orderform_evaluate, "field 'llMyselfOrderformEvaluate'", LinearLayout.class);
    target.llMyselfOrderformAftersale = Utils.findRequiredViewAsType(source, R.id.ll_myself_orderform_aftersale, "field 'llMyselfOrderformAftersale'", LinearLayout.class);
    target.tvMyselfPropertyTotalassets = Utils.findRequiredViewAsType(source, R.id.tv_myself_property_totalassets, "field 'tvMyselfPropertyTotalassets'", TextView.class);
    target.llMyselfPropertyRedpacket = Utils.findRequiredViewAsType(source, R.id.ll_myself_property_redpacket, "field 'llMyselfPropertyRedpacket'", LinearLayout.class);
    target.llMyselfPropertyRecharge = Utils.findRequiredViewAsType(source, R.id.ll_myself_property_recharge, "field 'llMyselfPropertyRecharge'", LinearLayout.class);
    target.destoonFinanceCharge = Utils.findRequiredViewAsType(source, R.id.ll_myself_property_destoonfinancecharge, "field 'destoonFinanceCharge'", LinearLayout.class);
    target.llMyselfPropertyIntegral = Utils.findRequiredViewAsType(source, R.id.ll_myself_property_integral, "field 'llMyselfPropertyIntegral'", LinearLayout.class);
    target.llMyselfServiceInvitefriends = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_invitefriends, "field 'llMyselfServiceInvitefriends'", LinearLayout.class);
    target.llMyselfServiceEvaluationlist = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_evaluationlist, "field 'llMyselfServiceEvaluationlist'", LinearLayout.class);
    target.llMyselfServiceEnshrine = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_enshrine, "field 'llMyselfServiceEnshrine'", LinearLayout.class);
    target.llMyselfServiceFootprint = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_footprint, "field 'llMyselfServiceFootprint'", LinearLayout.class);
    target.llMyselfServiceLocation = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_location, "field 'llMyselfServiceLocation'", LinearLayout.class);
    target.llMyselfServiceCustomerservice = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_customerservice, "field 'llMyselfServiceCustomerservice'", LinearLayout.class);
    target.llMyselfServiceHelp = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_help, "field 'llMyselfServiceHelp'", LinearLayout.class);
    target.llMyselfServiceStep = Utils.findRequiredViewAsType(source, R.id.ll_myself_service_step, "field 'llMyselfServiceStep'", LinearLayout.class);
    target.llMyselfHeadZoomScrollView = Utils.findRequiredViewAsType(source, R.id.ll_myself_headZoomScrollView, "field 'llMyselfHeadZoomScrollView'", HeadZoomScrollView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.tv_myself_name, "field 'name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivMyselfHeadportrait = null;
    target.rlMyselfHeadportrait = null;
    target.tvMyselfSignin = null;
    target.tvMyselfOrderform = null;
    target.tvMyselfOrderformSeeall = null;
    target.llMyselfOrderformObligation = null;
    target.llMyselfOrderformDelivergoods = null;
    target.llMyselfOrderformDelivered = null;
    target.llMyselfOrderformEvaluate = null;
    target.llMyselfOrderformAftersale = null;
    target.tvMyselfPropertyTotalassets = null;
    target.llMyselfPropertyRedpacket = null;
    target.llMyselfPropertyRecharge = null;
    target.destoonFinanceCharge = null;
    target.llMyselfPropertyIntegral = null;
    target.llMyselfServiceInvitefriends = null;
    target.llMyselfServiceEvaluationlist = null;
    target.llMyselfServiceEnshrine = null;
    target.llMyselfServiceFootprint = null;
    target.llMyselfServiceLocation = null;
    target.llMyselfServiceCustomerservice = null;
    target.llMyselfServiceHelp = null;
    target.llMyselfServiceStep = null;
    target.llMyselfHeadZoomScrollView = null;
    target.name = null;

    this.target = null;
  }
}
