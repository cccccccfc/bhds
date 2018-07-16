package com.baihuodasha.bhds.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.baihuodasha.bhds.R;

/**
 * @author  Administrator
 * @time 	2015-7-28 上午11:09:47
 * @des	TODO
 *
 * @version $Rev$
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes TODO
 */
public class SelectSexPopupWindow extends PopupWindow {
	public SelectSexPopupWindow(Context context, OnClickListener listener) {
		View contentView = View.inflate(context, R.layout.activity_dialog_fale, null);
		this.setContentView(contentView);
		this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		// 点击popupwindows范围以外的地方,让其消失
		this.setBackgroundDrawable(new BitmapDrawable());
		this.setOutsideTouchable(true);
		// 添加动画
		this.setAnimationStyle(R.style.PopupAnimation);
		/*	// 控制它放置的位置
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);*/
    //
		//TextView btnFaleMan = (TextView) contentView.findViewById(R.id.bt_fale_man);
		//TextView btnFaleWoman = (TextView) contentView.findViewById(R.id.bt_fale_woman);
    //
		//btnFaleMan.setOnClickListener(listener);
		//btnFaleWoman.setOnClickListener(listener);
	}

}
