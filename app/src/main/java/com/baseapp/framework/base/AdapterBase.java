package com.baseapp.framework.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @ClassName AdapterBase 
 * @Description TODO(Adapter基础数据源) 
 * @author 王博扬
 * @date 2011-7-3 上午11:03:00 
 * @history
 * 1.YYYY-MM-DD
 *    author:
 *    description:
 */
public abstract class AdapterBase<T> extends BaseAdapter {

	private final List<T> mList = new ArrayList<T>();

	public List<T> getList() {
		return mList;
	}
	/**
	 * 添加尾部数据
	 * @Title: appendToList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param list 
	 * @return void 
	 * @throws 
	 * @history
	 * 1.YYYY-MM-DD
	 *    author:
	 *    description:
	 */
	public void appendToList(List<T> list) {
		if (list == null) {
			return;
		}
		if(list.size()==0){
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}
	/**
	 * 向头部添加数据
	 * @Title: appendToTopList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param list 
	 * @return void 
	 * @throws 
	 * @history
	 * 1.YYYY-MM-DD
	 *    author:
	 *    description:
	 */
	public void appendToTopList(List<T> list) {
		if (list == null) {
			return;
		}
		if(list.size()==0){
			return;
		}
		mList.addAll(0, list);
		notifyDataSetChanged();
	}
	/**
	 * 清楚所有数据
	 * @Title: clearAll 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  
	 * @return void 
	 * @throws 
	 * @history
	 * 1.YYYY-MM-DD
	 *    author:
	 *    description:
	 */
	public void clearAll() {
		mList.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		if (position > mList.size() - 1) {
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		try {
			return getExView(position, convertView, parent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertView;
	}

	protected abstract View getExView(int position, View convertView,
			ViewGroup parent)throws Exception;


}
