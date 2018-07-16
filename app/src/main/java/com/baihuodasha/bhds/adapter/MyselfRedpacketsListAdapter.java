package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.AddressBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/26.
 */

public class MyselfRedpacketsListAdapter
    extends RecyclerView.Adapter<MyselfRedpacketsListAdapter.MyViewHolder> {
  private Context mContext;
  private List<AddressBean> newlist;

  public MyselfRedpacketsListAdapter(Activity mContext, List<AddressBean> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override
  public MyselfRedpacketsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(mContext)
        .inflate(R.layout.adapter_redpackets_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyselfRedpacketsListAdapter.MyViewHolder holder,
      final int position) {

  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mTime, mLable, mCondition, mPrice;
    ImageView mImage;

    public MyViewHolder(View itemView) {
      super(itemView);
      mImage = (ImageView) itemView.findViewById(R.id.iv_redpackrt_image);
      mTime = (TextView) itemView.findViewById(R.id.tv_redpackrt_time);
      mLable = (TextView) itemView.findViewById(R.id.tv_redpackrt_lable);
      mCondition = (TextView) itemView.findViewById(R.id.tv_redpackrt_condition);
      mPrice = (TextView) itemView.findViewById(R.id.tv_redpackrt_price);
    }
  }

  public void addList(List<AddressBean> list) {
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
