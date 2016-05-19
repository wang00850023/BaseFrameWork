package com.baseapp.framework.util;

import android.util.Log;

/**
 * @ClassName UtilsLog
 * @Description 打印log, isTest = true打印，isTest = false不打印,在正式发布时改为false
 * @author lxj
 * @date 2015-6-24 上午9:32:35
 * @history 1.YYYY-MM-DD author: description:
 */
public class UtilsLog {

	public static boolean isTest = true;
	public static String TAG = "tag";

	public static void d(String outprint) {
		if (isTest)
			Log.d(TAG, outprint);
	}

	public static void d(String paramString1, String paramString2) {
		if (isTest)
			Log.d(paramString1, paramString2);
	}

	public static void e(String paramString1, String paramString2) {
		if (isTest)
			Log.e(paramString1, paramString2);
	}

	public static void i(String paramString1, String paramString2) {
		if (isTest)
			Log.i(paramString1, paramString2);
	}

	public static void log(String paramString1, String paramString2) {
		StackTraceElement[] arrayOfStackTraceElement = new Throwable()
				.getStackTrace();
		if (isTest) {
			StackTraceElement localStackTraceElement = arrayOfStackTraceElement[1];
			Object[] arrayOfObject = new Object[4];
			arrayOfObject[0] = localStackTraceElement.getClassName();
			arrayOfObject[1] = Integer.valueOf(localStackTraceElement
					.getLineNumber());
			arrayOfObject[2] = localStackTraceElement.getMethodName();
			arrayOfObject[3] = paramString2;
			Log.e(paramString1,
					String.format("======[%s][%s][%s]=====%s", arrayOfObject));
		}
	}

	public static void w(String paramString1, String paramString2) {
		if (isTest)
			Log.w(paramString1, paramString2);
	}

	public static void w(String paramString1, String paramString2,
			Throwable paramThrowable) {
		if (isTest)
			Log.w(paramString1, paramString2, paramThrowable);
	}

	public static void w(String paramString, Throwable paramThrowable) {
		if (isTest)
			Log.w(paramString, paramThrowable);
	}
}
