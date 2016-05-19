package com.baseapp.framework.util;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

/**
 * 百度定位服务
 * 
 * @author deyi
 * 
 */
public class BaiduLocation {

	public LocationClient mLocationClient = null;
	public MyLocationListenner myListener = new MyLocationListenner();
	private BDLocationListener lsn;
	public GeofenceClient mGeofenceClient;
	
	public BaiduLocation(Context context) {
		
		mLocationClient = new LocationClient(context);
		mLocationClient.registerLocationListener(myListener);
		mGeofenceClient = new GeofenceClient(context);	
		InitLocation();
	}

	public void setBaiduLocationListenner(BDLocationListener lsn) {
		this.lsn = lsn;
	}

	public void start() {
		if (mLocationClient != null)
			mLocationClient.start();
	}

	public void stop() {
		if (mLocationClient != null)
			mLocationClient.stop();
	}

	private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setCoorType("bd09ll");
		option.setAddrType("all");
		//没隔10分钟定位一次
		int span=5000;
		option.setScanSpan(span);
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}
	
	public class MyLocationListenner implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;				
		
			if(lsn!=null)
				lsn.onReceiveLocation(location);			
		}

	}


	public interface BaiduLocationListenner {
		public void onReceiveLocation(BDLocation location);
	}
}
