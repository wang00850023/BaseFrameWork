package com.baseapp.framework.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.ImageColumns;

import com.baseapp.framework.bean.ImageBucket;
import com.baseapp.framework.bean.ImageItem;

/**
 * 专辑帮助类
 * 
 * @author Administrator
 * 
 */
public class AlbumHelper 
{
	   private static final String TAG = AlbumHelper.class.getSimpleName();

	    private Context context;

	    private ContentResolver cr;

	    private final String IMG_JPG = "image/jpg";

	    private final String IMG_JPEG = "image/jpeg";

	    private final String IMG_PNG = "image/png";

	    private final String IMG_BMP = "image/bmp";

	    private final String IMAGE_COUNT = "imageCount";

	    // 缩略图列表
	    HashMap<String, String> thumbnailList = new HashMap<String, String>();

	    // 专辑列表
	    List<HashMap<String, String>> albumList = new ArrayList<HashMap<String, String>>();

	    HashMap<String, ImageBucket> bucketList = new HashMap<String, ImageBucket>();

	    private static AlbumHelper instance;

	    private AlbumHelper()
	    {
	    }

	    public static AlbumHelper getHelper()
	    {
	        if (instance == null)
	        {
	            instance = new AlbumHelper();
	        }
	        return instance;
	    }

	    /**
	     * 初始化
	     * 
	     * @param context
	     */
	    public void init(Context context)
	    {
	        if (this.context == null)
	        {
	            this.context = context;
	            cr = context.getContentResolver();
	        }
	    }

	    // 排除同名文件,发现小米2s 同一个文件夹下出现多个同名文件,
	    public synchronized ArrayList<ImageBucket> getAlbumBucket(Context context)
	    {
	        ArrayList<ImageBucket> list = new ArrayList<ImageBucket>();
	        ImageBucket imageBucket = null;
	        ContentResolver mResolver = context.getContentResolver();
	        String[] IMAGE_PROJECTION = new String[]
	        { ImageColumns.BUCKET_ID, ImageColumns.BUCKET_DISPLAY_NAME, ImageColumns.DATA, "COUNT(distinct " + ImageColumns.DATA + " ) AS " + IMAGE_COUNT,ImageColumns.SIZE,ImageColumns.DISPLAY_NAME };

	        String selection = ImageColumns.SIZE + "> 10240" + " AND  1=1 AND " + ImageColumns.MIME_TYPE + " IN (?,?,?,?)) GROUP BY ("
	                + Images.ImageColumns.BUCKET_ID + ") ORDER BY (" + Images.ImageColumns.BUCKET_DISPLAY_NAME;

	        String[] selectionArgs = new String[]
	        { IMG_JPG, IMG_JPEG, IMG_PNG };

	        Cursor cursor = mResolver.query(Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, selection, selectionArgs, null);
	        if (null != cursor)
	        {
	            if (cursor.getCount() > 0)
	            {
	                list = new ArrayList<ImageBucket>();
	                while (cursor.moveToNext())
	                {
	                    imageBucket = new ImageBucket();
	                    imageBucket.setAlbumID(cursor.getString(0));
	                    imageBucket.setAlbumName(cursor.getString(1));
	                    imageBucket.setFirstPicPath(cursor.getString(2));
	                    imageBucket.setPicCount(cursor.getInt(3));
	                    imageBucket.setUserOtherPicSoft(false);
	                    ArrayList<ImageItem> itemList = getAlbumPicList(cursor.getString(0));
	                    if (itemList == null || itemList.isEmpty())
	                    {
	                        continue;
	                    }
	                    imageBucket.setFileImageList(itemList);
	                    list.add(imageBucket);
	                }
	            }
	            cursor.close();
	        }
	        return list;
	    }

	    // 排除同名文件,发现小米2s 同一个文件夹下出现多个同名文件,
	    public ArrayList<ImageItem> getAlbumPicList(String bucketID)
	    {

	        ArrayList<ImageItem> imageList = new ArrayList<ImageItem>();
	        ImageItem imageItem = null;

	        ContentResolver mResolver = context.getContentResolver();
	        String[] IMAGE_PROJECTION = new String[]
	        { ImageColumns.DATA, MediaStore.Images.Media._ID,ImageColumns.SIZE };

	        String selection = ImageColumns.SIZE + "> 10240" + " AND " + ImageColumns.BUCKET_ID + "= ?  AND " + ImageColumns.MIME_TYPE
	                + " IN (?,?,?,?)) GROUP BY (" + Images.ImageColumns.DATA;

	        String[] selectionArgs = new String[]
	        { bucketID, IMG_JPG, IMG_JPEG, IMG_PNG };

	        String orderBy = ImageColumns.DATE_TAKEN + " desc ";
	        Cursor cursor = mResolver.query(Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, selection, selectionArgs, orderBy);
	        if (null != cursor)
	        {
	            if (cursor.getCount() > 0)
	            {
	                while (cursor.moveToNext())
	                {
	                    imageItem = new ImageItem();
	                    imageItem.setImagePath(cursor.getString(0));
	                    imageItem.setImageId(cursor.getString(1));
	                    imageList.add(imageItem);
	                }
	            }
	            cursor.close();
	        }
	        return imageList;
	    }

	    public ArrayList<ImageItem> getNewPicLists(int size)
	    {
	        ArrayList<ImageItem> imageList = new ArrayList<ImageItem>();
	        ImageItem imageItem = null;

	        ContentResolver mResolver = context.getContentResolver();
	        String[] IMAGE_PROJECTION = new String[]
	        { ImageColumns.DATA, MediaStore.Images.Media._ID };

	        String selection = ImageColumns.SIZE + "> 10240" + " AND " + ImageColumns.MIME_TYPE + " IN (?,?,?,?)) GROUP BY (" + Images.ImageColumns.DATA;

	        String[] selectionArgs = new String[]
	        { IMG_JPG, IMG_JPEG, IMG_PNG, IMG_BMP };

	        String orderBy = ImageColumns.DATE_TAKEN + " desc " + " limit 0," + size;
	        Cursor cursor = mResolver.query(Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, selection, selectionArgs, orderBy);
	        if (null != cursor)
	        {
	            if (cursor.getCount() > 0)
	            {
	                while (cursor.moveToNext())
	                {
	                    imageItem = new ImageItem();
	                    imageItem.setImagePath(cursor.getString(0));
	                    imageItem.setImageId(cursor.getString(1));
	                    imageList.add(imageItem);
	                }
	            }
	            cursor.close();
	        }
	        return imageList;
	    }


}
