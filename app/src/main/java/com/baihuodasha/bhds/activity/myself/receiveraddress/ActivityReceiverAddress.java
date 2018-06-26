package com.baihuodasha.bhds.activity.myself.receiveraddress;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.MyselfShippingaddressListAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.AddressBean;
import com.baihuodasha.bhds.utils.CommonUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ActivityReceiverAddress extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.rv_myself_shippingaddress_list) RecyclerView rvMyselfShippingaddressList;
  @BindView(R.id.ll_myself_shippingaddress_bg) LinearLayout llMyselfShippingaddressBg;
  @BindView(R.id.rv_myself_shippingaddress_add) LinearLayout rvMyselfShippingaddressAdd;
  private MyselfShippingaddressListAdapter adapter;
  private List<AddressBean> item;
  private AddressBean bean;
  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_receiver_address);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    rvMyselfShippingaddressAdd.setOnClickListener(this);
    tvBaseTitle.setText("地址管理");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));


  }

  @Override public void dealLogicBeforeInitView() {
    LinearLayoutManager linearLayoutO = new LinearLayoutManager(this);
    linearLayoutO.setOrientation(LinearLayoutManager.VERTICAL);
    rvMyselfShippingaddressList.setLayoutManager(linearLayoutO);
    adapter = new MyselfShippingaddressListAdapter(this, null);
    rvMyselfShippingaddressList.setAdapter(adapter);
    getImageList();
  }

  @Override public void dealLogicAfterInitView() {
    adapter.setOnItemDelClickListener(new MyselfShippingaddressListAdapter.OnItemDelClickListener() {
      @Override public void onItemDle(View v, AddressBean bean) {
        CommonUtils.toastMessage("删除");
      }
    });
    adapter.setOnItemModifyClickListener(new MyselfShippingaddressListAdapter.OnItemModifyClickListener() {
      @Override public void onItemModify(View v, AddressBean bean) {
       // CommonUtils.toastMessage("编辑");
        Intent intent = new Intent(getApplication() ,ActivityNewAddress.class);
        intent.putExtra("type",2);
        startActivity(intent);
      }
    });
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
      case R.id.rv_myself_shippingaddress_add:
       // finish();
        Intent intent = new Intent(this ,ActivityNewAddress.class);
        intent.putExtra("type",1);
        startActivity(intent);
        break;
    }
  }

  private void getImageList() {
    item = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      bean = new AddressBean();
      bean.setAddress("北京朝阳区。。。。。。。。。。。。。");
      if (i ==2) {
        bean.setAddress("北京朝阳区北京朝阳区北京朝阳区北京朝阳区北京朝阳区北京朝阳区北京朝阳区北京朝阳区。。");
      }
      bean.setName("你猜猜");
      bean.setMobile("414847816487");
      item.add(bean);
    }
    if (item.size() != 0) {
      llMyselfShippingaddressBg.setVisibility(View.GONE);
    }
  //  Log.i("qaz", "getImageList: "+item.size());
    adapter.addList(item);
  }
}
