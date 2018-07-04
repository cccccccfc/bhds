package com.baihuodasha.bhds.utils.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.StringUtil;
import com.bumptech.glide.Glide;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.baihuodasha.bhds.R.id.tv_commodity_details_menus_add;

public class SpecificationPopupWindow extends PopupWindow implements OnClickListener {

  private final ImageView img_head;
  private final String images;
  private final TagAdapter layoutcolorAdapter;
  private final TagAdapter layoutsizeAdapter;
  private final List<String> listcolor;
  private final List<String> listsize;
  private TagFlowLayout flowlayoutcolor;
  private TagFlowLayout flowlayoutsize;
  private final TextView tv_oldprice;
  private final ImageView img_jian;
  private final ImageView img_add;
  private TextView tvCommodityDetailsMenusService; // 客服
  private TextView tvCommodityDetailsMenusCollect;  // 收藏
  private TextView tvCommodityDetailsMenusBuy;  // 购买
  private TextView tvCommodityDetailsMenusAdd;  //加入购物车
  View mMenuView;
  RelativeLayout rl_type;
  private int height;
  private Context context;
  private ImageView img_close;
  private static final int DETAIL_CODE = 234;
  private List<SpecModel> models;
  private List<TextView> textViews;
  //选择的规格
  public String spec;
  //选择的是第几个
  public int number;
  //选择的规格的价钱
  public double specPrice;
  //选择的数量
  public int choiceNum = 0;
  private TextView tv_num;
  //库存数量
  public int stockNum;
  private TGClickListener listener;
  //商品id
  private String goodsid;
  //商品价钱
  private TextView tv_price;
  private String specSize;
  private String specColor;

