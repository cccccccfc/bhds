package com.baihuodasha.bhds.utils.assistant;

import com.baihuodasha.bhds.bean.ShopProduct;

/**
 * Created by 曹博 on 2016/6/7.
 * 购物车添加接口回调
 */
public interface ShopToDetailListener {
    /**
     * Type表示添加或减少 1 加 2 减
     * @param product
     * @param type
     */
    void onUpdateDetailList(ShopProduct product, String type);

    void onRemovePriduct(ShopProduct product);
}
