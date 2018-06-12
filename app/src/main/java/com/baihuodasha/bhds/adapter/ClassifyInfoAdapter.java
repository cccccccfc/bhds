package com.baihuodasha.bhds.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.ParentInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/7.
 */

public class ClassifyInfoAdapter extends RecyclerView.Adapter<ClassifyInfoAdapter.MyViewHolder> {

  private final FragmentActivity mContext;
  private List<ParentInfo> newlist;
  public ClassifyInfoAdapter(FragmentActivity mContext,List<ParentInfo> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.classifyinfo_item, parent, false);
    return new ClassifyInfoAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyViewHolder holder, int position) {

    holder.mItemtxtitle.setText(newlist.get(position).getTitle());
    if (holder.mItemrecycinfo.getAdapter() == null) {
      holder.mItemrecycinfo.setAdapter(new ClassifyInfoChildAdapter(mContext,newlist.get(position).getTitle() ,newlist.get(position).getMenuList()));
    } else {
      holder.mItemrecycinfo.getAdapter().notifyDataSetChanged();
    }
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
      mItemrecycinfo = (RecyclerView) itemView.findViewById(R.id.item_classify_info);
      RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 3);
      // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
      manager.setAutoMeasureEnabled(true);
      mItemrecycinfo.setLayoutManager(manager);
    }
  }
  public void addList(List<ParentInfo> list) {
    Log.i("qaz", "addList: "+list.size());
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
