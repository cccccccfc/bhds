package com.baihuodasha.bhds.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.baihuodasha.bhds.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/19.
 */

public class CommentDetailChildsAdapter
    extends RecyclerView.Adapter<CommentDetailChildsAdapter.MyViewHolder> {

  private OnItemClickListener mOnItemClickListener = null;
  private final FragmentActivity mContext;
  private List<String> newlist;

  public CommentDetailChildsAdapter(FragmentActivity mContext, List<String> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.comment_details_info_item, parent, false);
    return new CommentDetailChildsAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, final int position) {
    Glide.with(mContext).load(newlist.get(position)).into(holder.mItemivimage);
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView mItemivimage;

    public MyViewHolder(View itemView) {
      super(itemView);
      mItemivimage = (ImageView) itemView.findViewById(R.id.child_item_img);
    }
  }

  public void addList(List<String> list) {
    Log.i("qaz", "addList: " + list.size());
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
    void onClick(int v, String position);
  }

  /**
   * 外界进行调用该方法，为item设置点击事件；重要
   */
  public void setOnItemClickListener(OnItemClickListener listener) {
    this.mOnItemClickListener = listener;
  }
}
