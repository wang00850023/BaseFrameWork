package com.baseapp.framework.bean;

import java.util.ArrayList;


/**
 * 一个目录的相册对象
 * 
 * @author Administrator
 * 
 */
public class ImageBucket implements java.io.Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -4255232139654984240L;
	private String albumID;
	private String albumName;
	private String firstPicPath;
	private boolean isUserOtherPicSoft;
	private int picCount = 0;
	
	private ArrayList<ImageItem> fileImageList;
	
	public String getAlbumID() {
		return albumID;
	}
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getFirstPicPath() {
		return firstPicPath;
	}
	public void setFirstPicPath(String firstPicPath) {
		this.firstPicPath = firstPicPath;
	}
	public boolean isUserOtherPicSoft() {
		return isUserOtherPicSoft;
	}
	public void setUserOtherPicSoft(boolean isUserOtherPicSoft) {
		this.isUserOtherPicSoft = isUserOtherPicSoft;
	}
	public int getPicCount() {
		return picCount;
	}
	public void setPicCount(int picCount) {
		this.picCount = picCount;
	}
    public ArrayList<ImageItem> getFileImageList()
    {
        return fileImageList;
    }
    public void setFileImageList(ArrayList<ImageItem> fileImageList)
    {
        this.fileImageList = fileImageList;
    }
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

}
