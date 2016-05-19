package com.baseapp.framework.util;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.baseapp.framework.util.ScalingUtilities.ScalingLogic;

public class Photo {
	public static void startComer(Activity context, int resultCode) {

		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		File file = new File(FileManageUtil.findByFileDirName("image/camer/")
				.getPath() + "/tempcomer.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		context.startActivityForResult(intent, resultCode);

	}

	public static void startComer(Fragment context, int resultCode) {

		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		File file = new File(FileManageUtil.findByFileDirName("image/camer/")
				.getPath() + "/tempcomer.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		context.startActivityForResult(intent, resultCode);

	}

	/**
	 * 调用系统相册
	 * 
	 * @param context
	 * @param resultCode
	 */
	public static void systemPhotoAlbum(Activity context, int resultCode) {

		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		context.startActivityForResult(i, resultCode);

	}

	/**
	 * 调用系统相册
	 * 
	 * @param context
	 * @param resultCode
	 */
	public static void systemPhotoAlbum(Fragment context, int resultCode) {

		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		context.startActivityForResult(i, resultCode);

	}
//
//	/**
//	 * 调用系统相册
//	 * 
//	 * @param context
//	 * @param resultCode
//	 */
//	public static void appPhotoAlbum(Activity context, int resultCode, int size) {
//
//		Intent i = new Intent(context, PhotoListActivity.class);
//		i.putExtra("limit", size);
//		context.startActivityForResult(i, resultCode);
//
//	}

	/**
	 * 调用系统相册
	 * 
	 * @param context
	 * @param resultCode
	 */
	public static void appPhotoAlbum(Fragment context, int resultCode, int size) {

		Intent i = new Intent("com.ruigan.kuxiao.activity.PhotoListActivity");
		i.putExtra("limit", size);
		context.startActivityForResult(i, resultCode);

	}

	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 90;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90 + 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180 + 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 0;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegreePic(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 根据宽度从本地图片路径获取该图片的缩略图
	 * 
	 * @param localImagePath
	 *            本地图片的路径
	 * @param width
	 *            缩略图的宽
	 * @param addedScaling
	 *            额外可以加的缩放比例
	 * @return bitmap 指定宽高的缩略图
	 */
	public static Bitmap getBitmapByWidth(String localImagePath, int width,
			int addedScaling) {
		if (TextUtils.isEmpty(localImagePath)) {
			return null;
		}

		Bitmap temBitmap = null;

		try {
			BitmapFactory.Options outOptions = new BitmapFactory.Options();

			// 设置该属性为true，不加载图片到内存，只返回图片的宽高到options中。
			outOptions.inJustDecodeBounds = true;

			// 加载获取图片的宽高
			BitmapFactory.decodeFile(localImagePath, outOptions);

			int height = outOptions.outHeight;

			if (outOptions.outWidth > width) {
				// 根据宽设置缩放比例
				outOptions.inSampleSize = outOptions.outWidth / width + 1
						+ addedScaling;
				outOptions.outWidth = width;

				// 计算缩放后的高度
				height = outOptions.outHeight / outOptions.inSampleSize;
				outOptions.outHeight = height;
			}

			// 重新设置该属性为false，加载图片返回
			outOptions.inJustDecodeBounds = false;
			outOptions.inPurgeable = true;
			outOptions.inInputShareable = true;
			temBitmap = BitmapFactory.decodeFile(localImagePath, outOptions);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		int degree = Photo.readPictureDegreePic(localImagePath);
		return Photo.rotaingImageView(degree, temBitmap);
		// return temBitmap;
	}

	public static Bitmap decodeFileImage(String imgPath) {

		Bitmap unscaledBitmap = ScalingUtilities.decodeResource(imgPath,
				480, 800,
				ScalingLogic.FIT);
		Bitmap scaledBitmap = ScalingUtilities.createScaledBitmap(
				unscaledBitmap, 480,
				800, ScalingLogic.FIT);
		int degree = Photo.readPictureDegreePic(imgPath);
		Bitmap mNewBitmap = Photo.rotaingImageView(degree, scaledBitmap);
		if (unscaledBitmap.isRecycled())
			unscaledBitmap.recycle();
		if (scaledBitmap.isRecycled())
			scaledBitmap.recycle();
		return mNewBitmap;
	}

	public static Bitmap compressImageFromFile(String srcPath) {

		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 800f;//
		float ww = 480f;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置采样率

		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		// return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
		// 其实是无效的,大家尽管尝试
		return bitmap;
	}

	public static String getCamerPic() {
		File tempfile = new File(FileManageUtil.findByFileDirName(
				"image/camer/").getPath()
				+ "/tempcomer.jpg");
		File newfile = new File(FileManageUtil.findByFileDirName(
				"image/camer/").getPath()
				+ "/"
				+ System.currentTimeMillis()
				+ "tempcomer.jpg");
		tempfile.renameTo(newfile);
		return newfile.getPath();
	}

	public static String getPhotoPic(Context context, Uri uri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		ContentResolver mResolver = context.getContentResolver();
		Cursor cursor = mResolver.query(uri, proj, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		// 最后根据索引值获取图片路径
		String path = cursor.getString(column_index);
		return path;
	}
}
