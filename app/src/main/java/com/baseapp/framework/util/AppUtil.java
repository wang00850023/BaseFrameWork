package com.baseapp.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class AppUtil {

	/**
	 * 格式化日期
	 * 
	 * @param str
	 * @return
	 */
	public static String ParseDate(Date date) {
		String result = "";
		long endTime=System.currentTimeMillis();
		
		long startTime=date.getTime();
		//获取时间差 分钟
		long timerresult=(endTime-startTime)/1000/60;
		
		Date dataTime = date;
		Calendar currentTimer = Calendar.getInstance();
		currentTimer.setTimeInMillis(System.currentTimeMillis());
		int currentMonth = currentTimer.getTime().getMonth() + 1;
		int currentDay = currentTimer.getTime().getDate();
		int getMonth = dataTime.getMonth() + 1;
		int getDay = dataTime.getDate();
		int cyer = currentTimer.getTime().getYear();
		int dyer = dataTime.getYear();

		if (getMonth == currentMonth && currentDay == getDay + 1
				&& cyer - dyer == 0) {

			result = "昨天" + ParseHoursToString(dataTime.getHours());
			return result;
		} else if (getMonth == currentMonth && currentDay == getDay
				&& cyer - dyer == 0) {
			int c_hours = currentTimer.getTime().getHours();
			int d_hours = dataTime.getHours();
			// 一小时之内
			if (c_hours - d_hours == 0) {
				int mts = currentTimer.getTime().getMinutes()
						- dataTime.getMinutes();
				if (mts < 1) {
					return "刚刚";
				}
				return mts + "分钟前";
			}
			//对此处的时间戳计算做了优化。 如果发现 当前时间搓比服务端大
			//然后再来比较实际的时间。
			// 1个小时以前
			else if (c_hours - d_hours > 0) {
				if(timerresult>60){
				
					result=(timerresult/60)%60+"小时前";
				}else{
					result=timerresult+"分钟前";
				}
			//	return c_hours - d_hours + "小时前";
			}
		}
			//当前天数-返回的天数-1    >1   或者 当前月数大于 返回的月数  或者
//		} else if (cyer - dyer == 0 &&((currentDay - getDay) > 1 || currentMonth > getMonth
//				|| ((getMonth == 12) && currentMonth == 0))) {
			//今年
			else if (cyer - dyer == 0 ){
			result = getMonth + "-" + getDay;
			return result;
		} else if (cyer - dyer >= 1) {
			return ParseDateToString(dataTime,"yyyy-MM-dd");
		}
		return result;
	}

	public static String ParseHoursToString(int hours) {
		if (hours < 12)
			return "早上";
		else if (hours > 12 && hours < 16)
			return "下午";
		return "晚上";

	}

	/**
	 * 解析时间
	 * 
	 * @param date
	 * @return
	 */
	public static String ParseDateToString(Date date) {
		return ParseDateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 解析时间
	 * 
	 * @param date
	 * @param format
	 *            :ʱ���ʽ��������yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String ParseDateToString(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		return dateFormat.format(date);
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date ParseUTCDate(String str) {
		// ��ʽ��2012-03-04T23:42:00+08:00
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ssZ", Locale.CHINA);
		try {
			Date date = formatter.parse(str);

			return date;
		} catch (ParseException e) {
			// ��ʽ��Sat, 17 Mar 2012 11:37:13 +0000
			// Sat, 17 Mar 2012 22:13:41 +0800
			try {
				SimpleDateFormat formatter2 = new SimpleDateFormat(
						"EEE, dd MMM yyyy HH:mm:ss Z", Locale.CHINA);
				Date date2 = formatter2.parse(str);

				return date2;
			} catch (ParseException ex) {
				return null;
			}
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static Drawable GetUrlDrawable(String url) {
		try {
			URL aryURI = new URL(url);
			URLConnection conn = aryURI.openConnection();
			InputStream is = conn.getInputStream();
			Bitmap bmp = BitmapFactory.decodeStream(is);
			return new BitmapDrawable(bmp);
		} catch (Exception e) {
			Log.e("ERROR", "urlImage2Drawable���������쳣��imageUrl��" + url, e);
			return null;
		}
	}

	/**
	 * 
	 * @param imageUrl
	 * @return
	 */
	public static Bitmap GetBitmap(String imageUrl) {
		Bitmap mBitmap = null;
		try {
			URL url = new URL(imageUrl);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			mBitmap = BitmapFactory.decodeStream(is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mBitmap;
	}

	/**
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap DrawableToBitmap(Drawable drawable) {
		try {
			Bitmap bitmap = Bitmap
					.createBitmap(
							drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight(),
							drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
									: Bitmap.Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);
			// canvas.setBitmap(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			drawable.draw(canvas);

			return bitmap;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取应用版本号
	 */
	public static double GetVersionCode(final Context con) {
		double version = 1;
		PackageManager packageManager = con.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					con.getPackageName(), 0);
			version = (double) packageInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	 * 获取应用版本名称
	 * 
	 * @param context
	 * @return
	 */
	public static String GetVersionName(final Context context) {
		String versionName = "1.0.0";
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 格式化过滤HTML标签s
	 * 
	 * @param str
	 * @return
	 */
	public static String HtmlToText(String str) {
		str = str.replace("<br />", "\n");
		str = str.replace("<br/>", "\n");
		str = str.replace("&nbsp;&nbsp;", "\t");
		str = str.replace("&nbsp;", " ");
		str = str.replace("&#39;", "\\");
		str = str.replace("&quot;", "\\");
		str = str.replace("&gt;", ">");
		str = str.replace("&lt;", "<");
		str = str.replace("&amp;", "&");

		return str;
	}

}
