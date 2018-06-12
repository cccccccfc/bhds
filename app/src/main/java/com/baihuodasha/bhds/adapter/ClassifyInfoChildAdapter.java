package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class ClassifyInfoChildAdapter extends RecyclerView.Adapter<ClassifyInfoChildAdapter.ViewHolder> {

    private final String title;
    private Context context;
    private List<ChildInfo> list; // List 集合（里面是image+text）

    /**
     * 构造函数
     * @param context
     * @param list
     */
    public ClassifyInfoChildAdapter(Context context, String title, List<ChildInfo> list) {
        this.context = context;
        this.list = list;
        this.title = title;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classify_child_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ChildInfo info = list.get(position);
        Glide.with(context).load(info.getIconImgID()).into(holder.imageInfo);
        holder.child_item_tv1.setText(info.getMenuName());
        holder.linear_back.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                CommonUtils.toastMessage("点击了"+title+"的"+info.getMenuName());
            }
        });
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
        TextView child_item_tv1;
        LinearLayout linear_back;
        public ViewHolder(View itemView) {
            super(itemView);
            imageInfo = (ImageView) itemView.findViewById(R.id.child_item_img);
            child_item_tv1 = (TextView) itemView.findViewById(R.id.child_item_tv1);
            linear_back = (LinearLayout)itemView.findViewById(R.id.linear_back);
        }
    }


}
