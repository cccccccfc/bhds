package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ActivityCommodityEvaluation;
import com.baihuodasha.bhds.activity.login.ActivityLogin;
import com.baihuodasha.bhds.activity.myself.ActivityMyRedPackets;
import com.baihuodasha.bhds.activity.myself.receiveraddress.ActivityReceiverAddress;
import com.baihuodasha.bhds.activity.myself.setting.ActivityMyselfInformation;
import com.baihuodasha.bhds.activity.myself.setting.ActivitySetting;
import com.baihuodasha.bhds.activity.order.ActivityOrderConfirmation;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.bean.MainUserInfoModel;
import com.baihuodasha.bhds.bean.Msg;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.net.SharePrefHelper;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.HeadZoomScrollView;
import com.baihuodasha.bhds.view.SelectPicPopupWindow;
import com.bumptech.glide.Glide;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author IMXU
 * @time 2017/5/3 13:21
 * @des 资讯首页
 * 邮箱：butterfly_xu@sina.com
 */
public class FragmentMyself extends BaseFragment implements View.OnClickListener {
  @BindView(R.id.iv_myself_headportrait) ImageView ivMyselfHeadportrait;
  @BindView(R.id.rl_myself_headportrait) RelativeLayout rlMyselfHeadportrait;
  @BindView(R.id.tv_myself_signin) TextView tvMyselfSignin;
  @BindView(R.id.tv_myself_orderform) TextView tvMyselfOrderform;
  @BindView(R.id.tv_myself_orderform_seeall) TextView tvMyselfOrderformSeeall;
  @BindView(R.id.ll_myself_orderform_obligation) LinearLayout llMyselfOrderformObligation;
  @BindView(R.id.ll_myself_orderform_delivergoods) LinearLayout llMyselfOrderformDelivergoods;
  @BindView(R.id.ll_myself_orderform_delivered) LinearLayout llMyselfOrderformDelivered;
  @BindView(R.id.ll_myself_orderform_evaluate) LinearLayout llMyselfOrderformEvaluate;
  @BindView(R.id.ll_myself_orderform_aftersale) LinearLayout llMyselfOrderformAftersale;
  @BindView(R.id.tv_myself_property_totalassets) TextView tvMyselfPropertyTotalassets;
  @BindView(R.id.ll_myself_property_redpacket) LinearLayout llMyselfPropertyRedpacket;
  @BindView(R.id.ll_myself_property_recharge) LinearLayout llMyselfPropertyRecharge;
  @BindView(R.id.ll_myself_property_destoonfinancecharge) LinearLayout destoonFinanceCharge;
  @BindView(R.id.ll_myself_property_integral) LinearLayout llMyselfPropertyIntegral;
  @BindView(R.id.ll_myself_service_invitefriends) LinearLayout llMyselfServiceInvitefriends;
  @BindView(R.id.ll_myself_service_evaluationlist) LinearLayout llMyselfServiceEvaluationlist;
  @BindView(R.id.ll_myself_service_enshrine) LinearLayout llMyselfServiceEnshrine;
  @BindView(R.id.ll_myself_service_footprint) LinearLayout llMyselfServiceFootprint;
  @BindView(R.id.ll_myself_service_location) LinearLayout llMyselfServiceLocation;
  @BindView(R.id.ll_myself_service_customerservice) LinearLayout llMyselfServiceCustomerservice;
  @BindView(R.id.ll_myself_service_help) LinearLayout llMyselfServiceHelp;
  @BindView(R.id.ll_myself_service_step) LinearLayout llMyselfServiceStep;
  @BindView(R.id.ll_myself_headZoomScrollView) HeadZoomScrollView llMyselfHeadZoomScrollView;
  private View view;
  @BindView(R.id.tv_myself_name) TextView name;//设置或昵称

  private static final int LOGIN = 101;//去登陆
  private static final int EDITINFOR = 102;//去编辑个人信息

  private SelectPicPopupWindow mPopupWindow;
  SharePrefHelper mSh;
  private String heading;

