package com.baihuodasha.bhds.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by fuwenbo on 2015/5/18.
 */
public class FragmentUtil {
    Fragment[] fragments;

    Fragment curFragment;
    FragmentManager manager;
    int layoutId;
    public FragmentUtil(Fragment[] fragments, FragmentManager manager, int layoutId){
        this.fragments=fragments;
        this.manager=manager;
        this.layoutId=layoutId;
    }
    int index;

    public int getIndex() {
        return index;
    }

    public void switchTo(int index){
        if (null==fragments || fragments.length<=index){
            throw  new IllegalAccessError("越界");
        }
        Fragment fragment=fragments[index];
        FragmentTransaction transaction= manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(layoutId,fragment);
        }
        transaction.show(fragment);
        for (int i=0;i<fragments.length;i++){
            if (i!=index){
                    transaction.hide(fragments[i]);
            }
        }
        curFragment=fragment;
        transaction.commitAllowingStateLoss();
        this.index=index;
    }

    public Fragment getCurFragment(){
        return curFragment;
    }

    public void switchTo(int index,String tag){
        if (null==fragments || fragments.length<=index){
            throw  new IllegalAccessError("越界");
        }
        Fragment fragment=fragments[index];
        FragmentTransaction transaction= manager.beginTransaction();
//        Logger.i("Test","fragment.isAdded():"+fragment.getClass()+"    "+fragment.isAdded());
        if (!fragment.isAdded()) {

            transaction.add(layoutId,fragment,tag);
        }
        transaction.show(fragment);
        for (int i=0;i<fragments.length;i++){
            if (i!=index){
                    transaction.hide(fragments[i]);
            }
        }
        curFragment=fragment;
        transaction.commitAllowingStateLoss();
        this.index=index;
    }


}
