package com.baihuodasha.bhds.databases.searchshopdp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ShopDataBaseDao {
    public SQLiteDatabase mReadShopSQL;
    public SQLiteDatabase mWriteShopSQL;
    private Context context;
    public ShopDataBaseDao(Context context) {
        super();
        this.context = context;
        mReadShopSQL = SearchShopHistoryHelper.getReadDataBase(context);
        mWriteShopSQL = SearchShopHistoryHelper.getWriteDataBase(context);
    }
}
