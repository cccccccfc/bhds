package com.baihuodasha.bhds.bean;

/**
 * @author Admin
 * @time 2017/4/7 9:44
 * @des ${TODO}
 */

public class EditUserInformationBean {
    public int ub_id;
    public String sid;
    public String index;
    public String uo_long;
    public String uo_lat;
    public String uo_high;
    public UserDetail user_detail;//用户信息

    public void setUb_id(int ub_id) {
        this.ub_id = ub_id;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setUo_long(String uo_long) {
        this.uo_long = uo_long;
    }

    public void setUo_lat(String uo_lat) {
        this.uo_lat = uo_lat;
    }

    public void setUo_high(String uo_high) {
        this.uo_high = uo_high;
    }

    public void setUser_detail(UserDetail user_detail) {
        this.user_detail = user_detail;
    }

}
