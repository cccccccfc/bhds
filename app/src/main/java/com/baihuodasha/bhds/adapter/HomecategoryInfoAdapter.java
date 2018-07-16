package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.MainCategoryGoodsListMdel;
import com.baihuodasha.bhds.net.URLContents;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class HomecategoryInfoAdapter
    extends RecyclerView.Adapter<HomecategoryInfoAdapter.ViewHolder> {

  private Context context;
  private List<MainCategoryGoodsListMdel.DataBean.GoodsListBean> list; // List 集合（里面是image+text）

  /**
   * 构造函数
   */
  public HomecategoryInfoAdapter(Context context,
      List<MainCategoryGoodsListMdel.DataBean.GoodsListBean> list) {
    this.context = context;
    this.list = list;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.menu_info_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    MainCategoryGoodsListMdel.DataBean.GoodsListBean info = list.get(position);
    Glide.with(context).load(URLContents.Goods_URL + info.getGoods_img()).into(holder.imageInfo);
    Log.i("qaz", "22222onBindViewHolder: "+URLContents.Goods_URL + info.getGoods_img());
    holder.child_item_tv1.setText(info.getGoods_name());
    holder.child_item_tv2.setText(info.getGoods_brief());
    holder.child_item_tv3.setText("¥" + info.getShop_price());
  }

  @Override public int getItemCount() {
    return list != null ? list.size() : 0;
  }

  /**
   * static ViewHolder
   */
  static class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageInfo;
    TextView child_item_tv1, child_item_tv2, child_item_tv3;

    public ViewHolder(View itemView) {
      super(itemView);
      imageInfo = (ImageView) itemView.findViewById(R.id.child_item_img);
      child_item_tv1 = (TextView) itemView.findViewById(R.id.child_item_tv1);
      child_item_tv2 = (TextView) itemView.findViewById(R.id.child_item_tv2);
      child_item_tv3 = (TextView) itemView.findViewById(R.id.child_item_tv3);
    }
  }
}
