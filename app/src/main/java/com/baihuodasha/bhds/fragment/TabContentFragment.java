package com.baihuodasha.bhds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.RecycInfoAdapter;
import com.baihuodasha.bhds.adapter.RecycshopAdapter;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.ParentInfo;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yifeng on 16/8/3.
 */
public class TabContentFragment extends Fragment implements View.OnClickListener {

  private static final String EXTRA_CONTENT = "content";
  private String[] images = new String[] {
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1522294960829052434.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189818366778385.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1511260867777304821.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189941117678111.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189618759975270.jpg"
  };
  private String[] image = new String[] {
      "http://test2.baihuodasha.com/images/201805/thumb_img/5028_thumb_G_1527068836793.jpg",
      "http://test2.baihuodasha.com/images/201805/thumb_img/5028_thumb_G_1527068836793.jpg",
      "http://test2.baihuodasha.com/images/201805/thumb_img/5028_thumb_G_1527068836793.jpg",
      "http://test2.baihuodasha.com/images/201805/thumb_img/5028_thumb_G_1527068836793.jpg",
      "http://test2.baihuodasha.com/images/201805/thumb_img/5028_thumb_G_1527068836793.jpg"
  };
  private ParentInfo parentInfo;

  private ArrayList<String> imageList;
  private Banner mBanner;
  private String mTitle;
  private TextView mtvtab1, mtvtab2, mtvtab3, mtvtab4, mtvtab5;
  private ImageView mImtab1, mImtab2, mImtab3, mImtab4, mImtab5;
  private RelativeLayout mRltab1, mRltab2, mRltab3, mRltab4, mRltab5;
  private RecyclerView mRecycshoping;
  private ArrayList<String> imageRecyc;
  private RecycshopAdapter adapter;
  private RecyclerView mRecycinfo;
  private RecycInfoAdapter infoadapter;
  private TagFlowLayout id_flowlayout;
  private TagAdapter tagAdapter;

  public static TabContentFragment newInstance(String content) {
    Bundle arguments = new Bundle();
    arguments.putString(EXTRA_CONTENT, content);
    TabContentFragment tabContentFragment = new TabContentFragment();
    tabContentFragment.setArguments(arguments);
    return tabContentFragment;
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

    init();
    initdata();
    initListener();
    SelectorTab();
    return contentView;
  }

  public void init() {
    if (!mTitle.equals("推荐")) {
      mBanner.setVisibility(View.GONE);
      mBanner.stopAutoPlay();
    } else {
      mBanner.startAutoPlay();
    }
    imageList = new ArrayList<>();
    for (int i = 0; i < images.length; i++) {
      imageList.add(images[i]);
    }
    //设置图片加载器
    mBanner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    mBanner.setImages(imageList);
    //banner设置方法全部调用完毕时最后调用
    mBanner.start();
  }

  public void initdata() {
    LinearLayoutManager linearLayoutO = new LinearLayoutManager(getActivity());
    linearLayoutO.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecycshoping.setLayoutManager(linearLayoutO);
    adapter = new RecycshopAdapter(getActivity(), null);
    mRecycshoping.setAdapter(adapter);
    LinearLayoutManager linearLayoutT = new LinearLayoutManager(getActivity());
    linearLayoutT.setOrientation(LinearLayoutManager.VERTICAL);
    mRecycinfo.setLayoutManager(linearLayoutT);
    infoadapter = new RecycInfoAdapter(getActivity(), null);
    mRecycinfo.setAdapter(infoadapter);
    getShopList();
  }

  public void initListener() {
    adapter.setOnItemClickListener(new RecycshopAdapter.OnItemClickListener() {
      @Override public void onClick(int v, String position) {
        Log.i("qaz", "onClick: " + v + "个");
      }
    });

  }

  @Override public void onClick(View v) {

    switch (v.getId()) {
    }
  }
  private  List<ParentInfo> dataInfoList = new ArrayList<>();
  private void getImageList(){
    imageRecyc = new ArrayList<>();
    for (int i = 0; i < image.length; i++) {
      imageRecyc.add(image[i]);
    }
    adapter.addList(imageRecyc);

  }
  private void getShopList(){
    dataInfoList.clear();
    for (int i = 0; i < images.length; i++) {
      parentInfo = new ParentInfo();
      List<ChildInfo> childInfoList = new ArrayList<>();
      parentInfo.setTitle("居家生活");
      parentInfo.setImage(images[i]);
      for (int j = 0; j < image.length; j++) {
        ChildInfo childInfo = new ChildInfo();
        childInfo.setMenuName(i + "-" + j);
        childInfo.setIconImgID(image[j]);
        childInfoList.add(childInfo);
      }
      parentInfo.setMenuList(childInfoList);
      dataInfoList.add(parentInfo);
    }
    infoadapter.addList(dataInfoList);
  }


  @Override public void onDestroy() {
    super.onDestroy();
  }

  public String[] showEntities = {"促销商品","热销排行","商城热卖","商城推荐","新品上市"};
  private void SelectorTab(){
    final LayoutInflater mInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    tagAdapter = new TagAdapter(showEntities) {
      @Override public View getView(FlowLayout parent, int position, Object o) {
        TextView tv =
            (TextView) mInflater.inflate(R.layout.selector_home_tv, id_flowlayout, false);
        tv.setText(showEntities[position]);
        return tv;
      }
    };
    tagAdapter.setSelectedList(1);
    id_flowlayout.setAdapter(tagAdapter);
    id_flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {
        //getList(showEntities.get(i).name, shopid, true);
        CommonUtils.toastMessage("点击了"+showEntities[i]);
        tagAdapter.setSelectedList(i);
        if (i == 1) {
          getImageList();
        }else if ( i ==4){
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
