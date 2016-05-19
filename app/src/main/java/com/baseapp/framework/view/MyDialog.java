package com.baseapp.framework.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MyDialog extends Dialog {

	private Window window = null;

	public MyDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODOAuto-generated constructor stub
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		// TODOAuto-generated constructor stub
	}
	 
	public MyDialog(Context context) {
		super(context);
		// TODOAuto-generated constructor stub
	}

	public void setView(View view) {
		setContentView(view);
	}

	public void setView(int id) {
		setContentView(id);
	}

	public void setProperty(int x, int y, int w, int h) {
		window = getWindow(); 
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = x; 
		wl.y = y;
		wl.width = w;
		wl.height = h;
		wl.gravity = Gravity.TOP | Gravity.LEFT;
		window.setAttributes(wl);
	}
}