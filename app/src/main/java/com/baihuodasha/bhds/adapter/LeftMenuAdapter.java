package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.BigCategoryList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.LeftMenuViewHolder> {
  private Context mContext;
  private List<BigCategoryList.DataBean> mMenuList;
  private int mSelectedNum;
  private List<onItemSelectedListener> mSelectedListenerList;

  public interface onItemSelectedListener {
    public void onLeftItemSelected(int postion, String menu);
  }

  public void addItemSelectedListener(onItemSelectedListener listener) {
    if (mSelectedListenerList != null) mSelectedListenerList.add(listener);
  }

  public void removeItemSelectedListener(onItemSelectedListener listener) {
    if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
      mSelectedListenerList.remove(listener);
    }
  }

  public LeftMenuAdapter(Context mContext, List<BigCategoryList.DataBean> mMenuList) {
    this.mContext = mContext;
    if (mMenuList != null && mMenuList.size() > 0) {
      this.mMenuList = mMenuList;
    } else {
      this.mMenuList = new ArrayList<>();
    }
    this.mSelectedNum = 0;
    this.mSelectedListenerList = new ArrayList<>();
    if (mMenuList != null && mMenuList.size() > 0) mSelectedNum = 0;
  }

  public void addList(List<BigCategoryList.DataBean> newList) {
    if (newList != null && newList.size() > 0) {
      mMenuList.addAll(newList);
      notifyDataSetChanged();
    }
  }

  public void clear() {
    mMenuList.clear();
    notifyDataSetChanged();
  }

  @Override public LeftMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext)
        .inflate(R.layout.itemview_categorize_listmenus, parent, false);
    return new LeftMenuViewHolder(view);
  }

  @Override public void onBindViewHolder(LeftMenuViewHolder holder, final int position) {

    holder.menuName.setText(mMenuList.get(position).getCat_name());
   // Log.i("qaz", "onBindViewHolder: "+mMenuList.get(position).getCat_name());
    if (mSelectedNum != position) {
      holder.menuLayout.setSelected(true);
      holder.menuLayout.setBackgroundColor(Color.parseColor("#f6f6f6"));
      holder.menuName.setTextColor(Color.parseColor("#666666"));
      holder.menuImage.setVisibility(View.GONE);
    } else {
      holder.menuLayout.setSelected(false);
      holder.menuImage.setVisibility(View.VISIBLE);
      holder.menuLayout.setBackgroundColor(Color.parseColor("#ffffff"));
      holder.menuName.setTextColor(Color.parseColor("#000000"));
    }
    holder.menuLayout.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mSelectedNum == position) {
          return;
        }else {
          setSelectedNum(position);
          notifyItemSelected(position);
        }

      }
    });
  }

  @Override public int getItemCount() {
    return mMenuList != null ? mMenuList.size() : 0;
  }

  public void setSelectedNum(int selectedNum) {
    if (selectedNum < getItemCount() && selectedNum >= 0) {
      this.mSelectedNum = selectedNum;
      notifyDataSetChanged();
    }
  }

  public int getSelectedNum() {
    return mSelectedNum;
  }

  public class LeftMenuViewHolder extends RecyclerView.ViewHolder {

    TextView menuName;
    LinearLayout menuLayout;
    ImageView menuImage;

    public LeftMenuViewHolder(final View itemView) {
      super(itemView);
      menuName = (TextView) itemView.findViewById(R.id.textView);
      menuLayout = (LinearLayout) itemView.findViewById(R.id.linear_back);
      menuImage = (ImageView) itemView.findViewById(R.id.lineView);
      menuLayout.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          int clickPosition = getAdapterPosition();

        }
      });
    }
  }

  private void notifyItemSelected(int position) {
    if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
      for (onItemSelectedListener listener : mSelectedListenerList) {
        listener.onLeftItemSelected(position, mMenuList.get(position).getCat_id());
      }
    }
  }
}
