package com.baseapp.framework.view.photo;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baseapp.framework.R;
import com.baseapp.framework.adapter.CommonAdapter;
import com.baseapp.framework.bean.ImageBucket;

public class ListImageDirPopupWindow extends PopupWindowForPhotoDirListView
{
	private ListView mListDir;
	private int currentIndex;
	CommonAdapter adapter;
	public ListImageDirPopupWindow(int width, int height,
			List<ImageBucket> datas, View convertView)
	{
		super(convertView, width, height, true, datas);
	}

	@Override
	public void initViews()
	{
		mListDir = (ListView) findViewById(R.id.id_list_dir);
		adapter = new CommonAdapter(context, mDatas);
		mListDir.setAdapter(adapter);
	}

	public interface OnImageDirSelected
	{
		void selected(ImageBucket floder);
	}

	private OnImageDirSelected mImageDirSelected;

	public void setOnImageDirSelected(OnImageDirSelected mImageDirSelected)
	{
		this.mImageDirSelected = mImageDirSelected;
	}

	@Override
	public void initEvents()
	{
		mListDir.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
			    currentIndex = position;
			    adapter.setTagindex(currentIndex);
				if (mImageDirSelected != null)
				{
					mImageDirSelected.selected(mDatas.get(position));
				}
			}
		});
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeInitWeNeedSomeParams(Object... params)
	{
		// TODO Auto-generated method stub
	}

}
