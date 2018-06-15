package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ShopSearchActivity;
import com.baihuodasha.bhds.adapter.SupermarkeGridtAdapter;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.SupermarketGridBean;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.countdowntimer.TimerUtils;
import com.baihuodasha.bhds.view.SimpleToolbar;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IMXU
 * @time 2017/5/3 13:21
 * @des 资讯首页
 * 邮箱：butterfly_xu@sina.com
 */
public class FragmentSupermarket extends BaseFragment implements View.OnClickListener {

  @BindView(R.id.title_return) ImageView title_return;
  @BindView(R.id.lin_seach) LinearLayout lin_seach;
  @BindView(R.id.img_message) ImageView img_message;
  @BindView(R.id.txt_left_title) TextView mTxtlefttitle;
  @BindView(R.id.txt_main_title) TextView mTxtmaintitle;
  @BindView(R.id.txt_right_title) TextView mTxtrighttitle;
  @BindView(R.id.simple_toolbar) SimpleToolbar mSimpletoolbar;
  @BindView(R.id.search_con) TextView searchCon;
  @BindView(R.id.title) RelativeLayout title;
  @BindView(R.id.banner_supermarket) Banner mBannersupermarket;
  @BindView(R.id.text_timelimit_title) TextView mTexttimelimittitle;
  @BindView(R.id.lin_timelimit_title) LinearLayout mLintimelimittitle;
  @BindView(R.id.recy_timelimit_image) RecyclerView mRecytimelimitimage;
  @BindView(R.id.recycler_supermarket) RecyclerView mRecyclerSupermarket;
  @BindView(R.id.lin_timelimit_timer) LinearLayout mLintimelimittimer;
  @BindView(R.id.rel_timelimit_backgroud) RelativeLayout mReltimelimitbackgroud;
  @BindView(R.id.text_timelimit_headline) TextView mTexttimelimitheadline;
  @BindView(R.id.text_timelimit_subhead) TextView mTexttimelimitsubhead;
  private View view;
  private String[] BannerImage;
  private List<String> imageList;
  private SupermarkeGridtAdapter adapter;
  private SupermarketGridBean supergridbean;
  private String[] gridumage;
  private String[] gridtext;
  @Override public View initView(LayoutInflater inflater) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_middle, null);
    }
    ViewGroup parent = (ViewGroup) view.getParent();
    if (parent != null) {
      parent.removeView(view);
    }
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void init() {
    super.init();
    //mSimpletoolbar.setLeftTitleDrawable(R.drawable.advisor_home_img);
    title_return.setVisibility(View.GONE);
    img_message.setVisibility(View.GONE);
    mSimpletoolbar.setMainTitle("百货超市");
    mSimpletoolbar.setVisibility(View.GONE);
    BannerImage = Config.Bannermarketimages;
    imageList = new ArrayList<>();
    for (int i = 0; i < BannerImage.length; i++) {
      imageList.add(BannerImage[i]);
    }
    //设置图片加载器
    mBannersupermarket.setImageLoader(new GlideImageLoader());
    //设置图片集合
    mBannersupermarket.setImages(imageList);
    //banner设置方法全部调用完毕时最后调用
    mBannersupermarket.start();
    RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 5);
    // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
    manager.setAutoMeasureEnabled(true);
    mRecyclerSupermarket.setLayoutManager(manager);
    adapter = new SupermarkeGridtAdapter(getActivity(), null);
    mRecyclerSupermarket.setAdapter(adapter);
  }
  private int padding =0;
  private int textSize = 44;
  @Override public void initdata() {
    TextView tv5= TimerUtils.getTimer(TimerUtils.JD_STYLE,getActivity(),1200000,TimerUtils.TIME_STYLE_ONE,R.drawable.timer_shape2)
        .setTimerPadding(15,15,20,15)
        .setTimerTextColor(Color.WHITE)
        .setTimerTextSize(textSize)
        .setTimerGapColor(Color.BLACK)
        .getmDateTv();
    mLintimelimittimer.addView(tv5);
    setmLayoutParams(tv5);
    getShopList();
    super.initdata();
  }
  private void setmLayoutParams(TextView tv) {
    tv.setGravity(Gravity.CENTER_HORIZONTAL);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
    params.setMargins(0,0,0,0);
    tv.setLayoutParams(params);
  }
  @Override public void initListener() {
    lin_seach.setOnClickListener(this);
    mBannersupermarket.setOnBannerClickListener(new OnBannerClickListener() {
      @Override public void OnBannerClick(int position) {
        CommonUtils.toastMessage("点击了第"+position+"张图片");
      }
    });
    adapter.setOnItemClickListener(new SupermarkeGridtAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {
        CommonUtils.toastMessage("点击了"+position);
      }
    });
    super.initListener();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {

      case R.id.lin_seach:
        Intent intent = new Intent(getActivity(), ShopSearchActivity.class);
        startActivity(intent);
        break;
    }
  }
  private List<SupermarketGridBean> supermarketList = new ArrayList<>();

  private void getShopList() {
    gridumage = Config.SupermarkeGridimages;
    gridtext = Config.SupermarkeGridtext;
    supermarketList.clear();
    for (int i = 0; i < gridumage.length; i++) {
      supergridbean = new SupermarketGridBean();
      List<ChildInfo> childInfoList = new ArrayList<>();
      supergridbean.setTitle(gridtext[i]);
      supergridbean.setUrl(gridumage[i]);
      supermarketList.add(supergridbean);
    }
    adapter.addList(supermarketList);
  }

}