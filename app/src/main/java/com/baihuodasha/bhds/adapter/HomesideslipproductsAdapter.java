package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.baihuodasha.bhds.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/7.
 */

public class HomesideslipproductsAdapter extends RecyclerView.Adapter<HomesideslipproductsAdapter.MyViewHolder> {
  private Context mContext;
  private OnItemClickListener mOnItemClickListener = null;
  private List<String> newlist;

  public HomesideslipproductsAdapter(FragmentActivity mContext, List<String> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override
  public HomesideslipproductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.recycshop_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(HomesideslipproductsAdapter.MyViewHolder holder, final int position) {
    //
    Glide.with(mContext).load(newlist.get(position)).into(holder.im);
    holder.im.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onClick(position, newlist.get(position));
        }
      }
    });
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView im;

    public MyViewHolder(View itemView) {
      super(itemView);
      im = (ImageView) itemView.findViewById(R.id.image);
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

  public void addList(List<String> list) {
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
