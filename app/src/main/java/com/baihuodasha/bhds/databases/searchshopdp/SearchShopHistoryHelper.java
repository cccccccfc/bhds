package com.baihuodasha.bhds.databases.searchshopdp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/11/22.
 */

public class SearchShopHistoryHelper extends SQLiteOpenHelper{
    //数据库名称
    private static final String DB_NAME = "ShopSearch.db";
    private static final String SHOP_DATABASE_NAME = "shop_history_data";
    private static SearchShopHistoryHelper helper;

    //数据库版本号
    private static final int DB_VERSON = 1;
    public SearchShopHistoryHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSON);
    }

    /**
     * 实例化数据库
     */
    public static synchronized SearchShopHistoryHelper getInstance(Context context){
        if(helper == null){
            helper = new SearchShopHistoryHelper(context);
        }
        return helper;
    }
    /**
     * 获取一个可读的数据库
     */
    public static SQLiteDatabase getReadDataBase(Context context){
        getInstance(context);
        return helper.getReadableDatabase();
    }
    /**
     * 获取一个可写的数据库
     */
    public static SQLiteDatabase getWriteDataBase(Context context){
        getInstance(context);
        return helper.getWritableDatabase();
    }
    /**
     * 创建数据库
     */
    public void createDataBase(SQLiteDatabase db){
        db.execSQL("create table "+SHOP_DATABASE_NAME+"(id INTEGER PRIMARY KEY autoincrement,position integer,name text)");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDataBase(db);
    }
    /**
     * 关闭数据库
     */
    public static void closeDataBase(){
        if(null != helper){
            helper.close();
            helper = null;
        }
    }
    //数据库更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
