package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.GetChildTreeByCatId;
import com.baihuodasha.bhds.net.URLContents;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class ClassifyInfoChildAdapter
    extends RecyclerView.Adapter<ClassifyInfoChildAdapter.ViewHolder> {

  private Context mContext;
  private List<GetChildTreeByCatId.DataBean.CatIdBean> newlist; //

  /**
   * 构造函数
   */
  public ClassifyInfoChildAdapter(Context mContext,
      List<GetChildTreeByCatId.DataBean.CatIdBean> cat_id) {
    this.mContext = mContext;
    if (cat_id != null && cat_id.size() > 0) {
      this.newlist = cat_id;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.classify_child_info_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {

    Glide.with(mContext)
        .load(URLContents.Goods_URL + newlist.get(position).getImg())
        .into(holder.imageInfo);
    holder.child_item_tv1.setText(newlist.get(position).getName());
    holder.linear_back.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        CommonUtils.toastMessage("点击了" + newlist.get(position).getName());
      }
    });
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  /**
   * static ViewHolder
   */
  static class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageInfo;
    TextView child_item_tv1;
    LinearLayout linear_back;

    public ViewHolder(View itemView) {
      super(itemView);
      imageInfo = (ImageView) itemView.findViewById(R.id.child_item_img);
      child_item_tv1 = (TextView) itemView.findViewById(R.id.child_item_tv1);
      linear_back = (LinearLayout) itemView.findViewById(R.id.linear_back);
    }
  }
  public void addList(List<GetChildTreeByCatId.DataBean.CatIdBean> list) {
    if (list != null && list.size() > 0) {
      newlist.clear();
      newlist.addAll(list);
      notifyDataSetChanged();
    }
  }
  public void clear() {
    newlist.clear();
    notifyDataSetChanged();
  }
}
