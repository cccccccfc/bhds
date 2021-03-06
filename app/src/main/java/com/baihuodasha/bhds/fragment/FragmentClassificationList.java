package com.baihuodasha.bhds.fragment;

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
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.adapter.ClassifyInfoAdapter;
import com.baihuodasha.bhds.base.Config;
import com.baihuodasha.bhds.bean.ChildInfo;
import com.baihuodasha.bhds.bean.ParentInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * author：Anumbrella
 * Date：16/5/26 下午7:13
 */
public class FragmentClassificationList extends Fragment {

  private ClassifyInfoAdapter adapter;
  private String[] fenleititle;
  private String[] fenleicommodity;
  private String[] contextImages;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.productlist_layout, null);
    RecyclerView recyclerViewList = (RecyclerView) view.findViewById(R.id.RecyclerViewList);
    ImageView item_iv_image = (ImageView) view.findViewById(R.id.item_iv_image);
    String index = getArguments().getString("index");
    Log.i("qaz", "onCreateView: "+index);
    String[] fenleiimages = Config.Fenleiimages;
    fenleititle = Config.Fenleititle;
    fenleicommodity = Config.Fenleicommodity;
    contextImages = Config.ContextImages;
    //Glide.with(getActivity()).load(fenleiimages[index]).into(item_iv_image);
    LinearLayoutManager linearLayoutT = new LinearLayoutManager(getActivity());
    linearLayoutT.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewList.setLayoutManager(linearLayoutT);
    adapter = new ClassifyInfoAdapter(getActivity(), null);
    recyclerViewList.setAdapter(adapter);
    getShopList();
    return view;
  }

  private List<ParentInfo> dataInfoList = new ArrayList<>();

  private void getShopList() {
    dataInfoList.clear();
    for (int i = 0; i < fenleititle.length; i++) {
      ParentInfo parentInfo = new ParentInfo();
      List<ChildInfo> childInfoList = new ArrayList<>();
      parentInfo.setTitle(fenleititle[i]);
      for (int j = 0; j < contextImages.length; j++) {
        ChildInfo childInfo = new ChildInfo();
        childInfo.setMenuName(fenleicommodity[j]);
        childInfo.setIconImgID(contextImages[j]);
        childInfoList.add(childInfo);
      }
      parentInfo.setMenuList(childInfoList);
      dataInfoList.add(parentInfo);
    }
   // adapter.addList(dataInfoList);
  }
}
