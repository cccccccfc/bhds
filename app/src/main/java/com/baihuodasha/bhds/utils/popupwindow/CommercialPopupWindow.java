package com.baihuodasha.bhds.utils.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.ComercialAdapter;
import com.baihuodasha.bhds.bean.RecommendationBean;
import java.util.ArrayList;
import java.util.List;

public class CommercialPopupWindow extends PopupWindow implements OnClickListener {

  private ComercialAdapter commercialAdapter;
  private TextView txSpecificationTitle;
  private RecyclerView rcSpecificationInfo;
  private RelativeLayout rlSpecificationClose;
  private RelativeLayout rlType;
  private List<RecommendationBean> recommendationList = new ArrayList<>();
  View mMenuView;
  private int height;
  private Context context;

  public CommercialPopupWindow(String goodsid, final Activity context, final List<SpecModel> models,
      final int stockNum, String images) {

    super(context);
    this.context = context;
    DisplayMetrics dm = new DisplayMetrics();
    context.getWindowManager().getDefaultDisplay().getMetrics(dm);
    height = (dm.heightPixels) * 2 / 3;
    final LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mMenuView = inflater.inflate(R.layout.specification_layout, null);
    rlSpecificationClose = (RelativeLayout) mMenuView.findViewById(R.id.rl_specification_close);
    rcSpecificationInfo = (RecyclerView) mMenuView.findViewById(R.id.rc_specification_info);
    rlType = (RelativeLayout) mMenuView.findViewById(R.id.rl_type);
    rlSpecificationClose.setOnClickListener(this);
    recommendationList.clear();
    for (int i = 0; i < 22; i++) {
      RecommendationBean recommendationBean = new RecommendationBean();
      if (i == 2) {
        recommendationBean.setTitle("百");
      }else {
        recommendationBean.setTitle("百安思保温杯");
      }

      recommendationBean.setLabel("大容量男女创意定制便携保温杯");
      recommendationList.add(recommendationBean);
    }
   // commercialAdapter.addList(recommendationList);

    LinearLayoutManager linearLayoutF = new LinearLayoutManager(context);
    linearLayoutF.setOrientation(LinearLayoutManager.VERTICAL);
    rcSpecificationInfo.setLayoutManager(linearLayoutF);
    commercialAdapter = new ComercialAdapter(context, recommendationList);
    rcSpecificationInfo.setAdapter(commercialAdapter);
    this.setBackgroundDrawable(new ColorDrawable(0));
    //		//设置按钮监听
    //		lv_car.setOnItemClickListener(itemsOnClick);
    //设置SelectPicPopupWindow的View
    this.setContentView(mMenuView);
    //设置SelectPicPopupWindow弹出窗体的宽
    this.setWidth(LayoutParams.MATCH_PARENT);
    //设置SelectPicPopupWindow弹出窗体的高
    //this.setHeight(LayoutParams.WRAP_CONTENT);
    //if (models.size() > 8) {
    this.setHeight(height);
    //} else {
    //this.setHeight(dm.heightPixels / 2);
    //}
    //设置SelectPicPopupWindow弹出窗体可点击
    this.setFocusable(true);
    //设置SelectPicPopupWindow弹出窗体动画效果
    this.setAnimationStyle(R.style.AnimBottom);
    //实例化一个ColorDrawable颜色为半透明
    //		ColorDrawable dw = new ColorDrawable(0xb0000000);
    //		ColorDrawable dw = new ColorDrawable(0xffffff);
    //设置SelectPicPopupWindow弹出窗体的背景
    //		this.setBackgroundDrawable(dw);
    //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
    mMenuView.setOnTouchListener(new OnTouchListener() {

      public boolean onTouch(View v, MotionEvent event) {

        int height = mMenuView.findViewById(R.id.rl_type).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
          if (y < height) {
            dismiss();
          }
        }
        return true;
      }
    });
  }

  @Override public void onClick(View v) {

    switch (v.getId()) {
      case R.id.rl_specification_close:
          dismiss();
        break;
    }
  }
}
