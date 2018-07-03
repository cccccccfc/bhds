package com.baihuodasha.bhds.utils.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/**
 * Created by 刘立元 on 2018/5/8.
 */

class ViewHelper {
    private Context mContext;
    private View mView;
    private SparseArray<WeakReference<View>> mViews;

    public ViewHelper(Context context, int viewLayoutResId) {
        this();
        this.mContext = context;
        this.mView = LayoutInflater.from(mContext).inflate(viewLayoutResId, null,false);
//        this.mView=View.inflate(mContext,viewLayoutResId,null);

    }

    public ViewHelper() {
        mViews = new SparseArray<>();
    }


    public void setContentView(View view) {
        this.mView = view;
    }

    public void setText(int viewId, CharSequence charSequence) {
        TextView tv = getView(viewId);
        if (tv != null) {
            tv.setText(charSequence);
        }

    }


    public void setClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public View getContentView() {
        return mView;
    }

    public  <T extends View> T getView(int viewId) {
        WeakReference<View> viewReference = mViews.get(viewId);
        View view = null;
        if (viewReference != null) {
            view = viewReference.get();
        }
        if (view == null) {
            view = mView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<View>(view));
            }

        }
        return (T) view;
    }
}
