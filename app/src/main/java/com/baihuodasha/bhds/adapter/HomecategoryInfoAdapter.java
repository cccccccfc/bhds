package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.bumptech.glide.Glide;
import java.util.List;


/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class HomecategoryInfoAdapter extends RecyclerView.Adapter<HomecategoryInfoAdapter.ViewHolder> {

    private Context context;
    private List<ChildInfo> list; // List 集合（里面是image+text）

    /**
     * 构造函数
     * @param context
     * @param list
     */
    public HomecategoryInfoAdapter(Context context, List<ChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChildInfo info = list.get(position);
        Glide.with(context).load(info.getIconImgID()).into(holder.imageInfo);
        holder.child_item_tv1.setText(info.getMenuName());
        holder.child_item_tv2.setText(info.getMenuName());
        holder.child_item_tv3.setText(info.getMenuName());
    }

    @Override
    public int getItemCount() {
        return  list != null ? list.size() : 0;
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageInfo;
        TextView child_item_tv1,child_item_tv2,child_item_tv3;

        public ViewHolder(View itemView) {
            super(itemView);
            imageInfo = (ImageView) itemView.findViewById(R.id.child_item_img);
            child_item_tv1 = (TextView) itemView.findViewById(R.id.child_item_tv1);
            child_item_tv2 = (TextView) itemView.findViewById(R.id.child_item_tv2);
            child_item_tv3 = (TextView) itemView.findViewById(R.id.child_item_tv3);
        }
    }


}