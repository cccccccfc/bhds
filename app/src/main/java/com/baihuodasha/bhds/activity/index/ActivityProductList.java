package com.baihuodasha.bhds.activity.index;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.CategoryGoodsListByCatIdAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.CategoryGoodsListByCatId;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import java.text.ParseException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductList extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.rc_product_list) RecyclerView rcProductList;
  private String title;
  private String cat_id;
  private CategoryGoodsListByCatIdAdapter newproductAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_product_list);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    if (getIntent().hasExtra("title")) {
      title = getIntent().getStringExtra("title");
    }
    if (getIntent().hasExtra("cat_id")) {
      cat_id = getIntent().getStringExtra("cat_id");
    }
    tvBaseTitle.setVisibility(View.VISIBLE);
    tvBaseTitle.setText(title);
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    newproductAdapter.setOnItemClickListener(new CategoryGoodsListByCatIdAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {

      }
    });

  }

  @Override public void dealLogicBeforeInitView() {
    LinearLayoutManager linearLayoutT = new GridLayoutManager(this, 2);
    linearLayoutT.setOrientation(LinearLayoutManager.VERTICAL);
    rcProductList.setLayoutManager(linearLayoutT);
    newproductAdapter = new CategoryGoodsListByCatIdAdapter(this, null);
    rcProductList.setAdapter(newproductAdapter);
  }

  @Override public void dealLogicAfterInitView() {
    sendGoodsList(cat_id);
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
    }
  }
  private void sendGoodsList(String cat_id){
    Log.i("qaz", "sendGoodsList: " +cat_id);
    SdjNetWorkManager.sendgetCategoryGoodsListByCatId(cat_id, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        CategoryGoodsListByCatId msg = (CategoryGoodsListByCatId) response.body();
        Log.i("qaz", "onResponse: "+msg.toString());
        if (msg != null) {

          newproductAdapter.addList(msg.getData().getGoodsList());
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        Log.i("qaz", "sendGoodsList: " +t);
      }
    });
  }
}
