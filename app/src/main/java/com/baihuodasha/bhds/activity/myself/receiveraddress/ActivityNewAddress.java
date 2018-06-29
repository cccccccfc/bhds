package com.baihuodasha.bhds.activity.myself.receiveraddress;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseActivity;
import com.baihuodasha.bhds.bean.JsonBean;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.GetJsonDataUtil;
import com.baihuodasha.bhds.utils.StringUtil;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;

public class ActivityNewAddress extends BaseActivity {

  @BindView(R.id.iv_base_backto) ImageView ivBaseBackto;
  @BindView(R.id.iv_base_back) ImageView ivBaseBack;
  @BindView(R.id.tv_base_title) TextView tvBaseTitle;
  @BindView(R.id.iv_base_edit) ImageView ivBaseEdit;
  @BindView(R.id.iv_base_more) ImageView ivBaseMore;
  @BindView(R.id.tv_base_save) TextView ivBaseSave;//
  @BindView(R.id.tv_myself_newaddress_location) TextView tvMyselfNewaddressLocation;
  @BindView(R.id.rl_myself_newaddress_location) RelativeLayout rlMyselfNewaddressLocation;
  @BindView(R.id.tv_myself_newaddress_particularlocation) TextView
      tvMyselfNewaddressParticularlocation;
  @BindView(R.id.et_myself_newaddress_particularlocation) EditText
      etMyselfNewaddressParticularlocation;
  @BindView(R.id.rl_myself_newaddress_particularlocation) RelativeLayout
      rlMyselfNewaddressParticularlocation;
  @BindView(R.id.tv_myself_newaddress_name) TextView tvMyselfNewaddressName;
  @BindView(R.id.et_myself_newaddress_name) EditText etMyselfNewaddressName;
  @BindView(R.id.rl_myself_newaddress_name) RelativeLayout rlMyselfNewaddressName;
  @BindView(R.id.tv_myself_newaddress_phone) TextView tvMyselfNewaddressPhone;
  @BindView(R.id.et_myself_newaddress_phone) EditText EtMyselfNewaddressPhone;
  @BindView(R.id.rl_myself_newaddress_phone) RelativeLayout rlMyselfNewaddressPhone;
  @BindView(R.id.ck_myself_newaddress_selector) CheckBox ckMyselfNewaddressSelector;

  private ArrayList<JsonBean> options1Items = new ArrayList<>();
  private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
  private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
  private Thread thread;
  private static final int MSG_LOAD_DATA = 0x0001;
  private static final int MSG_LOAD_SUCCESS = 0x0002;
  private static final int MSG_LOAD_FAILED = 0x0003;

  private boolean isLoaded = false;

  @Override public void setContentLayout(Bundle savedInstanceState) {
    setContentView(R.layout.activity_new_address);
    ButterKnife.bind(this);
    setStatusBarPlaceVisible(true);
    setViewColorStatusBar(false, getResources().getColor(R.color.titleBar));
  }

  @Override public void initView() {
    int type = getIntent().getIntExtra("type", 1);
    if (type == 1) {
      tvBaseTitle.setText("新建地址");
    } else {
      tvBaseTitle.setText("编辑地址");
    }
    ivBaseSave.setVisibility(View.VISIBLE);
    ivBaseSave.setOnClickListener(this);
    ivBaseBack.setOnClickListener(this);
    tvMyselfNewaddressLocation.setOnClickListener(this);
    tvBaseTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
  }

  @Override public void dealLogicBeforeInitView() {
    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
  }

  @Override public void dealLogicAfterInitView() {

  }

  @Override public void onClickEvent(View view) throws ParseException {
    switch (view.getId()) {
      case R.id.iv_base_back:
        finish();
        break;
      case R.id.tv_base_save:
        String addressphone = EtMyselfNewaddressPhone.getText().toString();
        String location = etMyselfNewaddressParticularlocation.getText().toString();
        String addressname = EtMyselfNewaddressPhone.getText().toString();
        String addresslocation = tvMyselfNewaddressLocation.getText().toString();
        // finish();
        if (StringUtil.isNullOrEmpty(addresslocation)) {
          showToast("请选择收货地址");
          //  return;
        } else if (StringUtil.isNullOrEmpty(location)) {
          showToast("请输入详细地址");
          return;
        } else if (StringUtil.isNullOrEmpty(addressphone)) {
          showToast("请输入手机号");
          return;
        } else if (!CommonUtils.isMobilePhone(addressphone)) {
          showToast("请输入正确的手机号");
          return;
        } else if (StringUtil.isNullOrEmpty(addressname)) {
          showToast("请输入收件人姓名");
          return;
        } else {
          CommonUtils.toastMessage("创建成功");
        }
        break;
      case R.id.tv_myself_newaddress_location:
        if (isLoaded) {
          showPickerView();
        } else {
          CommonUtils.toastMessage("数据暂未初始化");
        }
        break;
    }
  }

  @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case MSG_LOAD_DATA:
          if (thread == null) {//如果已创建就不再重新创建子线程了

            thread = new Thread(new Runnable() {
              @Override public void run() {
                // 子线程中解析省市区数据
                initJsonData();
              }
            });
            thread.start();
          }
          break;

        case MSG_LOAD_SUCCESS:
          isLoaded = true;
          CommonUtils.toastMessage("初始化成功");
          break;
        case MSG_LOAD_FAILED:
          CommonUtils.toastMessage("初始化失败");
          break;
      }
    }
  };

  private void showPickerView() {// 弹出选择器

    OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
      @Override public void onOptionsSelect(int options1, int options2, int options3, View v) {
        //返回的分别是三个级别的选中位置
        String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1)
            .get(options2) + options3Items.get(options1).get(options2).get(options3);

        tvMyselfNewaddressLocation.setText(tx);
      }
    })

        .setTitleText("城市选择")
        .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
        .setSubmitColor(R.color.colorText2)//确定按钮文字颜色
        .setCancelColor(R.color.colorText2)//取消按钮文字颜色
        .setContentTextSize(18)
        .setSubCalSize(18)//确定和取消文字大小
        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
    pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
    pvOptions.show();
  }

  private void initJsonData() {//解析数据

    /**
     * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
     * 关键逻辑在于循环体
     *
     * */
    String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

    ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

    /**
     * 添加省份数据
     *
     * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
     * PickerView会通过getPickerViewText方法获取字符串显示出来。
     */
    options1Items = jsonBean;

    for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
      ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
      ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

      for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
        String CityName = jsonBean.get(i).getCityList().get(c).getName();
        CityList.add(CityName);//添加城市
        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
        if (jsonBean.get(i).getCityList().get(c).getArea() == null
            || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
          City_AreaList.add("");
        } else {
          City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
        }
        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
      }

      /**
       * 添加城市数据
       */
      options2Items.add(CityList);

      /**
       * 添加地区数据
       */
      options3Items.add(Province_AreaList);
    }

    mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
  }

  public ArrayList<JsonBean> parseData(String result) {//Gson 解析
    ArrayList<JsonBean> detail = new ArrayList<>();
    try {
      JSONArray data = new JSONArray(result);
      Gson gson = new Gson();
      for (int i = 0; i < data.length(); i++) {
        JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
        detail.add(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
      mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
    }
    return detail;
  }
}
