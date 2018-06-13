package com.baihuodasha.bhds.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.BadTokenException;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.baihuodasha.bhds.utils.RepeatedClickHandler;
import java.text.ParseException;

/**
 * 
 * 类名称 ：BaseActivity 
 * 类描述 ：Activity基类
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
	public BaseApplication softApplication;
	private boolean isContantMainTitle = true;
	private RepeatedClickHandler repeatedClickHandler;
	private ProgressDialog progressDialog;
	public static String CURRENT_FRAGMENT = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		softApplication = (BaseApplication) getApplicationContext();
	//	AppManager.getAppManager().addActivity(this);
		setFullScreen(false);
		// 处理重复点击
		repeatedClickHandler = new RepeatedClickHandler();
		setContentLayout();
		if (isContantMainTitle) {
			initBaseView();
		}
		dealLogicBeforeInitView();
		initView();
		dealLogicAfterInitView();

	}

	/**
	 * 设置布局，在onCreate()生命周期中回调
	 */
	public abstract void setContentLayout();

	/**
	 * 初始化VIEW，在onCreate()生命周期中回调
	 */
	public abstract void initView();

	/**
	 * 在实例化布局之前处理的逻辑
	 */
	public abstract void dealLogicBeforeInitView();

	/**
	 * 在实例化布局之后处理的逻辑
	 */
	public abstract void dealLogicAfterInitView();

	private void initBaseView() {

	}
	
	public void onResume() {
		super.onResume();


	}

	public void onPause() {
		super.onPause();

	}
	
	/**
	 * 得到屏幕宽度
	 * 
	 * @return 宽度
	 */
	public int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		return screenWidth;
	}

	/**
	 * 得到屏幕高度
	 * 
	 * @return 高度
	 */
	public int getScreenHeight() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenHeight = dm.heightPixels;
		return screenHeight;
	}

	/**
	 * 是否全屏和显示标题，true为全屏和无标题，false为无标题，请在setContentView()方法前调用
	 * 
	 * @param fullScreen
	 */
	public void setFullScreen(boolean fullScreen) {
		if (fullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}

	}
	
	/**
	 * 本段代码用来处理如果输入法还显示的话就消失掉输入键盘
	 */
	protected void dismissSoftKeyboard(Activity activity) {
		try {
			InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManage.hideSoftInputFromWindow(activity
					.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示键盘
	 * @param
	 */
	protected void showKeyboard(View view) {
		try {
			InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManage.showSoftInput(view, InputMethodManager.SHOW_FORCED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 通过Action启动Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * 通过Action启动Activity，并且含有Bundle数据
	 * 
	 * @param pAction
	 * @param pBundle
	 */
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param info
	 */
	public void showToast(String info) {
		Toast toast = Toast.makeText(this, info, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM, 0, 100);
		toast.show();
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param info
	 */
	public void showToastLong(String info) {
		Toast toast = Toast.makeText(this, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM, 0, 100);
		toast.show();
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param
	 */
	public void showToast(int resId) {
		Toast toast = Toast.makeText(this, resId, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM, 0, 100);
		toast.show();
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param
	 */
	public void showToastLong(int resId) {
		Toast toast = Toast.makeText(this, resId, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM, 0, 100);
		toast.show();
	}

	/**
	 * onClick方法的封装，在此方法中处理点击事件
	 * 
	 * @param view
	 */
	abstract public void onClickEvent(View view) throws ParseException;

	@Override
	public void onClick(View v) {
		try {
			onClickEvent(v);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		repeatedClickHandler.handleRepeatedClick(v);
	}



	/**
	 * 显示正在加载的进度条
	 * 
	 */
	public void showProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage("正在加载请稍后...");
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}

	public void showProgressDialog(String msg) {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setMessage(msg);
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 隐藏正在加载的进度条
	 * 
	 */
	public void dismissProgressDialog() {
		if (null != progressDialog && progressDialog.isShowing() == true) {
			progressDialog.dismiss();
		}
	}
	
	@Override
	protected void onDestroy() {
		//AppManager.getAppManager().removeActivity(this);
		super.onDestroy();
	}
	



}
