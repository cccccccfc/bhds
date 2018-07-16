package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.MyGridView;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/6/19.
 */

public class CommodityEvaluationAdapter
    extends RecyclerView.Adapter<CommodityEvaluationAdapter.MyViewHolder> {

  private static final int REQUEST_CAMERA_CODE = 10;
  private static final int REQUEST_PREVIEW_CODE = 20;

  private OnItemClickListener mOnItemClickListener = null;
  private final Activity mContext;
  private List<String> newlist;
  private ArrayList<String> urllist = new ArrayList<>();
  private List<Integer> showing = new ArrayList<>();

  public CommodityEvaluationAdapter(Activity mContext, List<String> mList,
      ArrayList<String> listUrls) {
    this.mContext = mContext;
    if (mList != null && mList.size() > 0) {
      this.newlist = mList;
    } else {
      newlist = new ArrayList<>();
    }
    if (listUrls != null && listUrls.size() > 0) {
      this.urllist = listUrls;
    } else {
      newlist = new ArrayList<>();
    }
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.commodity_evaluation_item, parent, false);

    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, int position) {
    holder.gridView.setAdapter(new GridAdapter(mContext, urllist));

    holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String imgs = (String) parent.getItemAtPosition(position);
        if ("000000".equals(imgs)) {
          PhotoPickerIntent intent = new PhotoPickerIntent(mContext);
          intent.setSelectModel(SelectModel.MULTI);
          intent.setShowCarema(true); // 是否显示拍照
          intent.setMaxTotal(9); // 最多选择照片数量，默认为6
          intent.setSelectedPaths(urllist); // 已选中的照片地址， 用于回显选中状态
          mContext.startActivityForResult(intent, REQUEST_CAMERA_CODE);
        } else {
          PhotoPreviewIntent intent = new PhotoPreviewIntent(mContext);
          intent.setCurrentItem(position);
          intent.setPhotoPaths(urllist);
          mContext.startActivityForResult(intent, REQUEST_PREVIEW_CODE);
        }
      }
    });

    holder.ivCommodityEvaluationRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
      @Override public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        CommonUtils.toastMessage("评分星级=" + rating);
      }
    });
    //if (holder.gridView.getAdapter() == null) {
    //  holder.gridView.setAdapter(new GridImageAdapter(mContext,urllist));
    //} else {
    //  holder.gridView.getAdapter().notifyDataSetChanged();
    //}
  }

  @Override public int getItemCount() {
    return newlist != null ? newlist.size() : 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

     RatingBar ivCommodityEvaluationRatingbar;
    MyGridView gridView;
    CheckBox ckCommodityEvaluationSelection;
    ImageView ivCommodityEvaluationImage;
    TextView tvCommodityEvaluationTitle, tvCommodityEvaluationLabel;
    EditText tetCommodityEvaluationText;

    public MyViewHolder(View itemView) {
      super(itemView);
      ivCommodityEvaluationImage =
          (ImageView) itemView.findViewById(R.id.iv_commodity_evaluation_image);
      tvCommodityEvaluationTitle =
          (TextView) itemView.findViewById(R.id.tv_commodity_evaluation_title);
      tvCommodityEvaluationLabel =
          (TextView) itemView.findViewById(R.id.tv_commodity_evaluation_label);
      ivCommodityEvaluationRatingbar =
          (RatingBar) itemView.findViewById(R.id.iv_commodity_evaluation_ratingbar);
      tetCommodityEvaluationText =
          (EditText) itemView.findViewById(R.id.tet_commodity_evaluation_text);
      ckCommodityEvaluationSelection =
          (CheckBox) itemView.findViewById(R.id.ck_commodity_evaluation_selection);
      gridView = (MyGridView) itemView.findViewById(R.id.gridView);

      //RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 4);
      //// 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
      //manager.setAutoMeasureEnabled(true);
      //gridView.setLayoutManager(manager);
    }
  }

  public void addList(List<String> list, ArrayList<String> imagelist) {
    Log.i("qaz", "addList: " + list.size());

    if (list != null && list.size() > 0) {
      newlist.addAll(list);
      if (imagelist != null && imagelist.size() > 0) {
        urllist.clear();
        urllist.addAll(imagelist);
      }
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

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemViewType(int position) {
    return position;
  }
}
