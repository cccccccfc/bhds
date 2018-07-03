package com.baihuodasha.bhds.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
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
import com.baihuodasha.bhds.activity.index.ActivityCommodityDetails;
import com.baihuodasha.bhds.activity.login.ActivityLogin;
import com.baihuodasha.bhds.activity.myself.receiveraddress.ActivityReceiverAddress;
import com.baihuodasha.bhds.activity.myself.setting.ActivityMyselfInformation;
import com.baihuodasha.bhds.activity.myself.setting.ActivitySetting;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.Constants;
import com.baihuodasha.bhds.utils.HeadZoomScrollView;
import com.baihuodasha.bhds.utils.SpTools;
import com.baihuodasha.bhds.view.SelectPicPopupWindow;
import com.base.utilslibrary.internet.PersonalInternetRequestUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.ByteArrayOutputStream;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.app.Activity.RESULT_OK;

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
  }

  @Override public void initdata() {
    super.initdata();
    CommonUtils.deleteBitmap("myicons.jpg");
    Bitmap cacheBitmap = CommonUtils.getCacheFile("myicons.jpg");
    if (cacheBitmap != null) {
      byte[] bytes = CommonUtils.getBitMapByteArray(cacheBitmap);

      Glide.with(getActivity())
          .load(bytes)
          .placeholder(R.drawable.defaultavatar)
          .bitmapTransform(new CropCircleTransformation(getActivity()))
          .into(ivMyselfHeadportrait);
    }
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
      case R.id.tv_myself_signin:
        //判断是否登录，是则进入个人界面，否则进入登录界面
        if (SpTools.getBoolean(CommonUtils.getContext(), Constants.isLogin, false)) {

        } else {
          Intent intent1 = new Intent(getActivity(), ActivityLogin.class);
          startActivityForResult(intent1, LOGIN);
          getActivity().overridePendingTransition(0, 0);
        }
        break;

      case R.id.btn_pick_photo://本地
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .selectionMode(PictureConfig.SINGLE)//多选 or 单选
            .enableCrop(true)//是否裁剪
            .compress(true)//是否压缩
            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
            .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
            //系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
            .previewImage(false)// 是否可预览图片
            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        break;
      case R.id.btn_take_photo://拍照
        PictureSelector.create(this)
            .openCamera(PictureMimeType.ofImage())
            .selectionMode(PictureConfig.SINGLE)//多选 or 单选
            .enableCrop(true)//是否裁剪
            .compress(true)//是否压缩
            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
            .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
            .previewImage(false)// 是否可预览图片
            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        break;
      case R.id.tv_myself_orderform_seeall  ://查看全部订单
        CommonUtils.toastMessage("查看全部订单");
        break;
      case R.id.ll_myself_orderform_obligation ://待付款
        CommonUtils.toastMessage("待付款");
        break;
      case R.id.ll_myself_orderform_delivergoods ://待发货
        CommonUtils.toastMessage("待发货");
        break;
      case R.id.ll_myself_orderform_delivered ://已发货
        CommonUtils.toastMessage("已发货");
        break;
      case R.id.ll_myself_orderform_evaluate ://待评价
        CommonUtils.toastMessage("待评价");
        break;
      case R.id.ll_myself_orderform_aftersale  ://售后
        CommonUtils.toastMessage("售后");
        break;
      case R.id.tv_myself_property_totalassets ://查看全部资产
        CommonUtils.toastMessage("查看全部资产");
        break;
      case R.id.ll_myself_property_redpacket ://红包
        CommonUtils.toastMessage("红包");
        break;
      case R.id.ll_myself_property_destoonfinancecharge ://充值
        CommonUtils.toastMessage("充值");
        break;
      case R.id.ll_myself_property_recharge  ://e卡充值
        CommonUtils.toastMessage("e卡充值");
        break;
      case R.id.ll_myself_property_integral  ://我的积分
        CommonUtils.toastMessage("我的积分");
        break;
      case R.id.ll_myself_service_invitefriends  ://邀请好友
        CommonUtils.toastMessage("邀请好友");
        break;
      case R.id.ll_myself_service_evaluationlist  ://晒单评价
        CommonUtils.toastMessage("晒单评价");
        break;
      case R.id.ll_myself_service_enshrine ://收藏
        CommonUtils.toastMessage("收藏");
        break;
      case R.id.ll_myself_service_footprint  ://足迹
        CommonUtils.toastMessage("足迹");
        break;
      case R.id.ll_myself_service_location  ://地址
        Intent intentadd =new Intent(getActivity() , ActivityReceiverAddress.class);
        startActivity(intentadd);
        CommonUtils.toastMessage("地址");
        break;
      case R.id.ll_myself_service_customerservice  ://客服
        CommonUtils.toastMessage("客服");
        break;
      case R.id.ll_myself_service_help ://帮助
        Intent intenthelp =new Intent(getActivity() , ActivityCommodityDetails.class);
        startActivity(intenthelp);
      //  CommonUtils.toastMessage("帮助");
        break;
      case R.id.ll_myself_service_step : //设置
        Intent intentset =new Intent(getActivity() , ActivitySetting.class);
        startActivity(intentset);
       // CommonUtils.toastMessage("设置");
        break;


    }
  }

  public SelectPicPopupWindow getPicPopupWindow(Context context, View.OnClickListener itemsOnClick,
      View viewAttach) {
    //实例化SelectPicPopupWindow
    SelectPicPopupWindow menuWindow = new SelectPicPopupWindow(context, itemsOnClick);
    //显示窗口,设置layout在PopupWindow中显示的位置
    menuWindow.showAtLocation(viewAttach, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    return menuWindow;
  }

  private String picID = "";

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case PictureConfig.CHOOSE_REQUEST:
          // 图片选择结果回调
          LocalMedia media = PictureSelector.obtainMultipleResult(data).get(0);
          // 例如 LocalMedia 里面返回三种path
          // 1.media.getPath(); 为原图path
          // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
          // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
          // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
          final Bitmap zoomBitMap = CommonUtils.getBitmap(media.getCompressPath());

          if (zoomBitMap != null) {
            //先保持到本地，在传到服务器
            CommonUtils.saveBitmapFile(zoomBitMap, "myicon.jpg");//先保存文件到本地
         //   Log.i("qaz", "onActivityResult2: " + zoomBitMap);
            PersonalInternetRequestUtils.uplodePicture(getActivity(), "ub_id", "myicon.jpg",
                new PersonalInternetRequestUtils.ForResultListener() {
                  @Override public void onResponseMessage(String code) {
                    if (TextUtils.isEmpty(code)) {
                      return;
                    } else {
                      picID = code;
                      ByteArrayOutputStream baos = new ByteArrayOutputStream();
                      zoomBitMap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                      byte[] bytes = baos.toByteArray();
                      Glide.with(getActivity())
                          .load(bytes)
                          .placeholder(R.drawable.defaultavatar)
                          .bitmapTransform(new CropCircleTransformation(getActivity()))
                          .into(ivMyselfHeadportrait);
                    }
                  }
                });
          } else {
            //本地图片显示
            Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
            byte[] bytes = CommonUtils.getBitMapByteArray(cacheBitmap);
           // Log.i("qaz", "onActivityResult1: " + bytes);
            Glide.with(getActivity())
                .load(bytes)
                .placeholder(R.drawable.defaultavatar)
                .bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(ivMyselfHeadportrait);
          }
          break;
      }
    }
  }

  @Override public void onResume() {

    super.onResume();
  }

}