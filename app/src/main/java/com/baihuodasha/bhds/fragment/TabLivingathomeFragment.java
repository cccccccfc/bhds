package com.baihuodasha.bhds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.HomecategoryAdapter;
import com.baihuodasha.bhds.adapter.HomerecommendationAdapter;
import com.baihuodasha.bhds.adapter.HomesideslipproductsAdapter;
import com.baihuodasha.bhds.adapter.SupermarkeGridtAdapter;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.ParentInfo;
import com.baihuodasha.bhds.bean.RecommendationBean;
import com.baihuodasha.bhds.bean.SupermarketGridBean;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.TagAdapter;
import java.util.ArrayList;
import java.util.List;

import static com.baihuodasha.bhds.utils.FabbuttonUtils.FabbuttonUtil;

/**
 * Created by zhang on 2018/6/15.
 */

public class TabLivingathomeFragment extends Fragment implements View.OnClickListener {
  private static final String EXTRA_CONTENT = "content";
  private ParentInfo parentInfo;

  private ArrayList<String> imageList;
  private Banner mBanner;
  private String mTitle;
  private RecyclerView mRecycshoping;
  private ArrayList<String> imageRecyc;
  private HomesideslipproductsAdapter adapter;
  private RecyclerView mRecycinfo;
  private HomecategoryAdapter infoadapter;
  private TagAdapter tagAdapter;
  private String[] BannerImage;
  private String[] contextImages;
  private RecyclerView mRecyccommendation;
  private HomerecommendationAdapter recommendationAdapter;
  private RecommendationBean recommendationBean;
  private FloatingActionButton mFabbutton;
  private ScrollInterceptScrollView mScrollView;
  private SupermarkeGridtAdapter gridadapter;

  public static TabLivingathomeFragment newInstance(String content) {
    Bundle arguments = new Bundle();
    arguments.putString(EXTRA_CONTENT, content);
    TabLivingathomeFragment tabLivingathomeFragment = new TabLivingathomeFragment();
    tabLivingathomeFragment.setArguments(arguments);
    return tabLivingathomeFragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View contentView = inflater.inflate(R.layout.fragment_tab_livingathome, null);
    mTitle = getArguments().getString(EXTRA_CONTENT);
    // ((TextView) contentView.findViewById(R.id.tv_content)).setText(mTitle);
    mBanner = (Banner) contentView.findViewById(R.id.banner);
    mRecycshoping = (RecyclerView) contentView.findViewById(R.id.recyc_shoping);
    mRecycinfo = (RecyclerView) contentView.findViewById(R.id.recyc_info);
    mRecyccommendation = (RecyclerView) contentView.findViewById(R.id.recyc_commendation);
    mFabbutton = (FloatingActionButton) contentView.findViewById(R.id.fab);
    mScrollView =
        (ScrollInterceptScrollView) contentView.findViewById(R.id.ScrollInterceptScrollView);
    init();
    initdata();
    initListener();
    FabbuttonUtil(getActivity() ,mScrollView ,mFabbutton);
    return contentView;
  }

  public void init() {
    BannerImage = Config.Bannerhomeimages;
    contextImages = Config.ContextImages;
    imageList = new ArrayList<>();
    //for (int i = 0; i < BannerImage.length; i++) {
    //  imageList.add(BannerImage[i]);
    //}
    imageList.add("http://test2.baihuodasha.com/data/catflashimg/1527661111929476295.jpg");
    //设置图片加载器
    mBanner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    mBanner.setImages(imageList);
    //banner设置方法全部调用完毕时最后调用
    mBanner.start();

    mFabbutton.setVisibility(View.GONE);
  }

  public void initdata() {
    RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 5);
    manager.setAutoMeasureEnabled(true);
    mRecycshoping.setLayoutManager(manager);
    gridadapter = new SupermarkeGridtAdapter(getActivity(), null);
    mRecycshoping.setAdapter(gridadapter);

    LinearLayoutManager linearLayoutT = new LinearLayoutManager(getActivity());
    linearLayoutT.setOrientation(LinearLayoutManager.VERTICAL);
    mRecycinfo.setLayoutManager(linearLayoutT);
    infoadapter = new HomecategoryAdapter(getActivity(), null);
    mRecycinfo.setAdapter(infoadapter);

    LinearLayoutManager linearLayoutF = new LinearLayoutManager(getActivity());
    linearLayoutF.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyccommendation.setLayoutManager(linearLayoutF);
    recommendationAdapter = new HomerecommendationAdapter(getActivity(), null);
    mRecyccommendation.setAdapter(recommendationAdapter);
    getShopList();
  }

  public void initListener() {
    mFabbutton.setOnClickListener(this);
    gridadapter.setOnItemClickListener(new SupermarkeGridtAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {
        Log.i("qaz", "onClick: " + v + "个");
      }
    });
  }

  @Override public void onClick(View v) {

    switch (v.getId()) {
      case R.id.fab:
        mScrollView.post(new Runnable() {
          @Override public void run() {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
          }
        });
        mFabbutton.setVisibility(View.GONE);
        break;
    }
  }

  private List<RecommendationBean> recommendationList = new ArrayList<>();
  private List<ParentInfo> dataInfoList = new ArrayList<>();
  private String[] gridumage;
  private String[] gridtext;
  private List<SupermarketGridBean> supermarketList = new ArrayList<>();
  private SupermarketGridBean supergridbean;
  private void getShopList() {
    dataInfoList.clear();
    for (int i = 0; i < BannerImage.length; i++) {
      parentInfo = new ParentInfo();
      List<ChildInfo> childInfoList = new ArrayList<>();
      parentInfo.setTitle("家装软饰");
      parentInfo.setImage(BannerImage[i]);
      for (int j = 0; j < contextImages.length; j++) {
        ChildInfo childInfo = new ChildInfo();
        childInfo.setMenuName(i + "-" + j);
        childInfo.setIconImgID(contextImages[j]);
        childInfoList.add(childInfo);
      }
      parentInfo.setMenuList(childInfoList);
      dataInfoList.add(parentInfo);
    }
    infoadapter.addList(dataInfoList);

    recommendationList.clear();
    for (int i = 0; i < contextImages.length; i++) {
      recommendationBean = new RecommendationBean();
      recommendationBean.setTitle("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      recommendationBean.setOldprice("1990.00");
      recommendationBean.setPrice("999.00");
      recommendationBean.setLabel("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      recommendationBean.setUrl(contextImages[i]);
      recommendationList.add(recommendationBean);
    }
    recommendationAdapter.addList(recommendationList);

    gridumage = Config.SupermarkeGridimages;
    gridtext = Config.SupermarkeGridtext;
    supermarketList.clear();
    for (int i = 0; i < 5; i++) {
      supergridbean = new SupermarketGridBean();
      List<ChildInfo> childInfoList = new ArrayList<>();
      supergridbean.setTitle(gridtext[i]);
      supergridbean.setUrl(gridumage[i]);
      supermarketList.add(supergridbean);
    }
    gridadapter.addList(supermarketList);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }


}
