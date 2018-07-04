package com.baihuodasha.bhds.utils.popupwindow;

import java.io.Serializable;

/**
 * Created by qiaoyaqian on 17/12/19.
 */

public class SpecModel implements Serializable {
    /**
     * "spec":"商品规格",
     "price":"商品规格价格"
     */
    public String spec;
    public double price;
    public String specid;

    @Override
    public String toString() {
        return "SpecModel{" +
                "spec='" + spec + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
