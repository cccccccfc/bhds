package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.AddressBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/26.
 */

public class MyselfShippingaddressListAdapter
    extends RecyclerView.Adapter<MyselfShippingaddressListAdapter.MyViewHolder> {
  private Context mContext;
  private OnItemDelClickListener mOnItemDelClickListener = null;
  private List<AddressBean> newlist;
  private OnItemModifyClickListener mOnItemModifyClickListener = null;

  public MyselfShippingaddressListAdapter(Activity mContext, List<AddressBean> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override
  public MyselfShippingaddressListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(mContext)
        .inflate(R.layout.adapter_shippingaddaddress_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyselfShippingaddressListAdapter.MyViewHolder holder,
      final int position) {

    final AddressBean bean = newlist.get(position);
    holder.mName.setText(bean.getName());
    holder.mAddress.setText(bean.getAddress());
    holder.mPhone.setText(bean.getMobile());
    holder.mDel.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnItemDelClickListener != null) {
          mOnItemDelClickListener.onItemDle(v, bean);
        }
      }
    });
    holder.mModify.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnItemModifyClickListener != null) {
          mOnItemModifyClickListener.onItemModify(v, bean);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mName, mPhone, mAddress, mDefault, mDel, mModify;

    public MyViewHolder(View itemView) {
      super(itemView);
      mName = (TextView) itemView.findViewById(R.id.tv_shippingaddress_name);
      mPhone = (TextView) itemView.findViewById(R.id.tv_shippingaddress_phone);
      mAddress = (TextView) itemView.findViewById(R.id.tv_shippingaddress_address);
      mDefault = (TextView) itemView.findViewById(R.id.tv_shippingaddress_default);
      mDel = (TextView) itemView.findViewById(R.id.tv_shippingaddress_del);
      mModify = (TextView) itemView.findViewById(R.id.tv_shippingaddress_modify);
    }
  }

  public interface OnItemDelClickListener {
    //判断当前是否为item设置监听事件
    void onItemDle(View v, AddressBean bean);
  }

  public void setOnItemDelClickListener(OnItemDelClickListener listener) {
    this.mOnItemDelClickListener = listener;
  }

  public interface OnItemModifyClickListener {
    //判断当前是否为item设置监听事件
    void onItemModify(View v, AddressBean bean);
  }

  public void setOnItemModifyClickListener(OnItemModifyClickListener listener) {
    this.mOnItemModifyClickListener = listener;
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
