package com.baseapp.framework.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.baidu.mobstat.StatService;
import com.baseapp.framework.swipebacklayout.lib.SwipeBackActivity;

/**
 * 基础Activty
 * @ClassName BaseSwpiteActivity 
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author 王博扬
 * @date 2015-9-15 下午4:26:17 
 * @history
 * 1.YYYY-MM-DD
 *    author:
 *    description:
 */
public class BaseSwpiteActivity extends SwipeBackActivity {
	public static int REFRESH = 1, LOADMORE = 2;
	protected final int CURRENT_VERSION = Build.VERSION.SDK_INT;
	protected final int VERSION_KITKAT = Build.VERSION_CODES.KITKAT;
//	protected final int VERSION_LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;
//	private int theme = 0;// 当前界面设置的主题


	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		if (savedInstanceState == null) {
//
//			theme = configTheme();
//		} else {
//			theme = savedInstanceState.getInt("theme");
//		}
//		// 设置主题
//		if (theme > 0)
//			setTheme(theme);

		super.onCreate(savedInstanceState);

		AppManager.getAppManager().addActivity(this);

	}

//	protected int configTheme() {
//		int theme = ThemeUtils.themeArr[MyApplication.sp.getTheme()][0];
//		if (theme > 0)
//			return theme;
//		return -1;
//	}
//
//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
//		outState.putInt("theme", theme);
//	}
//
//	/**
//	 * 设置电池条导航的颜色
//	 * 
//	 * @Title: setStatusBarColor
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @param color
//	 * @return void
//	 * @throws
//	 * @history 1.YYYY-MM-DD author: description:
//	 */
//	public void setStatusBarColor(int color) {
//		if (android.os.Build.VERSION.SDK_INT >= 21) {
//			Window window = getWindow();
//			// 很明显，这两货是新API才有的。
//			window.setStatusBarColor(color);
//		}
//	}
//
//	/**
//	 * 设置底部导航的颜色
//	 * 
//	 * @Title: setNavigationBarColor
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @param color
//	 * @return void
//	 * @throws
//	 * @history 1.YYYY-MM-DD author: description:
//	 */
//	public void setNavigationBarColor(int color) {
//		if (android.os.Build.VERSION.SDK_INT >= 21) {
//			Window window = getWindow();
//			// 很明显，这两货是新API才有的。
//			window.setNavigationBarColor(color);
//		}
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		hideSoftInputView();
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

//		if (theme == configTheme()) {
//
//		} else {
//			Log.i("HTML", "theme changed, reload()");
//			reload();
//
//			return;
//		}
	}

//	public void reload() {
//		Intent intent = getIntent();
//		overridePendingTransition(0, 0);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//		// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		finish();
//		overridePendingTransition(0, 0);
//		startActivity(intent);
//	}

//	protected boolean isNavBarTransparent() {
//		return CURRENT_VERSION >= VERSION_KITKAT
//				&& VERSION_LOLLIPOP > CURRENT_VERSION;
//	}
//
//	protected int getVersionNumber() {
//		return Build.VERSION.SDK_INT;
//	}
//
//	protected int getStatusBarHeight() {
//		int result = 0;
//		int resourceId = getResources().getIdentifier("status_bar_height",
//				"dimen", "android");
//		if (resourceId > 0) {
//			result = getResources().getDimensionPixelSize(resourceId);
//		}
//		return result;
//	}
//
//	protected int getNavigationBarHeight() {
//		int result = 0;
//		int resourceId = getResources().getIdentifier("navigation_bar_height",
//				"dimen", "android");
//		if (resourceId > 0) {
//			result = getResources().getDimensionPixelSize(resourceId);
//		}
//		return result;
//	}
//
//	protected boolean isHasNavigationBar() {
//		boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
//		boolean hasBackKey = KeyCharacterMap
//				.deviceHasKey(KeyEvent.KEYCODE_BACK);
//		return !(hasBackKey && hasMenuKey);
//	}

	public void hideSoftInputView() {
		InputMethodManager manager = ((InputMethodManager) this
				.getSystemService(Activity.INPUT_METHOD_SERVICE));
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				manager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 防止窗体多次执行
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// super.onRestoreInstanceState(savedInstanceState);
	}
}
