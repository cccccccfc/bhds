package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ActivityCommoditySearch;
import com.baihuodasha.bhds.adapter.ClassifyInfoAdapter;
import com.baihuodasha.bhds.adapter.LeftMenuAdapter;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.bean.BigCategoryList;
import com.baihuodasha.bhds.bean.GetChildTreeByCatId;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.view.MyDialog;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author：Anumbrella
 * Date：16/5/24 下午8:04
 */
public class FragmentClassification extends BaseFragment
    implements View.OnClickListener, LeftMenuAdapter.onItemSelectedListener {

  @BindView(R.id.search_con) TextView searchCon;
  @BindView(R.id.title) RelativeLayout title;
  @BindView(R.id.recyclerView_tools) RecyclerView recyclerViewTools;
  @BindView(R.id.item_iv_image) ImageView itemIvImage;
  @BindView(R.id.RecyclerViewList) RecyclerView RecyclerViewList;
  @BindView(R.id.tools_scrollView) ScrollView tools_scrollView;
  @BindView(R.id.title_return) ImageView title_return;
  @BindView(R.id.lin_seach) LinearLayout lin_seach;
  @BindView(R.id.img_message) ImageView img_message;
  private static List<BigCategoryList.DataBean> listMenus;
  private List<BigCategoryList.DataBean> mens;
  private FragmentClassificationList goodsFragment;
  private LeftMenuAdapter leftAdapter;
  private ClassifyInfoAdapter rightadapter;
  private List<GetChildTreeByCatId.DataBean> rightList;
  private MyDialog loadDialog;
  private boolean shouLoad = true;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_categorize, container, false);
    ButterKnife.bind(this, view);
    title_return.setVisibility(View.GONE);
    img_message.setVisibility(View.GONE);
    setHasOptionsMenu(true);
    lin_seach.setOnClickListener(this);
    initTools();
    return view;
  }

  /**
   * 初始化左边目录
   */
  private void initTools() {
    //左侧
    LinearLayoutManager linearLayoutl = new LinearLayoutManager(getActivity());
    linearLayoutl.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewTools.setLayoutManager(linearLayoutl);
    leftAdapter = new LeftMenuAdapter(getActivity(), null);
    recyclerViewTools.setAdapter(leftAdapter);
    leftAdapter.addItemSelectedListener(this);
    //  右侧
    LinearLayoutManager linearLayoutr = new LinearLayoutManager(getActivity());
    linearLayoutr.setOrientation(LinearLayoutManager.VERTICAL);
    RecyclerViewList.setLayoutManager(linearLayoutr);
    rightadapter = new ClassifyInfoAdapter(getActivity(), null);
    RecyclerViewList.setAdapter(rightadapter);
    SdjNetWorkManager.sendBigCategoryList(new Callback() {
      @Override public void onResponse(Call call, Response response) {
        BigCategoryList msg = (BigCategoryList) response.body();
        if (msg != null) {
          listMenus = (ArrayList<BigCategoryList.DataBean>) msg.getData();
          if (listMenus != null) {
            leftAdapter.addList(listMenus);
            initRight(listMenus.get(0).getCat_id());
          }
        } else {
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
      }
    });
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {

      case R.id.lin_seach:
        Intent intent = new Intent(getActivity(), ActivityCommoditySearch.class);
        startActivity(intent);
        break;
    }
  }

  @Override public void onLeftItemSelected(int postion, String menu) {
    initRight(menu);
  }

  private void initRight(String cat_id) {
    if (shouLoad) {
      loadDialog = MyDialog.showDialog(getActivity());
      //shouLoad = false;
      loadDialog.show();
    }
    SdjNetWorkManager.sendGetChildTreeByCatId(cat_id, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        GetChildTreeByCatId msg = (GetChildTreeByCatId) response.body();
        if (msg != null) {
          if (loadDialog != null) {
            loadDialog.dismiss();
            loadDialog = null;
          }
          rightList = (ArrayList<GetChildTreeByCatId.DataBean>) msg.getData();
          if (rightList != null) {
            rightadapter.clear();
            rightadapter.addList(rightList);
          }else {

          }
        } else {
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        if (loadDialog != null) {
          loadDialog.dismiss();
          loadDialog = null;
        }
      }
    });
  }
}
