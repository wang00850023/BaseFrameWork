package com.baseapp.framework.util.task;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 异步加载本地资源
 * 
 * @author deyi
 * 
 */
public class BitmaoLocalLoadTask {
	private boolean mExitTasksEarly = false;
	private boolean mPauseWork = false;
	private final Object mPauseWorkLock = new Object();
	private static ExecutorService bitmapLoadAndDisplayExecutor;
	private Bitmap defaultBitmap;
	private Context context;

	public BitmaoLocalLoadTask(Context Context, Bitmap defaultBitmap) {
		// TODO Auto-generated constructor stub
		this.defaultBitmap = defaultBitmap;
		this.context = Context;
		bitmapLoadAndDisplayExecutor = Executors.newFixedThreadPool(3,
				new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						// 设置线程的优先级别，让线程先后顺序执行（级别越高，抢到cpu执行的时间越多）
						t.setPriority(Thread.NORM_PRIORITY - 1);
						return t;
					}
				});
	}

	/**
	 * 当mHardBitmapCache的key大于30的时候，会根据LRU算法把最近没有被使用的key放入到这个缓存中。
	 * Bitmap使用了SoftReference，当内存空间不足时，此cache中的bitmap会被垃圾回收掉
	 */
	private final  ConcurrentHashMap<String, SoftReference<Bitmap>> mSoftBitmapCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();

	/**
	 * 从缓存中获取图片
	 */
	private Bitmap getBitmapFromCache(String url) {

		// 如果mHardBitmapCache中找不到，到mSoftBitmapCache中找
		SoftReference<Bitmap> bitmapReference = mSoftBitmapCache.get(url);
		if (bitmapReference != null) {
			final Bitmap bitmap = bitmapReference.get();
			if (bitmap != null) {
				return bitmap;
			} else {
				mSoftBitmapCache.remove(url);
			}
		}
		return null;
	}

	public void doDisplay(ImageView img, String uri) {
		if (checkImageTask(uri, img)) {
			//去缓存中查找
			if(getBitmapFromCache(uri)!=null){
				img.setImageBitmap(getBitmapFromCache(uri));
				return ;
			}		
			final BitmapLoadAndDisplayTask task = new BitmapLoadAndDisplayTask(
					img, uri);
			// 设置默认图片
			final AsyncDrawable asyncDrawable = new AsyncDrawable(
					context.getResources(), defaultBitmap, task);
			img.setImageDrawable(asyncDrawable);
			task.executeOnExecutor(bitmapLoadAndDisplayExecutor, uri);
		}
	}

	private  BitmapLoadAndDisplayTask getBitmapTaskFromImageView(
			ImageView imageView) {
		if (imageView != null) {
			final Drawable drawable = imageView.getDrawable();
			if (drawable instanceof AsyncDrawable) {
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}

	/**
	 * 检测 imageView中是否已经有线程在运行
	 * 
	 * @param data
	 * @param imageView
	 * @return true 没有 false 有线程在运行了
	 */
	public  boolean checkImageTask(Object data, ImageView imageView) {
		final BitmapLoadAndDisplayTask bitmapWorkerTask = getBitmapTaskFromImageView(imageView);

		if (bitmapWorkerTask != null) {
			final Object bitmapData = bitmapWorkerTask.data;
			if (bitmapData == null || !bitmapData.equals(data)) {
				bitmapWorkerTask.cancel(true);
			} else {
				// 同一个线程已经在执行
				return false;
			}
		}
		return true;
	}

	/**
	 * bitmap下载显示的线程
	 * 
	 * @author michael yang
	 */
	private class BitmapLoadAndDisplayTask extends
			AsyncTask<Object, Integer, Bitmap> {
		private Object data;
		private final WeakReference<ImageView> imageViewReference;
		// 文件路径
		private String uri;

		public BitmapLoadAndDisplayTask(ImageView imageView, String uri) {
			imageViewReference = new WeakReference<ImageView>(imageView);
			this.uri = uri;

		}

		@Override
		protected Bitmap doInBackground(Object... params) {
			data = params[0];
			// final String dataString = String.valueOf(data);
			Bitmap bitmap = null;

			synchronized (mPauseWorkLock) {
				while (mPauseWork && !isCancelled()) {
					try {
						mPauseWorkLock.wait();
					} catch (InterruptedException e) {
					}
				}
			}
			bitmap = getBitmapByWidth(uri, 300, 1);
			mSoftBitmapCache.put(uri,new SoftReference<Bitmap>(bitmap));
			return bitmap;
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
	    public  Bitmap getBitmapByWidth(String localImagePath, int width,
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
	            return temBitmap;
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	        return null;
	        //return temBitmap;
	    }
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled() || mExitTasksEarly) {
				bitmap = null;
			}

			// 判断线程和当前的imageview是否是匹配
			final ImageView imageView = getAttachedImageView();
			if (bitmap != null && imageView != null) {
				imageView.setImageBitmap(bitmap);
			}
		}

		@Override
		protected void onCancelled(Bitmap bitmap) {
			super.onCancelled(bitmap);
			synchronized (mPauseWorkLock) {
				mPauseWorkLock.notifyAll();
			}
		}

		/**
		 * 获取线程匹配的imageView,防止出现闪动的现象
		 * 
		 * @return
		 */
		private ImageView getAttachedImageView() {
			final ImageView imageView = imageViewReference.get();
			final BitmapLoadAndDisplayTask bitmapWorkerTask = getBitmapTaskFromImageView(imageView);

			if (this == bitmapWorkerTask) {
				return imageView;
			}

			return null;
		}
	}
	
	private  class AsyncDrawable extends BitmapDrawable {
		private final WeakReference<BitmapLoadAndDisplayTask> bitmapWorkerTaskReference;

		public AsyncDrawable(Resources res, Bitmap bitmap,
				BitmapLoadAndDisplayTask bitmapWorkerTask) {
			super(res, bitmap);
			bitmapWorkerTaskReference = new WeakReference<BitmapLoadAndDisplayTask>(
					bitmapWorkerTask);
		}

		public BitmapLoadAndDisplayTask getBitmapWorkerTask() {
			return bitmapWorkerTaskReference.get();
		}
	}
}
