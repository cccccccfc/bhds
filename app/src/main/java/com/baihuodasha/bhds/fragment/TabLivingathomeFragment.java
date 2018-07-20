package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.index.ActivityProductList;
import com.baihuodasha.bhds.adapter.HomecategoryTwoAdapter;
import com.baihuodasha.bhds.adapter.HomerecommendationAdapter;
import com.baihuodasha.bhds.adapter.HomesideslipproductsAdapter;
import com.baihuodasha.bhds.adapter.SupermarkeGridtAdapter;
import com.baihuodasha.bhds.bean.MainCategoryGoodsListMdel;
import com.baihuodasha.bhds.bean.MainIndexBannerModel;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.net.URLContents;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.TagAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.baihuodasha.bhds.utils.FabbuttonUtils.FabbuttonUtil;

/**
 * Created by zhang on 2018/6/15.
 */

public class TabLivingathomeFragment extends Fragment implements View.OnClickListener {
  private static final String EXTRA_CONTENT = "content";

  private Banner mBanner;
  private RecyclerView mRecycshoping;
  private ArrayList<String> imageRecyc;
  private HomesideslipproductsAdapter adapter;
  private RecyclerView mRecycinfo;
  private HomecategoryTwoAdapter infoadapter;
  private TagAdapter tagAdapter;
  private RecyclerView mRecyccommendation;
  private HomerecommendationAdapter recommendationAdapter;
  private FloatingActionButton mFabbutton;
  private ScrollInterceptScrollView mScrollView;
  private SupermarkeGridtAdapter gridadapter;
  private String mId;
  private List<MainIndexBannerModel.DataBean> bean;

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
    mId = getArguments().getString(EXTRA_CONTENT);
  //  Log.i("qaz", "onCreateView: " + mId);
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
    FabbuttonUtil(getActivity(), mScrollView, mFabbutton);
    return contentView;
  }

  public void init() {

    SdjNetWorkManager.sendcategoryBanner(mId, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainIndexBannerModel msg = (MainIndexBannerModel) response.body();
        if (msg != null) {
          ArrayList<String> imageList = new ArrayList<>();
          bean = (ArrayList<MainIndexBannerModel.DataBean>) msg.getData();
          if (bean != null ) {
            for (int i = 0; i < bean.size(); i++) {
            //  Log.i("qaz", "onResponse: " + URLContents.Image_URL + bean.get(i).getImage());
              imageList.add(URLContents.Goods_URL + bean.get(i).getImage());
              //设置图片加载器
              mBanner.setImageLoader(new GlideImageLoader());
              //设置图片集合
              mBanner.setImages(imageList);
              //banner设置方法全部调用完毕时最后调用
              mBanner.start();
            }
          }else {
           // CommonUtils.toastMessage("请求数据失败");
          }
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
      }
    });

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
    infoadapter = new HomecategoryTwoAdapter(getActivity(), null);
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
      @Override public void onClick(int v, String position,String name) {
       // Log.i("qaz", "onClick: " + v + "个");
        Intent intent = new Intent(getActivity(), ActivityProductList.class);
        intent.putExtra("cat_id",position);
        intent.putExtra("title",name);
        startActivity(intent);
      }
    });
    infoadapter.setOnItemClickListener(new HomecategoryTwoAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position, String name) {
        Intent intent = new Intent(getActivity(), ActivityProductList.class);
        intent.putExtra("cat_id",position);
        intent.putExtra("title",name);
        startActivity(intent);
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

  private void getShopList() {

    SdjNetWorkManager.sendcatgoryNextInformationList(mId, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainCategoryGoodsListMdel msg = (MainCategoryGoodsListMdel) response.body();
        if (msg != null) {
          gridadapter.addList(msg.getData());
          infoadapter.addList(msg.getData());
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
      }
    });
    //infoadapter.addList(dataInfoList);

    // recommendationAdapter.addList(recommendationList);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }
}
