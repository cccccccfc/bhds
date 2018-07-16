package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.ShopProduct;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class OrderConfirmaitonListAdapter
    extends RecyclerView.Adapter<OrderConfirmaitonListAdapter.FullDelDemoVH> {
  private Context mContext;
  private LayoutInflater mInfalter;
  private List<ShopProduct> newlist;

  public OrderConfirmaitonListAdapter(Context context, List<ShopProduct> mList) {
    mContext = context;
    mInfalter = LayoutInflater.from(context);
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {
    return new FullDelDemoVH(
        mInfalter.inflate(R.layout.item_ordercomfirmaiton_list, parent, false));
  }

  @Override public void onBindViewHolder(final FullDelDemoVH holder, final int position) {
    holder.mTextordertitle.setText(newlist.get(position).getGoods());
    holder.mTextorderabel.setText(newlist.get(position).getType());
    holder.mTextorderprice.setText("¥ " + newlist.get(position).getPrice());
    holder.mTextorderamount.setText("× " + newlist.get(position).getNumber());
    Glide.with(mContext).load(newlist.get(position).getPicture()).into(holder.mImgorderimage);
  }

  @Override public int getItemCount() {
    return null != newlist ? newlist.size() : 0;
  }

  class FullDelDemoVH extends RecyclerView.ViewHolder {
    ImageView mImgorderimage;
    TextView mTextordertitle, mTextorderabel, mTextorderprice, mTextorderamount;

    public FullDelDemoVH(View itemView) {
      super(itemView);
      mImgorderimage = (ImageView) itemView.findViewById(R.id.img_order_image);
      mTextordertitle = (TextView) itemView.findViewById(R.id.text_order_title);
      mTextorderabel = (TextView) itemView.findViewById(R.id.text_order_label);
      mTextorderprice = (TextView) itemView.findViewById(R.id.text_order_price);
      mTextorderamount = (TextView) itemView.findViewById(R.id.text_order_amount);
    }
  }

  public void addList(List<ShopProduct> list) {
    // Log.i("qaz", "addList: "+list.size());
    if (list != null && list.size() > 0) {
      newlist.addAll(list);
      notifyDataSetChanged();
    }
  }

  public void clear() {
    newlist.clear();
    notifyDataSetChanged();
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemViewType(int position) {
    return position;
  }
}

