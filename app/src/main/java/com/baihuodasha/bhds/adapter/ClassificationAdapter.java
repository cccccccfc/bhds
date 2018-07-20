package com.baihuodasha.bhds.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.baihuodasha.bhds.bean.BigCategoryList;
import com.baihuodasha.bhds.fragment.FragmentClassificationList;
import java.util.List;

/**
 * author：Anumbrella
 * Date：16/5/26 下午7:08
 */
public class ClassificationAdapter extends FragmentStatePagerAdapter {


    private List<BigCategoryList.DataBean> list;

    public ClassificationAdapter(FragmentManager fm, List<BigCategoryList.DataBean> array) {
        super(fm);
        list = array;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("qaz", "onPageSelected: "+ position);
        Fragment fragment = new FragmentClassificationList();
        Bundle bundle = new Bundle();
        // 把选中的index指针传入过去
        bundle.putString("index", list.get(position).getCat_id());
        // 设定在fragment当中去
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
