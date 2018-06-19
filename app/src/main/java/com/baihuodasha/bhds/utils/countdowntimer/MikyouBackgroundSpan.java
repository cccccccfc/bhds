package com.baihuodasha.bhds.utils.countdowntimer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Created by mikyou on 16-10-22.
 */
public class MikyouBackgroundSpan extends ImageSpan {
  private Rect mTextBound;
  private int maxHeight = 0;
  private int maxWidth = 0;
  private int mPaddingLeft = 0;
  private int mPaddingRight = 0;
  private int mPaddingTop = 0;
  private int mPaddingBottom = 0;
  private int mTextColor = Color.GREEN;
  private int mTextSize = 50;

  public MikyouBackgroundSpan(Drawable d, int verticalAlignment) {
    super(d, verticalAlignment);
    mTextBound = new Rect();
  }

  public MikyouBackgroundSpan setTimerTextColor(int mTextColor) {
    this.mTextColor = mTextColor;
    return this;
  }

  public MikyouBackgroundSpan setTimerTextSize(int textSize) {
    this.mTextSize = textSize;
    return this;
  }

  public MikyouBackgroundSpan setTimerPadding(int left, int top, int right, int bottom) {
    this.mPaddingLeft = left;
    this.mPaddingRight = right;
    this.mPaddingBottom = bottom;
    this.mPaddingTop = top;
    return this;
  }

  @Override
  public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y,
      int bottom, Paint paint) {
    //绘制文本的内容的背景
    paint.setTextSize(mTextSize);
    //测量文本的宽度和高度，通过mTextBound得到
    paint.getTextBounds(text.toString(), start, end, mTextBound);
      //drawable将被绘制在canvas的哪个矩形区域内。
    getDrawable().setBounds(4, 4, 40, 40);
    //绘制文本背景
    super.draw(canvas, text, start, end, x, top, y, bottom, paint);
    //设置文本的颜色
    paint.setColor(mTextColor);
    //设置字体的大小
    paint.setTextSize(mTextSize);
    paint.setTypeface(Typeface.DEFAULT_BOLD);
    paint.setStyle(Paint.Style.FILL);
    //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
    paint.setTextAlign(Paint.Align.CENTER);
    int mGapX = 40 / 2+2;
    int mGapY = 40 ;
    canvas.drawText(text.subSequence(start, end).toString(), x + mGapX, mGapY,
        paint);

  }
}
