package com.baseapp.framework.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.baidu.mobstat.StatService;

/**
 * 
 * @ClassName BaseActivity 
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author 王博扬
 * @date 2015-12-3 上午11:39:03 
 * @history
 * 1.YYYY-MM-DD
 *    author:
 *    description:
 */
@SuppressLint("NewApi")
public class BaseActivity extends FragmentActivity {
	
	public static int REFRESH = 1, LOADMORE = 2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		StatService.onPause(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StatService.onResume(this);
	}
	/**
	 * 防止窗体多次执行
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// super.onRestoreInstanceState(savedInstanceState);
	}
}
