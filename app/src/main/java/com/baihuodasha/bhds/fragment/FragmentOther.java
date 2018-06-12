package com.baihuodasha.bhds.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.base.BaseFragment;

/**
 * @author IMXU
 * @time   2017/5/3 13:21
 * @des    资讯首页
 * 邮箱：butterfly_xu@sina.com
 *
*/
public class FragmentOther extends BaseFragment implements View.OnClickListener{
	private View view;
	@Override
	public View initView(LayoutInflater inflater) {
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_other, null);
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
	}

	@Override
	public void initListener() {
		super.initListener();
	}
	@Override
	public void onClick(View v) {

	}
}