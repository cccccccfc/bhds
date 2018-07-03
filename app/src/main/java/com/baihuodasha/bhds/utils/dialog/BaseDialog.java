package com.baihuodasha.bhds.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import com.baihuodasha.bhds.R;

/**
 * Created by 刘立元 on 2018/5/8.
 */

public class BaseDialog extends Dialog {

    private static Controller mController;

    public BaseDialog( Context context, int themeResId) {
        super(context, themeResId);
        mController = new Controller(this, getWindow());
    }


    public static class Builder {
        private Controller.ControllerParams P;

        public Builder(Context context){
            this(context, R.style.baseDialog);
        }

        public Builder(Context context, int themeId) {
            P = new Controller.ControllerParams(context, themeId);
        }


        public BaseDialog onCreat(){
            final  BaseDialog myDialog=new BaseDialog(P.mContext,P.mThemeResId);
            P.aplly(mController);
            myDialog.setCancelable(P.mCancelable);
            myDialog.setOnCancelListener(P.mOnCancelListener);
            myDialog.setOnDismissListener(P.mOnDismissListener);
            if(P.mOnKeyListener!=null){
                myDialog.setOnKeyListener(P.mOnKeyListener);
            }
            return  myDialog;
        }

        public Builder setContentView(View view){
            P.mView=view;
            P.mViewLayoutResId=0;
            return  this;
        }

        public Builder setContentView(int layoutId){
            P.mView=null;
            P.mViewLayoutResId=layoutId;
            return  this;
        }

        public Builder setCancelable(boolean cancelable){
            P.mCancelable = cancelable;
            return  this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener){
            P.mOnCancelListener = onCancelListener;
            return  this;
        }

        public Builder setOnDismissListener( OnDismissListener onDismissListener){
            P.mOnDismissListener = onDismissListener;
            return  this;
        }
        public Builder setOnKeyListener( OnKeyListener onKeyListener){
            P.mOnKeyListener = onKeyListener;
            return  this;
        }

        //设置文本
        public Builder setText(int viewId,CharSequence charSequence){
            P.mTextArray.put(viewId,charSequence);
            return  this;
        }
        //设置点击事件
        public Builder setOnClickListener(int viewId,View.OnClickListener onClickListener){
            P.mClickeArray.put(viewId,onClickListener);
            return  this;
        }
        //设置全屏
        public Builder fullWith(){
            P.mWidth= ViewGroup.LayoutParams.MATCH_PARENT;
            return  this;
        }
        //从底部弹出 是否有动画
        public Builder fromBottom(boolean isAnimation){
            if(isAnimation){
                P.mAnimaton=R.style.dialog_from_bottom;
            }
            P.mGrivaty= Gravity.BOTTOM;
            return  this;
        }
        //设置宽高
        public Builder setWihtAndHeight(int width,int height){
            P.mWidth=width;
            P.mHeight=height;
            return  this;
        }
        //显示在上不
        public Builder fromTop(int y){
            P.mGrivaty= Gravity.TOP;
            P.y=y;
            return  this;
        }
        //默认动画
        public Builder defuatAnimation(){
            P.mAnimaton=R.style.dialog_sf_animation;
            return this;
        }

        //添加动画
        public Builder setAnimation(int style){
            P.mAnimaton=style;
            return  this;
        }
        //设置点击空白处是否消失
        public Builder setCanceble(boolean cancel){
            P.mCancelable=cancel;
            return this;
        }

        public BaseDialog show() {
            final BaseDialog dialog = onCreat();
            dialog.show();
            return dialog;
        }

    }

}
