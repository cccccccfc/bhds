package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.RecommendationBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/29.
 */

public class ComercialAdapter extends RecyclerView.Adapter<ComercialAdapter.MyViewHolder> {
  private final Activity mContext;
  private List<RecommendationBean> newlist;

  public ComercialAdapter(Activity mContext, List<RecommendationBean> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.specification_item, parent, false);
    return new ComercialAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyViewHolder holder, final int position) {
    holder.mTexttitle.setText(newlist.get(position).getTitle());
    holder.mTextlabel.setText(newlist.get(position).getLabel());

  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView mTexttitle, mTextlabel;

    public MyViewHolder(View itemView) {
      super(itemView);
      mTexttitle = (TextView) itemView.findViewById(R.id.tx_specification_name);
      mTextlabel = (TextView) itemView.findViewById(R.id.tx_specification_info);

    }
  }

  public void addList(List<RecommendationBean> list) {
   // Log.i("qaz", "addList: " + list.size());
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
