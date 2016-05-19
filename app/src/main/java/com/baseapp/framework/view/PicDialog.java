package com.baseapp.framework.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 选择从从图库还是相机
 * 
 * @author deyi
 * 
 */
public class PicDialog extends AlertDialog.Builder {

	public PicDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		final String[] cityNames = new String[] { "相机", "相册" };
		setItems(cityNames, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (lsn != null)
					if(which==0){
						lsn.onClick(1);
					}else{
						lsn.onClick(2);
					}
				dialog.dismiss();
			}
		});
	}

	/**
	 * 监听Dialog是否按下返回键 如果按下 就做相应处理
	 * 
	 * @author deyi
	 * 
	 */
	public interface onBackLsn {
		void onClick(int type);
	}

	onBackLsn lsn;

	public void setBackLsn(onBackLsn lsn) {
		this.lsn = lsn;
	}

}
