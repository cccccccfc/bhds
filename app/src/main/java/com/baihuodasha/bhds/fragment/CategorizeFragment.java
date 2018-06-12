package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ShopSearchActivity;
import com.baihuodasha.bhds.adapter.CategorizeProductAdapter;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.utils.CustomViewPager;

/**
 * author：Anumbrella
 * Date：16/5/24 下午8:04
 */
public class CategorizeFragment extends BaseFragment implements View.OnClickListener{

  private LayoutInflater linflater;
  private CategorizeProductAdapter categorizeProductAdapter;
  private ImageView[] shopViews;
  private TextView[] listMenuTextViews;
  private LinearLayout[] linearLayout;
  private Bundle savedState;

  /**
   * 默认的ViewPager选中的项
   */
  private int currentItem = 0;

  @BindView(R.id.goods) CustomViewPager viewPager;
  @BindView(R.id.tools_scrollView) ScrollView tools_scrollView;
  @BindView(R.id.title_return) ImageView title_return;
  @BindView(R.id.lin_seach) LinearLayout lin_seach;
  @BindView(R.id.img_message) ImageView img_message;
  private View view;
  private String[] listMenus;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_categorize, container, false);
    ButterKnife.bind(this, view);
    title_return.setVisibility(View.GONE);
    img_message.setVisibility(View.GONE);
    setHasOptionsMenu(true);
    linflater = LayoutInflater.from(getContext());
    initTools(view);
    initViewPager();
    return view;
  }

  private void initViewPager() {
    lin_seach.setOnClickListener(this);
    // 由于使用了支持包所以最终必须确保所有的导入包都是来自支持包
    categorizeProductAdapter = new CategorizeProductAdapter(getChildFragmentManager(), listMenus);
    viewPager.setAdapter(categorizeProductAdapter);
    // 为ViewPager设置页面变化的监控
    viewPager.addOnPageChangeListener(onPageChangeListener);
  }

  ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override public void onPageSelected(int position) {

      if (viewPager.getCurrentItem() != position) {
        viewPager.setCurrentItem(position);
      }
      // 通过ViewPager监听点击字体颜色和背景的改变
      if (currentItem != position) {

        changeTextColor(position);
        //changeTextLocation(position);
      }
      currentItem = position;
    }

    @Override public void onPageScrollStateChanged(int state) {

    }
  };

  /**
   * 初始化左边目录
   */
  private void initTools(View layoutView) {
    listMenus = Config.categorizeTools;
    listMenuTextViews = new TextView[listMenus.length];
    linearLayout = new LinearLayout[listMenus.length];
    shopViews = new ImageView[listMenus.length];
    LinearLayout toolsLayout = (LinearLayout)layoutView.findViewById(R.id.recyclerView_tools);
    for (int i = 0; i < listMenus.length; i++) {
       view = linflater.inflate(R.layout.itemview_categorize_listmenus, null);
      // 给每个View设定唯一标识
      view.setId(i);
      // 给每个view添加点击监控事件
      view.setOnClickListener(ListItemMenusClickListener);
      // 获取到左侧栏的的TextView的组件
      TextView textView = (TextView) view.findViewById(R.id.textView);
      LinearLayout linear_back =  (LinearLayout) view.findViewById(R.id.linear_back);
      ImageView imageView = (ImageView) view.findViewById(R.id.lineView);
      textView.setText(listMenus[i]);
      toolsLayout.addView(view);
      // 传入的是地址不是复制的值
      listMenuTextViews[i] = textView;
      linearLayout[i] =linear_back;
      shopViews[i] = imageView;
    }
    changeTextColor(0);
  }

  private void changeTextColor(int position) {
    for (int i = 0; i < listMenus.length; i++) {
      if (position != i) {
        linearLayout[i].setBackgroundColor(Color.parseColor("#f6f6f6"));
        listMenuTextViews[i].setTextColor(Color.parseColor("#666666"));
        shopViews[i].setVisibility(View.GONE);
      }
    }
    shopViews[position].setVisibility(View.VISIBLE);
    linearLayout[position].setBackgroundColor(Color.parseColor("#ffffff"));
    listMenuTextViews[position].setTextColor(Color.parseColor("#000000"));
  }

  private void changeTextLocation(int clickPosition) {
    int y = (shopViews[clickPosition].getTop());
    // 如果滑动条可以滑动的情况下就把点击的视图移动到顶部
    tools_scrollView.smoothScrollTo(0, y);
  }

  View.OnClickListener ListItemMenusClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      Log.i("qaz", "onClick: "+v.getId());
      viewPager.setCurrentItem(v.getId());
    }
  };

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    return super.onOptionsItemSelected(item);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    restoreState();
   // changeTextColor(currentItem);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    savedState = saveState();
  }

  private void restoreState() {
    if (savedState != null) {
      currentItem = savedState.getInt("index");
    }
  }

  private Bundle saveState() {
    Bundle state = new Bundle();
    state.putInt("index", currentItem);
    return state;
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {

      case  R.id.lin_seach:
        Intent intent = new Intent(getActivity(), ShopSearchActivity.class);
        startActivity(intent);
        break;
    }
  }
}
