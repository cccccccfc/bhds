package com.baihuodasha.bhds.activity.index;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.CommentDetailsAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ActivityCommentDetails extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.iv_comment_details_xing1) ImageView ivCommentDetailsXing1;
  @BindView(R.id.iv_comment_details_xing2) ImageView ivCommentDetailsXing2;
  @BindView(R.id.iv_comment_details_xing3) ImageView ivCommentDetailsXing3;
  @BindView(R.id.iv_comment_details_xing4) ImageView ivCommentDetailsXing4;
  @BindView(R.id.iv_comment_details_xing5) ImageView ivCommentDetailsXing5;
  @BindView(R.id.tv_comment_details_eputation) TextView tvCommentDetailsEputation;
  @BindView(R.id.tf_comment_details_item) TagFlowLayout tfCommentDetailsItem;
  @BindView(R.id.rc_comment_details_info) RecyclerView rcCommentDetailsInfo;
  @BindView(R.id.tf_comment_details_hint) TextView tfCommentDetailsHint;  //没有评论提示
  @BindView(R.id.sl_comment_details_info) ScrollInterceptScrollView slCommentDetailsInfo;
  private LayoutInflater mInflater;
  private TagAdapter commentDetailsItemAdapter;
  private CommentDetailsAdapter commentDetailsAdapter;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_comment_details);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    mInflater = LayoutInflater.from(this);
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    tvBaseTitle.setText("评论");
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  private List<String> DetailsItem;

  @Override public void dealLogicBeforeInitView() {
    DetailsItem = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      DetailsItem.add("测试测试");
    }
  }

  @Override public void dealLogicAfterInitView() {
    rcCommentDetailsInfo.setNestedScrollingEnabled(false);
    commentDetailsItemAdapter = new TagAdapter(DetailsItem) {
      @Override public View getView(FlowLayout parent, int position, Object o) {
        TextView tv =
            (TextView) mInflater.inflate(R.layout.comment_details_item, tfCommentDetailsItem,
                false);
        tv.setText(DetailsItem.get(position));
        return tv;
      }
    };
    tfCommentDetailsItem.setAdapter(commentDetailsItemAdapter);
    tfCommentDetailsItem.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {
        commentDetailsItemAdapter.setSelectedList(i);
        //specColor = DetailsItem.get(i);
        CommonUtils.toastMessage(DetailsItem.get(i));
        return true;
      }
    });
    rcCommentDetailsInfo.setNestedScrollingEnabled(false);
    LinearLayoutManager linearLayout = new LinearLayoutManager(this);
    linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
    rcCommentDetailsInfo.setLayoutManager(linearLayout);
    commentDetailsAdapter = new CommentDetailsAdapter(this, null);
    rcCommentDetailsInfo.setAdapter(commentDetailsAdapter);
    ArrayList<String> imageList = new ArrayList<>();
    for (int i = 0; i < 13; i++) {
      imageList.add("1");
    }
    commentDetailsAdapter.addList(imageList);
  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
    }
  }

}
