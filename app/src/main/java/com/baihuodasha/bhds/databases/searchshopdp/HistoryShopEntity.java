package com.baihuodasha.bhds.databases.searchshopdp;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/22.
 */

public class HistoryShopEntity implements Serializable{
    public int position;
    public String name;

    @Override public String toString() {
        return "HistoryShopEntity{" + "position=" + position + ", name='" + name + '\'' + '}';
    }
}
