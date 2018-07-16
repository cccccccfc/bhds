package com.baihuodasha.bhds.activity.myself;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.MyselfRedpacketsListAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import java.text.ParseException;

public class ActivityMyRedPackets extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.tv_select_packet_unused) TextView tvSelectPacketUnused;
  @BindView(R.id.tv_select_packet_used) TextView tvSelectPacketUsed;
  @BindView(R.id.tv_select_packet_pastdue) TextView tvSelectPacketPastdue;
  @BindView(R.id.tv_select_packet_unused_lin) TextView tvSelectPacketUnusedLin;
  @BindView(R.id.tv_select_packet_used_lin) TextView tvSelectPacketUsedLin;
  @BindView(R.id.tv_select_packet_pastdue_lin) TextView tvSelectPacketPastdueLin;
  @BindView(R.id.rl_red_packet_list) RecyclerView rlRedPacketList;
  @BindView(R.id.red_packet_backgroud) ImageView redPacketBackgroud;
  private MyselfRedpacketsListAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_my_red_packets);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("我的红包");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    tvSelectPacketUnused.setOnClickListener(this);
    tvSelectPacketUsed.setOnClickListener(this);
    tvSelectPacketPastdue.setOnClickListener(this);
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {
    LinearLayoutManager linearLayoutO = new LinearLayoutManager(this);
    linearLayoutO.setOrientation(LinearLayoutManager.VERTICAL);
    rlRedPacketList.setLayoutManager(linearLayoutO);
    adapter = new MyselfRedpacketsListAdapter(this, null);
    rlRedPacketList.setAdapter(adapter);
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();

        break;
      case R.id.tv_select_packet_unused:
        tvSelectPacketUnusedLin.setBackgroundColor(Color.parseColor("#D3BF9C"));
        tvSelectPacketUsedLin.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvSelectPacketPastdueLin.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvSelectPacketUnused.setTextColor(Color.parseColor("#333333"));
        tvSelectPacketUsed.setTextColor(Color.parseColor("#999999"));
        tvSelectPacketPastdue.setTextColor(Color.parseColor("#999999"));

        break;
      case R.id.tv_select_packet_used:
        tvSelectPacketUnusedLin.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvSelectPacketUsedLin.setBackgroundColor(Color.parseColor("#D3BF9C"));
        tvSelectPacketPastdueLin.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvSelectPacketUnused.setTextColor(Color.parseColor("#999999"));
        tvSelectPacketUsed.setTextColor(Color.parseColor("#333333"));
        tvSelectPacketPastdue.setTextColor(Color.parseColor("#999999"));

        break;
      case R.id.tv_select_packet_pastdue:
        tvSelectPacketUnusedLin.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvSelectPacketUsedLin.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvSelectPacketPastdueLin.setBackgroundColor(Color.parseColor("#D3BF9C"));
        tvSelectPacketUnused.setTextColor(Color.parseColor("#999999"));
        tvSelectPacketUsed.setTextColor(Color.parseColor("#999999"));
        tvSelectPacketPastdue.setTextColor(Color.parseColor("#333333"));

        break;
    }
  }
}
