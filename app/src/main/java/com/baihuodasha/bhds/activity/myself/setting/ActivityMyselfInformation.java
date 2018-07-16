package com.baihuodasha.bhds.activity.myself.setting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.view.SelectPicPopupWindow;
import com.baihuodasha.bhds.view.SelectSexPopupWindow;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.baihuodasha.bhds.utils.DateUtil.parseDateToString;

public class ActivityMyselfInformation extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.iv_myself_info_headimg) ImageView ivMyselfInfoHeadimg;
  @BindView(R.id.rl_myself_info_headimg) RelativeLayout rlMyselfInfoHeadimg;
  @BindView(R.id.tv_myself_info_name) TextView tvMyselfInfoName;
  @BindView(R.id.et_myself_info_name) EditText etMyselfInfoName;
  @BindView(R.id.tv_myself_info_sex) TextView tvMyselfInfoSex;
  @BindView(R.id.et_myself_info_sex) TextView etMyselfInfoSex;
  @BindView(R.id.tv_myself_info_birthday) TextView tvMyselfInfoBirthday;
  @BindView(R.id.et_myself_info_birthday) TextView etMyselfInfoBirthday;
  @BindView(R.id.tv_myself_info_email) TextView tvMyselfInfoEmail;
  @BindView(R.id.et_myself_info_email) EditText etMyselfInfoEmail;
  @BindView(R.id.activity_main) LinearLayout activityMain;
  @BindView(R.id.rl_myself_info_birthday) RelativeLayout rlMyselfInfoBirthday;
  @BindView(R.id.rl_myself_info_sex) RelativeLayout rlMyselfInfoSex;
  private SelectPicPopupWindow mPopupWindow;
  private OptionsPickerView pvCustomOptions;
  private boolean PopupWindowShowing = true;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_myself_info);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    tvBaseSave.setVisibility(View.VISIBLE);
    ivBaseBack.setOnClickListener(this);
    tvBaseSave.setOnClickListener(this);
    tvBaseTitle.setText("个人信息编辑页面");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    rlMyselfInfoHeadimg.setOnClickListener(this);
    rlMyselfInfoBirthday.setOnClickListener(this);
    rlMyselfInfoSex.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {
    Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
    if (cacheBitmap != null) {
      byte[] bytes = CommonUtils.getBitMapByteArray(cacheBitmap);
      Glide.with(this)
          .load(bytes)
          .placeholder(R.drawable.defaultavatar)
          .bitmapTransform(new CropCircleTransformation(this))
          .into(ivMyselfInfoHeadimg);
    } else {
      Glide.with(this)
          .load(
              "http://test2.baihuodasha.com/mobile/themesmobile/68ecshopcom_mobile/css/gaiban/img/User_center/headp.png")
          .placeholder(R.drawable.defaultavatar)
          .bitmapTransform(new CropCircleTransformation(this))
          .into(ivMyselfInfoHeadimg);
    }
    food.add("男");
    food.add("女");
    initCustomOptionPicker();
  }

  @Override public void onClickEvent(View view) throws ParseException {
    if (mPopupWindow != null) {
      mPopupWindow.dismiss();
    }

    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
      case R.id.tv_base_save:

        break;
      case R.id.rl_myself_info_sex:
        pvCustomOptions.show();

        break;
      case R.id.rl_myself_info_headimg:
        Log.i("qaz", "onClickEvent: "+PopupWindowShowing);
        if (PopupWindowShowing) {
          mPopupWindow = getPicPopupWindow(this, this, activityMain);
        }else {
          mPopupWindow.dismiss();
          PopupWindowShowing =true;
        }
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
          @Override public void onDismiss() {
            setBackgroundAlpha(1.0f);
          }
        });
        break;
      case R.id.rl_myself_info_birthday:
        showPickerView();
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
      //case R.id.bt_fale_man://男
      //  etMyselfInfoSex.setText("男");
      //
      //  break;
      //case R.id.bt_fale_woman://女
      //  etMyselfInfoSex.setText("女");
      //  break;
    }
  }

  private void showPickerView() {// 弹出选择器
    //时间选择器
    TimePickerView pvTime =
        new TimePickerBuilder(ActivityMyselfInformation.this, new OnTimeSelectListener() {
          @Override public void onTimeSelect(Date date, View v) {
            etMyselfInfoBirthday.setText(parseDateToString(date, "yyyy-MM-dd"));
          }
        }).setTitleText("选择出生日期").setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setSubmitColor(R.color.blackText)//确定按钮文字颜色
            .setCancelText("").setCancelColor(R.color.blackText)//取消按钮文字颜色
            .setSubCalSize(18)//确定和取消文字大小
            .build();
    pvTime.show();
  }

  public SelectPicPopupWindow getPicPopupWindow(Context context, View.OnClickListener itemsOnClick,
      View viewAttach) {
    //实例化SelectPicPopupWindow
    PopupWindowShowing =false;
    setBackgroundAlpha(0.5f);
    SelectPicPopupWindow menuWindow = new SelectPicPopupWindow(context, itemsOnClick);
    //显示窗口,设置layout在PopupWindow中显示的位置
    menuWindow.showAtLocation(viewAttach, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    return menuWindow;
  }

  public SelectSexPopupWindow getSexPopupWindow(Context context, View.OnClickListener itemsOnClick,
      View viewAttach) {
    //实例化SelectPicPopupWindow
    setBackgroundAlpha(0.5f);
    SelectSexPopupWindow menuWindow = new SelectSexPopupWindow(context, itemsOnClick);
    //显示窗口,设置layout在PopupWindow中显示的位置
    menuWindow.showAtLocation(viewAttach, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    return menuWindow;
  }

  private void setBackgroundAlpha(float v) {
    WindowManager.LayoutParams lp = getWindow().getAttributes();
    lp.alpha = v;
    getWindow().setAttributes(lp);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            Log.i("qaz", "onActivityResult2: " + zoomBitMap);
            Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
            byte[] bytes = CommonUtils.getBitMapByteArray(cacheBitmap);

            Glide.with(this)
                .load(bytes)
                .placeholder(R.drawable.defaultavatar)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(ivMyselfInfoHeadimg);
          } else {
            //本地图片显示
            Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
            byte[] bytes = CommonUtils.getBitMapByteArray(cacheBitmap);
            Log.i("qaz", "onActivityResult1: " + bytes);

            Glide.with(this)
                .load(bytes)
                .placeholder(R.drawable.defaultavatar)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(ivMyselfInfoHeadimg);
          }
          break;
      }
    }
  }

  private ArrayList<String> food = new ArrayList<>();

  private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
    /**
     * @description
     *
     * 注意事项：
     * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
     * 具体可参考demo 里面的两个自定义layout布局。
     */
    pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
      @Override public void onOptionsSelect(int options1, int option2, int options3, View v) {
        //返回的分别是三个级别的选中位置
        String tx = food.get(options1);
        etMyselfInfoSex.setText(tx);
      }
    }).setLayoutRes(R.layout.activity_dialog_fale, new CustomListener() {
      @Override public void customLayout(View v) {
        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            pvCustomOptions.returnData();
            pvCustomOptions.dismiss();
          }
        });
      }
    }).isDialog(false).build();

    pvCustomOptions.setPicker(food);//添加数据
  }
}
