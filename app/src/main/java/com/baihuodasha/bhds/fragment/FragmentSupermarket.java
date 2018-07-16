package com.baihuodasha.bhds.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.baihuodasha.bhds.activity.index.ActivityCommodityDetails;
import com.baihuodasha.bhds.activity.index.ActivityCommoditySearch;
import com.baihuodasha.bhds.activity.supermarket.ActivityFlashSale;
import com.baihuodasha.bhds.adapter.SupermarkeGridtAdapter;
import com.baihuodasha.bhds.adapter.SupermarketChoicenessAdapter;
import com.baihuodasha.bhds.adapter.SupermarketCountdownAdapter;
import com.baihuodasha.bhds.adapter.SupermarketNewproductAdapter;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.SupermarketGridBean;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.baihuodasha.bhds.utils.ScrollInterceptScrollView;
import com.baihuodasha.bhds.utils.countdowntimer.TimerUtils;
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

  @BindView(R.id.text_timelimit_more) TextView mTexttimelimitMore; //限时查看更多
  @BindView(R.id.text_bhds_recommend) TextView textBhdsRecommend;
  @BindView(R.id.img_bhds_recommend) ImageView imgBhdsRecommend;
  @BindView(R.id.img_bhds_recommend1) ImageView imgBhdsRecommend1;
  @BindView(R.id.img_bhds_recommend2) ImageView imgBhdsRecommend2;
  @BindView(R.id.text_recommend_more) TextView textRecommendMore; //推荐查看更多
  @BindView(R.id.lin_bhds_recommend) LinearLayout linBhdsRecommend;

  @BindView(R.id.text_bhds_specialoffer) TextView textBhdsSpecialoffer;
  @BindView(R.id.img_bhds_specialoffer) ImageView imgBhdsSpecialoffer;
  @BindView(R.id.img_bhds_special1) ImageView imgBhdsSpecial1;
  @BindView(R.id.img_bhds_special2) ImageView imgBhdsSpecial2;
  @BindView(R.id.img_bhds_special3) ImageView imgBhdsSpecial3;
  @BindView(R.id.lin_bhds_special) LinearLayout linBhdsSpecial;
  @BindView(R.id.text_bhds_more) TextView textBhdsMore; //特卖查看更多

  @BindView(R.id.rel_bhds_specialoffer) RelativeLayout relBhdsSpecialoffer;
  @BindView(R.id.text_bhds_newproduct) TextView textBhdsNewproduct;
  @BindView(R.id.rec_bhds_newproduct) RecyclerView mRecBhdsNewproduct;
  @BindView(R.id.text_newproduct_more) TextView textNewproductMore;  // 新品查看更多
  @BindView(R.id.lin_bhds_newproduct) LinearLayout linBhdsNewproduct;  //

  @BindView(R.id.rec_bhds_choiceness) RecyclerView mRecBhdsChoiceness;
  @BindView(R.id.fab) FloatingActionButton mFabbutton;//scrollInterceptScrollView
  @BindView(R.id.scrollInterceptScrollView) ScrollInterceptScrollView mScrollView;
      //scrollInterceptScrollView

  private View view;
  private SupermarkeGridtAdapter adapter;
  private SupermarketCountdownAdapter countdownAdapter;
  private SupermarketNewproductAdapter newproductAdapter;
  private SupermarketChoicenessAdapter choicenessAdapter;

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
    //mSimpletoolbar.setLeftTitleDrawable(R.drawable.advisor_home_img);

    img_message.setImageDrawable(getResources().getDrawable(R.mipmap.sort_light));
    title_return.setImageDrawable(getResources().getDrawable(R.mipmap.market));
    String[] bannerImage = Config.Bannermarketimages;
    List<String> imageList = new ArrayList<>();
    for (int i = 0; i < bannerImage.length; i++) {
      imageList.add(bannerImage[i]);
    }
    //设置图片加载器
    mBannersupermarket.setImageLoader(new GlideImageLoader());
    //设置图片集合
    mBannersupermarket.setImages(imageList);
    //banner设置方法全部调用完毕时最后调用
    mBannersupermarket.start();
    mFabbutton.setVisibility(View.GONE);
  }

  private int padding = 0;

  @Override public void initdata() {
    RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 5);
    // 需要注意的是GridLayoutManager要设置setAutoMeasureEnabled(true)成自适应高度
    manager.setAutoMeasureEnabled(true);
    mRecyclerSupermarket.setLayoutManager(manager);
    adapter = new SupermarkeGridtAdapter(getActivity(), null);
    mRecyclerSupermarket.setAdapter(adapter);
    //限时抢购条目
    LinearLayoutManager linearLayoutO = new GridLayoutManager(getActivity(), 2);
    mRecytimelimitimage.setLayoutManager(linearLayoutO);
    countdownAdapter = new SupermarketCountdownAdapter(getActivity(), null);
    mRecytimelimitimage.setAdapter(countdownAdapter);
    // 新品推荐条目
    LinearLayoutManager linearLayoutT = new GridLayoutManager(getActivity(), 2);
    linearLayoutT.setOrientation(LinearLayoutManager.VERTICAL);
    mRecBhdsNewproduct.setLayoutManager(linearLayoutT);
    newproductAdapter = new SupermarketNewproductAdapter(getActivity(), null);
    mRecBhdsNewproduct.setAdapter(newproductAdapter);
    //精选推荐条目
    LinearLayoutManager linearLayoutF = new GridLayoutManager(getActivity(), 3);
    linearLayoutF.setOrientation(LinearLayoutManager.VERTICAL);
    mRecBhdsChoiceness.setLayoutManager(linearLayoutF);
    choicenessAdapter = new SupermarketChoicenessAdapter(getActivity(), null);
    mRecBhdsChoiceness.setAdapter(choicenessAdapter);
    setmLayoutParams();
    getShopList();
    SetList();
    //FabbuttonUtil(getActivity(), mScrollView, mFabbutton);
  }

  //设置倒计时
  private void setmLayoutParams() {
    int textSize = 28;
    TextView tv =
        TimerUtils.getTimer(TimerUtils.VIP_STYLE, getActivity(), 1200000, TimerUtils.TIME_STYLE_ONE,
            R.drawable.timer_shape2)
            .setTimerTextColor(Color.WHITE)
            .setTimerTextSize(textSize)
            .setTimerGapColor(Color.BLACK)
            .getmDateTv();
    mLintimelimittimer.addView(tv);

    tv.setGravity(Gravity.CENTER_HORIZONTAL);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv.getLayoutParams();
    params.setMargins(0, 0, 0, 0);
    tv.setLayoutParams(params);
  }

  @Override public void initListener() {
    mFabbutton.setOnClickListener(this);
    lin_seach.setOnClickListener(this);
    mTexttimelimitMore.setOnClickListener(this);
    textRecommendMore.setOnClickListener(this);
    textBhdsMore.setOnClickListener(this);
    textNewproductMore.setOnClickListener(this);

    mBannersupermarket.setOnBannerClickListener(new OnBannerClickListener() {
      @Override public void OnBannerClick(int position) {
        CommonUtils.toastMessage("点击了第" + position + "张图片");
      }
    });
    adapter.setOnItemClickListener(new SupermarkeGridtAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {
        CommonUtils.toastMessage("点击了" + position);
      }
    });
    countdownAdapter.setOnItemClickListener(new SupermarketCountdownAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {
        CommonUtils.toastMessage("点击了" + position);
      }
    });
    newproductAdapter.setOnItemClickListener(
        new SupermarketNewproductAdapter.OnItemClickListener() {
          @Override public void onClick(int v, String position) {
            Intent intenthelp =new Intent(getActivity() , ActivityCommodityDetails.class);
            startActivity(intenthelp);
            CommonUtils.toastMessage("点击了" + position);
          }
        });
    choicenessAdapter.setOnItemClickListener(
        new SupermarketChoicenessAdapter.OnItemClickListener() {
          @Override public void onClick(int v, String position) {
            CommonUtils.toastMessage("点击了" + position);
          }
        });
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {

      case R.id.lin_seach:
        Intent intent = new Intent(getActivity(), ActivityCommoditySearch.class);
        startActivity(intent);
        break;
      case R.id.fab:
        mScrollView.post(new Runnable() {
          @Override public void run() {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
          }
        });
        mFabbutton.setVisibility(View.GONE);
        break;
      case R.id.text_timelimit_more:
        CommonUtils.toastMessage("限时特卖查看更多");
        Intent intentlimit = new Intent(getActivity() , ActivityFlashSale.class);
        startActivity(intentlimit);
        break;
      case R.id.text_recommend_more:
        CommonUtils.toastMessage("百货大厦推荐查看更多");
        break;
      case R.id.text_bhds_more:
        CommonUtils.toastMessage("品牌特卖查看更多");
        break;
      case R.id.text_newproduct_more:
        CommonUtils.toastMessage("新品首发查看更多");
        break;
    }
  }

  private List<SupermarketGridBean> supermarketList = new ArrayList<>();

  private void getShopList() {
    String[] gridumage = Config.SupermarkeGridimages;
    String[] gridtext = Config.SupermarkeGridtext;
    supermarketList.clear();
    for (int i = 0; i < gridumage.length; i++) {
      SupermarketGridBean supergridbean = new SupermarketGridBean();
      List<ChildInfo> childInfoList = new ArrayList<>();
      supergridbean.setTitle(gridtext[i]);
      supergridbean.setUrl(gridumage[i]);
      supermarketList.add(supergridbean);
    }
    adapter.addList(supermarketList);
  }

  private List<String> price = new ArrayList<>();
  private List<String> price1 = new ArrayList<>();
  private List<String> price2 = new ArrayList<>();

  private void SetList() {
    String[] supermarketprice = Config.supermarketprice;
    price.clear();
    for (int i = 0; i < supermarketprice.length; i++) {
      price.add(supermarketprice[i]);
    }
    countdownAdapter.addList(price);

    price1.clear();
    for (int i = 0; i < 6; i++) {
      price1.add(i + "测试");
    }
    newproductAdapter.addList(price1);

    price2.clear();
    for (int i = 0; i < 14; i++) {
      price2.add(i + "测试");
    }
    choicenessAdapter.addList(price2);
  }
}