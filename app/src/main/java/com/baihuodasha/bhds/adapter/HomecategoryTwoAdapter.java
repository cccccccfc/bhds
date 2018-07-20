package com.baihuodasha.bhds.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.MainCategoryGoodsListMdel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/7.
 */

public class HomecategoryTwoAdapter extends RecyclerView.Adapter<HomecategoryTwoAdapter.MyViewHolder> {

  private final FragmentActivity mContext;
  private List<MainCategoryGoodsListMdel.DataBean> newlist;
  private OnItemClickListener mOnItemClickListener = null;

  public HomecategoryTwoAdapter(FragmentActivity mContext,List<MainCategoryGoodsListMdel.DataBean> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.recycinfo_item, parent, false);
    return new HomecategoryTwoAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyViewHolder holder, final int position) {
    holder.mItemtxtitle.setText(newlist.get(position).getCat_name());
    //Glide.with(mContext).load(URLContents.Goods_URL +newlist.get(position).getType_img()).into(holder.mItemivimage);
    //Log.i("qaz", "11111onBindViewHolder: "+URLContents.Goods_URL +newlist.get(position).getType_img());
    holder.mItemivimage.setVisibility(View.GONE);
    if (holder.mItemrecycinfo.getAdapter() == null) {
      holder.mItemrecycinfo.setAdapter(new HomecategoryInfoAdapter(mContext, newlist.get(position).getGoodsList()));
    } else {
      holder.mItemrecycinfo.getAdapter().notifyDataSetChanged();
    }
    holder.mItemlinmore.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onClick(position, newlist.get(position).getCat_id(),newlist.get(position).getCat_name());
        }
      }
    });
  }

  @Override public int getItemCount() {
    return  newlist != null ? newlist.size() : 0;
  }



  public class MyViewHolder extends RecyclerView.ViewHolder {

    RecyclerView mItemrecycinfo;
    ImageView mItemivimage;
    TextView mItemtxtitle;
    LinearLayout mItemlinmore;

    public MyViewHolder(View itemView) {
      super(itemView);
      mItemtxtitle = (TextView) itemView.findViewById(R.id.item_tx_title);
      mItemlinmore = (LinearLayout) itemView.findViewById(R.id.item_lin_more);
      mItemivimage = (ImageView) itemView.findViewById(R.id.item_iv_image);
      mItemrecycinfo = (RecyclerView) itemView.findViewById(R.id.item_recyc_info);
      RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 2);
      // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
      //manager.setAutoMeasureEnabled(true);
      mItemrecycinfo.setLayoutManager(manager);
    }
  }
  public void addList(List<MainCategoryGoodsListMdel.DataBean> list) {
  //  Log.i("qaz", "addList: "+list.size());
    if (list != null && list.size() > 0) {
      newlist.addAll(list);
      notifyDataSetChanged();
    }
  }
  public void clear() {
    newlist.clear();
    notifyDataSetChanged();
  }
  /**
   * 创建一个监听事件的接口；重要
   */
  public interface OnItemClickListener {
    //判断当前是否为item设置监听事件
    void onClick(int v, String position, String name);
  }

  /**
   * 外界进行调用该方法，为item设置点击事件；重要
   */
  public void setOnItemClickListener(OnItemClickListener listener) {
    this.mOnItemClickListener = listener;
  }
}
