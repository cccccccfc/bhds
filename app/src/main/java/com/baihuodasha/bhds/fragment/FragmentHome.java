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
import com.baihuodasha.bhds.activity.index.ActivityCommoditySearch;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.bean.MainIndexNavigaitonModel;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.utils.tablayout.TabLayout;
import com.baihuodasha.bhds.view.MyDialog;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author IMXU
 * @time 2017/5/3 13:21
 * @des 资讯首页
 * 邮箱：butterfly_xu@sina.com
 */
public class FragmentHome extends BaseFragment implements View.OnClickListener {
  private TabLayout mTabTl;
  private ViewPager mContentVp;
  private LinearLayout linSeach;

  private List<String> tabIndicators;
  private List<String> tabid;
  private List<Fragment> tabFragments;
  private View view;
  private List<MainIndexNavigaitonModel.DataBean> bean;
  private MyDialog loadDialog;
  private boolean shouLoad = true;

  @Override public View initView(LayoutInflater inflater) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_home, null);
    }

    ViewGroup parent = (ViewGroup) view.getParent();
    if (parent != null) {
      parent.removeView(view);
    }

    //StatusBarCompat.initSystemBar(getActivity(), R.color.titleBar);
    ImageView titleReturn = (ImageView) view.findViewById(R.id.title_return);
    TextView searchCon = (TextView) view.findViewById(R.id.search_con);
    RelativeLayout title = (RelativeLayout) view.findViewById(R.id.title);
    mTabTl = (TabLayout) view.findViewById(R.id.tl_tab);
    mContentVp = (ViewPager) view.findViewById(R.id.vp_content);
    linSeach = (LinearLayout) view.findViewById(R.id.lin_seach);

    ButterKnife.bind(this, view);
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
        Intent intent = new Intent(getActivity(), ActivityCommoditySearch.class);
        startActivity(intent);
      }
    });
    //ViewCompat.setElevation(mTabTl, 2);
    mTabTl.setupWithViewPager(mContentVp);
  }

  private void initContent() {
    if (shouLoad) {
      loadDialog = MyDialog.showDialog(getActivity());
      //shouLoad = false;
      loadDialog.show();
    }
    final View view =
        LayoutInflater.from(getActivity()).inflate(R.layout.item_skill_tab, null, false);
    final TextView tvName = (TextView) view.findViewById(R.id.tv_name);
    tabIndicators = new ArrayList<>();
  //  tabIndicators.add("推荐");
    tabid = new ArrayList<>();
    tabFragments = new ArrayList<>();
    final ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getChildFragmentManager());
    mContentVp.setAdapter(contentAdapter);
    SdjNetWorkManager.sendIndexNavigation(new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainIndexNavigaitonModel msg = (MainIndexNavigaitonModel) response.body();
        if (msg != null) {
          if (loadDialog != null) {
            loadDialog.dismiss();
            loadDialog = null;
          }
          bean =
              (ArrayList<MainIndexNavigaitonModel.DataBean>) msg.getData();
          for (int i = 0; i < bean.size(); i++) {
            tvName.setText(bean.get(i).getName());
            TabLayout.Tab tab = mTabTl.newTab().setCustomView(view);
            if (i == 0) {
              mTabTl.addTab(tab, true);
            } else {
              mTabTl.addTab(tab);
            }
            tabIndicators.add(bean.get(i).getName());
           // tabid.add(bean.get(i).getCid());
          }
          tabFragments.add(TabTecommendFragment.newInstance(""));
          tabFragments.add(TabLivingathomeFragment.newInstance(""));
          tabFragments.add(TabLivingathomeFragment.newInstance(""));
          tabFragments.add(TabLivingathomeFragment.newInstance("4"));
          contentAdapter.notifyDataSetChanged();
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        if (loadDialog != null) {
          loadDialog.dismiss();
          loadDialog = null;
        }
      }
    });


    mTabTl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        updateTabTextView(tab, true);
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {
        updateTabTextView(tab, false);
      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
    //  mContentVp.setCurrentItem(tab.getPosition());
    if (isSelect) {
      //选中加粗
      TextView tabSelect =
          (TextView) (((LinearLayout) ((LinearLayout) mTabTl.getChildAt(0)).getChildAt(
              tab.getPosition())).getChildAt(1));
      // TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tv_name);
      // tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
      //tabSelect.setText(tab.getText());
    } else {
      TextView tabUnSelect =
          (TextView) (((LinearLayout) ((LinearLayout) mTabTl.getChildAt(0)).getChildAt(
              tab.getPosition())).getChildAt(1));

      //    TextView tabUnSelect = (TextView) tab.getCustomView().findViewById(R.id.tv_name);
      //tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
      // tabUnSelect.setText(tab.getText());
    }
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