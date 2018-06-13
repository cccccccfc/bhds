package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.SupermarketGridBean;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/13.
 */

public class SupermarkeGridtAdapter
    extends RecyclerView.Adapter<SupermarkeGridtAdapter.MyViewHolder> {

  private Context mContext;
  private OnItemClickListener mOnItemClickListener = null;
  private List<SupermarketGridBean> newlist;

  public SupermarkeGridtAdapter(FragmentActivity mContext, List<SupermarketGridBean> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override
  public SupermarkeGridtAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.supermarkegrid_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(SupermarkeGridtAdapter.MyViewHolder holder, final int position) {
    //
    Glide.with(mContext).load(newlist.get(position).url).into(holder.imageInfo);
    holder.child_item_tv1.setText(newlist.get(position).title);
    holder.linear_back.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onClick(position, newlist.get(position).title);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageInfo;
    TextView child_item_tv1;
    LinearLayout linear_back;
    public MyViewHolder(View itemView) {
      super(itemView);
      imageInfo = (ImageView) itemView.findViewById(R.id.child_item_img);
      child_item_tv1 = (TextView) itemView.findViewById(R.id.child_item_tv1);
      linear_back = (LinearLayout)itemView.findViewById(R.id.linear_back);
    }
  }
  /**
   * 创建一个监听事件的接口；重要
   */
  public interface OnItemClickListener {
    //判断当前是否为item设置监听事件
    void onClick(int v, String position);
  }

  /**
   * 外界进行调用该方法，为item设置点击事件；重要
   */
  public void setOnItemClickListener(OnItemClickListener listener) {
    this.mOnItemClickListener = listener;
  }

  public void addList(List<SupermarketGridBean> list) {
    if (list != null && list.size() > 0) {
      newlist.addAll(list);
      notifyDataSetChanged();
    }
  }
  public void clear() {
    newlist.clear();
    notifyDataSetChanged();
  }
}
