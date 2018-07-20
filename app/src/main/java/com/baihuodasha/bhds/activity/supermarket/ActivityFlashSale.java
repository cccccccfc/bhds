package com.baihuodasha.bhds.activity.supermarket;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.FlashSaleListAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.FlashSaleModel;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.countdowntimer.TimerUtils;
import com.baihuodasha.bhds.view.MyDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import java.text.ParseException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFlashSale extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.lin_timelimit_timer) LinearLayout mLintimelimittimer;
  @BindView(R.id.rc_flashsale_list) RecyclerView rcFlashsaleList;
  @BindView(R.id.rel_timelimit_backgroud) RelativeLayout relTimelimitBackgroud;
  @BindView(R.id.ScrollInterceptScrollView) SmartRefreshLayout Msmart;
  private FlashSaleListAdapter flashsalelistAdapter;
  private MyDialog loadDialog;
  private boolean shouLoad = true;

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
    Msmart.setEnableRefresh(false);
    Msmart.setOnLoadmoreListener(new OnLoadmoreListener() {
      @Override public void onLoadmore(final RefreshLayout refreshlayout) {
        refreshlayout.getLayout().postDelayed(new Runnable() {
          @Override
          public void run() {
            if (flashsalelistAdapter.getIsLoadOver()) {
              CommonUtils.toastMessage("数据全部加载完毕");
              refreshlayout.setLoadmoreFinished(true);
            } else {
              setlist(false, "15");
              refreshlayout.finishLoadmore();
            }
            refreshlayout.finishLoadmore();
          }
        }, 1000);
      }
    });
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
    setlist(true,"15");
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

  private void setlist(final boolean isReflash ,String size) {
    if (isReflash) {
      loadDialog = MyDialog.showDialog(this);
      loadDialog.show();
    }
    int page = isReflash ? 1 : flashsalelistAdapter.getPage();
    Log.i("qaz", "setlist: ");
    SdjNetWorkManager.sendGroupbuy("list", page, size, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        FlashSaleModel msg = (FlashSaleModel) response.body();
         Log.i("qaz", "11111onResponse: "+msg.toString());
        if (msg != null) {
          if (loadDialog != null) {
            loadDialog.dismiss();
            loadDialog = null;
          }
          if (msg.getData()!= null && msg.getData().size() > 0) {
            flashsalelistAdapter.addList(msg.getData());
          }else {
            Msmart.finishLoadmore();
          }

        }else {
          Msmart.setLoadmoreFinished(false);
          Msmart.finishLoadmore();
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        Log.i("qaz", "sendGoodsList: " +t);
        if (loadDialog != null) {
          loadDialog.dismiss();
          loadDialog = null;
        }
      }
    });
  }

}
