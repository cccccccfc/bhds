package com.baihuodasha.bhds.databases.searchshopdp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class HistoryShopShopDataBaseInfo extends ShopDataBaseDao {
    private static final String SHOP_DATABASE_NAME = "shop_history_data";
    private static final String sql = "select * from "+SHOP_DATABASE_NAME;
    private static HistoryShopShopDataBaseInfo mInfo;
    public HistoryShopShopDataBaseInfo(Context context) {
        super(context);
    }

    /**
     * 实例化
     */
    public static synchronized HistoryShopShopDataBaseInfo getInstance(Context context){
        if(null == mInfo){
            mInfo = new HistoryShopShopDataBaseInfo(context);
        }
        return mInfo;
    }
    /**
     * 增加数据
     */
    public void addHistoryShopName(int position,String name){
        ContentValues values = new ContentValues();
        values.put("position", position);
        values.put("name", name);
        Log.i("qaz", "addHistoryShopName: " +position+name);
        mWriteShopSQL.insert(SHOP_DATABASE_NAME, null, values);
    }
    /**
     * 通过position删除对应的数据
     */
    public void dele2Position(int position){
        mWriteShopSQL.delete(SHOP_DATABASE_NAME, "position=?", new String[]{String.valueOf(position)});
    }
    /**
     * 通过输入文字删除已存在的重复数据
     */
    public void deleRepetition(String s){
        mWriteShopSQL.delete(SHOP_DATABASE_NAME, "content=?", new String[]{s});

    }
    /**
     * 查询数据
     */
    public List<HistoryShopEntity> queryEntit(){
        List<HistoryShopEntity> list = new ArrayList<HistoryShopEntity>();
        Cursor c = mReadShopSQL.rawQuery(sql, null);
        while (c.moveToNext()) {
            HistoryShopEntity entity = new HistoryShopEntity();
            int p = c.getColumnIndex("position");
            int cName = c.getColumnIndex("name");
            entity.position = c.getInt(p);
            entity.name = c.getString(cName);
            list.add(entity);
        }
        return list;
    }

    /**
     * 清空数据库
     */
    public void clearDataBase(){
        mWriteShopSQL.delete(SHOP_DATABASE_NAME, null, null);
    }
}
