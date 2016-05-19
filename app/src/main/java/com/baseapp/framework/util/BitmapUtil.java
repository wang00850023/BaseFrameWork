package com.baseapp.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.util.Base64;

public class BitmapUtil
{
    public InputStream compressBmpToFile(File file)
    {
        Bitmap bmp = readBitmap(file.getPath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;// 个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 80)
        {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        if (!bmp.isRecycled())
        {
            bmp.recycle();
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public static File compressBmpToFile(int type,String file)
    {
        File tempFile = new File(FileManageUtil.findByFileDirName("upimage"), System.currentTimeMillis() + ".jpg");
        try
        {
            Bitmap bmp;
            if(type==1){
                bmp = readBitmap(file);                
            }else{
                bmp = Photo.compressImageFromFile(file);                
            }
            FileOutputStream baos = new FileOutputStream(tempFile);
            int options = 80;// 个人喜欢从80开始,
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            baos.close();
            baos = null;
            if (!bmp.isRecycled())
            {
                bmp.recycle();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tempFile;
    }
    
    public static File compressBmpToFile(Bitmap bm)
    {
        File tempFile = new File(FileManageUtil.findByFileDirName("upimage"), System.currentTimeMillis() + ".jpg");
        try
        {
            Bitmap bmp = bm;
            FileOutputStream baos = new FileOutputStream(tempFile);
            int options = 80;// 个人喜欢从80开始,
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            baos.close();
            baos = null;
            if (!bmp.isRecycled())
            {
                bmp.recycle();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tempFile;
    }

    public static String compressBmpToBase64(String imgPath, Bitmap bitmap)
    {
        if (imgPath != null && imgPath.length() > 0)
        {
            bitmap = readBitmap(imgPath);
        }
        if (bitmap == null)
        {
            // bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try
        {
            out = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);

            out.flush();
            out.close();
            byte[] imgBytes = out.toByteArray();

            if (!bitmap.isRecycled())
            {
                bitmap.recycle();
            }
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            return null;
        }
        finally
        {
            try
            {
                out.flush();
                out.close();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap(String imgPath)
    {
        try
        {

            return Photo.decodeFileImage(imgPath);

            // return BitmapFactory.decodeFile(imgPath);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block

            return null;
        }

    }
}
