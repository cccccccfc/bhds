package com.baihuodasha.bhds.activity.order;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.OrderConfirmaitonListAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ShopProduct;
import com.baihuodasha.bhds.utils.DoubleUtil;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单确认
 */
public class ActivityOrderConfirmation extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.tv_ocm_address_name) TextView tvOcmAddressName;
  @BindView(R.id.tv_ocm_address_phone) TextView tvOcmAddressPhone;
  @BindView(R.id.tv_ocm_address_info) TextView tvOcmAddressInfo;
  @BindView(R.id.rl_ocm_address_info) RelativeLayout rlOcmAddressInfo;
  @BindView(R.id.rv_ocm_shop_list) RecyclerView rvOcmShopList;
  @BindView(R.id.tv_ocm_pop_redpacket_text) TextView tvOcmPopRedpacketText;
  @BindView(R.id.tv_ocm_pop_redpacket_more) ImageView tvOcmPopRedpacketMore;
  @BindView(R.id.rl_ocm_pop_redpacket) RelativeLayout rlOcmPopRedpacket;
  @BindView(R.id.tv_ocm_pop_deliverytime_text) TextView tvOcmPopDeliverytimeText;
  @BindView(R.id.tv_ocm_pop_deliverytime_more) ImageView tvOcmPopDeliverytimeMore;
  @BindView(R.id.rl_ocm_pop_deliverytime) RelativeLayout rlOcmPopDeliverytime;
  @BindView(R.id.tv_ocm_pop_stockout_text) TextView tvOcmPopStockoutText;
  @BindView(R.id.tv_ocm_pop_stockout_more) ImageView tvOcmPopStockoutMore;
  @BindView(R.id.rl_ocm_pop_stockout) RelativeLayout rlOcmPopStockout;
  @BindView(R.id.et_ocm_pop_remarks_edit) EditText etOcmPopRemarksEdit;
  @BindView(R.id.rl_ocm_pop_remarks) RelativeLayout rlOcmPopRemarks;
  @BindView(R.id.et_ocm_info_price) TextView etOcmInfoPrice;
  @BindView(R.id.et_ocm_info_carriage) TextView etOcmInfoCarriage;
  @BindView(R.id.et_ocm_redpacket) TextView etOcmRedpacket;
  @BindView(R.id.et_ocm_perch) TextView etOcmPerch;
  @BindView(R.id.ss_ocm) ScrollInterceptScrollView ssOcm;
  @BindView(R.id.shopping) TextView shopping;
  @BindView(R.id.tv_ocm_confirm_price) TextView tvOcmConfirmPrice;
  @BindView(R.id.tv_ocm_confirm_button) TextView tvOcmConfirmButton;
  @BindView(R.id.shoping_car) LinearLayout shopingCar;
  private OrderConfirmaitonListAdapter orderConfirmaitonListAdapter;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_order_confirmation);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("确认订单");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    rlOcmAddressInfo.setOnClickListener(this);  // 修改地址
    rlOcmPopRedpacket.setOnClickListener(this);  // 使用红包
    rlOcmPopDeliverytime.setOnClickListener(this);  // 送达时间
    rlOcmPopStockout.setOnClickListener(this);  // 缺货处理
    rlOcmPopRemarks.setOnClickListener(this);  // 备注
    tvOcmConfirmButton.setOnClickListener(this); //确认支付
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {
    initRedPacketOptionPicker();
    initDeliveryTimeOptionPicker();
    initSockOutOptionPicker();
    LinearLayoutManager linearLayoutF = new LinearLayoutManager(this);
    linearLayoutF.setOrientation(LinearLayoutManager.VERTICAL);
    rvOcmShopList.setLayoutManager(linearLayoutF);
    orderConfirmaitonListAdapter = new OrderConfirmaitonListAdapter(this, null);
    orderConfirmaitonListAdapter.setHasStableIds(true);
    rvOcmShopList.setAdapter(orderConfirmaitonListAdapter);
    SetList();
    setPrise();
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
      case R.id.rl_ocm_address_info:

        break;
      case R.id.rl_ocm_pop_redpacket:
        redPacketOptions.show();
        break;
      case R.id.rl_ocm_pop_deliverytime:
        deliveryTimeOptions.show();
        break;
      case R.id.rl_ocm_pop_stockout:
        sockOutOptions.show();
        break;
      case R.id.tv_ocm_confirm_button:
        Intent intent =new Intent(this ,ActivityModePayment.class);
        startActivity(intent);
        break;
    }
  }

  private List<ShopProduct> productList = new ArrayList<>();

  private void SetList() {
    String[] contextImages = Config.OrderImages;
    int[] carids = Config.carid;
    productList.clear();
    for (int i = 0; i < contextImages.length; i++) {
      ShopProduct shopProduct = new ShopProduct();
      shopProduct.setGoods("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      shopProduct.setPrice("999.00");
      shopProduct.setType("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      if (i == 2) {
        shopProduct.setGoods("百安思保温杯304不锈钢真");
      }
      shopProduct.setNumber(1);
      shopProduct.setIsselector(false);
      shopProduct.setPicture(contextImages[i]);
      productList.add(shopProduct);
    }
    orderConfirmaitonListAdapter.addList(productList);

    deliveryTime.add("工作日/周末/假日均可");
    deliveryTime.add("仅周末送货");
    deliveryTime.add("仅工作日送货");
    sockOut.add("等待所以货品齐备后再发货");
    sockOut.add("与商家协商");
    sockOut.add("取消订单");
    redPacket.add("满1000减100");
    redPacket.add("满1000减100");
    redPacket.add("满1000减100");
    redPacket.add("满1000减100");
  }

  /**
   * 更新购物车价格
   */
  public void setPrise() {
    double sum = 0;
    for (ShopProduct pro : productList) {
      sum = DoubleUtil.sum(sum,
          DoubleUtil.mul((double) pro.getNumber(), Double.parseDouble(pro.getPrice())));
    }
    tvOcmConfirmPrice.setText("¥" + " " + (new DecimalFormat("0.00")).format(sum));
  }

  private OptionsPickerView redPacketOptions, deliveryTimeOptions, sockOutOptions;
  private ArrayList<String> redPacket = new ArrayList<>();
  private ArrayList<String> deliveryTime = new ArrayList<>();
  private ArrayList<String> sockOut = new ArrayList<>();

  private void initRedPacketOptionPicker() {//红包
    redPacketOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
      @Override public void onOptionsSelect(int options1, int option2, int options3, View v) {
        //返回的分别是三个级别的选中位置
        String tx = redPacket.get(options1);
        tvOcmPopRedpacketText.setText(tx);
      }
    }).setLayoutRes(R.layout.activity_dialog_redpacket, new CustomListener() {
      @Override public void customLayout(View v) {
        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            redPacketOptions.returnData();
            redPacketOptions.dismiss();
          }
        });
      }
    }).isDialog(false).build();
    redPacketOptions.setPicker(redPacket);//添加数据
  }

  private void initDeliveryTimeOptionPicker() { //送货时间
    deliveryTimeOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
      @Override public void onOptionsSelect(int options1, int option2, int options3, View v) {
        //返回的分别是三个级别的选中位置
        String tx = sockOut.get(options1);
        tvOcmPopDeliverytimeText.setText(tx);
      }
    }).setLayoutRes(R.layout.activity_dialog_deliverytime, new CustomListener() {
      @Override public void customLayout(View v) {
        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            deliveryTimeOptions.returnData();
            deliveryTimeOptions.dismiss();
          }
        });
      }
    }).isDialog(false).build();
    deliveryTimeOptions.setPicker(sockOut);//添加数据
  }

  private void initSockOutOptionPicker() {  //缺货处理
    sockOutOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
      @Override public void onOptionsSelect(int options1, int option2, int options3, View v) {
        //返回的分别是三个级别的选中位置
        String tx = deliveryTime.get(options1);
        tvOcmPopStockoutText.setText(tx);
      }
    }).setLayoutRes(R.layout.activity_dialog_sockout, new CustomListener() {
      @Override public void customLayout(View v) {
        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            sockOutOptions.returnData();
            sockOutOptions.dismiss();
          }
        });
      }
    }).isDialog(false).build();
    sockOutOptions.setPicker(deliveryTime);//添加数据
  }
}
