package com.baihuodasha.bhds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.ClassifyInfoAdapter;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.ParentInfo;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * author：Anumbrella
 * Date：16/5/26 下午7:13
 */
public class CategorizeListContentFragment extends Fragment {

  private ClassifyInfoAdapter adapter;
  private RecyclerView RecyclerViewList;
  private ImageView item_iv_image;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.productlist_layout, null);
    RecyclerViewList = (RecyclerView) view.findViewById(R.id.RecyclerViewList);
    item_iv_image = (ImageView) view.findViewById(R.id.item_iv_image);
    int index = getArguments().getInt("index");
    Glide.with(getActivity()).load(images[index]).into(item_iv_image);
    LinearLayoutManager linearLayoutT = new LinearLayoutManager(getActivity());
    linearLayoutT.setOrientation(LinearLayoutManager.VERTICAL);
    RecyclerViewList.setLayoutManager(linearLayoutT);
    adapter = new ClassifyInfoAdapter(getActivity(), null);
    RecyclerViewList.setAdapter(adapter);
    getShopList();
    return view;
  }

  private String[] images = new String[] {
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1522294960829052434.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189818366778385.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1511260867777304821.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189941117678111.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1511260867777304821.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189941117678111.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1511260867777304821.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189941117678111.jpg",
      "http://test2.baihuodasha.com/mobile/data/afficheimg/1521189618759975270.jpg"
  };
  private String[] image = new String[] {
      "http://test2.baihuodasha.com//images/201805/1527145341571611352.jpg",
      "http://test2.baihuodasha.com//images/201805/1527145294792439769.jpg",
      "http://test2.baihuodasha.com//images/201805/1527148858223918198.jpg",
      "http://test2.baihuodasha.com//images/201805/1527145351939811366.jpg",
      "http://test2.baihuodasha.com//images/201805/1527145318831036967.jpg",
      "http://test2.baihuodasha.com//images/201805/1527145330758184757.jpg"
  };
  private String[] name = new String[] {
      "茶具套装", "餐具套装", "咖啡用具", "茶盘","茶叶罐",  "茶道/其他"
  };
  private String[] title = new String[] {
      "户外运动", "汽车用品", "家居生活", "生活电器", "厨房小电"
  };
  private ParentInfo parentInfo;
  private List<ParentInfo> dataInfoList = new ArrayList<>();

  private void getShopList() {
    dataInfoList.clear();
    for (int i = 0; i < title.length; i++) {
      parentInfo = new ParentInfo();
      List<ChildInfo> childInfoList = new ArrayList<>();
      parentInfo.setTitle(title[i]);
      for (int j = 0; j < image.length; j++) {
        ChildInfo childInfo = new ChildInfo();
        childInfo.setMenuName(name[j]);
        childInfo.setIconImgID(image[j]);
        childInfoList.add(childInfo);
      }
      parentInfo.setMenuList(childInfoList);
      dataInfoList.add(parentInfo);
    }
    adapter.addList(dataInfoList);
  }
}
