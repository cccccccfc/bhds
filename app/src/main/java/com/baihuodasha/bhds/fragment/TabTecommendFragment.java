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
import com.baihuodasha.bhds.bean.MainCategoryGoodsListMdel;
import com.baihuodasha.bhds.bean.MainIndexBannerModel;
import com.baihuodasha.bhds.bean.MainIndexBestGoodsList;
import com.baihuodasha.bhds.net.SdjNetWorkManager;
import com.baihuodasha.bhds.net.URLContents;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.baihuodasha.bhds.view.MyDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
  private List<MainIndexBannerModel.DataBean> bean;
  private MyDialog loadDialog;
  private boolean shouLoad = true;
  private String mold;
  private boolean touch = true;
  private SmartRefreshLayout mSmartR;

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
    mSmartR = (SmartRefreshLayout) contentView.findViewById(R.id.smart_commendation);
    mScrollView =
        (ScrollInterceptScrollView) contentView.findViewById(R.id.ScrollInterceptScrollView);

    initdata();
    initListener();
    SelectorTab();
   // FabbuttonUtil(getActivity(), mScrollView, mFabbutton);

    return contentView;
  }

  public void init() {
    BannerImage = Config.Bannerhomeimages;
    contextImages = Config.ContextImages;
    if (!mTitle.equals("推荐")) {
      //mBanner.setVisibility(View.GONE);
      // mBanner.stopAutoPlay();
    } else {
      // mBanner.startAutoPlay();
    }

    SdjNetWorkManager.sendIndexBanner("ads", new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainIndexBannerModel msg = (MainIndexBannerModel) response.body();
        if (msg != null) {
          ArrayList<String> imageList = new ArrayList<>();
          bean = (ArrayList<MainIndexBannerModel.DataBean>) msg.getData();
          for (int i = 0; i < bean.size(); i++) {
            imageList.add(URLContents.Image_URL + bean.get(i).getImage());
            //设置图片加载器
            mBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            mBanner.setImages(imageList);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
          }
        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
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
    sendBestGoodsList(true, "15");
  }

  public void initListener() {
    mFabbutton.setOnClickListener(this);
    adapter.setOnItemClickListener(new HomesideslipproductsAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {
        Log.i("qaz", "第" + v + "个" + position);
      }
    });
    mSmartR.setEnableRefresh(false);
    mSmartR.setLoadmoreFinished(true);
    //进行加载更多的逻辑处理
    mSmartR.setOnLoadmoreListener(new OnLoadmoreListener() {
      @Override public void onLoadmore(RefreshLayout refreshlayout) {
        new Timer().schedule(new TimerTask() {
          @Override public void run() {
            if (recommendationAdapter.getPage() != 1) {
              sendBestGoodsList(false, "15");
            }else {

            }

          }
        }, 3000);
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
    SdjNetWorkManager.sendCategoryGoodsList(new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainCategoryGoodsListMdel msg = (MainCategoryGoodsListMdel) response.body();
        if (msg != null) {
          infoadapter.addList(msg.getData());
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
       // Log.i("qaz", "onFailure: "+t);
      }
    });


  }
  private void sendBestGoodsList(final boolean isReflash ,String size){

    int page = isReflash ? 1 : recommendationAdapter.getPage();
    Log.i("qaz", "sendBestGoodsList: "+ isReflash);
    SdjNetWorkManager.sendBestGoodsList(page, size, "空", new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainIndexBestGoodsList msg = (MainIndexBestGoodsList) response.body();
        if (msg != null) {
          recommendationAdapter.addList(msg.getData());

          if (recommendationAdapter.getIsLoadOver()) {
            mSmartR.setLoadmoreFinished(true);
            return;
          }
        }else {
          //mSmartR.setLoadmoreFinished(false);
        }
      }

      @Override public void onFailure(Call call, Throwable t) {

      }
    });
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
    tagAdapter.setSelectedList(0);
    sendIndexInfo("0");
    id_flowlayout.setAdapter(tagAdapter);

    id_flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {
        //getList(showEntities.get(i).name, shopid, true);
        tagAdapter.setSelectedList(i);


        return true;
      }
    });
    id_flowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
      @Override public void onSelected(Set<Integer> selectPosSet) {
        //Log.i("qaz", "onSelected: "+selectPosSet.hashCode());
        if (touch) {
          touch = false;
          sendIndexInfo(String.valueOf(selectPosSet.hashCode()));
        } else {
          adapter.clear();
          CommonUtils.toastMessage("操作频繁");
        }
      }
    });
  }

  //type = ads轮播图  promoteGoods促销商品  	top10销售排名  best精品   hot热品  new新品
  private void sendIndexInfo(String type) {
    if (type.equals("0")) {
      mold = "promoteGoods";
    } else if (type.equals("1")) {
      mold = "top10";
    } else if (type.equals("2")) {
      mold = "hot";
    } else if (type.equals("3")) {
      mold = "best";
    } else {
      mold = "new";
    }
 //   Log.i("qaz", "sendIndexInfo: " + mold);
    adapter.clear();
    SdjNetWorkManager.sendIndexBanner(mold, new Callback() {
      @Override public void onResponse(Call call, Response response) {
        MainIndexBannerModel msg = (MainIndexBannerModel) response.body();
        if (msg != null) {
          bean = (ArrayList<MainIndexBannerModel.DataBean>) msg.getData();
         // bean.clear();
          adapter.addList(bean);
          adapter.notifyDataSetChanged();
          touch = true;
        }else {
          adapter.clear();
        }
      }

      @Override public void onFailure(Call call, Throwable t) {
        touch = true;
      }
    });
  }
}
