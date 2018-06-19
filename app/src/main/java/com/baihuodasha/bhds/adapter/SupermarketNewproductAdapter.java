package com.baihuodasha.bhds.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/19.
 */

public class SupermarketNewproductAdapter extends RecyclerView.Adapter<SupermarketNewproductAdapter.MyViewHolder>{

  private final FragmentActivity mContext;
  private OnItemClickListener mOnItemClickListener = null;
  private List<String> newlist;
  public SupermarketNewproductAdapter(FragmentActivity mContext,List<String> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.supermarket_newproduct_item, parent, false);
    return new SupermarketNewproductAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyViewHolder holder, final int position) {

    holder.mItemtxtitle.setText(newlist.get(position));
    holder.mItemlinearl.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onClick(position, newlist.get(position));
        }
      }
    });
  }

  @Override public int getItemCount() {
    return  newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

     LinearLayout mItemlinearl;
    ImageView mItemivimage;
    TextView mItemtxtitle , mItemintroduce,mItemprice;

    public MyViewHolder(View itemView) {
      super(itemView);
      mItemtxtitle = (TextView) itemView.findViewById(R.id.txe_supermarket_newproduct1);
      mItemintroduce = (TextView) itemView.findViewById(R.id.txe_supermarket_newproduct2);
      mItemprice= (TextView) itemView.findViewById(R.id.txe_supermarket_newproduct3);
      mItemivimage = (ImageView) itemView.findViewById(R.id.img_supermarket_newproduct);
      mItemlinearl = (LinearLayout) itemView.findViewById(R.id.lin_supermarket_newproduct);

    }
  }
  public void addList(List<String> list) {
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
