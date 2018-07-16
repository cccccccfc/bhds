package com.baihuodasha.bhds.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;

/**
 * WebView 加载页面 Created by sun on 2015/7/26.
 */
public class WebViewActivity extends BaseActivity {
  private static final String APP_CACAHE_DIRNAME = "/webcache";
  private static String shop_id, oid, edubills, terminal, mmid, aid;
  private static String ed = "1";
  private static String id, appStatus, object, oid1, source;
  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView tvBaseSave;
  @BindView(R.id.wv) WebView wv;
  //判断是否点击返回按钮
  private boolean isFlag = false;

  public static void start(Context context, String title, String url) {
    Intent intent = new Intent(context, WebViewActivity.class);
    //	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("title", title);
    intent.putExtra("url", url);
    context.startActivity(intent);
  }

  public static void startHBs(Context context, String title, String url, String type) {
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("title", title);
    intent.putExtra("url", url);
    intent.putExtra("type", type);
    context.startActivity(intent);
  }

  public static void start1(Intent i1, Context context, String title, String url) {
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("title", title);
    intent.putExtra("url", url);
    context.startActivities(new Intent[] { i1, intent });
  }

  public static void start2(Context context, String title, String url, String shop_id, String ed,
      String oid, String edubills, String terminal, String mmid, String aid) {
    WebViewActivity.shop_id = shop_id;
    WebViewActivity.ed = ed;
    WebViewActivity.oid = oid;
    WebViewActivity.terminal = terminal;
    WebViewActivity.mmid = mmid;
    WebViewActivity.edubills = edubills;
    WebViewActivity.aid = aid;
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("title", title);
    intent.putExtra("url", url);
    context.startActivity(intent);
  }

  public static void start4(Context context, String title, String url, String id, String appStatus,
      String object, String oid, String source) {
    WebViewActivity.id = id;
    WebViewActivity.oid1 = oid;
    WebViewActivity.appStatus = appStatus;
    WebViewActivity.object = object;
    WebViewActivity.source = source;
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("title", title);
    intent.putExtra("url", url);
    context.startActivity(intent);
  }

  public static void start3(Context context, String title, String url, String ed) {
    Intent intent = new Intent(context, WebViewActivity.class);
    WebViewActivity.ed = ed;
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("title", title);
    intent.putExtra("url", url);
    context.startActivity(intent);
  }

  String title;
  String url;


  @Override public void dealLogicBeforeInitView() {
    // TODO Auto-generated method stub

  }

  @Override public void dealLogicAfterInitView() {
    // TODO Auto-generated method stub

  }

  @Override public void onClickEvent(View view) {
    switch (view.getId()) {
      //		case store_title_bt:
      //			isFlag=true;
      //			mHandler.sendEmptyMessageDelayed(1,10000);
      //			if (ed.equals("ED")) {
      ////				PaySuccess(oid, edubills, terminal, mmid, aid);
      //				return;
      //			}
      ////			SMSuccess(id,appStatus,object,oid1,source);
      //			finish();
      //			break;

      case R.id.iv_base_back:
        Log.e("aaaaa", wv.canGoBack() + "=====");
        if (wv.canGoBack()) {
          wv.goBack();
        } else {
          finish();
        }
        break;
    }
  }


  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_webview);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    ivBaseBack.setOnClickListener(this);
    title = getIntent().getStringExtra("title");
    if (!StringUtil.isNullOrEmpty(title))
    tvBaseTitle.setText(title);
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    url = getIntent().getStringExtra("url");
    WebSettings webSettings = wv.getSettings();
    webSettings.setJavaScriptEnabled(true);
    wv.setWebChromeClient(new WebChromeClient());
    if (!StringUtil.isNullOrEmpty(url)) wv.loadUrl(url);
    wv.addJavascriptInterface(new JavaScriptObject(this), "kqObj");
    wv.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        if (Build.VERSION.SDK_INT < 26) {
          view.loadUrl(url);
          return true;
        }
        return false;
      }
    });
    //wv.setOnKeyListener(new OnKeyListener() {
    //
    //	@Override
    //	public boolean onKey(View v, int keyCode, KeyEvent event) {
    //		if (event.getAction() == KeyEvent.ACTION_DOWN) {
    //			if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
    //				wv.goBack();
    //			}
    //		}
    //		return false;
    //	}
    //});

  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      if (wv.canGoBack()) {
        wv.goBack();// 返回前一个页面
      } else {
        finish();
      }
    }
    return false;
  }

  public class JavaScriptObject {
    Context mContxt;

    // sdk17版本以上加上注解
    public JavaScriptObject(Context mContxt) {
      this.mContxt = mContxt;
    }

    @JavascriptInterface public void closeView() {
      if (ed.equals("ED")) {
        //				PaySuccess(oid, edubills, terminal, mmid, aid);
        return;
      }
      finish();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    //		if(!isFlag){
    //			mHandler.sendEmptyMessageDelayed(1,10000);
    //			Intent intent=new Intent(WebViewActivity.this,MainActivity.class);
    //			intent.putExtra("IS","1");
    //			startActivity(intent);
    //			WebViewActivity.this.finish();
    //		}

  }

  Handler mHandler = new Handler() {
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      if (msg.what == 1) {
        Log.e("id++++", id + "+++++" + oid1);
        SeeBank(oid1);
      }
    }
  };

  //查看银联支付状态接口
  public void SeeBank(String oid) {
    //		ShopRequest.SeeBank(oid,new ApiCallBack2<Msg>(){
    //			@Override
    //			public void onMsgSuccess(Msg param) {
    //				super.onMsgSuccess(param);
    //			}
    //
    //			@Override
    //			public void onMsgFailure(String errMsg) {
    //				super.onMsgFailure(errMsg);
    //			}
    //		});
  }
}