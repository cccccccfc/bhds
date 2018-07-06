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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/19.
 */

public class CommentDetailsAdapter
    extends RecyclerView.Adapter<CommentDetailsAdapter.MyViewHolder> {

  private OnItemClickListener mOnItemClickListener = null;
  private final FragmentActivity mContext;
  private List<String> newlist;

  public CommentDetailsAdapter(FragmentActivity mContext, List<String> mList) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.commentontheentry_item, parent, false);
    return new CommentDetailsAdapter.MyViewHolder(view);
  }

  @Override public void onBindViewHolder( MyViewHolder holder, int position) {

    holder.mItemtxinfo.setText("测试测试测试测试测试测测测试测试测试测试试测测试测试测试测试试测试测试测试测试测试测试测试测试试测"
        + "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试"
        + "测试测试试测试测试测试测试测试测试测试测试测测试测试测试测试试测试测试测试测试测试测试");

    ArrayList<String> imageList = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      imageList.add("http://test2.baihuodasha.com//images/201805/1527145330758184757.jpg");
    }
    if (holder.mItemReyimage.getAdapter() == null) {
      if (imageList.size()>0){
        holder.mItemReyimage.setAdapter(new CommentDetailChildsAdapter(mContext ,imageList));
      }
    } else {
      holder.mItemReyimage.getAdapter().notifyDataSetChanged();
    }
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
     RecyclerView mItemReyimage;
    TextView mItemtxinfo;
    LinearLayout mItemlinearl;
    ImageView mItemivimage;
    TextView mItemtxtitle, mItem,mItemtime;

    public MyViewHolder(View itemView) {
      super(itemView);
      mItemtxtitle = (TextView) itemView.findViewById(R.id.tv_comment_details_name);
      mItemtxinfo = (TextView) itemView.findViewById(R.id.tv_comment_details_info);
      mItem = (TextView) itemView.findViewById(R.id.text);
      mItemivimage = (ImageView) itemView.findViewById(R.id.iv_comment_details_headportrait);
      mItemlinearl = (LinearLayout) itemView.findViewById(R.id.ll_comment_details_xing);
      mItemReyimage = (RecyclerView) itemView.findViewById(R.id.ry_comment_details_image);
      mItemtime = (TextView) itemView.findViewById(R.id.txcomment_details_time);

      RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 3);
      // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
      manager.setAutoMeasureEnabled(true);
      mItemReyimage.setLayoutManager(manager);
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
