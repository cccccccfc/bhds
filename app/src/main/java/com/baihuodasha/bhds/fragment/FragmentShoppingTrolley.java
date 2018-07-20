package com.baihuodasha.bhds.fragment;

import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.ShoppingTrolleyFullAdapter;
import com.baihuodasha.bhds.adapter.SupermarketChoicenessAdapter;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ShopProduct;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.DoubleUtil;
import com.baihuodasha.bhds.utils.assistant.ShopToDetailListener;
import com.baihuodasha.bhds.utils.assistant.onCallBackListener;
import com.baihuodasha.bhds.utils.dialog.BaseDialog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IMXU
 * @time 2017/5/3 13:21
 * @des 资讯首页
 * 邮箱：butterfly_xu@sina.com
 */
public class FragmentShoppingTrolley extends BaseFragment
    implements View.OnClickListener, onCallBackListener, ShopToDetailListener {
  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.recy_shop_trolley) RecyclerView recyShopTrolley;

  @BindView(R.id.shopping_cart) CheckBox shoppingCart; //复选框图标
  @BindView(R.id.shopping1) TextView shopping1; //全部
  @BindView(R.id.shoping_selector) LinearLayout shopingSelector;  //复选框
  @BindView(R.id.shopping2) TextView shopping2;  //合计
  @BindView(R.id.shopping3) TextView shopping3;   //已选
  @BindView(R.id.shoppingPrise) TextView shoppingPrise;  //价格
  @BindView(R.id.shoping_closeanaccount) TextView shopingCloseanaccount;  //结算
  @BindView(R.id.shoping_car) LinearLayout shopingCar; //
  @BindView(R.id.bhds_checkbox) CheckBox bhdscheckbox;
  @BindView(R.id.rec_bhds_choiceness) RecyclerView mRecBhdsChoiceness; //推荐商品
  private View view;
  private ShoppingTrolleyFullAdapter shoppingTrolleyFullAdapter;
  private int shopNum;
  private SupermarketChoicenessAdapter choicenessAdapter;
  private BaseDialog endDialog;

  @Override public View initView(LayoutInflater inflater) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_other, null);
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
    tvBaseTitle.setVisibility(View.VISIBLE);
    ivBaseBackto.setVisibility(View.GONE);
    tvBaseTitle.setText("购物车");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    tvBaseSave.setVisibility(View.VISIBLE);
    tvBaseSave.setText("编辑");
    shoppingCart.setChecked(false);
    bhdscheckbox.setChecked(true);
    shoppingPrise.setText("¥" + "0");
    LinearLayoutManager linearLayoutF = new LinearLayoutManager(getActivity());
    linearLayoutF.setOrientation(LinearLayoutManager.VERTICAL);
    recyShopTrolley.setLayoutManager(linearLayoutF);
    shoppingTrolleyFullAdapter = new ShoppingTrolleyFullAdapter(getActivity(), null);
    shoppingTrolleyFullAdapter.setHasStableIds(true);
    recyShopTrolley.setAdapter(shoppingTrolleyFullAdapter);
    shoppingTrolleyFullAdapter.setCallBackListener(this);
    //推荐商品
    LinearLayoutManager linearLayout = new GridLayoutManager(getActivity(), 3);
    linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
    mRecBhdsChoiceness.setLayoutManager(linearLayout);
    choicenessAdapter = new SupermarketChoicenessAdapter(getActivity(), null);
    mRecBhdsChoiceness.setAdapter(choicenessAdapter);
    SetList();
    setPrise();
  }

  @Override public void initListener() {
    shopingSelector.setOnClickListener(this);
    shopingCloseanaccount.setOnClickListener(this);
    shoppingCart.setOnClickListener(this);
    tvBaseSave.setOnClickListener(this);
    super.initListener();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.shopping_cart:
        AllTheSelected();
        break;
      case R.id.shoping_closeanaccount://
        if (shopingCloseanaccount.getText().toString().equals("去结算")) {
          if (shopNum <= 0) {
            CommonUtils.toastMessage("请选择要付款的商品~");
            return;
          }
          CommonUtils.toastMessage("钱就是另一回事了~");
        } else {
          if (shopNum <= 0) {
            CommonUtils.toastMessage("请选择要删除的商品~");
          } else {
            showEndDialog();
            CommonUtils.toastMessage("删除所选~");
          }
        }

        break;
      case R.id.tv_base_save: //编辑
        setPrise();
        Selectdelete();
        break;
      case R.id.cancel: //取消
        endDialog.dismiss();
        CommonUtils.toastMessage("取消");
        break;
      case R.id.yes: //删除
        endDialog.dismiss();
        CommonUtils.toastMessage("删除");
        break;
    }
  }

  private List<ShopProduct> productList = new ArrayList<>();
  private List<String> price2 = new ArrayList<>();

  private void SetList() {
    String[] contextImages = Config.CarImages;
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
      shopProduct.setId(carids[i]);
      shopProduct.setIsselector(false);
      shopProduct.setPicture(contextImages[i]);
      productList.add(shopProduct);
    }
    shoppingTrolleyFullAdapter.addList(productList);

    price2.clear();
    for (int i = 0; i < 14; i++) {
      price2.add(i + "测试");
    }
    //choicenessAdapter.addList(price2);

    shoppingTrolleyFullAdapter.setOnDelListener(new ShoppingTrolleyFullAdapter.onSwipeListener() {
      @Override public void onDel(int pos) {
        CommonUtils.toastMessage("删除" + pos);
        // shoppingTrolleyFullAdapter.notifyItemRemoved(pos);

        //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
        //且如果想让侧滑菜单同时关闭，需要同时调用 ((SwipeMenuLayout) holder.itemView).quickClose();
      }

      @Override public void onAdd(int pos) {
        CommonUtils.toastMessage("添加" + pos);
      }
    });
  }

  /**
   * 回调函数更新购物车和价格显示状态
   */
  @Override public void updateProduct(ShopProduct product, String type) {
    if (type.equals("1")) {
      if (!productList.contains(product)) {
        productList.add(product);
      } else {
        for (ShopProduct shopProduct : productList) {
          if (product.getId() == shopProduct.getId()) {
            shopProduct.setNumber(shopProduct.getNumber());
          }
        }
      }
    } else if (type.equals("2")) {
      if (productList.contains(product)) {
        if (product.getNumber() == 0) {
          productList.remove(product);
        } else {
          for (ShopProduct shopProduct : productList) {
            if (product.getId() == shopProduct.getId()) {
              shopProduct.setNumber(shopProduct.getNumber());
            }
          }
        }
      }
    }
    // shoppingTrolleyFullAdapter.notifyDataSetChanged();
    setPrise();
  }

  @Override public void onUpdateDetailList(ShopProduct product, String type) {
    if (type.equals("1")) {
      for (ShopProduct shopProduct : productList) {
        if (product.getId() == shopProduct.getId()) {
          shopProduct.setNumber(product.getNumber());
        }
      }
    } else if (type.equals("2")) {

      for (ShopProduct shopProduct : productList) {
        if (product.getId() == shopProduct.getId()) {
          shopProduct.setNumber(product.getNumber());
        }
      }
    }
    setPrise();
  }

  @Override public void onRemovePriduct(ShopProduct product) {

    for (ShopProduct shopProduct : productList) {
      if (product.getId() == shopProduct.getId()) {
        productList.remove(product);
        shopProduct.setNumber(shopProduct.getNumber());
      }
    }
    ;
    setPrise();
  }

  /**
   * 更新购物车价格
   */
  public void setPrise() {
    double sum = 0;
    shopNum = 0;
    for (ShopProduct pro : productList) {
      //            sum = sum + (pro.getNumber() * Double.parseDouble(pro.getPrice()));
      if (pro.isselector()) {
        sum = DoubleUtil.sum(sum,
            DoubleUtil.mul((double) pro.getNumber(), Double.parseDouble(pro.getPrice())));
        shopNum = shopNum + pro.getNumber();
      }
    }
    int shopnum = shopNum;
    shopping3.setText("已选(" + shopnum + ")");
    shoppingPrise.setText("¥" + " " + (new DecimalFormat("0.00")).format(sum));
  }

  private void Selectdelete() {

    if (tvBaseSave.getText().toString().equals("编辑")) {
      shopping2.setVisibility(View.GONE);
      shoppingPrise.setVisibility(View.GONE);
      shopping3.setVisibility(View.VISIBLE);
      shopingCloseanaccount.setText("删除所选");
      tvBaseSave.setText("完成");
    } else {
      tvBaseSave.setText("编辑");
      shopping2.setVisibility(View.VISIBLE);
      shoppingPrise.setVisibility(View.VISIBLE);
      shopping3.setVisibility(View.GONE);
      shopingCloseanaccount.setText("去结算");
    }
  }

  //全选或反选
  private void AllTheSelected() {

    boolean isCheck = true;
    boolean isUnCheck = true;
    if (shoppingCart.isChecked()) {
      isCheck = false;
    } else {
      isCheck = true;
    }
    if (isCheck) {//已经全选,做反选
      for (ShopProduct shopProduct : productList) {
        shopProduct.setIsselector(false);
      }
      shoppingCart.setChecked(false);
    } else if (!isCheck) {//一个没选,做全选
      for (ShopProduct shopProduct : productList) {
        shopProduct.setIsselector(true);
      }
      shoppingCart.setChecked(true);
    }
    setPrise();
    shoppingTrolleyFullAdapter.notifyDataSetChanged();
  }

  AlertDialog dialog_del;

  public void showEndDialog() {
    endDialog =
        new BaseDialog.Builder(getActivity()).setContentView(R.layout.dialog_commoditydetails)
            .setCancelable(false)
            .setText(R.id.tv_content, "确认要删除这" + shopNum + "件商品吗？")
            .setOnClickListener(R.id.cancel, this)
            .setOnClickListener(R.id.yes, this)
            .show();
  }
  //dialog_del = new AlertDialog.Builder(getActivity()).create();
  //View view = View.inflate(getActivity(), R.layout.dialog_commoditydetails, null);
  //TextView cancel = (TextView) view.findViewById(R.id.cancel);
  //TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
  //tv_content.setText("确认要删除这"+shopNum+"件商品吗？");
  //cancel.setOnClickListener(new View.OnClickListener() {
  //  @Override public void onClick(View view) {
  //    dialog_del.dismiss();
  //  }
  //});
  //TextView yes = (TextView) view.findViewById(R.id.yes);
  //yes.setOnClickListener(new View.OnClickListener() {
  //  @Override public void onClick(View view) {
  //    dialog_del.dismiss();
  //  }
  //});
  //dialog_del.show();
  //dialog_del.getWindow().setContentView(view);
}
