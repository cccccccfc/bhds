package com.baihuodasha.bhds.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.activity.login.ActivityLogin;
import com.baihuodasha.bhds.activity.myself.ActivityMyselfInformation;
import com.baihuodasha.bhds.base.BaseFragment;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.Constants;
import com.baihuodasha.bhds.utils.SpTools;
import com.baihuodasha.bhds.view.SelectPicPopupWindow;
import com.base.utilslibrary.internet.PersonalInternetRequestUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.ByteArrayOutputStream;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;

/**
 * @author IMXU
 * @time   2017/5/3 13:21
 * @des    资讯首页
 * 邮箱：butterfly_xu@sina.com
 *
*/
public class FragmentMyself extends BaseFragment implements View.OnClickListener{
	private View view;

	@BindView(R.id.ll_myself_home)
	LinearLayout ll_home;//登录
	@BindView(R.id.rl_myself_img)
	RelativeLayout login;//登录
	@BindView(R.id.tv_myself_name)
	TextView name;//设置或昵称
	@BindView(R.id.tv_myself_pic)
	TextView pic;//修改头像
	@BindView(R.id.iv_myself_img)
	ImageView img;//修改头像

	private static final int LOGIN	= 101;//去登陆
	private static final int EDITINFOR	= 102;//去编辑个人信息

	private SelectPicPopupWindow mPopupWindow;

	@Override
	public View initView(LayoutInflater inflater) {
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_myself, null);
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent !=null){
			parent.removeView(view);
		}
		ButterKnife.bind(this,view);
		return view;
	}

	@Override
	public void init() {
		super.init();
	}
	@Override
	public void initdata() {
		super.initdata();
		Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
		if(cacheBitmap !=null){
			byte[] bytes=CommonUtils.getBitMapByteArray(cacheBitmap);
			Glide.with(getActivity()).load(bytes)
					.placeholder(R.drawable.advisor_home_img)
					.bitmapTransform(new CropCircleTransformation(getActivity()))
					.into(img);
		}
		OkHttpUtils.post().url("https://www.aqkcw.com/index.php/quanzi/hotqz")
				.build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				CommonUtils.logMes("----e---"+e);
			}

			@Override
			public void onResponse(String response, int id) {
				CommonUtils.logMes("----response---"+response);
			}
		});
	}

	@Override
	public void initListener() {
		super.initListener();
		login.setOnClickListener(this);
		name.setOnClickListener(this);
		pic.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
		switch(v.getId()){
			case R.id.rl_myself_img:
			case R.id.tv_myself_name:
				//判断是否登录，是则进入个人界面，否则进入登录界面
				if(SpTools.getBoolean(CommonUtils.getContext(), Constants.isLogin, false)){
					Intent intent = new Intent(getActivity(), ActivityMyselfInformation.class);
					startActivityForResult(intent,EDITINFOR);
					getActivity().overridePendingTransition(0,0);
				}else{
					Intent intent = new Intent(getActivity(), ActivityLogin.class);
					startActivityForResult(intent,LOGIN);
					getActivity().overridePendingTransition(0,0);
				}
//				TestRequest testRequest = new TestRequest();
//				testRequest.setTd("GetPM25");
//				Gson gson = new Gson();
//				String toJson = gson.toJson(testRequest);
//				OkHttpUtils.postString().url("https://121.41.72.156:8030/ctl.do")
//						.content(toJson)
//						.mediaType(MediaType.parse("application/json; charset=utf-8"))
//						.build().execute(new StringCallback() {
//					@Override
//					public void onError(Call call, Exception e, int id) {
//						CommonUtils.logMes(e.toString());
//					}
//
//					@Override
//					public void onResponse(String response, int id) {
//						CommonUtils.logMes(response);
//					}
//				});

				break;
			case R.id.tv_myself_pic:
				mPopupWindow = getPicPopupWindow(getActivity(), this, ll_home);
				break;
			case R.id.btn_pick_photo://本地
				PictureSelector.create(this).openGallery(PictureMimeType.ofImage())
						.selectionMode(PictureConfig.SINGLE)//多选 or 单选
						.enableCrop(true)//是否裁剪
						.compress(true)//是否压缩
						.freeStyleCropEnabled(true)// 裁剪框是否可拖拽
						.compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
						//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
						.previewImage(false)// 是否可预览图片
						.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
				break;
			case R.id.btn_take_photo://拍照
				PictureSelector.create(this).openCamera(PictureMimeType.ofImage())
						.selectionMode(PictureConfig.SINGLE)//多选 or 单选
						.enableCrop(true)//是否裁剪
						.compress(true)//是否压缩
						.freeStyleCropEnabled(true)// 裁剪框是否可拖拽
						.compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
						.previewImage(false)// 是否可预览图片
						.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
				break;
		}
	}
	public SelectPicPopupWindow getPicPopupWindow(Context context, View.OnClickListener itemsOnClick, View viewAttach) {
		//实例化SelectPicPopupWindow
		SelectPicPopupWindow menuWindow = new SelectPicPopupWindow(context, itemsOnClick);
		//显示窗口,设置layout在PopupWindow中显示的位置
		menuWindow.showAtLocation(viewAttach, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		return menuWindow;
	}

	private String picID = "";
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case PictureConfig.CHOOSE_REQUEST:
					// 图片选择结果回调
					LocalMedia media = PictureSelector.obtainMultipleResult(data).get(0);
					// 例如 LocalMedia 里面返回三种path
					// 1.media.getPath(); 为原图path
					// 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
					// 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
					// 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
					final Bitmap zoomBitMap = CommonUtils.getBitmap(media.getCompressPath());
					if(zoomBitMap!=null){
						//先保持到本地，在传到服务器
						CommonUtils.saveBitmapFile(zoomBitMap,"myicon.jpg");//先保存文件到本地
						PersonalInternetRequestUtils.uplodePicture(getActivity(),"ub_id","myicon.jpg",new PersonalInternetRequestUtils.ForResultListener() {
							@Override
							public void onResponseMessage(String code) {
								if(TextUtils.isEmpty(code)){
									return;
								}else {
									picID = code;
									ByteArrayOutputStream baos = new ByteArrayOutputStream();
									zoomBitMap.compress(Bitmap.CompressFormat.PNG, 100, baos);
									byte[] bytes=baos.toByteArray();
									Glide.with(getActivity()).load(bytes)
											.placeholder(R.drawable.advisor_home_img)
											.bitmapTransform(new CropCircleTransformation(getActivity()))
											.into(img);
								}
							}
						});
					}else {
						//本地图片显示
						Bitmap cacheBitmap = CommonUtils.getCacheFile("myicon.jpg");
						byte[] bytes=CommonUtils.getBitMapByteArray(cacheBitmap);
						Glide.with(getActivity()).load(bytes)
								.placeholder(R.drawable.advisor_home_img)
								.bitmapTransform(new CropCircleTransformation(getActivity()))
								.into(img);
					}
					break;
			}
		}
	}
}