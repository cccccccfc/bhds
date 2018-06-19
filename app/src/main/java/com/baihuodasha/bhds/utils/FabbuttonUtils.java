package com.baihuodasha.bhds.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by zhang on 2018/6/19.
 */

public class FabbuttonUtils {

  private static View contentView;
  private static int scrollY = 0;// 标记上次滑动位置

  public static void FabbuttonUtil(Context context, final ScrollInterceptScrollView mScrollView,

      final FloatingActionButton mFabbutton) {

    if (contentView == null) {
      contentView = mScrollView.getChildAt(0);
    }
    /******************** 监听ScrollView滑动停止 *****************************/
    mScrollView.setOnTouchListener(new View.OnTouchListener() {
      private int lastY = 0;
      private int touchEventId = -9983761;
      Handler handler = new Handler() {
        @Override public void handleMessage(Message msg) {
          super.handleMessage(msg);
          View scroller = (View) msg.obj;
          if (msg.what == touchEventId) {
            if (lastY == scroller.getScrollY()) {
              handleStop(scroller);
            } else {
              handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 5);
              lastY = scroller.getScrollY();
            }
          }
        }
      };

      public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
          handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
        }
        return false;
      }

      /**
       * ScrollView 停止
       *
       * @param view
       */
      private void handleStop(Object view) {
        ScrollView scroller = (ScrollView) view;
        scrollY = scroller.getScrollY();
        // 底部判断
        if (contentView != null
            && contentView.getMeasuredHeight()
            <= mScrollView.getScrollY() + mScrollView.getHeight()) {
          mFabbutton.setVisibility(View.VISIBLE);
        }
        // 顶部判断
        else if (mScrollView.getScrollY() == 0) {
          mFabbutton.setVisibility(View.GONE);
        } else if (mScrollView.getScrollY() > 30) {
          mFabbutton.setVisibility(View.VISIBLE);
        }
      }
    });
  }
}
