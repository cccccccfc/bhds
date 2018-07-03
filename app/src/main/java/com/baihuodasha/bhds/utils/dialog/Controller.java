package com.baihuodasha.bhds.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 刘立元 on 2018/5/8.
 */

class Controller {
    private Window mWindow;
    private BaseDialog mDialog;

    public Controller(BaseDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    //获取dialog
    public BaseDialog getDialog(){
        return mDialog;
    }
    //获取dialog的window
    public Window getWindow(){
        return  mWindow;
    }




    public static class ControllerParams {
        public Context mContext;
        public int mThemeResId;
        //点击空白是否能取消
        public boolean mCancelable=true;
        //dialog  Cance监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        //dialog  Dismiss监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        //dialog  Key监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        //布局view
        public View mView;
        //布局的id
        public int mViewLayoutResId;
        //存放字体修改
        public SparseArray<CharSequence> mTextArray=new SparseArray<>();
        //存放点击事件
        public SparseArray<View.OnClickListener> mClickeArray=new SparseArray<>();
        //默认宽度为包裹
//        public int mWith= ViewGroup.LayoutParams.WRAP_CONTENT;

        public int mGrivaty= Gravity.CENTER;
        //动画
        public int mAnimaton=0;
        public int mWidth= ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight= ViewGroup.LayoutParams.WRAP_CONTENT;
        public int y;

        public ControllerParams(Context context, int themeId) {
            this.mContext = context;
            this.mThemeResId = themeId;
        }

        public void aplly(Controller mController) {
            ViewHelper mViewHelper = null;
            //1.渲染布局
            if(mViewLayoutResId!=0){
                mViewHelper=new ViewHelper(mContext,mViewLayoutResId);
//                Log.e("+++++","++++++1");
            }
            if(mView!=null){
                mViewHelper=new ViewHelper();
                mViewHelper.setContentView(mView);
//                Log.e("+++++","++++++2");
            }
//            Log.e("+++++","++++++3");
            if(mViewHelper==null){
                throw new IllegalArgumentException("请设置布局");
            }
            //设置布局
            mController.getDialog().setContentView(mViewHelper.getContentView());
//            Log.e("++++++",mViewLayoutResId+"++++++"+mViewHelper.getContentView());
            //2.设置文本
            for(int i=0;i<mTextArray.size();i++){
                mViewHelper.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
            }

            //3.设置点击
            for(int i=0;i<mClickeArray.size();i++){
                mViewHelper.setClickListener(mClickeArray.keyAt(i),mClickeArray.valueAt(i));
            }
            //4.配置自定义的效果  全屏，底部弹出，动画等
            Window mWinow=mController.getWindow();
            //设置位置
            mWinow.setGravity(mGrivaty);
            if(mAnimaton!=0){
                mWinow.setWindowAnimations(mAnimaton);
            }
            //设置宽高
            WindowManager.LayoutParams params=mWinow.getAttributes();
            params.width=mWidth;
            params.height=mHeight;
            params.y=y;
            mWinow.setAttributes(params);

        }

    }
}
