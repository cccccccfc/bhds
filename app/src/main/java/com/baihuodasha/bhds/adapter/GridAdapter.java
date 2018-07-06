package com.baihuodasha.bhds.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.baihuodasha.bhds.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

/**
 * Created by zhang on 2018/7/5.
 */

public class GridAdapter extends BaseAdapter {
  private  Activity mContext;
  private ArrayList<String> listUrls;
  private LayoutInflater inflater;
  public GridAdapter(Activity mContext ,ArrayList<String> listUrls) {
    this.listUrls = listUrls;
    this.mContext = mContext;
    if(listUrls.size() == 9){
      listUrls.remove(listUrls.size()-1);
    }
    inflater = LayoutInflater.from(mContext);
  }


  public int getCount(){
    return  listUrls.size();
  }
  @Override
  public String getItem(int position) {
    return listUrls.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
    if (convertView == null) {
      holder = new ViewHolder();
      convertView = inflater.inflate(R.layout.item_image, parent,false);
      holder.image = (ImageView) convertView.findViewById(R.id.imageView);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder)convertView.getTag();
    }

    final String path=listUrls.get(position);
    if (path.equals("000000")){
      holder.image.setImageResource(R.mipmap.phone);
    }else {
      Glide.with(mContext)
          .load(path)
          .placeholder(R.mipmap.default_error)
          .error(R.mipmap.default_error)
          .centerCrop()
          .crossFade()
          .into(holder.image);
    }
    return convertView;
  }
  class ViewHolder {
    ImageView image;
  }
}
