package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.ShopProduct;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.SwipeMenuLayout.SwipeMenuLayout;
import com.baihuodasha.bhds.utils.assistant.onCallBackListener;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：zhangxutong@imcoming.com
 * 时间： 2016/9/12.
 */

public class ShoppingTrolleyFullAdapter
    extends RecyclerView.Adapter<ShoppingTrolleyFullAdapter.FullDelDemoVH> {
  private Context mContext;
  private LayoutInflater mInfalter;
  private List<ShopProduct> newlist;
  private onCallBackListener callBackListener;

  public void setCallBackListener(onCallBackListener callBackListener) {
    this.callBackListener = callBackListener;
  }

  public ShoppingTrolleyFullAdapter(Context context, List<ShopProduct> mList) {
    mContext = context;
    mInfalter = LayoutInflater.from(context);
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {
    return new FullDelDemoVH(mInfalter.inflate(R.layout.item_shoppingtrolley_full, parent, false));
  }

  @Override public void onBindViewHolder(final FullDelDemoVH holder, final int position) {
    ((SwipeMenuLayout) holder.itemView).setIos(false).setLeftSwipe(true);//这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑

    if (newlist.get(position).isselector()) {
      holder.mImgfullselector.setChecked(true);
    } else {
      holder.mImgfullselector.setChecked(false);
    }

    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (null != mOnSwipeListener) {
          ((SwipeMenuLayout) holder.itemView).quickClose();
          mOnSwipeListener.onDel(holder.getAdapterPosition());
        }else {

        }
      }
    });
    holder.btnUnRead.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (null != mOnSwipeListener) {
          ((SwipeMenuLayout) holder.itemView).quickClose();
          mOnSwipeListener.onAdd(holder.getAdapterPosition());
        }else {

        }
      }
    });
    holder.mTextfulltitle.setText(newlist.get(position).getGoods());
    holder.mTextfulllabel.setText(newlist.get(position).getType());
    holder.mTextfullprice.setText("¥ "+newlist.get(position).getPrice());
    Glide.with(mContext).load(newlist.get(position).getPicture()).into(holder.mImgfullimage);

    holder.mImgfullminus.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        holder.mImgfullselector.setChecked(true);
        newlist.get(position).setIsselector(true);
        int num = Integer.parseInt(holder.mTextfullamount.getText().toString());
        if (num > 1) {
          num--;
          newlist.get(position).setNumber(num);
          holder.mTextfullamount.setText(newlist.get(position).getNumber() + "");
          // notifyItemChanged(position);
          if (callBackListener != null) {
            callBackListener.updateProduct(newlist.get(position), "2");
          }else {
            CommonUtils.toastMessage("空指针");
          }
        } else {
          CommonUtils.toastMessage("商品数量最少为一件");
        }
      }
    });
    holder.mImgfullplus.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        holder.mImgfullselector.setChecked(true);
        newlist.get(position).setIsselector(true);
        int num = Integer.parseInt(holder.mTextfullamount.getText().toString());
        num++;
        newlist.get(position).setNumber(num);
        holder.mTextfullamount.setText(newlist.get(position).getNumber() + "");
        //notifyItemChanged(position);
        if (callBackListener != null) {
          callBackListener.updateProduct(newlist.get(position), "1");
        } else {
          CommonUtils.toastMessage("空指针");
        }
      }
    });
    holder.mImgfullselector.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        if (holder.mImgfullselector.isChecked()) {
          newlist.get(position).setIsselector(true);
          callBackListener.updateProduct(newlist.get(position), "1");
        } else {
          newlist.get(position).setIsselector(false);
          callBackListener.updateProduct(newlist.get(position), "2");
        }
      }
    });
  }

  @Override public int getItemCount() {
    return null != newlist ? newlist.size() : 0;
  }

  /**
   * 和fragment通信的接口
   */
  public interface onSwipeListener {
    void onDel(int pos);

    void onAdd(int pos);
  }

  private onSwipeListener mOnSwipeListener;

  public onSwipeListener getOnDelListener() {
    return mOnSwipeListener;
  }

  public void setOnDelListener(onSwipeListener mOnDelListener) {
    this.mOnSwipeListener = mOnDelListener;
  }

  class FullDelDemoVH extends RecyclerView.ViewHolder {
    CheckBox mImgfullselector;
    ImageView mImgfullimage;
    ImageView mImgfullminus;
    ImageView mImgfullplus;
    TextView mTextfulltitle, mTextfulllabel, mTextfullprice, mTextfullamount;
    Button btnDelete;
    Button btnUnRead;

    public FullDelDemoVH(View itemView) {
      super(itemView);
      btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
      btnUnRead = (Button) itemView.findViewById(R.id.btnUnRead);
      mImgfullselector = (CheckBox) itemView.findViewById(R.id.img_full_selector);
      mImgfullimage = (ImageView) itemView.findViewById(R.id.img_full_image);
      mTextfulltitle = (TextView) itemView.findViewById(R.id.text_full_title);
      mTextfulllabel = (TextView) itemView.findViewById(R.id.text_full_label);
      mTextfullprice = (TextView) itemView.findViewById(R.id.text_full_price);
      mImgfullminus = (ImageView) itemView.findViewById(R.id.img_full_minus);
      mTextfullamount = (TextView) itemView.findViewById(R.id.text_full_amount);
      mImgfullplus = (ImageView) itemView.findViewById(R.id.img_full_plus);
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

