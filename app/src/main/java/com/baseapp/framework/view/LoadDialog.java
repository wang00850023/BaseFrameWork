package com.baseapp.framework.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.baseapp.framework.R;

public class LoadDialog extends Dialog {
	private TextView tv_msg;
	private String msg;

	public LoadDialog(Context context, String msg) {
		super(context,R.style.loaddialog);
		this.msg = msg;
		// TODO Auto-generated constructor stub
	}
	public LoadDialog(Context context, int theme, String msg) {

		super(context, theme);
		this.msg = msg;
	}
	/**
	 * 监听Dialog是否按下返回键 如果按下 就做相应处理
	 * @author deyi
	 *
	 */
	public interface onBackLsn {
		void backlsn();
	}
	onBackLsn lsn;
	public void setBackLsn(onBackLsn lsn){
		this.lsn=lsn;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCanceledOnTouchOutside(false);
		this.setContentView(R.layout.dialog_load);
		tv_msg = (TextView) findViewById(R.id.msg);
		tv_msg.setText(msg);
		this.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if(lsn!=null){
						lsn.backlsn();
					}
				} 
					return false; // 默认返回 false
			}
		});
	}

}
