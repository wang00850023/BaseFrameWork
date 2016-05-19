package com.baseapp.framework.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * 文件管理器
 *
 * @author deyi
 *
 */
public class FileManageUtil {

	private static Context mContext;
	//appFileDir
	private	static String mAppName;
	private static String appDirPath;
	public static void initFileManage(Context context,String appName) {
		mContext = context;
		mAppName = appName;
	}

	/**
	 * 获取APP主缓存目录
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppCacheDir() {
		// File appDir;
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			if(appDirPath!=null)return appDirPath;
			File appDir = new File(Environment.getExternalStorageDirectory(),
					mAppName+"/");
			if (!appDir.exists()) {
				appDir.mkdirs();
			}
			appDirPath=appDir.getPath()+"/";
			return appDirPath;
		}
		else{
			appDirPath=mContext.getCacheDir() + "/";
		}
		return appDirPath;
	}
		// return "";
	
	/**
	 * 查找指定名称的 文件目录
	 * 
	 * @param context
	 * @param Name
	 *            目录名称
	 * @return
	 */
	public static File findByFileDirName(String dirName) {
		File file = new File(getAppCacheDir() + dirName);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}
	

}
