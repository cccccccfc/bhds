package com.baihuodasha.bhds.databases.searchshopdp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ShopDataBaseDao {
    public SQLiteDatabase mReadShopSQL;
    public SQLiteDatabase mWriteShopSQL;

  public ShopDataBaseDao(Context context) {
        super();
    Context context1 = context;
        mReadShopSQL = SearchShopHistoryHelper.getReadDataBase(context);
        mWriteShopSQL = SearchShopHistoryHelper.getWriteDataBase(context);
    }
}