  @Override public View initView(LayoutInflater inflater) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_myself, null);
    }
    ViewGroup parent = (ViewGroup) view.getParent();
    if (parent != null) {
      parent.removeView(view);
    }

    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void init() {
    super.init();

    mSh = SharePrefHelper.getInstance();
    mSh.setLoginSuccess(false);
    if (mSh.getLoginSuccess()) {

    }
    name.setText(mSh.getUserName());
  }

  @Override public void initdata() {
    super.initdata();
    getIcon();
    sendUserInfo();
  }

  @Override public void initListener() {
    tvMyselfSignin.setOnClickListener(this);
    rlMyselfHeadportrait.setOnClickListener(this);
    name.setOnClickListener(this);
    tvMyselfOrderformSeeall.setOnClickListener(this);
    llMyselfOrderformObligation.setOnClickListener(this);
    llMyselfOrderformObligation.setOnClickListener(this);
    llMyselfOrderformDelivergoods.setOnClickListener(this);
    llMyselfOrderformDelivered.setOnClickListener(this);
    llMyselfOrderformEvaluate.setOnClickListener(this);
    llMyselfOrderformAftersale.setOnClickListener(this);
    tvMyselfPropertyTotalassets.setOnClickListener(this);
    llMyselfPropertyRedpacket.setOnClickListener(this);
    llMyselfPropertyRecharge.setOnClickListener(this);
    destoonFinanceCharge.setOnClickListener(this);
    llMyselfPropertyIntegral.setOnClickListener(this);
    llMyselfServiceInvitefriends.setOnClickListener(this);
    llMyselfServiceEvaluationlist.setOnClickListener(this);
    llMyselfServiceEnshrine.setOnClickListener(this);
    llMyselfServiceFootprint.setOnClickListener(this);
    llMyselfServiceLocation.setOnClickListener(this);
    llMyselfServiceCustomerservice.setOnClickListener(this);
    llMyselfServiceHelp.setOnClickListener(this);
    llMyselfServiceStep.setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    if (mPopupWindow != null) {
      mPopupWindow.dismiss();
    }
    switch (v.getId()) {
      case R.id.rl_myself_headportrait:
        Intent intent = new Intent(getActivity(), ActivityMyselfInformation.class);
        startActivityForResult(intent, EDITINFOR);
        getActivity().overridePendingTransition(0, 0);
        break;
      case R.id.tv_myself_name:
        //判断是否登录，是则进入个人界面，否则进入登录界面
        if (mSh.getLoginSuccess()) {
          Intent intent1 = new Intent(getActivity(), ActivityMyselfInformation.class);
          startActivityForResult(intent1, EDITINFOR);
          getActivity().overridePendingTransition(0, 0);
        } else {
          Intent intent2 = new Intent(getActivity(), ActivityLogin.class);
          startActivity(intent2);
          getActivity().overridePendingTransition(0, 0);
          getActivity().finish();
        }
        break;
      case R.id.tv_myself_signin:

        break;

      case R.id.tv_myself_orderform_seeall://查看全部订单
        CommonUtils.toastMessage("查看全部订单");
        break;
      case R.id.ll_myself_orderform_obligation://待付款
        CommonUtils.toastMessage("待付款");
        break;
      case R.id.ll_myself_orderform_delivergoods://待发货
        CommonUtils.toastMessage("待发货");
        break;
      case R.id.ll_myself_orderform_delivered://已发货
        CommonUtils.toastMessage("已发货");
        break;
      case R.id.ll_myself_orderform_evaluate://待评价
        CommonUtils.toastMessage("待评价");
        break;
      case R.id.ll_myself_orderform_aftersale://售后
        CommonUtils.toastMessage("售后");
        break;
      case R.id.tv_myself_property_totalassets://查看全部资产
        CommonUtils.toastMessage("查看全部资产");
        break;
      case R.id.ll_myself_property_redpacket://红包
        Intent intentred = new Intent(getActivity(), ActivityMyRedPackets.class);
        startActivity(intentred);
        CommonUtils.toastMessage("红包");
        break;
      case R.id.ll_myself_property_destoonfinancecharge://充值
        CommonUtils.toastMessage("充值");
        break;
      case R.id.ll_myself_property_recharge://e卡充值
        CommonUtils.toastMessage("e卡充值");
        break;
      case R.id.ll_myself_property_integral://我的积分
        CommonUtils.toastMessage("我的积分");
        break;
      case R.id.ll_myself_service_invitefriends://邀请好友
        CommonUtils.toastMessage("邀请好友");
        break;
      case R.id.ll_myself_service_evaluationlist://晒单评价
        CommonUtils.toastMessage("晒单评价");
        break;
      case R.id.ll_myself_service_enshrine://收藏
        CommonUtils.toastMessage("收藏");
        break;
      case R.id.ll_myself_service_footprint://足迹ActivityOrderConfirmation
        CommonUtils.toastMessage("足迹");
        Intent intentfoot = new Intent(getActivity(), ActivityOrderConfirmation.class);
        startActivity(intentfoot);
        break;
      case R.id.ll_myself_service_location://地址
        Intent intentadd = new Intent(getActivity(), ActivityReceiverAddress.class);
        startActivity(intentadd);
        CommonUtils.toastMessage("地址");
        break;
      case R.id.ll_myself_service_customerservice://客服ActivityCommodityEvaluation
        Intent intentser = new Intent(getActivity(), ActivityCommodityEvaluation.class);
        startActivity(intentser);
        CommonUtils.toastMessage("客服");
        break;
      case R.id.ll_myself_service_help://帮助
        if (mSh.getLoginSuccess()) {
          //  WebViewActivity.start(getActivity(), "帮助中心", "http://www.baihuodasha.com/mobile/user.php?act=aboutus");
        } else {
          Intent help = new Intent(getActivity(), ActivityLogin.class);
          startActivity(help);
        }
        CommonUtils.toastMessage("帮助");
        break;
      case R.id.ll_myself_service_step: //设置
        Intent intentset = new Intent(getActivity(), ActivitySetting.class);
        startActivity(intentset);
        // CommonUtils.toastMessage("设置");
        break;
    }
  }

  private String picID = "";

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override public void onResume() {
    super.onResume();
    getIcon();
    //判断是否是登录状态
    //if (StringUtil.isNullOrEmpty(mSh.getUserId()) || StringUtil.isNullOrEmpty(mSh.getUserToken())) {
    //  name.setText("注册/登陆");
    //} else {
    //  name.setText("百货大厦");
    //}
    name.setText(mSh.getUserName());
  }

  private void sendUserInfo() {
    SdjNetWorkManager.sendUserInfo(new Callback() {
      @Override public void onResponse(Call call, Response response) {
        Msg msg = (Msg) response.body();
        if (msg != null) {
          MainUserInfoModel bean = (MainUserInfoModel) msg.getData();
          Log.i("qaz", "onResponse: " + bean.toString());
          if (bean.getHeadimg() != null) {
            heading = bean.getHeadimg();
          } else {
            heading =
                "http://test2.baihuodasha.com/mobile/themesmobile/68ecshopcom_mobile/css/gaiban/img/User_center/headp.png";
          }
          Glide.with(getActivity())
              .load(heading)
              .placeholder(R.drawable.defaultavatar)
              .bitmapTransform(new CropCircleTransformation(getActivity()))
              .into(ivMyselfHeadportrait);
          mSh.setUserName(bean.getNick_name());
          //  name.setText(bean.getNick_name());
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        Log.i("qaz", "onFailure: " + t);
      }
    });
  }

  private void getIcon() {
    Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
    if (cacheBitmap != null) {
      byte[] bytes = CommonUtils.getBitMapByteArray(cacheBitmap);
      Glide.with(this)
          .load(bytes)
          .placeholder(R.drawable.defaultavatar)
          .bitmapTransform(new CropCircleTransformation(getActivity()))
          .into(ivMyselfHeadportrait);
    } else {
      Glide.with(this)
          .load(
              "http://test2.baihuodasha.com/mobile/themesmobile/68ecshopcom_mobile/css/gaiban/img/User_center/headp.png")
          .placeholder(R.drawable.defaultavatar)
          .bitmapTransform(new CropCircleTransformation(getActivity()))
          .into(ivMyselfHeadportrait);
    }
  }
}