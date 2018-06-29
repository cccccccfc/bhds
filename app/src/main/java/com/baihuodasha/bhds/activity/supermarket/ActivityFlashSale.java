package com.baihuodasha.bhds.activity.supermarket;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.FlashSaleListAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.RecommendationBean;
import com.baihuodasha.bhds.utils.countdowntimer.TimerUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ActivityFlashSale extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.lin_timelimit_timer) LinearLayout mLintimelimittimer;
  @BindView(R.id.rc_flashsale_list) RecyclerView rcFlashsaleList;
  private FlashSaleListAdapter flashsalelistAdapter;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_flash_sale);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    tvBaseTitle.setVisibility(View.VISIBLE);
    tvBaseTitle.setText("限时购");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {
    setmLayoutParams();
    //精选推荐条目
    LinearLayoutManager linearLayoutF = new LinearLayoutManager(this);
    linearLayoutF.setOrientation(LinearLayoutManager.VERTICAL);
    rcFlashsaleList.setLayoutManager(linearLayoutF);
    flashsalelistAdapter = new FlashSaleListAdapter(this, null);
    rcFlashsaleList.setAdapter(flashsalelistAdapter);
  }

  @Override public void dealLogicAfterInitView() {
    setlist();
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
    }
  }

  //设置倒计时
  private void setmLayoutParams() {
    TextView tv =
        TimerUtils.getTimer(TimerUtils.VIP_STYLE, this, 1200000, TimerUtils.TIME_STYLE_ONE,
            R.drawable.timer_shape2)
            .setTimerTextColor(Color.WHITE)
            .setTimerTextSize(28)
            .setTimerGapColor(Color.BLACK)
            .getmDateTv();
    mLintimelimittimer.addView(tv);

    tv.setGravity(Gravity.CENTER_HORIZONTAL);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
    params.setMargins(0, 0, 0, 0);
    tv.setLayoutParams(params);
  }

  private List<RecommendationBean> recommendationList = new ArrayList<>();

  private void setlist() {
    String[] contextImages = Config.ContextImages;
    recommendationList.clear();
    for (int i = 0; i < contextImages.length; i++) {
      RecommendationBean recommendationBean = new RecommendationBean();
      recommendationBean.setTitle("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      recommendationBean.setOldprice("1990.00");
      recommendationBean.setPrice("999.00");
      recommendationBean.setLabel("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      recommendationBean.setUrl(contextImages[i]);
      recommendationList.add(recommendationBean);
    }
    flashsalelistAdapter.addList(recommendationList);
  }
}
