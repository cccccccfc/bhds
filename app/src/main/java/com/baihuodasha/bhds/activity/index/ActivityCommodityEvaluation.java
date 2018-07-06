package com.baihuodasha.bhds.activity.index;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.CommodityEvaluationAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class ActivityCommodityEvaluation extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.rl_commdoity_evaluation_info) RecyclerView rlCommdoityEvaluationInfo;
  @BindView(R.id.slcommdoity_evaluation_info) ScrollInterceptScrollView slcommdoityEvaluationInfo;
  @BindView(R.id.rl_commdoity_evaluation_confirm) RelativeLayout rlCommdoityEvaluationConfirm;
  private CommodityEvaluationAdapter commodityEvaluationAdapter;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_commodity_evaluation);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("评价");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  @Override public void dealLogicBeforeInitView() {

  }

  private List<String> DetailsItem = new ArrayList<>();

  @Override public void dealLogicAfterInitView() {

    LinearLayoutManager linearLayout = new LinearLayoutManager(this);
    linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
    rlCommdoityEvaluationInfo.setLayoutManager(linearLayout);
    commodityEvaluationAdapter = new CommodityEvaluationAdapter(this, null, null);
    rlCommdoityEvaluationInfo.setAdapter(commodityEvaluationAdapter);
    DetailsItem.clear();
    for (int i = 0; i < 5; i++) {
      DetailsItem.add("测试测试");
    }
    imagePaths.clear();
    for (int i = 0; i < 5; i++) {
      if (i == 0) {
        imagePaths.add("000000");
      }
    }
    //Log.i("qaz", "dealLogicAfterInitView: "+imagePaths.size());
    commodityEvaluationAdapter.addList(DetailsItem, imagePaths);
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
    }
  }

  private static final int REQUEST_CAMERA_CODE = 10;
  private static final int REQUEST_PREVIEW_CODE = 20;

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        // 选择照片
        case REQUEST_CAMERA_CODE:
          ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
          Log.d("qaz", "list: " + "list = [" + list.size());

          loadAdpater(list);
          break;
        // 预览
        case REQUEST_PREVIEW_CODE:
          ArrayList<String> ListExtra =
              data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
          Log.d("qaz", "ListExtra: " + "ListExtra = [" + ListExtra.size());
          loadAdpater(ListExtra);
          break;
      }
    }
  }

  private ArrayList<String> imagePaths =new ArrayList<>();

  private void loadAdpater(ArrayList<String> paths) {
    if (imagePaths != null && imagePaths.size() > 0) {
      imagePaths.clear();
    }
    if (paths.contains("000000")) {
      paths.remove("000000");
    }
    paths.add("000000");
    imagePaths.addAll(paths);

    //gridAdapter = new GridAdapter(this ,imagePaths);
    //gridView.setAdapter(gridAdapter);
    commodityEvaluationAdapter.addList(DetailsItem, imagePaths);
    try {
      JSONArray obj = new JSONArray(imagePaths);
      Log.d("qaz", obj.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
