package com.baseapp.framework.util;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.text.TextUtils;
import android.util.Log;

import com.baseapp.framework.base.EEApplication;
import com.baseapp.framework.cache.AppCacheManager;
import com.baseapp.framework.cache.CacheModel;

public class FinalHttpLog extends FinalHttp {
	
	@Override
	public void get(String url, AjaxCallBack<? extends Object> callBack) {
		// TODO Auto-generated method stub
		Log.i("HTML", url);
		super.get(url, callBack);
	}

	/**
	 * get请求
	 * 
	 * @Title: get 缓存
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param opencache 是否开启缓存
	 * @param @param url 请求地址
	 * @param @param callBack 请求回调
	 * @return void
	 * @throws
	 * @history 1.YYYY-MM-DD author: description:
	 */
	public void get(boolean opencache, final String url,
			final AjaxCallBack<String> callBack) {
		Log.i("HTML", url);
		// 启用缓存
		if (opencache) {
			final CacheModel cache = AppCacheManager.getUrlCache(url);
			EEApplication.http.get(url, new AjaxCallBack<String>() {
				@Override
				public void onSuccess(String t) {
					// TODO Auto-generated method stub
					super.onSuccess(t);
					if (cache != null) {
						cache.setUrl(url);
						cache.setJson(t);
						cache.setDate(System.currentTimeMillis());
						EEApplication.db.update(cache);
					} else {
						CacheModel newCacheModel = new CacheModel();
						newCacheModel.setUrl(url);
						newCacheModel.setJson(t);
						newCacheModel.setDate(System.currentTimeMillis());
						EEApplication.db.save(newCacheModel);
					}
					callBack.onSuccess(t);
				}

				@Override
				public void onFailure(Throwable t, String strMsg) {
					// TODO Auto-generated method stub
					super.onFailure(t, strMsg);
					if (cache != null && !TextUtils.isEmpty(cache.getJson())) {
						callBack.onSuccess(cache.getJson());
					} else {
						callBack.onFailure(t, strMsg);
					}
				}

			});
			return;
		}
		// 不启用缓存
		else {
			EEApplication.http.get(url, new AjaxCallBack<String>() {
				@Override
				public void onSuccess(String t) {
					callBack.onSuccess(t);
				};

				@Override
				public void onFailure(Throwable t, String strMsg) {
					callBack.onFailure(t, strMsg);

				};

			});
		}
	}

	/**
	 * post请求 缓存
	 * 
	 * @Title: post
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param opencache 设置是否开启缓存
	 * @param @param url 请求地址
	 * @param @param params 请求参数
	 * @param @param callBack 请求回调
	 * @return void
	 * @throws
	 * @history 1.YYYY-MM-DD author: description:
	 */
	public void post(boolean opencache, String url, AjaxParams params,
			AjaxCallBack<? extends Object> callBack) {
		Log.i("HTML", url);
		super.post(url, params, callBack);
	}

	@Override
	public void post(String url, AjaxParams params,
			AjaxCallBack<? extends Object> callBack) {
		// TODO Auto-generated method stub
		Log.i("HTML", url);
		super.post(url, params, callBack);
	}
}
