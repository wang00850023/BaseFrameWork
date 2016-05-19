package com.baseapp.framework.base;

import android.support.v4.app.Fragment;

import com.baidu.mobstat.StatService;

public class BaseFragment extends Fragment {

	public static int REFRESH = 1, LOADMORE = 2;

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 StatService.onResume(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 StatService.onResume(this);
	}

}
