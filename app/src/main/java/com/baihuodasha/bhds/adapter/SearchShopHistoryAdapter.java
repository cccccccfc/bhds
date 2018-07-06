package com.baihuodasha.bhds.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ActivityCommoditySearch;
import com.baihuodasha.bhds.databases.searchshopdp.HistoryShopEntity;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class SearchShopHistoryAdapter extends BaseAdapter{
    List<HistoryShopEntity> list;
    private Context mContext;

    public SearchShopHistoryAdapter( ActivityCommoditySearch context,
        List<HistoryShopEntity> list) {
        this.mContext=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        if(list!=null&&list.size()>0){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if(view==null){
            vh=new ViewHolder();
            view=View.inflate(mContext, R.layout.shop_search_histroy,null);
            vh.history_text= (TextView) view.findViewById(R.id.history_text);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        HistoryShopEntity model=list.get(i);
        if(model!=null){
            vh.history_text.setText(model.name);
        }
        return view;
    }
    class ViewHolder{
        TextView history_text;
    }
}
