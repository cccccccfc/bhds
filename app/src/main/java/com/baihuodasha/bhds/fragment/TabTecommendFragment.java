package com.baihuodasha.bhds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.HomecategoryAdapter;
import com.baihuodasha.bhds.adapter.HomerecommendationAdapter;
import com.baihuodasha.bhds.adapter.HomesideslipproductsAdapter;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.ParentInfo;
import com.baihuodasha.bhds.bean.RecommendationBean;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.baihuodasha.bhds.utils.FabbuttonUtils.FabbuttonUtil;

/**
 * Created by yifeng on 16/8/3.
 */
public class TabTecommendFragment extends Fragment implements View.OnClickListener {

  private static final String EXTRA_CONTENT = "content";

  private Banner mBanner;
  private String mTitle;
  private RecyclerView mRecycshoping;
  private HomesideslipproductsAdapter adapter;
  private RecyclerView mRecycinfo;
  private HomecategoryAdapter infoadapter;
  private TagFlowLayout id_flowlayout;
  private TagAdapter tagAdapter;
  private String[] BannerImage;
  private String[] contextImages;
  private RecyclerView mRecyccommendation;
  private HomerecommendationAdapter recommendationAdapter;
  private FloatingActionButton mFabbutton;
  private ScrollInterceptScrollView mScrollView;

  public static TabTecommendFragment newInstance(String content) {
    Bundle arguments = new Bundle();
    arguments.putString(EXTRA_CONTENT, content);
    TabTecommendFragment tabTecommendFragment = new TabTecommendFragment();
    tabTecommendFragment.setArguments(arguments);
    return tabTecommendFragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View contentView = inflater.inflate(R.layout.fragment_tab_content, null);
    mTitle = getArguments().getString(EXTRA_CONTENT);
    // ((TextView) contentView.findViewById(R.id.tv_content)).setText(mTitle);
    mBanner = (Banner) contentView.findViewById(R.id.banner);
    id_flowlayout = (TagFlowLayout) contentView.findViewById(R.id.id_flowlayout);
    mRecycshoping = (RecyclerView) contentView.findViewById(R.id.recyc_shoping);
    mRecycinfo = (RecyclerView) contentView.findViewById(R.id.recyc_info);
    mRecyccommendation = (RecyclerView) contentView.findViewById(R.id.recyc_commendation);
    mFabbutton = (FloatingActionButton) contentView.findViewById(R.id.fab);
    mScrollView =
        (ScrollInterceptScrollView) contentView.findViewById(R.id.ScrollInterceptScrollView);

    init();
    initdata();
    initListener();
    SelectorTab();
    FabbuttonUtil(getActivity() ,mScrollView ,mFabbutton);

    return contentView;
  }

  public void init() {
    BannerImage = Config.Bannerhomeimages;
    contextImages = Config.ContextImages;
    if (!mTitle.equals("推荐")) {
      mBanner.setVisibility(View.GONE);
      mBanner.stopAutoPlay();
    } else {
      mBanner.startAutoPlay();
    }
    ArrayList<String> imageList = new ArrayList<>();
    for (int i = 0; i < BannerImage.length; i++) {
      imageList.add(BannerImage[i]);
    }
    //设置图片加载器
    mBanner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    mBanner.setImages(imageList);
    //banner设置方法全部调用完毕时最后调用
    mBanner.start();

    mFabbutton.setVisibility(View.GONE);
  }

  public void initdata() {
    LinearLayoutManager linearLayoutO = new LinearLayoutManager(getActivity());
    linearLayoutO.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecycshoping.setLayoutManager(linearLayoutO);
    adapter = new HomesideslipproductsAdapter(getActivity(), null);
    mRecycshoping.setAdapter(adapter);
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
    adapter.setOnItemClickListener(new HomesideslipproductsAdapter.OnItemClickListener() {
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

  private void getImageList() {
    ArrayList<String> imageRecyc = new ArrayList<>();
    for (int i = 0; i < contextImages.length; i++) {
      imageRecyc.add(contextImages[i]);
    }
    adapter.addList(imageRecyc);
  }

  private void getShopList() {
    dataInfoList.clear();
    for (int i = 0; i < BannerImage.length; i++) {
      ParentInfo parentInfo = new ParentInfo();
      List<ChildInfo> childInfoList = new ArrayList<>();
      parentInfo.setTitle("居家生活");
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
      RecommendationBean recommendationBean = new RecommendationBean();
      recommendationBean.setTitle("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      recommendationBean.setOldprice("1990.00");
      recommendationBean.setPrice("999.00");
      recommendationBean.setLabel("百安思保温杯304不锈钢真空高端保温杯 大容量男女创意定制便携保温杯");
      recommendationBean.setUrl(contextImages[i]);
      recommendationList.add(recommendationBean);
    }
    recommendationAdapter.addList(recommendationList);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  public String[] showEntities = { "促销商品", "热销排行", "商城热卖", "商城推荐", "新品上市" };

  private void SelectorTab() {
    final LayoutInflater mInflater =
        (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    tagAdapter = new TagAdapter(showEntities) {
      @Override public View getView(FlowLayout parent, int position, Object o) {
        TextView tv = (TextView) mInflater.inflate(R.layout.selector_home_tv, id_flowlayout, false);
        tv.setText(showEntities[position]);
        return tv;
      }
    };
    tagAdapter.setSelectedList(1);
    id_flowlayout.setAdapter(tagAdapter);
    id_flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {
        //getList(showEntities.get(i).name, shopid, true);
        CommonUtils.toastMessage("点击了" + showEntities[i]);
        tagAdapter.setSelectedList(i);
        if (i == 1) {
          getImageList();
        } else if (i == 4) {
          getImageList();
        }
        return true;
      }
    });
    id_flowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
      @Override public void onSelected(Set<Integer> selectPosSet) {
        //Log.i("qaz", "onSelected: "+selectPosSet.hashCode());
      }
    });
  }


}
