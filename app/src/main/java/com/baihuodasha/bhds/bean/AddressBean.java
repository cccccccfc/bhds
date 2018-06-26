package com.baihuodasha.bhds.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/18.
 */

public class AddressBean implements Serializable{
    public String id;
    public String memberid;
    public String name;
    public String address;
    public String mobile;

    public String getId() {
        return id;
    }

    public String getMemberid() {
        return memberid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    public String getLocaddress() {
        return locaddress;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getTag() {
        return tag;
    }

    public String gender;

    public void setId(String id) {
        this.id = id;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocaddress(String locaddress) {
        this.locaddress = locaddress;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String locaddress;
    public String longtitude;
    public String latitude;
    public String tag;

}
