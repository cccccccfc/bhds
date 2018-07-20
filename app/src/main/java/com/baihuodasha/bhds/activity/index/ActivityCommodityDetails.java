package com.baihuodasha.bhds.activity.index;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.SupermarketChoicenessAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.XCRoundImageView;
import com.baihuodasha.bhds.utils.popupwindow.CommercialPopupWindow;
import com.baihuodasha.bhds.utils.popupwindow.SpecificationPopupWindow;
import com.youth.banner.Banner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.baihuodasha.bhds.R.id.tx_commodity_details_evaluate_info;

public class ActivityCommodityDetails extends BaseActivity implements SpecificationPopupWindow.TGClickListener {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_shop) TextView tvBaseShop;
  @BindView(R.id.tv_base_details) TextView tvBaseDetails;
  @BindView(R.id.iv_base_car) ImageView ivBaseCar;
  @BindView(R.id.iv_base_share) ImageView ivBaseShare;
  @BindView(R.id.ba_commodity_details_banner) Banner baCommodityDetailsBanner;
  @BindView(R.id.tv_commodity_details_title) TextView tvCommodityDetailsTitle;
  @BindView(R.id.tv_commodity_details_presentation) TextView tvCommodityDetailsPresentation;
  @BindView(R.id.tv_commodity_details_price) TextView tvCommodityDetailsPrice;
  @BindView(R.id.tv_commodity_details_post) TextView tvCommodityDetailsPost;
  @BindView(R.id.tv_commodity_details_integral) TextView tvCommodityDetailsIntegral;
  @BindView(R.id.ll_commodity_details_presentation) LinearLayout llCommodityDetailsPresentation;
  @BindView(R.id.tv_commodity_details_oldpricetext) TextView tvCommodityDetailsOldpricetext;
  @BindView(R.id.tv_commodity_details_oldprice) TextView tvCommodityDetailsOldprice;
  @BindView(R.id.tv_commodity_details_itemspecifics) TextView tvCommodityDetailsItemspecifics;
  @BindView(R.id.rl_commodity_details_itemspecifics) RelativeLayout rlCommodityDetailsItemspecifics;
  @BindView(R.id.tv_commodity_details_commodityparameter) TextView
      tvCommodityDetailsCommodityparameter;
  @BindView(R.id.rl_commodity_details_commodityparameter) RelativeLayout
      rlCommodityDetailsCommodityparameter;
  @BindView(R.id.tv_commodity_details_service) TextView tvCommodityDetailsService;
  @BindView(R.id.ll_service1) LinearLayout llService1;
  @BindView(R.id.rl_commodity_details_service) RelativeLayout rlCommodityDetailsService;
  @BindView(R.id.tv_commodity_details_evaluate) TextView tvCommodityDetailsEvaluate;  // 用户评价
  @BindView(R.id.tv_commodity_details_evaluate_seeall) TextView tvCommodityDetailsEvaluateSeeall;  // 查看全部评价
  @BindView(R.id.tv_commodity_details_hint) TextView tvCommodityDetailsHint;  // 暂无评论时限时
  @BindView(R.id.rl_commodity_details_evaluate) RelativeLayout rlCommodityDetailsEvaluate;
  @BindView(R.id.rl_commodity_details_info) RelativeLayout rlCommodityDetailsInfo;
  @BindView(R.id.item_tx_title) TextView itemTxTitle;
  @BindView(R.id.rc_commodity_details_choiceness) RecyclerView rcCommodityDetailsChoiceness;
  @BindView(R.id.ScrollInterceptScrollView) com.baihuodasha.bhds.utils.ScrollInterceptScrollView
      ScrollInterceptScrollView;
  @BindView(R.id.tv_commodity_details_menus_service) TextView tvCommodityDetailsMenusService; // 客服
  @BindView(R.id.tv_commodity_details_menus_collect) TextView tvCommodityDetailsMenusCollect;  // 收藏
  @BindView(R.id.shoping_selector) LinearLayout shopingSelector;
  @BindView(R.id.tv_commodity_details_menus_buy) TextView tvCommodityDetailsMenusBuy;  // 购买
  @BindView(R.id.tv_commodity_details_menus_add) TextView tvCommodityDetailsMenusAdd;  //加入购物车
  @BindView(R.id.rl_commodity_details_menus) RelativeLayout rlCommodityDetailsMenus;
  @BindView(R.id.iv_commodity_details_evaluate_more) ImageView rlCommodityDetailsEvaluateMore;  // 评论图片超过三张
  @BindView(R.id.iv_commodity_details_evaluate_headportrait) XCRoundImageView
      ivCommodityDetailsEvaluateHeadportrait;   // 评论用户头像
  @BindView(R.id.tx_commodity_details_evaluate_name) TextView txCommodityDetailsEvaluateName;  //用户名
  @BindView(R.id.iv_commodity_details_evaluate_xing1) ImageView ivCommodityDetailsEvaluateXing1; // 评论星
  @BindView(R.id.iv_commodity_details_evaluate_xing2) ImageView ivCommodityDetailsEvaluateXing2;
  @BindView(R.id.iv_commodity_details_evaluate_xing3) ImageView ivCommodityDetailsEvaluateXing3;
  @BindView(R.id.iv_commodity_details_evaluate_xing4) ImageView ivCommodityDetailsEvaluateXing4;
  @BindView(R.id.iv_commodity_details_evaluate_xing5) ImageView ivCommodityDetailsEvaluateXing5;
  @BindView(R.id.rl_myself_headportrait) LinearLayout rlMyselfHeadportrait; // 评论头像
  @BindView(tx_commodity_details_evaluate_info) TextView txCommodityDetailsEvaluateInfo;  //评论详情
  @BindView(R.id.ll_commodity_details_evaluate_img) LinearLayout llCommodityDetailsEvaluateImg; //评论图片
  private SupermarketChoicenessAdapter choicenessAdapter;
  private int y;
  private SpecificationPopupWindow specificationPopupWindow;
  private CommercialPopupWindow commercialPopupWindow;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_commodity_details);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseShop.setOnClickListener(this);
    tvBaseDetails.setOnClickListener(this);
    ivBaseCar.setOnClickListener(this);
    ivBaseShare.setOnClickListener(this);
    tvBaseShop.setEnabled(false);
    tvBaseDetails.setEnabled(true);
    tvCommodityDetailsMenusCollect.setOnClickListener(this);
    tvCommodityDetailsMenusBuy.setOnClickListener(this);
    tvCommodityDetailsMenusAdd.setOnClickListener(this);
    tvCommodityDetailsMenusService.setOnClickListener(this);
    tvCommodityDetailsEvaluateSeeall.setOnClickListener(this);
    rlCommodityDetailsItemspecifics.setOnClickListener(this);
    rlCommodityDetailsCommodityparameter.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  private String[] BannerImage;

  @Override public void dealLogicAfterInitView() {
    BannerImage = Config.Bannerhomeimages;
    ArrayList<String> imageList = new ArrayList<>();
    for (int i = 0; i < BannerImage.length; i++) {
      imageList.add(BannerImage[i]);
    }
    //设置图片加载器
    baCommodityDetailsBanner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    baCommodityDetailsBanner.setImages(imageList);
    //banner设置方法全部调用完毕时最后调用
    baCommodityDetailsBanner.start();
    //推荐商品
    LinearLayoutManager linearLayout = new GridLayoutManager(this, 3);
    linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
    rcCommodityDetailsChoiceness.setLayoutManager(linearLayout);
    choicenessAdapter = new SupermarketChoicenessAdapter(this, null);
    rcCommodityDetailsChoiceness.setAdapter(choicenessAdapter);
    setList();
    choicenessAdapter.setOnItemClickListener(
        new SupermarketChoicenessAdapter.OnItemClickListener() {
          @Override public void onClick(int v, String position) {
            CommonUtils.toastMessage("点击了" + position);
          }
        });

    specificationPopupWindow = new SpecificationPopupWindow(null, this,null,100, null ,this);
    commercialPopupWindow = new CommercialPopupWindow(null, this,null,100, null);
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
      case R.id.tv_base_shop:
        tvBaseShop.setEnabled(false);
        tvBaseDetails.setEnabled(true);
        ScrollInterceptScrollView.post(new Runnable() {
          @Override public void run() {
            ScrollInterceptScrollView.fullScroll(ScrollView.FOCUS_UP);
          }
        });
        break;
      case R.id.tv_base_details:
        tvBaseShop.setEnabled(true);
        tvBaseDetails.setEnabled(false);
        ;
        ScrollInterceptScrollView.post(new Runnable() {
          @Override public void run() {
            ScrollInterceptScrollView.smoothScrollTo(0, y);
          }
        });
        // scrollToPosition();
        break;
      case R.id.iv_base_car:

        break;
      case R.id.iv_base_share:

        break;
      case R.id.rl_commodity_details_commodityparameter:
        CommonUtils.toastMessage("查看商品参数");
        showCategory(2);

        break;
      case R.id.rl_commodity_details_itemspecifics:
        CommonUtils.toastMessage("选择商品规格");
        showCategory(1);
        break;
      case R.id.tx_commodity_details_evaluate_info:
      case R.id.iv_commodity_details_evaluate_more:
      case R.id.tv_commodity_details_evaluate_seeall:
        CommonUtils.toastMessage("查看更多评价");
        Intent intent =new Intent(this ,ActivityCommentDetails.class);
        startActivity(intent);
        break;
      case R.id.tv_commodity_details_menus_service:
        CommonUtils.toastMessage("客服");
        break;
      case R.id.tv_commodity_details_menus_collect:
        CommonUtils.toastMessage("收藏");
        if (tvCommodityDetailsMenusCollect.getText().toString().equals("收藏")) {
          tvCommodityDetailsMenusCollect.setText("已收藏");
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvCommodityDetailsMenusCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.mipmap.collection_un, null), null, null);
          }
          // tvCommodityDetailsMenusCollect.setTextColor(getResources().getColor(R.color.colorText2));
        } else {
          tvCommodityDetailsMenusCollect.setText("收藏");
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvCommodityDetailsMenusCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.mipmap.collection_up, null), null, null);
          }
        }
        break;
      case R.id.tv_commodity_details_menus_buy:
        CommonUtils.toastMessage("购买");
        break;
      case R.id.tv_commodity_details_menus_add:
        CommonUtils.toastMessage("添加到购物车");
        break;
    }
  }

  private List<String> price2 = new ArrayList<>();

  private void setList() {
    price2.clear();
    for (int i = 0; i < 14; i++) {
      price2.add(i + "测试");
    }
  //  choicenessAdapter.addList(price2);
  }

  private Handler handler = new Handler();

  /**
   * 滑动到指定位置
   */
  private void scrollToPosition() {
    handler.post(new Runnable() {
      @Override public void run() {
        ScrollInterceptScrollView.smoothScrollTo(0, y);
      }
    });
  }

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    y = rlCommodityDetailsInfo.getTop() + tvCommodityDetailsOldprice.getTop();
  }

  @Override public void onWork(int flag) {

  }
  /**
   * 显示购物车
   */
  private void showCategory(int type) {
    RotateAnimation rotateAnimation = new RotateAnimation(0, 90,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
        0.5f);
    rotateAnimation.setDuration(300);
    rotateAnimation.setFillAfter(true);
    // imgbut_left.setImageResource(R.drawable.menu_left_02);
    // // //显示窗口  // baCommodityDetailsBanner
    if (type ==1) {
      specificationPopupWindow.showAtLocation(rlCommodityDetailsMenus, Gravity.BOTTOM, 0, 0);
      backgroundAlpha(0.5f);
      specificationPopupWindow
          .setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
              backgroundAlpha(1f);
            }
          });
    }else {
      commercialPopupWindow.showAtLocation(rlCommodityDetailsMenus, Gravity.BOTTOM, 0, 0);
      backgroundAlpha(0.5f);
      commercialPopupWindow
          .setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
              backgroundAlpha(1f);

            }
          });
    }

  }

  /**
   * 设置添加屏幕的背景透明度
   *
   * @param bgAlpha
   */
  public void backgroundAlpha(float bgAlpha) {
    WindowManager.LayoutParams lp = getWindow().getAttributes();
    lp.alpha = bgAlpha; // 0.0-1.0
    getWindow().setAttributes(lp);
  }
}
