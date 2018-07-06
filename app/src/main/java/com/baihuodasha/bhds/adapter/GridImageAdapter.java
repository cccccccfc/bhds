package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class GridImageAdapter extends RecyclerView.Adapter<GridImageAdapter.ViewHolder> {

    private ArrayList<String> mList;
    private Context context;
    private List<ChildInfo> list; // List 集合（里面是image+text）
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener = null;
    DisplayMetrics dm;
    /**
     * 构造函数
     * @param context
     * @param list
     */
    public GridImageAdapter(Context context,ArrayList<String> list) {
        this.mList = list;
        this.context = context;
        if(list.size() == 9){
            list.remove(list.size()-1);
        }
        inflater = LayoutInflater.from(context);
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        //动态设置ImageView的宽高，根据自己每行item数量计算
        //dm.widthPixels-dip2px(20)即屏幕宽度-左右10dp+10dp=20dp再转换为px的宽度，最后/3得到每个item的宽高
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((dm.widthPixels - dip2px(20)) / 3, (dm.widthPixels - dip2px(20)) / 3);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(position, mList.get(position));
                }

            }
        });
        final String path=mList.get(position);
        if (path.equals("000000")){
            holder.imageInfo.setImageResource(R.mipmap.phone);
        }else {
            Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.default_error)
                .error(R.mipmap.default_error)
                .centerCrop()
                .crossFade()
                .into(holder.imageInfo);
        }
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
        public ViewHolder(View itemView) {
            super(itemView);
            imageInfo = (ImageView) itemView.findViewById(R.id.imageView);
        }
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
    int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