  public SpecificationPopupWindow(String goodsid, final Activity context, final List<SpecModel> models,
      final int stockNum, String images, TGClickListener listener) {

    super(context);
    this.context = context;
    this.goodsid = goodsid;
    this.stockNum = stockNum;
    this.models = models;
    this.listener = listener;
    this.images = images;
    textViews = new ArrayList<TextView>();
    listcolor = new ArrayList<>();
    listsize = new ArrayList<>();

    DisplayMetrics dm = new DisplayMetrics();
    context.getWindowManager().getDefaultDisplay().getMetrics(dm);
    height = (dm.heightPixels) * 2 / 3;
    final LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mMenuView = inflater.inflate(R.layout.commercial_layout, null);
    img_head = (ImageView) mMenuView.findViewById(R.id.im_product_head);
    img_close = (ImageView) mMenuView.findViewById(R.id.im_product_close);
    flowlayoutcolor = (TagFlowLayout) mMenuView.findViewById(R.id.fl_commodity_color); //颜色
    flowlayoutsize = (TagFlowLayout) mMenuView.findViewById(R.id.fl_product_size);  // 大小
    tv_price = (TextView) mMenuView.findViewById(R.id.tv_product_price);
    tv_oldprice = (TextView) mMenuView.findViewById(R.id.tv_product_oldprice);
    img_jian = (ImageView) mMenuView.findViewById(R.id.tv_product_num_minus);
    tv_num = (TextView) mMenuView.findViewById(R.id.tv_product_num);
    img_add = (ImageView) mMenuView.findViewById(R.id.tv_product_num_plus);
    tvCommodityDetailsMenusService =
        (TextView) mMenuView.findViewById(R.id.tv_commodity_details_menus_service);
    tvCommodityDetailsMenusCollect =
        (TextView) mMenuView.findViewById(R.id.tv_commodity_details_menus_collect);
    tvCommodityDetailsMenusBuy =
        (TextView) mMenuView.findViewById(R.id.tv_commodity_details_menus_buy);
    tvCommodityDetailsMenusAdd = (TextView) mMenuView.findViewById(tv_commodity_details_menus_add);
    tvCommodityDetailsMenusBuy.setOnClickListener(this);
    tvCommodityDetailsMenusAdd.setOnClickListener(this);
    tvCommodityDetailsMenusCollect.setOnClickListener(this);
    tvCommodityDetailsMenusService.setOnClickListener(this);
    img_jian.setOnClickListener(this);
    img_add.setOnClickListener(this);
    LinearLayout ll_product_size = (LinearLayout) mMenuView.findViewById(R.id.ll_product_size);
    LinearLayout ll_commodity_color =
        (LinearLayout) mMenuView.findViewById(R.id.ll_commodity_color);
    img_close.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        dismiss();
      }
    });
    choiceNum = Integer.parseInt(tv_num.getText().toString());
    for (int i = 0; i < 6; i++) {
      if (i == 2) {
        listcolor.add("测试");
      }
      if (i == 4) {
        listcolor.add("测试测试测试测试测试测试测试");
      }
      listcolor.add("测试测试测试");
    }
    for (int i = 0; i < 5; i++) {
      listsize.add("测试测试");
    }
    layoutcolorAdapter = new TagAdapter(listcolor) {
      @Override public View getView(FlowLayout parent, int position, Object o) {
        TextView tv =
            (TextView) inflater.inflate(R.layout.shop_search_histroy, flowlayoutcolor, false);
        tv.setText(listcolor.get(position));
        return tv;
      }
    };
    flowlayoutcolor.setAdapter(layoutcolorAdapter);
    flowlayoutcolor.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {
        layoutcolorAdapter.setSelectedList(i);
        specColor = listcolor.get(i);
        if (!StringUtil.isNullOrEmpty(specSize) && !StringUtil.isNullOrEmpty(specColor)) {
          tvCommodityDetailsMenusAdd.setEnabled(true);
          tvCommodityDetailsMenusAdd.setBackground(
              context.getResources().getDrawable(R.color.full_add));
        } else {

        }
        return true;
      }
    });
    layoutsizeAdapter = new TagAdapter(listsize) {
      @Override public View getView(FlowLayout parent, int position, Object o) {
        TextView tv =
            (TextView) inflater.inflate(R.layout.shop_search_histroy, flowlayoutcolor, false);
        tv.setText(listsize.get(position));

        return tv;
      }
    };
    flowlayoutsize.setAdapter(layoutsizeAdapter);
    flowlayoutsize.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public boolean onTagClick(View view, int i, FlowLayout parent) {
        layoutsizeAdapter.setSelectedList(i);
        specSize = listsize.get(i);
        if (!StringUtil.isNullOrEmpty(specSize) && !StringUtil.isNullOrEmpty(specColor)) {
          tvCommodityDetailsMenusAdd.setEnabled(true);
          tvCommodityDetailsMenusAdd.setBackground(
              context.getResources().getDrawable(R.color.full_add));
        } else {

        }
        return true;
      }
    });
    if (context != null) {
      Glide.with(context).load(images).placeholder(R.mipmap.img_logo)//加载过程中图片未显示时显示的本地图片
          .error(R.mipmap.img_logo)//加载异常时显示的图片
          //                .centerCrop()//图片图填充ImageView设置的大小
          .fitCenter()//缩放图像测量出来等于或小于ImageView的边界范围,该图像将会完全显示
          .into(img_head);
    }
    if (StringUtil.isNullOrEmpty(specSize) && StringUtil.isNullOrEmpty(specColor)) {
      tvCommodityDetailsMenusAdd.setEnabled(false);
      tvCommodityDetailsMenusAdd.setBackground(
          context.getResources().getDrawable(R.color.itemback));
    }
    this.setBackgroundDrawable(new ColorDrawable(0));
    //		//设置按钮监听
    //		lv_car.setOnItemClickListener(itemsOnClick);
    //设置SelectPicPopupWindow的View
    this.setContentView(mMenuView);
    //设置SelectPicPopupWindow弹出窗体的宽
    this.setWidth(LayoutParams.MATCH_PARENT);
    //设置SelectPicPopupWindow弹出窗体的高
    this.setHeight(LayoutParams.WRAP_CONTENT);
    //if (models.size() > 8) {
    //  this.setHeight(height);
    //} else {
    //this.setHeight(dm.heightPixels / 2);
    //}
    //设置SelectPicPopupWindow弹出窗体可点击
    this.setFocusable(true);
    //设置SelectPicPopupWindow弹出窗体动画效果
    this.setAnimationStyle(R.style.AnimBottom);
    //实例化一个ColorDrawable颜色为半透明
    //		ColorDrawable dw = new ColorDrawable(0xb0000000);
    //		ColorDrawable dw = new ColorDrawable(0xffffff);
    //设置SelectPicPopupWindow弹出窗体的背景
    //		this.setBackgroundDrawable(dw);
    //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
    mMenuView.setOnTouchListener(new OnTouchListener() {

      public boolean onTouch(View v, MotionEvent event) {

        int height = mMenuView.findViewById(R.id.rl_type).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
          if (y < height) {
            dismiss();
          }
        }
        return true;
      }
    });
  }

  public void showPopListener(String type) {

  }

  //规格id
  public String specid;

  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int px2dip(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public double getNum(double f) {
    DecimalFormat df = new DecimalFormat("###.00");
    return Double.parseDouble(df.format(f));
  }

  @Override public void onClick(View v) {

    switch (v.getId()) {
      case R.id.tv_product_num_minus:
        if (StringUtil.isNullOrEmpty(specSize) && StringUtil.isNullOrEmpty(specColor)) {
          Toast.makeText(context, "请选择规格和数量", Toast.LENGTH_SHORT).show();
          return;
        }
        if (choiceNum > 1) {
          choiceNum--;
        } else {
          Toast.makeText(context, "已经达到最小数量了", Toast.LENGTH_SHORT).show();
        }
        tv_num.setText(choiceNum + "");

        break;
      case R.id.tv_product_num_plus:
        if (StringUtil.isNullOrEmpty(specSize) && StringUtil.isNullOrEmpty(specColor)) {
          Toast.makeText(context, "请选择规格", Toast.LENGTH_SHORT).show();
          return;
        }
        if (choiceNum < stockNum) {
          choiceNum++;
        } else {
          Toast.makeText(context, "数量已经达到最大库存量", Toast.LENGTH_SHORT).show();
        }
        tv_num.setText(choiceNum + "");
        break;
      //case R.id.tv_add_car:
      //  if (StringUtil.isNullOrEmpty(spec)) {
      //    Toast.makeText(context, "请选择规格", Toast.LENGTH_SHORT).show();
      //    return;
      //  }
      //  if (typeShow.equals("1")) {
      //    setOnTGClickListener(listener, 1);
      //  } else if (typeShow.equals("2")) {
      //    setOnTGClickListener(listener, 2);
      //  } else {
      //    setOnTGClickListener(listener, 1);
      //  }
      //
      //  break;
      //case tv_buy:
      //  if (StringUtil.isNullOrEmpty(spec)) {
      //    Toast.makeText(context, "请选择规格", Toast.LENGTH_SHORT).show();
      //    return;
      //  }
      //  setOnTGClickListener(listener, 2);
      //  break;
      case R.id.tv_commodity_details_menus_service:
        CommonUtils.toastMessage("客服");
        break;
      case R.id.tv_commodity_details_menus_collect:
        CommonUtils.toastMessage("收藏");
        if (tvCommodityDetailsMenusCollect.getText().toString().equals("收藏")) {
          tvCommodityDetailsMenusCollect.setText("已收藏");
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvCommodityDetailsMenusCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.mipmap.collection_un, null), null, null);
          }
          // tvCommodityDetailsMenusCollect.setTextColor(getResources().getColor(R.color.colorText2));
        } else {
          tvCommodityDetailsMenusCollect.setText("收藏");
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvCommodityDetailsMenusCollect.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.mipmap.menuscollection, null), null, null);
          }
        }
        break;
      case R.id.tv_commodity_details_menus_buy:
        if (!StringUtil.isNullOrEmpty(specSize)
            && !StringUtil.isNullOrEmpty(specColor)
            && choiceNum != 0) {
          CommonUtils.toastMessage("购买");
        } else {
          CommonUtils.toastMessage("请选择商品规格");
        }
        break;
      case tv_commodity_details_menus_add:
        if (!StringUtil.isNullOrEmpty(specSize)
            && !StringUtil.isNullOrEmpty(specColor)
            && choiceNum != 0) {
          CommonUtils.toastMessage("添加到购物车");
        } else {
          CommonUtils.toastMessage("请选择商品规格");
        }
        break;
    }
  }

  public void setOnTGClickListener(TGClickListener listener, int flag) {
    if (null != listener) {
      listener.onWork(flag);
    }
  }

  public interface TGClickListener {
    public abstract void onWork(int flag);
  }
}
