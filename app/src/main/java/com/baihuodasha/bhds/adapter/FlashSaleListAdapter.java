package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.RecommendationBean;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/29.
 */

public class FlashSaleListAdapter extends RecyclerView.Adapter<FlashSaleListAdapter.MyViewHolder> {
  private final Activity mContext;
  private List<RecommendationBean> newlist;

  public FlashSaleListAdapter(Activity mContext, List<RecommendationBean> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.flashsalelist_item, parent, false);
    return new FlashSaleListAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder(MyViewHolder holder, final int position) {
    holder.mTexttitle.setText(newlist.get(position).getTitle());
    holder.mTextlabel.setText(newlist.get(position).getLabel());
    holder.mTextoldprice.setText(newlist.get(position).getOldprice());
    holder.mTextoldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    holder.mTextoldprice.getPaint().setAntiAlias(true);
    holder.mTextprice.getPaint().setAntiAlias(true);
    holder.mProgressbar.setProgress(90);
    holder.mTextprice.setText(newlist.get(position).getPrice());
    Glide.with(mContext).load(newlist.get(position).getUrl()).into(holder.mItemivimage);
    holder.mTextbuy.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        CommonUtils.toastMessage("点击了" + position + "的购买");
      }
    });
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    ProgressBar mProgressbar;
    ImageView mItemivimage;
    TextView mTexttitle, mTextlabel, mTextoldprice, mTextprice, mTextbuy, mTextnum;

    public MyViewHolder(View itemView) {
      super(itemView);
      mTexttitle = (TextView) itemView.findViewById(R.id.recommendatio_text_title);
      mTextlabel = (TextView) itemView.findViewById(R.id.recommendatio_text_label);
      mTextoldprice = (TextView) itemView.findViewById(R.id.recommendatio_text_oldprice);
      mTextprice = (TextView) itemView.findViewById(R.id.recommendatio_text_price);
      mTextbuy = (TextView) itemView.findViewById(R.id.recommendatio_text_buy);
      mItemivimage = (ImageView) itemView.findViewById(R.id.recommendatio_image);
      mProgressbar = (ProgressBar) itemView.findViewById(R.id.recommendatio_progressbar);
      mTextnum = (TextView) itemView.findViewById(R.id.recommendatio_text_num);
    }
  }

  public void addList(List<RecommendationBean> list) {
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
}
