package com.baihuodasha.bhds.activity.index;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.SearchShopHistoryAdapter;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.StoreSearchBean;
import com.baihuodasha.bhds.databases.searchshopdp.HistoryShopEntity;
import com.baihuodasha.bhds.databases.searchshopdp.HistoryShopShopDataBaseInfo;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/9.
 */

public class ActivityCommoditySearch extends BaseActivity {
  private ImageView title_return;
  private EditText search_con;
  //没有搜索结果
  private LinearLayout no_lienar;
  private LayoutInflater mInflater;
  private ListView mListView;
  private String keyword;

  private RelativeLayout his_rl;
  private ImageView im_edit_clear;
  private LinearLayout linear_group;
  private ListView search_history_list;
  //历史搜索的数据库
  public HistoryShopShopDataBaseInfo historyShopDataBaseInfo;
  //历史搜索的显示内容
  public List<HistoryShopEntity> showEntities;
  private SearchShopHistoryAdapter searchHistoryAdapter;
  private TagFlowLayout id_flowlayout;
  private TagAdapter tagAdapter;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_shopsearch);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
    mInflater = LayoutInflater.from(this);
    historyShopDataBaseInfo = HistoryShopShopDataBaseInfo.getInstance(this);
    showEntities = new ArrayList<>();
  }

  @Override public void initView() {

    search_con = (EditText) findViewById(R.id.search_con);
    TextView search_tv = (TextView) findViewById(R.id.search_tv);
    search_tv.setOnClickListener(this);
    no_lienar = (LinearLayout) findViewById(R.id.no_lienar);
    mListView = (ListView) findViewById(R.id.listview);
    his_rl = (RelativeLayout) findViewById(R.id.his_rl); //历史搜索
    linear_group = (LinearLayout) findViewById(R.id.linear_group); //历史搜索条目
    ImageView history_del = (ImageView) findViewById(R.id.history_del);
    history_del.setOnClickListener(this);
    im_edit_clear = (ImageView) findViewById(R.id.im_edit_clear); //输入内容删除
    im_edit_clear.setOnClickListener(this);
    RelativeLayout remn_rl = (RelativeLayout) findViewById(R.id.remn_rl);
    id_flowlayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
    TagFlowLayout id_flow_remen = (TagFlowLayout) findViewById(R.id.id_flow_remen);
    search_con.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
          linear_group.setVisibility(View.VISIBLE);
          his_rl.setVisibility(View.VISIBLE);
        } else {
          linear_group.setVisibility(View.GONE);
          his_rl.setVisibility(View.GONE);
        }
      }
    });
    search_con.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          keyword = search_con.getText().toString();
          if (TextUtils.isEmpty(keyword)) {
            CommonUtils.toastMessage("请输入你要搜索的商品");
           // Toast.makeText(CommoditySearchActivity.this, "请输入你要搜索的地址", Toast.LENGTH_SHORT).show();
          } else {
            if (historyShopDataBaseInfo.queryEntit() != null
                && historyShopDataBaseInfo.queryEntit().size() > 0) {
              List<String> content = new ArrayList<String>();
              for (int i = 0; i < historyShopDataBaseInfo.queryEntit().size(); i++) {
                content.add(historyShopDataBaseInfo.queryEntit().get(i).name);
              }
              if (!content.contains(search_con.getText().toString())) {
                historyShopDataBaseInfo.addHistoryShopName(
                    historyShopDataBaseInfo.queryEntit().size(), keyword);
              }
            } else {
              historyShopDataBaseInfo.addHistoryShopName(
                  historyShopDataBaseInfo.queryEntit().size(), keyword);
            }
            CommonUtils.toastMessage("暂无商品");
            // getList(keyword, shopid, true);
          }
          return true;
        }
        return false;
      }
    });
    search_con.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        linear_group.setVisibility(View.VISIBLE);
        his_rl.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
        no_lienar.setVisibility(View.GONE);
        Historysearch();
      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null && s.length() > 0) {
          im_edit_clear.setVisibility(View.VISIBLE);
        } else {
          im_edit_clear.setVisibility(View.GONE);
        }
      }

      @Override public void afterTextChanged(Editable s) {
        if (!search_con.getText().toString().equals("")) {
          keyword = search_con.getText().toString();
        }
      }
    });
    Historysearch();
  }

  @Override public void dealLogicBeforeInitView() {

  }

  @Override public void dealLogicAfterInitView() {

    //adapter = new StoreSearchAdapter(this, null, this);
    //mListView.setAdapter(adapter);
    // Log.i("qaz", "dealLogicAfterInitView: " + historyShopDataBaseInfo.queryEntit());
    Historysearch();
  }

  private void Historysearch() {

    no_lienar.setVisibility(View.GONE);
    mListView.setVisibility(View.GONE);
    if (historyShopDataBaseInfo.queryEntit() != null
        && historyShopDataBaseInfo.queryEntit().size() > 0) {
      showEntities.clear();
      showEntities.addAll(historyShopDataBaseInfo.queryEntit());
    }

    tagAdapter = new TagAdapter(showEntities) {
      @Override public View getView(FlowLayout parent, int position, Object o) {
        TextView tv =
            (TextView) mInflater.inflate(R.layout.shop_search_histroy, id_flowlayout, false);
        tv.setText(showEntities.get(position).name);
        return tv;
      }
    };
    id_flowlayout.setAdapter(tagAdapter);
    id_flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {

        search_con.setText(showEntities.get(i).name);
        search_con.setSelection(search_con.getText().toString().length());
        //getList(showEntities.get(i).name, shopid, true);
        tagAdapter.setSelectedList(i);
        return true;
      }
    });
    id_flowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
      @Override public void onSelected(Set<Integer> selectPosSet) {
        //Log.i("qaz", "onSelected: "+selectPosSet.hashCode());
      }
    });

  }

  @Override public void onClickEvent(View view) {
    switch (view.getId()) {
      case R.id.im_edit_clear:
        search_con.setText("");
        break;
      case R.id.search_tv:
        hintKeyboard();
        finish();
        //keyword = search_con.getText().toString();
        //if (TextUtils.isEmpty(keyword)) {
        //  Toast.makeText(ShopSearchActivity.this, "请输入你要搜索的地址", Toast.LENGTH_SHORT).show();
        //} else {
        //  if (historyShopDataBaseInfo.queryEntit() != null
        //      && historyShopDataBaseInfo.queryEntit().size() > 0) {
        //    List<String> content = new ArrayList<String>();
        //    for (int i = 0; i < historyShopDataBaseInfo.queryEntit().size(); i++) {
        //      content.add(historyShopDataBaseInfo.queryEntit().get(i).name);
        //    }
        //    if (!content.contains(search_con.getText().toString())) {
        //      historyShopDataBaseInfo.addHistoryShopName(
        //          historyShopDataBaseInfo.queryEntit().size(), keyword);
        //    }
        //  } else {
        //    historyShopDataBaseInfo.addHistoryShopName(historyShopDataBaseInfo.queryEntit().size(),
        //        keyword);
        //  }
        //}
        //    getList(keyword, shopid, true);
        //  Toast.makeText(this, "暂无商品", Toast.LENGTH_SHORT).show();
        break;

      case R.id.history_del:
        showDialog();
        break;
    }
  }

  //获取搜索数据
  private List<StoreSearchBean> goods;

  public void search() {
    String searchContext = search_con.getText().toString().trim();
    if (TextUtils.isEmpty(searchContext)) {
      showToast("输入框为空，请输入");
    } else {
      //   getList(keyword, shopid, true);
    }
  }

  AlertDialog dialog_del;
  public void showDialog() {
    dialog_del = new AlertDialog.Builder(this).create();
    View view = View.inflate(this, R.layout.dialog_del, null);
    TextView cancel = (TextView) view.findViewById(R.id.cancel);
    cancel.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        dialog_del.dismiss();
      }
    });
    TextView yes = (TextView) view.findViewById(R.id.yes);
    yes.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (historyShopDataBaseInfo.queryEntit() != null
            && historyShopDataBaseInfo.queryEntit().size() > 0) {
          historyShopDataBaseInfo.clearDataBase();
          showEntities.clear();
          linear_group.setVisibility(View.GONE);
          his_rl.setVisibility(View.GONE);
        }else {
          CommonUtils.toastMessage("暂无历史记录");
        }

        dialog_del.dismiss();
      }
    });
    dialog_del.show();
    dialog_del.getWindow().setContentView(view);
  }

  private void hintKeyboard() {
    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    if(imm.isActive()&&getCurrentFocus()!=null){
      if (getCurrentFocus().getWindowToken()!=null) {
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
      }
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();

  }
}
