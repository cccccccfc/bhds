package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ShopSearchActivity;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.utils.tablayout.TabLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IMXU
 * @time 2017/5/3 13:21
 * @des 资讯首页
 * 邮箱：butterfly_xu@sina.com
 */
public class FragmentHome extends BaseFragment implements View.OnClickListener {
  private ImageView titleReturn;
  private TextView searchCon;
  private RelativeLayout title;
  private TabLayout mTabTl;
  private ViewPager mContentVp;
  private LinearLayout linSeach;

  private List<String> tabIndicators;
  private List<Fragment> tabFragments;
  private ContentPagerAdapter contentAdapter;
  private View view;

  @Override public View initView(LayoutInflater inflater) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_home, null);
    }

    ViewGroup parent = (ViewGroup) view.getParent();
    if (parent != null) {
      parent.removeView(view);
    }

    //StatusBarCompat.initSystemBar(getActivity(), R.color.titleBar);
    titleReturn = (ImageView)view.findViewById(R.id.title_return);
    searchCon = (TextView)view.findViewById(R.id.search_con);
    title = (RelativeLayout)view.findViewById(R.id.title);
    mTabTl = (TabLayout)view.findViewById(R.id.tl_tab);
    mContentVp = (ViewPager)view.findViewById(R.id.vp_content);
    linSeach = (LinearLayout)view.findViewById(R.id.lin_seach);

    ButterKnife.bind(this,view);
    return view;
  }

  @Override public void init() {
    super.init();
  }

  @Override public void initdata() {
    super.initdata();
    initContent();
    initTab();
  }

  @Override public void initListener() {
    super.initListener();
  }

  @Override public void onClick(View v) {

  }

  private void initTab() {
    linSeach.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ShopSearchActivity.class);
        startActivity(intent);
      }
    });
    //ViewCompat.setElevation(mTabTl, 2);
    mTabTl.setupWithViewPager(mContentVp);
  }

  private void initContent() {
    tabIndicators = new ArrayList<>();
    String[] src = { "推荐", "居家", "电器", "厨具", "3C数码", "智能家电" };
    for (int i = 0; i < src.length; i++) {
      View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_skill_tab, null, false);
      TextView tvName = (TextView) view.findViewById(R.id.tv_name);
      tvName.setText(src[i]);
      TabLayout.Tab tab = mTabTl.newTab().setCustomView(view);
      //设置第一个默认选中Tab
      if (i == 0) {
        mTabTl.addTab(tab, true);
      } else {
        mTabTl.addTab(tab);
      }
      tabIndicators.add(src[i]);
    }
    tabFragments = new ArrayList<>();
    for (String s : tabIndicators) {
      tabFragments.add(TabContentFragment.newInstance(s));
    }
    contentAdapter = new ContentPagerAdapter(getChildFragmentManager());
    mContentVp.setAdapter(contentAdapter);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }

  class ContentPagerAdapter extends FragmentPagerAdapter {

    public ContentPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      return tabFragments.get(position);
    }

    @Override public int getCount() {
      return tabIndicators.size();
    }

    @Override public CharSequence getPageTitle(int position) {
      return tabIndicators.get(position);
    }
  }
}